package com.z.designpatterns.structure.bridge;

public abstract class Abstraction {

    private  Implementor implementor;

    public Implementor getImplementor() {
        return implementor;
    }

    public Abstraction(Implementor implementor ){
        this.implementor=implementor;
    }

    public abstract void operation();

}
