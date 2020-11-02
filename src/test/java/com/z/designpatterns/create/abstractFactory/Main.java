package com.z.designpatterns.create.abstractFactory;

public class Main {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new ConcreteFactory1();
        ProductA productA = abstractFactory.creatProductA();
        ProductB productB = abstractFactory.creatProductB();
        productA.use();
        productB.eat();

        AbstractFactory abstractFactory1 = new ConcreteFactory2();
        ProductA productA1 = abstractFactory1.creatProductA();
        ProductB productB1 = abstractFactory1.creatProductB();
        productA1.use();
        productB1.eat();
    }
}
