package com.z.designpatterns.create._1;

public class Main {
    public static void main(String[] args) {
        Product product =ProductFactory.getProduct("a");
        product.use();
    }
}
