package com.z.thread.test;

public class NumChangeTest {

    private static int num = 2;

    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                System.out.println(num);
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                num = 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
