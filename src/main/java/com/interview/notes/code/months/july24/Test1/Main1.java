package com.interview.notes.code.months.july24.Test1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main1 {
    public static void main(String[] args) {
        // Create an ExecutorService with a fixed thread pool of 3 threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit tasks to the executor
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executorService.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Task " + taskId + " is running on " + threadName);
                try {
                    // Simulate some work with Thread.sleep
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " completed on " + threadName);
            });
        }

        // Shutdown the executor service
        executorService.shutdown();
        try {
            // Wait for all tasks to finish
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow(); // Force shutdown if tasks are not finished
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow(); // Re-interrupt the current thread
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks completed.");
    }
}
