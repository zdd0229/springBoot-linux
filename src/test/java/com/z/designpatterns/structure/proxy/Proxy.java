package com.z.designpatterns.structure.proxy;

public class Proxy implements Subject {

    private Subject realSubject;

    public Proxy(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        beforeRequest();
        realSubject.request();
        afterSubject();
    }

    private void afterSubject() {
        System.out.println("请求后。。。。。。。。。");
    }

    private void beforeRequest() {
        System.out.println("请求前。。。。。。。。。");
    }

}
