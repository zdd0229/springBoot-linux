package com.z.designpatterns.create.prototype.example.prototypeManager;

public class Square extends Graph {

    private double side;

    public Square(double side){
        this.side=side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

}
