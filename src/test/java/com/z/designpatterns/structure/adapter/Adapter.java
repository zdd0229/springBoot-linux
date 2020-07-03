package com.z.designpatterns.structure.adapter;

public class Adapter implements Target {

    private Adaptee adaptee;

    @Override
    public void request(String name) {
        adaptee.spRequest(name);
    }

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
}
