package com.z.java;

public class Apple extends Fruit implements Cloneable{

    private String name;

    public Apple(String name) {
        super("apple");
        this.name = name;
    }

    public Apple() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
