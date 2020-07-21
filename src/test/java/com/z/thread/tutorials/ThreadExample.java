package com.z.thread.tutorials;

public class ThreadExample {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());

        for (int i = 0; i < 10; i++) {

            new Thread(()->{
                if("Thread_5".equals(Thread.currentThread().getName())){
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName());
            },"Thread_"+i).start();

        }
    }

}
