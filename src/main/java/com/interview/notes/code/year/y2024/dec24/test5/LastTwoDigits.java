package com.interview.notes.code.year.y2024.dec24.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LastTwoDigits {
    // Main solution method
    public static int solve(List<Integer> ar) {
        if (ar == null || ar.isEmpty()) {
            return 0;
        }

        int result = 1;
        // Calculate product and keep only last two digits in each step
        for (int num : ar) {
            result = (result * num) % 100;
        }

        return result;
    }

    // Test method
    public static void main(String[] args) {
        // Test cases
        runTest(Arrays.asList(25, 10), 50, "Basic test case 1");
        runTest(Arrays.asList(2, 4, 5), 40, "Basic test case 2");

        // Edge cases
        runTest(List.of(1), 1, "Single element");
        runTest(Arrays.asList(100, 100), 0, "Large numbers");
        runTest(Arrays.asList(99, 99), 1, "Numbers causing overflow");

        // Large input test
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            largeInput.add(99);
        }
        runTest(largeInput, 1, "Maximum size input");

        // Boundary test
        runTest(Arrays.asList(1, 1, 1), 1, "Minimum values");
        runTest(Arrays.asList(100, 100, 100), 0, "Maximum values");
    }

    // Helper method to run tests
    private static void runTest(List<Integer> input, int expected, String testName) {
        int result = solve(input);
        String status = result == expected ? "PASS" : "FAIL";
        System.out.printf("Test %s: %s (Expected: %02d, Got: %02d)%n",
                testName, status, expected, result);
    }
}
