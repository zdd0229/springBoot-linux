package com.z.core.proxy.jdkproxy;

public class RealSubject implements Subject {
    @Override
    public void sayHello(String name) {
        System.out.println(String.format("%s,你好", name));
    }

    @Override
    public void sayGoodBey() {
        System.out.println("再见");
    }
}
