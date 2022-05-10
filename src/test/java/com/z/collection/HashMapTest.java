package com.z.collection;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap map = new HashMap(5);
        for (int i = 0; i < 10; i++) {
            map.put(Integer.toString(i), i);
        }
    }
}
