package com.z.designpatterns.create.singleton;

public class Singleton2 {

    private static volatile Singleton2 instance;

    private Singleton2() {
    }

    public Singleton2 getInstance() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }

}
