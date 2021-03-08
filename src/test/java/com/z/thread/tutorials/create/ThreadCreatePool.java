package com.z.thread.tutorials.create;

import java.util.concurrent.*;

public class ThreadCreatePool {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            5,
            200,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(10000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static ExecutorService executer = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        executer.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("ni hao");
            }
        });

    }

}
