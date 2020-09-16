package com.z.main;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        List<Integer> list = new ArrayList<>(32);
        List<Integer> link = new LinkedList<>();

        Map<String,String> hashMap= new HashMap<>();

        hashMap.put("name","zdd");


        System.out.println(tableSizeFor(65));

    }
    static final int MAXIMUM_CAPACITY = 1 << 30;


    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
