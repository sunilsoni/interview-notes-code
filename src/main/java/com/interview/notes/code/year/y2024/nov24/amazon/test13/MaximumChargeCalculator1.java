package com.interview.notes.code.year.y2024.nov24.amazon.test13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MaximumChargeCalculator1 {

    // Optimized solution with O(n) complexity
    public static long getMaximumCharge(List<Integer> charge) {
        if (charge == null || charge.isEmpty()) {
            return 0;
        }

        int n = charge.size();
        if (n == 1) {
            return charge.get(0);
        }

        // Find the maximum element
        long maxCharge = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxCharge = Math.max(maxCharge, charge.get(i));
        }

        return maxCharge;
    }

    // Test method
    public static void main(String[] args) {
        runAllTests();
        runPerformanceTest();
    }

    private static void runAllTests() {
        // Basic test cases
        testCase(Arrays.asList(-2, 4, 9, 1, -1), 9, "Basic Test 1");
        testCase(Arrays.asList(-1, 3, 2), 3, "Basic Test 2");
        testCase(Arrays.asList(-3, 1, 4, -1, 5, -9), 5, "Basic Test 3");

        // Edge cases
        testCase(List.of(1), 1, "Single Element");
        testCase(Arrays.asList(-1, -1), -1, "All Negative");
        testCase(Arrays.asList(0, 0, 0), 0, "All Zeros");
        testCase(Arrays.asList(Integer.MAX_VALUE, -1, -2), Integer.MAX_VALUE, "Max Integer");
        testCase(Arrays.asList(Integer.MIN_VALUE, 1, 2), 2, "Min Integer");
    }

    private static void runPerformanceTest() {
        System.out.println("\nRunning Performance Test...");

        // Generate large input (200,000 elements)
        List<Integer> largeInput = new ArrayList<>();
        Random rand = new Random(42);
        for (int i = 0; i < 200_000; i++) {
            largeInput.add(rand.nextInt(2_000_000_000) - 1_000_000_000);
        }

        // Measure execution time
        long startTime = System.nanoTime();
        long result = getMaximumCharge(largeInput);
        long endTime = System.nanoTime();

        double executionTimeMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Large input (n=%d) processed in %.2f ms\n",
                largeInput.size(), executionTimeMs);
        System.out.println("Result: " + result);
    }

    private static void testCase(List<Integer> input, long expected, String testName) {
        try {
            long startTime = System.nanoTime();
            long result = getMaximumCharge(input);
            long endTime = System.nanoTime();

            boolean passed = result == expected;
            System.out.printf("Test '%s': %s (%.3f ms)\n",
                    testName,
                    passed ? "PASSED" : "FAILED",
                    (endTime - startTime) / 1_000_000.0);

            if (!passed) {
                System.out.printf("  Expected: %d\n  Got: %d\n", expected, result);
            }
        } catch (Exception e) {
            System.out.printf("Test '%s': FAILED with exception\n", testName);
            e.printStackTrace();
        }
    }
}