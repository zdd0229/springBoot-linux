package com.z.connectionpool.test;

import com.z.connectionpool.Zpool;
import com.z.connectionpool.ZpoolConfig;

import java.sql.Connection;

public class MyZpool {

    private static final String url = "jdbc:mysql://101.132.128.127:3307/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "root";

    private static final ZpoolConfig config = new ZpoolConfig();
    private static Zpool ZPOOL;

    static {
        config.setUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setConnSize(1);
        ZPOOL = new Zpool(config);
    }

    public static Connection getConnection(){
        return ZPOOL.getConnection();
    }

}
