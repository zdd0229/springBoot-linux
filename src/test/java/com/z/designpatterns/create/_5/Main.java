package com.z.designpatterns.create._5;

public class Main {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton .getInsance();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    singleton1.plus();
                }
            }
        }).start();
        System.out.println("//////////////////////");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    singleton1.plus();
                }
            }
        }).start();
    }
}
