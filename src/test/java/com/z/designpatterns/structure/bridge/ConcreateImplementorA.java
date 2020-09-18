package com.z.designpatterns.structure.bridge;

public class ConcreateImplementorA implements Implementor {
    @Override
    public void operationImp() {
        System.out.println("圆");
    }
}
