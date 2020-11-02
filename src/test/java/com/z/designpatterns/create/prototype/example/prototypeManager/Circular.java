package com.z.designpatterns.create.prototype.example.prototypeManager;

public class Circular extends Graph {

    private double radius;

    public Circular(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

}
