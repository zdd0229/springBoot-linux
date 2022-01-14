package com.z.framework.fastjson;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

public class BigdecimalTest {

    public static void main(String[] args) {

        Account me = new Account();
        me.setName("zhao");
        me.setAmount(new BigDecimal("1000000.03"));

        String json = JSON.toJSONString(me);
        System.out.println(json);

        Account you = JSON.parseObject(json, Account.class);
        System.out.println(you);

    }

}
