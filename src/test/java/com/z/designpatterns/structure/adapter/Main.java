package com.z.designpatterns.structure.adapter;

public class Main {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target= new Adapter(adaptee);
        target.request("666");
    }
}
