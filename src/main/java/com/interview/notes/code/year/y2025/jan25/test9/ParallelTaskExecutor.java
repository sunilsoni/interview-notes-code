package com.interview.notes.code.year.y2025.jan25.test9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelTaskExecutor {
    public static void main(String[] args) {
        // Create a single thread executor
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            // Create three tasks
            CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
                return performTask("Task 1", 2);
            }, executor);

            CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
                return performTask("Task 2", 3);
            }, executor);

            CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
                return performTask("Task 3", 1);
            }, executor);

            // Combine all tasks
            CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);

            // Wait for all tasks to complete and get results
            allTasks.thenRun(() -> {
                try {
                    System.out.println("\nAll tasks completed!");
                    System.out.println("Task 1 result: " + task1.get());
                    System.out.println("Task 2 result: " + task2.get());
                    System.out.println("Task 3 result: " + task3.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).get(); // Wait for completion

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Shutdown the executor
            shutdownExecutor(executor);
        }
    }

    private static String performTask(String taskName, int seconds) {
        System.out.println(taskName + " started at: " + System.currentTimeMillis());
        try {
            // Simulate work
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return taskName + " was interrupted";
        }
        System.out.println(taskName + " completed at: " + System.currentTimeMillis());
        return taskName + " completed successfully";
    }

    private static void shutdownExecutor(ExecutorService executor) {
        try {
            System.out.println("\nShutting down executor...");
            executor.shutdown();
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
