package com.z.service;

import com.rabbitmq.client.Channel;
import com.z.bean.VO.*;
import com.z.bean.dto.StockLockItemDto;
import com.z.constant.RedisKey;
import com.z.dao.WareSkuMapper;
import com.z.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RabbitListener(queues = {"stock.release.stock.queue"})
@Slf4j
public class OrderService {

    @Autowired
    private WareSkuMapper wareSkuMapper;

    @Autowired
    private ThreadPoolExecutor executor;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public OrderConfirmVo confirmOrder() {

        OrderConfirmVo res = new OrderConfirmVo();

        //获取用户信息
        Long userId = 1l;

        CompletableFuture<Void> itemAndStockFuture = CompletableFuture.supplyAsync(() -> {
            //获取被选中的购物项
            List<OrderItemVo> orderItems = getOrderItems(userId);
            res.setItems(orderItems);
            return orderItems;
        }, executor).thenAcceptAsync((orderItemVos) -> {
            //获取库存
            List<Long> ids = orderItemVos.stream().map(OrderItemVo::getGoodsId).collect(Collectors.toList());
            Map<Long, Boolean> hasStock = getStock(ids);
            res.setStocks(hasStock);
        }, executor);

        CompletableFuture<Void> addressFuture = CompletableFuture.runAsync(() -> {
            //获取地址信息
            res.setAddress(getAddress());
        }, executor);

        //防重令牌
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(RedisKey.USER_ORDER_TOKEN_PREFIX + userId, token, 30, TimeUnit.MINUTES);
        res.setOrderToken(token);

        try {
            CompletableFuture.allOf(itemAndStockFuture, addressFuture).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 获取地址信息
     *
     * @return
     */
    private List<MemberAddressVo> getAddress() {
        List<MemberAddressVo> res = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            MemberAddressVo addressVo = new MemberAddressVo();
            addressVo.setName("小明" + i);
            addressVo.setAddress("XX省XX市XX路XX号");
            addressVo.setPhone("1381234123" + i);
            res.add(addressVo);
        }
        return res;
    }

    /**
     * 获取是否还有库存
     */
    private Map<Long, Boolean> getStock(List<Long> ids) {
        Map<Long, Boolean> res = new HashMap<>(5);
        ids.forEach(item -> res.put(item, true));
        return res;
    }

    /**
     * 获取被选中的购物项
     */
    private List<OrderItemVo> getOrderItems(Long userId) {
        List<OrderItemVo> res = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            OrderItemVo item = new OrderItemVo();
            item.setGoodsId((long) i);
            item.setGoodsName("商品名" + i);
            item.setAmount(i + 1);
            if (i == 4) {
                item.setAmount(i + 1);
            }
            item.setPrice("5." + i);
            res.add(item);
        }
        return res;
    }

    /**
     * 提交订单
     *
     * @return
     */
    @Transactional
    public SubmitOrderResponseVo submitOrder(OrderSubmitVo submit) {

        Long userId = 1L;

        //1) 验证防重令牌
        String script = "if redis.call('get', KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        Long execute = (Long) redisTemplate.execute(new DefaultRedisScript(script, Long.class), Arrays.asList(RedisKey.USER_ORDER_TOKEN_PREFIX + userId), submit.getOrderToken());
        if (execute == 0L) {
            //1.1 防重令牌验证失败
            return new SubmitOrderResponseVo().setCode(1).setMsg("防重令牌验证失败");
        }

        //2) 创建订单、订单项
        List<OrderItemVo> orderItems = getOrderItems(userId);
        //3) 验价

        //4) 保存订单

        //5) 锁定库存
        List<StockLockItemDto> msg = new ArrayList<>();

        for (int i = 0; i < orderItems.size(); i++) {
            OrderItemVo item = orderItems.get(i);
            int affect = wareSkuMapper.lockStock(1l, item.getGoodsId(), item.getAmount());
            if (affect == 0) {
                throw new BusinessException("-1", "库存不足");
            }
            msg.add(new StockLockItemDto(1l,1l, item.getGoodsId(), item.getAmount()));
        }
        //发送库存锁定消息(哪个订单锁了哪个sku的多少库存)
        rabbitTemplate.convertAndSend("stock-event-exchange", "stock.locked", msg);

        return new SubmitOrderResponseVo().setCode(0).setMsg("下单成功");
    }

    /**
     * 库存释放监听
     */
    @RabbitHandler
    public void handleStockRelease(List<StockLockItemDto> msg, Message message, Channel channel) throws IOException {
        log.info("库存解锁监听：msg{}",msg);
        if (this.releaseStock(msg)){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }else {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }

    /**
     * 库存释放
     */
    public Boolean releaseStock(List<StockLockItemDto> msg){

        return transactionTemplate.execute((status)->{
            try {
                //根据订单id校验订单状态
                //解锁库存
                msg.forEach(item ->{
                    wareSkuMapper.unlockStock(1l, item.getSkuId(), item.getNum());
                });
//                if (msg.size()==5){
//                    throw new BusinessException("500","出错了");
//                }
            } catch (BusinessException e) {
                e.printStackTrace();
                status.setRollbackOnly();
                return false;
            }
            return true;
        });


    }

}
