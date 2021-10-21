package com.z.core.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogProxy implements InvocationHandler {

    private Subject subject;

    public LogProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行前");
        Object invoke = method.invoke(subject, args);
        System.out.println("执行后");
        return invoke;
    }
}
