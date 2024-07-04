package com.interview.notes.code.months.july24.Test1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ExecutorServiceExmaple {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        try {
            List<Future<String>> futures = numbers.stream()
                    .map(number -> executorService.submit(() -> {
                        String threadName = Thread.currentThread().getName();
                        System.out.println("Processing number " + number + " on " + threadName);
                        Thread.sleep(1000);
                        return "Result of " + number + " processed on " + threadName;
                    }))
                    .collect(Collectors.toList());

            List<String> results = futures.stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());

            results.forEach(System.out::println);
        } finally {
            executorService.shutdown();
        }
    }
}
