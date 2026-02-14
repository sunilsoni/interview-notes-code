package com.interview.notes.code.year.y2026.feb.common.test7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Counter {
    // AtomicInteger handles the low-level synchronization for us
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        // This is a thread-safe, non-blocking operation
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        int numberOfThreads = 10;
        int incrementsPerThread = 1000;

        // Creating a pool of threads to simulate concurrent access
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter.increment();
                }
            });
        }

        // Shut down the executor and wait for tasks to finish
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Final Count: " + counter.getCount());
        System.out.println("Expected Count: " + (numberOfThreads * incrementsPerThread));
    }
}