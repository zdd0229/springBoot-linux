package com.z.core.thread.tutorials.waitnotify;

public class WaitNotify {

    private int num = 0;

    public void inc() {
        try {
            this.notifyAll();
            num++;
            this.wait();
            System.out.println(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WaitNotify test = new WaitNotify();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> test.inc()).start();
        }
    }
}
