package com.z.designpatterns.structure.proxy;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("哈哈，请求来啦");
    }
}
