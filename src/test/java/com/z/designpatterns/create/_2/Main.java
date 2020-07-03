package com.z.designpatterns.create._2;

public class Main {
    public static void main(String[] args) {
        Factory factory = new productAFactory();
        Product product = factory.factoryMethod();
        product.use();
    }
}
