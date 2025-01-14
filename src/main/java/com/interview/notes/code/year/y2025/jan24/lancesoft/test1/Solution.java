package com.interview.notes.code.year.y2025.jan24.lancesoft.test1;

import java.util.Arrays;
import java.util.List;

class Solution {
    public static int solve(List<Integer> ar) {
        // Edge case check
        if (ar == null || ar.isEmpty()) {
            return 0;
        }

        long product = 1L; // Using long to handle larger intermediate results

        // Calculate product
        for (int num : ar) {
            product = (product * num) % 100; // Keep only last two digits during multiplication
        }

        return (int) product;
    }

    public static void main(String[] args) {
        // Test cases
        testCase(Arrays.asList(25, 10), 50, "Basic test case 1");
        testCase(Arrays.asList(2, 4, 5), 40, "Basic test case 2");
        testCase(Arrays.asList(99, 99), 1, "Large numbers");
        testCase(Arrays.asList(1, 1, 1, 1, 1), 1, "Multiple ones");
        testCase(Arrays.asList(10, 10, 10), 0, "Multiple tens");
        testCase(Arrays.asList(100), 0, "Single hundred");
    }

    private static void testCase(List<Integer> input, int expected, String testName) {
        int result = solve(input);
        System.out.printf("Test %s: %s (Expected=%02d, Got=%02d)%n",
                testName,
                result == expected ? "PASS" : "FAIL",
                expected,
                result);
    }
}