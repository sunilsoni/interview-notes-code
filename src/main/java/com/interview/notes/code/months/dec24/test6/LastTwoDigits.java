package com.interview.notes.code.months.dec24.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LastTwoDigits {
    // Enhanced solution method
    public static int solve(List<Integer> ar) {
        if (ar == null || ar.isEmpty()) {
            return 0;
        }

        long result = 1; // Using long for intermediate calculations
        for (int num : ar) {
            // Handle zero case explicitly
            if (num == 0) {
                return 0;
            }
            // Ensure positive numbers and take modulo after each multiplication
            result = (result * (num % 100)) % 100;
        }

        // Format result to always show two digits
        return (int) result;
    }

    public static void main(String[] args) {
        // Original test cases
        runTest(Arrays.asList(25, 10), 50, "Basic test 1");
        runTest(Arrays.asList(2, 4, 5), 40, "Basic test 2");

        // Additional edge cases
        runTest(Arrays.asList(0), 0, "Zero input");
        runTest(Arrays.asList(1, 0, 5), 0, "Zero in middle");
        runTest(Arrays.asList(100), 0, "Single 100");
        runTest(Arrays.asList(99, 99), 1, "Two 99s");
        runTest(Arrays.asList(7, 7, 7, 7), 41, "Multiple same numbers");

        // Boundary cases
        runTest(Arrays.asList(1), 1, "Single 1");
        runTest(Arrays.asList(100, 100, 100), 0, "Multiple 100s");

        // Large numbers that might cause overflow
        runTest(Arrays.asList(99, 99, 99), 99, "Three 99s");
        runTest(Arrays.asList(50, 50, 50, 50), 25, "Multiple 50s");

        // Special cases
        runTest(Arrays.asList(11, 11, 11), 31, "Multiple 11s");
        runTest(Arrays.asList(25, 25, 25), 25, "Multiple 25s");
        runTest(Arrays.asList(2, 5, 4, 3), 20, "Sequential numbers");

        // Maximum size test with varying numbers
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            largeInput.add(i % 10 + 1); // Adding numbers 1-10 repeatedly
        }
        runTest(largeInput, 0, "Max size varying numbers");

        // Test with numbers ending in zero
        runTest(Arrays.asList(10, 20, 30), 0, "Numbers ending in zero");

        // Test with prime numbers
        runTest(Arrays.asList(2, 3, 5, 7), 10, "Prime numbers");
    }

    private static void runTest(List<Integer> input, int expected, String testName) {
        int result = solve(input);
        String status = result == expected ? "PASS" : "FAIL";
        System.out.printf("Test %s: %s (Expected: %02d, Got: %02d) Input: %s%n",
                testName, status, expected, result, input);

        // Additional debug information for failing tests
        if (!status.equals("PASS")) {
            System.out.println("Debug: Intermediate steps for " + testName);
            debugCalculation(input);
        }
    }

    // Helper method to debug failing cases
    private static void debugCalculation(List<Integer> input) {
        long result = 1;
        for (int num : input) {
            long prevResult = result;
            result = (result * (num % 100)) % 100;
            System.out.printf("Step: %d * %d = %d (mod 100)%n",
                    prevResult, num, result);
        }
    }
}
