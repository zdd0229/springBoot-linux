package com.z.designpatterns.create.singleton.example.president;

public class President {

    private static volatile President PRESIDENT = null;

    private President(){
        System.out.println("产生一个新总统");
    }

    public void getName(){
        System.out.println("我是特朗普");
    }

    public static synchronized President getInstance(){

        if (PRESIDENT == null){
            PRESIDENT = new President();
        }else {
            System.out.println("已经有一个总统了，不能产生新的总统");
        }

        return PRESIDENT;
    }

    public static void main(String[] args) {

        President president1 = President.getInstance();
        president1.getName();

        President president2 = President.getInstance();
        president2.getName();

    }
}
