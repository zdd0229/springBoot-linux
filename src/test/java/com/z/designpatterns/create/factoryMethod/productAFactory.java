package com.z.designpatterns.create.factoryMethod;

public class productAFactory extends Factory {
    @Override
    public Product factoryMethod() {
        return new ProductA();
    }
}
