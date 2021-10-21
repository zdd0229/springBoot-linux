package com.z.core.jdbc;

import java.util.concurrent.CountDownLatch;

public class RunnableTest implements Runnable {

    private CountDownLatch latch;

    @Override
    public void run() {
        System.out.println(String.format("第s%个线程"));
        latch.countDown();
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
}
