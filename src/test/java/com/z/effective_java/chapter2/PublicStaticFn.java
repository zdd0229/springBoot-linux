package com.z.effective_java.chapter2;

import java.math.BigInteger;
import java.util.Random;

public class PublicStaticFn {
    public static void main(String[] args) {


//        Random random= new Random();
//        BigInteger a = BigInteger.probablePrime(5,random);
//        System.out.println(a);

        NutritionFacts facts= new NutritionFacts.Builder(1,2).calories(2).build();

    }
}
