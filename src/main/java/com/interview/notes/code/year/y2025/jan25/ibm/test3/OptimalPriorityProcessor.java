package com.interview.notes.code.year.y2025.jan25.ibm.test3;

import java.util.*;
import java.util.stream.Collectors;

public class OptimalPriorityProcessor {

    public static List<Integer> getOptimalPriority(List<Integer> priority) {
        // Create a mutable copy of the original list
        List<Integer> result = new ArrayList<>(priority);

        // Perform swaps to achieve lexicographically smallest sequence
        for (int i = 0; i < result.size() - 1; i++) {
            for (int j = 0; j < result.size() - i - 1; j++) {
                // Check if adjacent elements can be swapped
                if (canSwap(result.get(j), result.get(j + 1))) {
                    // Swap if it leads to lexicographically smaller sequence
                    if (result.get(j) > result.get(j + 1)) {
                        Collections.swap(result, j, j + 1);
                    }
                }
            }
        }

        return result;
    }

    // Helper method to check if swap is valid
    private static boolean canSwap(int a, int b) {
        // One task should be odd (CPU-bound), other should be even (I/O-bound)
        return (a % 2 == 0 && b % 2 != 0) || (a % 2 != 0 && b % 2 == 0);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1
        List<Integer> priority1 = Arrays.asList(2, 4, 6, 4, 3, 2);
        List<Integer> expected1 = Arrays.asList(2, 3, 4, 6, 4, 2);
        testCase(priority1, expected1, "Test Case 1");

        // Test Case 2
        List<Integer> priority2 = Arrays.asList(0, 7, 0, 9);
        List<Integer> expected2 = Arrays.asList(0, 0, 7, 9);
        testCase(priority2, expected2, "Test Case 2");

        // Test Case 3
        List<Integer> priority3 = Arrays.asList(9, 4, 8, 6, 3);
        List<Integer> expected3 = Arrays.asList(4, 8, 6, 9, 3);
        testCase(priority3, expected3, "Test Case 3");

        // Large Input Test
        List<Integer> largeInput = generateLargeInput(200000);
        List<Integer> largeResult = getOptimalPriority(largeInput);
        System.out.println("Large Input Test: " +
                (largeResult.size() == largeInput.size() ? "PASS" : "FAIL"));

        // Performance Test
        performanceTest(largeInput);
    }

    // Helper method to test individual cases
    private static void testCase(List<Integer> input,
                                 List<Integer> expected,
                                 String testName) {
        List<Integer> result = getOptimalPriority(input);
        boolean passed = result.equals(expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));

        // Detailed output if test fails
        if (!passed) {
            System.out.println("Input: " + input);
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + result);
        }
    }

    // Generate large random input for testing
    private static List<Integer> generateLargeInput(int size) {
        Random random = new Random();
        return random.ints(size, 0, 10)
                .boxed()
                .collect(Collectors.toList());
    }

    // Performance testing method
    private static void performanceTest(List<Integer> input) {
        long startTime = System.nanoTime();
        getOptimalPriority(input);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("Performance Test:");
        System.out.println("Input Size: " + input.size());
        System.out.println("Execution Time: " + duration + " ms");
    }
}