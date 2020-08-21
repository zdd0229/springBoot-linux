package com.z.thread.tutorials.create;

public class ThreadCreateExtend {

    public static void main(String[] args) {

        Thread thread = new Thread("thread_name"){
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("nihao "+getName());
            }
        };

        thread.start();
        System.out.println("nihao1");

    }


}
