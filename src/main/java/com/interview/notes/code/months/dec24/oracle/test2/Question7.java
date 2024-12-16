package com.interview.notes.code.months.dec24.oracle.test2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Question7 {

    public static void main(String[] args) {
        // Create an instance of the Test class
        Test futureTask = new Test();

        // Define the numbers array with more than 5 elements (which will trigger an exception)
        int[] numbers = {1, 1, 2, 3, 5, 8, 13, 21};

        // Call calculateSum asynchronously and get the Future result
        Future<Integer> sumFuture = futureTask.calculateSum(numbers);

        try {
            // Block until the result is available
            sumFuture.get(); // This will throw an exception if there is an error during calculation
            System.out.println("Task Completed.");
        } catch (ExecutionException e) {
            System.out.println("An execution exception occurred" + e.getCause());
        } catch (InterruptedException e) {
            System.out.println("An interrupted exception occurred");
        } catch (IllegalArgumentException e) {
            System.out.println("An illegal argument exception occurred");
        } catch (Exception e) {
            System.out.println("An exception occurred");
        }
    }

    // Test class with calculateSum method
    public static class Test {
        // This method calculates the sum asynchronously
        public Future<Integer> calculateSum(int... values) {
            return CompletableFuture.supplyAsync(() -> {
                int sum = 0;
                for (int i = 0; i < values.length; ++i) {
                    sum += values[i]; // Add the current number to the sum
                    if (i == 5) { // If more than 5 values are given, throw an exception
                        throw new IllegalArgumentException("Can only add up to 5 numbers.");
                    }
                }
                return sum;
            });
        }
    }
}
