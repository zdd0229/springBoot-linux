package com.z.core.hashcode;

import java.util.HashSet;
import java.util.Set;

public class test {
    public static void main(String[] args) {

        String s1 = new String("zhangsan");

        String s2 = new String("zhangsan");

        System.out.println(s1 == s2);// false

        System.out.println(s1.equals(s2));// true

        System.out.println(s1.hashCode());// s1.hashcode()等于s2.hashcode()

        System.out.println(s2.hashCode());

        Set hashset = new HashSet();

        hashset.add(s1);

        hashset.add(s2);

        System.out.println(hashset.size());//1
    }
}
