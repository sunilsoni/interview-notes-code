package com.interview.notes.code.year.y2025.march.caspex.test12;

import java.util.Arrays;

public class MinimalExample {
    // Simple example method: sum of even integers using Java 8 Streams
    public static int solve(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .sum();
    }

    public static void main(String[] args) {
        // Basic PASS/FAIL tests
        int[] test1 = {2, 4, 5};
        int expected1 = 6;  // 2 + 4
        checkTest("Test1", solve(test1), expected1);

        int[] test2 = {1, 3, 5};
        int expected2 = 0;  // no even numbers
        checkTest("Test2", solve(test2), expected2);

        // Large data test (illustrative)
        int[] largeTest = new int[100000];
        for (int i = 0; i < largeTest.length; i++) {
            largeTest[i] = i;
        }
        // For a large array, just check it runs without error and 
        // optionally verify a known sum of even numbers
        int resultLarge = solve(largeTest);
        System.out.println("LargeTest result: " + resultLarge);
    }

    private static void checkTest(String testName, int actual, int expected) {
        if (actual == expected) {
            System.out.println(testName + " PASS");
        } else {
            System.out.println(testName + " FAIL (Expected: " + expected + ", Got: " + actual + ")");
        }
    }
}
