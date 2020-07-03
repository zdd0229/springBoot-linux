package com.z.designpatterns.create._2;

public class productAFactory extends Factory {
    @Override
    public Product factoryMethod() {
        return new ProductA();
    }
}
