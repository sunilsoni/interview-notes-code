package com.interview.notes.code.year.y2025.jan25.amazon.test6;

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

    // A helper method to run a test case
    public static void runTestCase(List<Integer> payload, int expected) {
        int result = getMaximumEvents(payload);
        System.out.println("Input: " + payload);
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println(result == expected ? "PASS\n" : "FAIL\n");
    }

    public static void main(String[] args) {
        // Sample Test Cases
        List<Integer> payload1 = Arrays.asList(1, 100);
        runTestCase(payload1, 2);  // Sample Case 1

        List<Integer> payload0 = Arrays.asList(5, 5, 2, 1, 3, 4, 5);
        runTestCase(payload0, 6);  // Sample Case 0 as given (based on provided explanation)

        List<Integer> payloadExample = Arrays.asList(1, 3, 5, 4, 2, 6, 8, 7, 9);
        runTestCase(payloadExample, 9);  // Example case

        // Additional Test Cases
        // Case: All elements the same
        List<Integer> allSame = new ArrayList<>(Collections.nCopies(1000, 42));
        // Only 3 can be selected because of the three-segment rule
        runTestCase(allSame, 3);

        // Case: Large input simulation with increasing values
        List<Integer> largeInput = new ArrayList<>();
        int largeN = 200000;
        for (int i = 0; i < largeN; i++) {
            largeInput.add(i % 1000);  // simulate duplicates but many distinct numbers repeated
        }
        // Expected result: sum(min(freq, 3)) over all distinct values.
        // For simplicity here, we will not compute expected manually, just run to check performance.
        System.out.println("Running large input test...");
        long startTime = System.currentTimeMillis();
        int largeResult = getMaximumEvents(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input test completed in " + (endTime - startTime) + " ms. Result: " + largeResult);
    }
}