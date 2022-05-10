package com.z.designpatterns.create.singleton;

public class Main {
    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getInsance();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        singleton1.plus();
                    }
                }
            }).start();
        }

    }
}
