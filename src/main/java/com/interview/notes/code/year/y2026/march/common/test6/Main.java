package com.interview.notes.code.year.y2026.march.common.test6;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {

    static String apiCall() {
        try { Thread.sleep(2000); } catch (Exception e) {}
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        var f1 = CompletableFuture.supplyAsync(Main::apiCall, executor);
        var f2 = CompletableFuture.supplyAsync(Main::apiCall, executor);

        var result = f1.thenCombine(f2, (r1, r2) -> r1 + " | " + r2).join();

        System.out.println(result);

        executor.shutdown();
    }
}