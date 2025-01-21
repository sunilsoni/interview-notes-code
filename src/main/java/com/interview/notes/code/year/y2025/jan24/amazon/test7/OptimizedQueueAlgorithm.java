package com.interview.notes.code.year.y2025.jan24.amazon.test7;

import java.util.*;

public class OptimizedQueueAlgorithm {

    public static int getMaximumEvents(List<Integer> payload) {
        Map<Integer, Integer> freq = new HashMap<>();
        int total = 0;
        for (int val : payload) {
            int count = freq.getOrDefault(val, 0);
            // Only count up to 3 occurrences per value
            if (count < 3) {
                total++;
            }
            freq.put(val, count + 1);
        }
        return total;
    }

    // Helper method for running and validating test cases
    public static void runTestCase(List<Integer> payload, int expected) {
        int result = getMaximumEvents(payload);
        System.out.println("Input: " + payload);
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println(result == expected ? "PASS\n" : "FAIL\n");
    }

    public static void main(String[] args) {
        // Test Case 1: Sample Case 1
        List<Integer> payload1 = Arrays.asList(1, 100);
        runTestCase(payload1, 2);  // Expected 2
        
        // Test Case 2: Sample Case 0
        List<Integer> payload0 = Arrays.asList(5, 5, 2, 1, 3, 4, 5);
        runTestCase(payload0, 7);  // Our analysis predicts 7 as correct
        
        // Test Case 3: Example case
        List<Integer> payloadExample = Arrays.asList(1, 3, 5, 4, 2, 6, 8, 7, 9);
        runTestCase(payloadExample, 9);
        
        // Additional Test Cases:
        
        // All elements the same
        List<Integer> allSame = new ArrayList<>(Collections.nCopies(1000, 42));
        // Only 3 can be selected because we can use value 42 at most 3 times
        runTestCase(allSame, 3);
        
        // Large input simulation with increasing and repeating pattern
        List<Integer> largeInput = new ArrayList<>();
        int largeN = 200000;
        for (int i = 0; i < largeN; i++) {
            largeInput.add(i % 1000);  // simulate duplicates among distinct values
        }
        System.out.println("Running large input test...");
        long startTime = System.currentTimeMillis();
        int largeResult = getMaximumEvents(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input test completed in " + (endTime - startTime) + " ms. Result: " + largeResult);
    }
}