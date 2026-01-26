package com.interview.notes.code.year.y2026.jan.common.test7;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelThreadsExample {

    public static void main(String[] args) throws InterruptedException {
        // Latch initialized with 3 (number of threads to wait for)
        CountDownLatch latch = new CountDownLatch(3);

        // Thread pool with 3 workers
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Thread 1: 5 seconds
        executor.submit(() -> {
            try {
                System.out.println("Thread 1 started...");
                Thread.sleep(5000);
                System.out.println("Thread 1 completed.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown(); // signal completion
            }
        });

        // Thread 2: 2 seconds
        executor.submit(() -> {
            try {
                System.out.println("Thread 2 started...");
                Thread.sleep(2000);
                System.out.println("Thread 2 completed.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
            }
        });

        // Thread 3: 1 second
        executor.submit(() -> {
            try {
                System.out.println("Thread 3 started...");
                Thread.sleep(1000);
                System.out.println("Thread 3 completed.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown();
            }
        });

        // Wait until all threads finish
        latch.await();

        // Now print final message
        System.out.println("✅ All threads completed successfully!");

        executor.shutdown();
    }
}
