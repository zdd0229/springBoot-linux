package com.z.core.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class JdbcPool {

    private static final Logger log = LoggerFactory.getLogger(JdbcPool.class);

    private static List<Connection> pool = new CopyOnWriteArrayList();

    private static final String url = "jdbc:mysql://101.132.128.127:3307/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "root";

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            5, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue());

    private static int conNum = 10;

    public static Connection getConnection() {
        Random random = new Random();
        int i = random.nextInt(conNum);
        log.info("共{}个连接，获取第{}个连接",pool.size(), i);
        Connection connection = pool.get(i);
        return connection;
    }

    public static void init(int customNum) {
        conNum = customNum;
        CountDownLatch latch = new CountDownLatch(conNum);
        for (int i = 0; i < conNum; i++) {
            executor.submit(() -> {
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(url, username, password);
                    pool.add(connection);
                    latch.countDown();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("连接池初始化");
    }

    public static void destroy() {
        pool.forEach(e -> {
            try {
                e.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        executor.shutdown();
    }
}
