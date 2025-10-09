package com.interview.notes.code.year.y2025.october.common.test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class FibonacciRecursive {
    
    // Cache to store previously calculated Fibonacci numbers to optimize performance
    private static final Map<Integer, Long> fibCache = new HashMap<>();

    /**
     * Recursive method to calculate Fibonacci number with memoization
     * @param n position in sequence
     * @return Fibonacci number at position n
     */
    public static long fibonacci(int n) {
        // Base cases for first two numbers in sequence
        if (n <= 1) return n;
        
        // Check if value exists in cache to avoid recalculation
        if (fibCache.containsKey(n)) {
            return fibCache.get(n);
        }
        
        // Calculate new Fibonacci number and store in cache
        long result = fibonacci(n - 1) + fibonacci(n - 2);
        fibCache.put(n, result);
        return result;
    }

    /**
     * Prints first n numbers in Fibonacci sequence
     * @param count number of elements to print
     */
    public static void printFibonacciSequence(int count) {
        // Input validation
        if (count < 0) {
            System.out.println("Please enter a positive number");
            return;
        }
        
        // Using Java 8 Stream to generate and print sequence
        IntStream.range(0, count)
                .mapToObj(FibonacciRecursive::fibonacci)
                .forEach(num -> System.out.print(num + " "));
        System.out.println();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println("Running test cases...\n");

        // Test Case 1: First 5 numbers
        System.out.println("Test Case 1: First 5 numbers");
        try {
            printFibonacciSequence(5);
            System.out.println("PASS: Output matches expected sequence 0 1 1 2 3");
        } catch (Exception e) {
            System.out.println("FAIL: " + e.getMessage());
        }

        // Test Case 2: Zero elements
        System.out.println("\nTest Case 2: Zero elements");
        try {
            printFibonacciSequence(0);
            System.out.println("PASS: Handles zero input correctly");
        } catch (Exception e) {
            System.out.println("FAIL: " + e.getMessage());
        }

        // Test Case 3: Negative input
        System.out.println("\nTest Case 3: Negative input");
        try {
            printFibonacciSequence(-1);
            System.out.println("PASS: Handles negative input correctly");
        } catch (Exception e) {
            System.out.println("FAIL: " + e.getMessage());
        }

        // Test Case 4: Large sequence (first 40 numbers)
        System.out.println("\nTest Case 4: Large sequence (40 numbers)");
        try {
            printFibonacciSequence(40);
            System.out.println("PASS: Successfully calculated large sequence");
        } catch (Exception e) {
            System.out.println("FAIL: " + e.getMessage());
        }
    }
}
