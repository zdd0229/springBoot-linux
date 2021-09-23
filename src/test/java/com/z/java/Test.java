package com.z.java;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Boolean a = new Boolean(true);
        String b = "true";
        String c = "true";
        System.out.println(c == b);

        Fruit fruit1 = new Apple();
        Fruit fruit2 = (Fruit) fruit1.clone();

        fruit2.getName();

        System.out.println(fruit1 == fruit2);

    }
}
