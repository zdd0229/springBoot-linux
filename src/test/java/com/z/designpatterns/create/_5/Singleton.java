package com.z.designpatterns.create._5;

public class Singleton {
    private static final Singleton singleton=new Singleton();;
    private Singleton(){}
    public static Singleton getInsance(){
        return singleton;
    }

     private static int num=0;

    public void plus(){
        num++;
        num++;
        System.out.println(Thread.currentThread().getId()+ ":"+num);
    }
}
