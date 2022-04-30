package com.z.core.concurrency.aync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 1/0;
        })
                .thenApply(i -> i + 1)
                .thenApply(i -> i * 2)
                .whenComplete((r, e) -> {
                    System.out.println(r);
                    System.out.println(e);
        });

        Integer s = future.join();
        System.out.println(s);

    }

}
