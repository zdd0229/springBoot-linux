package com.z.designpatterns.create.bulider;

public class PublicStaticFn {
    public static void main(String[] args) {


//        Random random= new Random();
//        BigInteger a = BigInteger.probablePrime(5,random);
//        System.out.println(a);

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        executorService.execute(()->{
//                NutritionFacts facts= new NutritionFacts.Builder(1,1).calories(1).build();
//                System.out.println(facts);
//        });
//        executorService.execute(()->{
//                NutritionFacts facts= new NutritionFacts.Builder(2,2).calories(2).build();
//                System.out.println(facts);
//        });

        NutritionFacts.Builder builder = new NutritionFacts.Builder(1,1);
        NutritionFacts.Builder builder1 = new NutritionFacts.Builder(1,1);

        System.out.println(builder==builder1);


    }
}
