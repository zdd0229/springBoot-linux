package com.z.designpatterns.structure.bridge;

public class ConcreateImplementorB implements Implementor{
    @Override
    public void operationImp() {
        System.out.println("红色");
    }
}
