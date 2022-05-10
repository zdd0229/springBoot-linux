package com.z.designpatterns.create.singleton;

public class Singleton1 {
    private static final Singleton1 singleton=new Singleton1();
    private Singleton1(){}
    public static Singleton1 getInsance(){
        return singleton;
    }

     private static int num=0;

    public void plus(){
        num++;
        System.out.println(Thread.currentThread().getId()+ ":"+num);
    }
}
