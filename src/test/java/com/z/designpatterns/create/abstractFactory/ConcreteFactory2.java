package com.z.designpatterns.create.abstractFactory;

public class ConcreteFactory2 extends AbstractFactory {

    @Override
    public ProductA creatProductA() {
        return new ProductA2();
    }

    @Override
    public ProductB creatProductB() {
        return new ProductB2();
    }
}
