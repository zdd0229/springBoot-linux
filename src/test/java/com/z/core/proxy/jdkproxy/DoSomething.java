package com.z.core.proxy.jdkproxy;

import java.lang.reflect.Proxy;

public class DoSomething {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        Subject subjectLogProxy = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(), RealSubject.class.getInterfaces(), new LogProxy(realSubject));
        subjectLogProxy.sayHello("赵丁丁");
        subjectLogProxy.sayGoodBey();
    }
}
