package com.interview.notes.code.year.y2025.november.oci.test6;

import java.util.concurrent.CompletableFuture;

public class ParallelAsyncExample {

    // Simulate an async task
    public static CompletableFuture<String> asyncTask(String name, int delayMillis) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(delayMillis); // Simulate delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Result from " + name;
        });
    }

    public static void main(String[] args) {
        // Start multiple async tasks
        CompletableFuture<String> task1 = asyncTask("ServiceA", 1000);
        CompletableFuture<String> task2 = asyncTask("ServiceB", 1500);
        CompletableFuture<String> task3 = asyncTask("ServiceC", 500);

        // Combine all results
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);

        // Process results after all tasks complete
        allTasks.thenRun(() -> {
            try {
                String result1 = task1.get();
                String result2 = task2.get();
                String result3 = task3.get();

                System.out.println("All results:");
                System.out.println(result1);
                System.out.println(result2);
                System.out.println(result3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Prevent main thread from exiting early
        try {
            allTasks.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
