package com.z.java;

public class Fruit implements Cloneable{

    private String name;

    public Fruit(String name){
        this.name = name;
        System.out.println("fruit");
    }

    public Fruit() {

    }

    public void getName(){
        System.out.println(name);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
