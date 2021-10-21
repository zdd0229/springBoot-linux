package com.z.core.thread.tutorials.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {

    private static final int THREADS_COUNT = 20;

    private static CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

    private AtomicInteger num = new AtomicInteger(0);

    private void add() {
        num.incrementAndGet();
    }

    public int getNum() {
        return num.get();
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[THREADS_COUNT];

        CASTest test = new CASTest();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    test.add();
                }
                countDownLatch.countDown();
            });
            threads[i].start();
        }

        countDownLatch.await();

        System.out.println(test.getNum());

        System.out.println(test.exceptionTest());

    }

    private int exceptionTest(){
        try {
            System.out.println("try");
            return 4;
        }finally {
            System.out.println("finally");
            return 5;
        }
    }

}
