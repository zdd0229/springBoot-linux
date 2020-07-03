package com.z.thread;

import javax.sound.midi.Soundbank;
import java.util.Random;

public class main {
    public static void main(String[] args) {

        long s1= System.currentTimeMillis();

        Random random = new Random();
        int a[] = new int[200000000];
        for (int i = 0; i < a.length; i++) {
            a[i]=random.nextInt(1000);
        }

        System.out.println(System.currentTimeMillis());

        int b[] = new int[200000000];
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000000; i++) {
                    b[i]=random.nextInt(1000);
                }
                System.out.println(System.currentTimeMillis());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 100000001; i < 200000000; i++) {
                   b[i]=random.nextInt(1000);
                }
                System.out.println(System.currentTimeMillis());
            }
        }).start();


        long s2=System.currentTimeMillis();

        System.out.println((s2-s1));

        System.out.println(b[22222]);

    }
}
