package com.interview.notes.code.year.y2024.march24.test12;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFuture3Threads {
    public static void main(String[] args) {
        // Create three CompletableFutures for three separate tasks
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> task("Task 1"));
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> task("Task 2"));
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> task("Task 3"));

        // Wait for all CompletableFuture to complete
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);

        // Handle completion or failure of allFutures
        allFutures.thenRun(() -> {
            try {
                // Retrieve the results of all CompletableFuture when all are completed successfully
                String result1 = future1.get();
                String result2 = future2.get();
                String result3 = future3.get();

                System.out.println("All tasks completed successfully:");
                System.out.println("Result 1: " + result1);
                System.out.println("Result 2: " + result2);
                System.out.println("Result 3: " + result3);
            } catch (InterruptedException | ExecutionException e) {
                // Handle exception if any CompletableFuture fails
                System.err.println("One of the tasks failed:");
                e.printStackTrace();
                // Cancel all CompletableFuture if one of them fails
                future1.cancel(true);
                future2.cancel(true);
                future3.cancel(true);
            }
        });

        // Wait for all CompletableFuture to complete or fail
        try {
            allFutures.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Sample task method that may throw an exception
    private static String task(String name) {
        try {
            // Simulate task execution
            Thread.sleep(1000);
            if (name.equals("Task 2")) {
                throw new RuntimeException("Task 2 failed");
            }
            return name + " completed";
        } catch (InterruptedException e) {
            // Handle interruption
            Thread.currentThread().interrupt();
            return name + " interrupted";
        }
    }
}
