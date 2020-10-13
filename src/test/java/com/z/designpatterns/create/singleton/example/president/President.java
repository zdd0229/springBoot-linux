package com.z.designpatterns.create.singleton.example.president;

public class President {

    private static final President PRESIDENT = new President();

    private President(){
    }

    private String name = "特朗普";

    public String getName(){
        return this.name;
    }

    public static President getInstance(){
        return PRESIDENT;
    }

    public static void main(String[] args) {

        President president = President.getInstance();

        System.out.println(president.getName());

    }
}
