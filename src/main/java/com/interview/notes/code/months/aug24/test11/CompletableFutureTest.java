package com.interview.notes.code.months.aug24.test11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFuture<Integer> doCalc = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            try {
                Thread.sleep(10);
                doCalc.complete(2 + 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        try {
            System.out.println(2 * 4);  // Prints 8
            System.out.println(doCalc.get());  // Prints 4
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
    }
}
