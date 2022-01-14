package com.z.core.collection;

import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {

    public static void main(String[] args) {

        ConcurrentHashMap test = new ConcurrentHashMap(4);
        for (int i = 0; i < 100; i++) {
            test.put(new hha(), "1");
            if (i == 99) {
                test.put(new hha(), "1");
            }
        }

        System.out.println("");
    }

    private static class hha {
        @Override
        public int hashCode() {
            return 1;
        }
    }

}
