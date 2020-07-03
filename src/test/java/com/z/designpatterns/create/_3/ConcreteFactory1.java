package com.z.designpatterns.create._3;

public class ConcreteFactory1 extends AbstractFactory {

    @Override
    public ProductA creatProductA() {
        return new ProductA1();
    }

    @Override
    public ProductB creatProductB() {
        return new ProductB1();
    }
}
