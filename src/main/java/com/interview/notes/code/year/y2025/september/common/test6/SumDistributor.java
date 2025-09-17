package com.interview.notes.code.year.y2025.september.common.test6;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SumDistributor {

    public static void main(String[] args) throws InterruptedException {
        List<List<Integer>> inputs = List.of(
                List.of(1, 2, 3),
                List.of(4, 5),
                List.of(6, 7, 8, 9),
                List.of(10)
        );

        System.out.println("=== Manual Threads ===");
        distributeWithThreads(inputs);

        System.out.println("=== ExecutorService ===");
        distributeWithExecutor(inputs);
    }

    // Approach 1: one Thread per task
    static void distributeWithThreads(List<List<Integer>> inputs) throws InterruptedException {
        var threads = inputs.stream()
                .map(chunk -> new Thread(() -> {
                    int sum = chunk.stream().mapToInt(Integer::intValue).sum();
                    System.out.println("Sum of " + chunk + " = " + sum +
                            " via " + Thread.currentThread().getName());
                }))
                .toList();

        threads.forEach(Thread::start);
        for (var t : threads) {
            t.join();
        }
    }

    // Approach 2: fixed-size thread pool
    static void distributeWithExecutor(List<List<Integer>> inputs) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (var chunk : inputs) {
            executor.submit(() -> {
                int sum = chunk.stream().mapToInt(Integer::intValue).sum();
                System.out.println("Sum of " + chunk + " = " + sum +
                        " via " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}