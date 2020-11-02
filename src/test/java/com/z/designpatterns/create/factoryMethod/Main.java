package com.z.designpatterns.create.factoryMethod;

public class Main {
    public static void main(String[] args) {
        Factory factory = new productAFactory();
        Product product = factory.factoryMethod();
        product.use();
    }
}
