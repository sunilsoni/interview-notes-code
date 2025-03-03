package com.interview.notes.code.year.y2025.feb.common.test10;

import java.util.Arrays;
import java.util.Comparator;

public class SecondHighestNumber {

    // Main solution method
    public static Integer findSecondHighest(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return null;  // Invalid case
        }

        // Using distinct to handle duplicates
        return Arrays.stream(numbers)
                .boxed()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);
    }

    // Test method
    public static void testCase(int[] input, Integer expected) {
        System.out.println("Input: " + Arrays.toString(input));
        Integer result = findSecondHighest(input);
        System.out.println("Result: " + result);
        System.out.println("Expected: " + expected);

        boolean passed = (result == null && expected == null) ||
                (result != null && result.equals(expected));
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
        System.out.println("------------------------");
    }

    public static void main(String[] args) {
        // Test Case 1: Normal array
        testCase(
                new int[]{5, 2, 8, 1, 9},
                8
        );

        // Test Case 2: Array with duplicates
        testCase(
                new int[]{10, 10, 9, 8, 8},
                9
        );

        // Test Case 3: Empty array
        testCase(
                new int[]{},
                null
        );

        // Test Case 4: Single element
        testCase(
                new int[]{1},
                null
        );

        // Test Case 5: All same elements
        testCase(
                new int[]{5, 5, 5, 5},
                null
        );

        // Test Case 6: Negative numbers
        testCase(
                new int[]{-1, -5, -2, -8},
                -2
        );

        // Test Case 7: Large numbers
        testCase(
                new int[]{1000000, 999999, 999998},
                999999
        );
    }
}
