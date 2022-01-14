package com.z.core.aqs;

public class VolatialTest {

    private volatile int num = 0;

    public static void main(String[] args) {

        VolatialTest volatialTest = new VolatialTest();
        volatialTest.num = 2;

        System.out.println(volatialTest.num);

    }

}
