package com.interview.notes.code.year.y2023.nov23.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadingExample {

    public static void main(String[] args) {
        // Create a list of numbers
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        // Create an ExecutorService with two threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Create a list to store the results
        List<Future<Integer>> results = new ArrayList<>();

        // Submit tasks to calculate the square of each number concurrently
        for (int num : numbers) {
            Future<Integer> result = executor.submit(() -> {
                // Code to calculate the square of a number
                System.out.println("Calculating square of " + num + " in thread " + Thread.currentThread().getId());
                return num * num;
            });
            results.add(result);
        }

        // Shutdown the executor
        executor.shutdown();

        // Wait for all tasks to complete and collect the results
        for (Future<Integer> result : results) {
            try {
                int square = result.get();
                System.out.println("Result: " + square);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
