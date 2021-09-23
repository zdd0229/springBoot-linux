package com.z.java;

public class Apple extends Fruit implements Cloneable{

    private String name;

    public Apple(String name) {
        super("apple");
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
