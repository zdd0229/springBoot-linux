package com.z.designpatterns.create.singleton;

import java.util.ArrayList;
import java.util.List;

public class Multitcm {

    private static List<Multitcm> list = new ArrayList<Multitcm>();

    private static final int n = 10;

    static {
        for (int i = 0; i < n; i++) {
            list.add(new Multitcm(i));
        }
    }

    private int load;

    private Multitcm(int n) {
        this.load=n;
    }

    public static Multitcm getRandomInstance(){
//        int value = (int)(Math.random()*10);
        int value = 5;
        return list.get(value);
    }

    public int getLoad(){
        return this.load;
    }

    public static void main(String[] args) {

        Multitcm multitcm1 = Multitcm.getRandomInstance();
        Multitcm multitcm2 = Multitcm.getRandomInstance();

        System.out.println(multitcm1==multitcm2);

    }
}
