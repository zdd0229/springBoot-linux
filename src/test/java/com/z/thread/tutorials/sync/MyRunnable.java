package com.z.thread.tutorials.sync;

public class MyRunnable implements Runnable {

    private static long num = 0;

    @Override
    public synchronized void run() {

        String threadName=Thread.currentThread().getName();
        long start= System.nanoTime();

        for (int i = 0; i < 10000; i++) {
                num++;
        }

        System.out.println(threadName+"执行耗时："+(System.nanoTime()-start)/1000L+"微秒");
        System.out.println(threadName + ":" + num);
    }

    public static void main(String[] args) {

        MyRunnable runnable = new MyRunnable();

        Thread thread1 = new Thread(runnable, "thread1");
        Thread thread2 = new Thread(runnable, "thread2");

        thread1.start();
        thread2.start();

        System.out.println(Thread.currentThread().getName() + ":" + num);

    }

}
