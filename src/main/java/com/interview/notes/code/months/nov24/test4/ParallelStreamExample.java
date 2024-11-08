package com.interview.notes.code.months.nov24.test4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class ParallelStreamExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ForkJoinPool customThreadPool = new ForkJoinPool(4);

        customThreadPool.submit(() ->
                IntStream.range(0, 100)
                        .parallel()
                        .forEach(i -> {
                            System.out.println("Processing: " + i + " in thread: " + Thread.currentThread().getName());
                        })
        ).join();

        executor.shutdown();
        customThreadPool.shutdown();
    }
}
