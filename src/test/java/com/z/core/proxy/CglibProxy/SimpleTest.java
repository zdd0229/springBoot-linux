package com.z.core.proxy.CglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class SimpleTest {
    public void test(){
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SimpleTest.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> {
            System.out.println("before method run...");
            Object result = proxy.invokeSuper(obj, args1);
            System.out.println("after method run...");
            return result;
        });
        SimpleTest sample = (SimpleTest) enhancer.create();
        sample.test();
    }
}
