package com.z.service;

import com.z.bean.dto.StockLockItemDto;
import com.z.dao.WareSkuMapper;
import com.z.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {


    @Autowired
    private WareSkuMapper wareSkuMapper;


    /**
     * 库存释放
     */
    @Transactional
    public void releaseStock(List<StockLockItemDto> msg) throws Exception {
        //根据订单id校验订单状态
        //解锁库存
        msg.forEach(item ->{
            wareSkuMapper.unlockStock(1l, item.getSkuId(), item.getNum());
        });
        if (msg.size()==5){
            throw new BusinessException("500","出错了");
        }
    }

}
