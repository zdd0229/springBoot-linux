package com.z.designpatterns.behavior.templatemethod;

public class Test {
    public static void main(String[] args) {

        SpecificBusiness b1 = new SpecificBusiness1();
        SpecificBusiness b2 = new SpecificBusiness2();

        b1.exec();
        b2.exec();

    }
}
