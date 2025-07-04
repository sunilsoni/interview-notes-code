package com.interview.notes.code.year.y2025.June.common.test16;

import java.util.Arrays;

/*

**Problem Statement:**

Given an array of characters:

1. Replace every occurrence of `'x'` with **two `'a'`** characters.
2. Delete **every occurrence of `'y'`** from the array.

---

**Examples:**

**Input:** `['x', 'x', 'y', 'z', 'x']`
**Output:** `['a', 'a', 'a', 'a', 'z', 'a', 'a']`

**Input:** `['x', 'y', 'y', 'x', 'y']`
**Output:** `['a', 'a', 'a', 'a']`

---

 */
public class CharArrayTransformer {

    // Main method to transform array and handle test cases
    public static char[] transformArray(char[] input) {
        // If input is null or empty, return empty array
        if (input == null || input.length == 0) {
            return new char[0];
        }

        // First pass: Count final array size needed
        // Each 'x' needs 2 spaces, 'y' needs 0 spaces, others need 1 space
        int finalSize = 0;
        for (char c : input) {
            if (c == 'x') {
                finalSize += 2;  // 'x' becomes 'aa'
            } else if (c != 'y') {
                finalSize += 1;  // Keep non-'y' characters
            }
        }

        // Create result array with calculated size
        char[] result = new char[finalSize];

        // Second pass: Fill the result array
        int index = 0;
        for (char c : input) {
            if (c == 'x') {
                result[index++] = 'a';  // First 'a'
                result[index++] = 'a';  // Second 'a'
            } else if (c != 'y') {
                result[index++] = c;    // Copy non-'y' character
            }
        }

        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        runTest(new char[]{'x', 'x', 'y', 'z', 'x'},
                new char[]{'a', 'a', 'a', 'a', 'z', 'a', 'a'},
                "Basic Test Case 1");

        runTest(new char[]{'x', 'y', 'y', 'x', 'y'},
                new char[]{'a', 'a', 'a', 'a'},
                "Basic Test Case 2");

        // Edge cases
        runTest(new char[]{},
                new char[]{},
                "Empty Array");

        runTest(null,
                new char[]{},
                "Null Array");

        // Large input test
        char[] largeInput = new char[10000];
        Arrays.fill(largeInput, 'x');
        char[] expectedLarge = new char[20000];
        Arrays.fill(expectedLarge, 'a');
        runTest(largeInput, expectedLarge, "Large Input Test");
    }

    // Helper method to run tests and print results
    private static void runTest(char[] input, char[] expected, String testName) {
        char[] result = transformArray(input);
        boolean passed = Arrays.equals(result, expected);

        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Got: " + Arrays.toString(result));
        }
    }
}
