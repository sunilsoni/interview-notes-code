package com.interview.notes.code.year.y2025.jan25.oracle.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CPUMaximizer {
    public static int maximizeCPU(List<Integer> requirements, int processingCapacity) {
        if (requirements == null || requirements.isEmpty() || processingCapacity <= 0) {
            return 0;
        }

        int n = requirements.size();
        // For very large inputs, use a different approach
        if (n > 32) {
            return handleLargeInput(requirements, processingCapacity);
        }

        long maxSum = 0; // Using long to prevent overflow

        // Generate all possible combinations using bit manipulation
        for (int mask = 0; mask < (1L << n); mask++) {
            long currentSum = 0;
            boolean isValid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1L << i)) != 0) {
                    currentSum += requirements.get(i);
                    if (currentSum > processingCapacity) {
                        isValid = false;
                        break;
                    }
                }
            }

            if (isValid && currentSum <= processingCapacity && currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        return (int) maxSum;
    }

    // Handle large inputs using a different approach
    private static int handleLargeInput(List<Integer> requirements, int processingCapacity) {
        // Sort in descending order
        requirements.sort(Collections.reverseOrder());

        long maxSum = 0;
        int left = 0;
        int right = requirements.size() - 1;

        // Use two pointers approach
        while (left <= right) {
            long currentSum = requirements.get(left);
            if (currentSum <= processingCapacity && currentSum > maxSum) {
                maxSum = currentSum;
            }

            // Try combinations with smaller numbers
            long tempSum = currentSum;
            for (int i = right; i > left; i--) {
                if (tempSum + requirements.get(i) <= processingCapacity) {
                    tempSum += requirements.get(i);
                    if (tempSum > maxSum) {
                        maxSum = tempSum;
                    }
                }
            }
            left++;
        }

        return (int) maxSum;
    }

    public static void main(String[] args) {
        // Test suite
        runAllTests();
    }

    private static void runAllTests() {
        System.out.println("Running all tests...\n");

        // Basic test cases
        runTest("Basic Test 1", Arrays.asList(7, 6, 9, 11), 25, 24);
        runTest("Basic Test 2", Arrays.asList(2, 9, 7), 15, 11);

        // Edge cases
        runTest("Empty List", new ArrayList<>(), 10, 0);
        runTest("Single Element", Arrays.asList(5), 10, 5);
        runTest("Zero Capacity", Arrays.asList(3, 4, 5), 0, 0);

        // Large numbers
        runTest("Large Numbers", Arrays.asList(1000000000, 999999999), 1000000000, 1000000000);

        // Maximum capacity
        runTest("Max Capacity", Arrays.asList(1, 2, 3), 1000000000, 6);

        // Large input size
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            largeInput.add(i + 1);
        }
        runTest("Large Input Size", largeInput, 100, 99);

        // Exact capacity match
        runTest("Exact Match", Arrays.asList(5, 7, 8), 15, 15);

        // All numbers larger than capacity
        runTest("All Large", Arrays.asList(16, 17, 18), 15, 0);

        // Complex combinations
        runTest("Complex Case", Arrays.asList(3, 7, 9, 11, 13, 15), 30, 30);
    }

    private static void runTest(String testName, List<Integer> requirements,
                                int capacity, int expected) {
        try {
            int result = maximizeCPU(requirements, capacity);
            boolean passed = result == expected;
            System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("  Expected: " + expected + ", Got: " + result);
                System.out.println("  Input: " + requirements);
                System.out.println("  Capacity: " + capacity);
            }
        } catch (Exception e) {
            System.out.println(testName + ": FAIL (Exception)");
            System.out.println("  Error: " + e.getMessage());
        }
        System.out.println();
    }
}
