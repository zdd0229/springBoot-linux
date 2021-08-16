package com.z.connectionpool;

import com.z.util.RunTime;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class Zpool {

    private ZpoolConfig config;

    private CopyOnWriteArrayList<Connection> pool;

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /**
     * zpool构造函数
     * 1、获取配置
     * 2、获取连接
     *
     * @param config
     */
    public Zpool(ZpoolConfig config) {
        this.config = config;
        initConnections();
    }

    private void initConnections() {

        pool=new CopyOnWriteArrayList<>();
        CountDownLatch countDown = new CountDownLatch(config.getConnSize());

        RunTime runTime = new RunTime();
        runTime.start();
        for (int i = 0; i < config.getConnSize(); i++) {
            EXECUTOR.submit(()->{
                try {
                    Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
                    pool.add(connection);
                    countDown.countDown();
                } catch (SQLException e) {
                    log.error("z-pool获取连接失败");
                }
            });
        }
        try {
            countDown.await();
        } catch (InterruptedException e) {
            log.error("z-pool获取连接失败");
        }
        log.info("连接池初始化成功,耗时{}",runTime.end());
    }

    /**
     * 获取连接
     */
    public Connection getConnection(){
        Random random = new Random();
        return pool.get(random.nextInt(config.getConnSize()));
    }

}
