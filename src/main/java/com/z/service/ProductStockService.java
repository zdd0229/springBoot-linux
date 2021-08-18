package com.z.service;

import com.z.dao.ProductStockMapper;
import com.z.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class ProductStockService {

    @Autowired
    private ProductStockMapper stockMapper;

    private ReentrantLock lock = new ReentrantLock();

    private AtomicInteger success = new AtomicInteger(0);

    public boolean deductStock(Integer pid, Integer num) {
//        System.out.println(String.format("pid:%s,num%s", pid, num));

        //扣减库存
        Integer affect = stockMapper.deductStock(pid, num);

        if (affect == 0) {
            throw new BusinessException("-1", "库存不足");
        }

        //并发加库存
        stockMapper.addStock(pid, num);

//        System.out.println("success:" + success.incrementAndGet());
        return true;
    }
}
