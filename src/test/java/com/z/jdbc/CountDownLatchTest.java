package com.z.jdbc;

import com.z.annotation.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.*;

public class CountDownLatchTest {


    public static void main(String[] args) throws InterruptedException {
//        test1();
        CountDownLatch latch = new CountDownLatch(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                System.out.println("nihao");
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("都执行完了");
        executor.shutdown();
    }

    public static void test1() throws InterruptedException {
        //10名运动员
        final CountDownLatch count = new CountDownLatch(10);

        //java的线程池
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        for (int index = 1; index <= 10; index++) {
            final int number = index;
            executor.submit(new Runnable() {
                public void run() {
                    try {

                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(number + ": arrived");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //运动员到达终点,count数减一
                        count.countDown();
                    }
                }
            });
        }
        System.out.println("Game Started");
        //等待count数变为0,否则会一直处于等待状态,游戏就没法结束了
        count.await();
        System.out.println("Game Over");
        //关掉线程池
        executor.shutdown();
    }


}
