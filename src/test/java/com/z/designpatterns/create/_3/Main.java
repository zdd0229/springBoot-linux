package com.z.designpatterns.create._3;

public class Main {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new ConcreteFactory1();
        ProductA productA = abstractFactory.creatProductA();
        ProductB productB = abstractFactory.creatProductB();
        productA.use();
        productB.eat();
    }
}
