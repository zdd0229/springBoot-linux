package com.z.suanfa;

import com.z.suanfa.paixu.Paixu;

import java.util.Arrays;
import java.util.Random;

public class PaixuTest {

    public static void main(String[] args) {

        Random random= new Random();
        Paixu paixu=new Paixu();

        int a[] = new int[10];
        for (int i = 0; i < a.length; i++) {
            a[i]=random.nextInt(100);
        }

        int b[] = paixu.maoPao(Arrays.copyOf(a,a.length));
        System.out.println(Arrays.toString(b));

        int d[] = paixu.maoPaoPlus(Arrays.copyOf(a,a.length));
        System.out.println(Arrays.toString(d));

        int c[] = paixu.xuanZe(Arrays.copyOf(a,a.length));
        System.out.println(Arrays.toString(c));

        System.out.println(Arrays.toString(a));
    }
}
