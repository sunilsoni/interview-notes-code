package com.interview.notes.code.year.y2026.jan.common.test7;

import java.util.concurrent.CompletableFuture;

public class ParallelThreadsCF {

    public static void main(String[] args) {

        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(() -> sleep(5000));
        CompletableFuture<Void> f2 =
                CompletableFuture.runAsync(() -> sleep(2000));
        CompletableFuture<Void> f3 =
                CompletableFuture.runAsync(() -> sleep(1000));

        CompletableFuture.allOf(f1, f2, f3).join();

        System.out.println("ALL THREADS COMPLETED SUCCESSFULLY");
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
