package com.z.thread.tutorials;

public class ThreadCreateImpl implements Runnable {
    @Override
    public void run() {
        System.out.println("run by：" +Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadCreateImpl(),"thread1");

        Thread thread2 = new Thread(()->{
            System.out.println("run by：" +Thread.currentThread().getName());
        },"thread2");

        thread1.start();
        thread2.start();
        System.out.println("run by：" +Thread.currentThread().getName());
    }

}
