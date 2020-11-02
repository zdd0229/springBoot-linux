package com.z.designpatterns.create.abstractFactory;

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
