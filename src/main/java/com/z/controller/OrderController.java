package com.z.controller;

import com.z.bean.VO.OrderConfirmVo;
import com.z.bean.VO.OrderSubmitVo;
import com.z.bean.VO.SubmitOrderResponseVo;
import com.z.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("confirmOrder")
    public OrderConfirmVo confirmOrder(){
        OrderConfirmVo orderConfirm = orderService.confirmOrder();
        return orderConfirm;
    }

    @PostMapping("submitOrder")
    public SubmitOrderResponseVo submitOrder(@RequestBody OrderSubmitVo submit){
        return orderService.submitOrder(submit);
    }

}
