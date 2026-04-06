package com.interview.notes.code.year.y2026.april.assessments.test7;

import java.util.Arrays; // Needed for array printing, copying, and comparison

public class MoveZerosToEnd { // Class for zero movement logic and testing

    static void moveZerosToEnd(int[] arr) { // Method updates the same array in-place
        int insert = 0; // Points to the next position where a non-zero value should go

        for (int num : arr) { // Go through each element in the array one by one
            if (num != 0) { // Only process non-zero values because zeros must go to the end
                arr[insert++] = num; // Put non-zero value at current insert position, then move insert ahead
            }
        }

        while (insert < arr.length) { // Fill all remaining positions after non-zero values
            arr[insert++] = 0; // Put zero in leftover positions so all zeros move to the end
        }
    }

    static void runTest(String name, int[] input, int[] expected) { // Helper method to run one test case
        int[] actual = Arrays.copyOf(input, input.length); // Copy input so original test data stays unchanged
        moveZerosToEnd(actual); // Apply the zero-moving logic on copied array
        boolean pass = Arrays.equals(actual, expected); // Compare actual result with expected result
        System.out.println( // Print test result in PASS/FAIL format
            name + ": " + (pass ? "PASS" : "FAIL") +
            " | input=" + Arrays.toString(input) +
            " | output=" + Arrays.toString(actual) +
            " | expected=" + Arrays.toString(expected)
        );
    }

    static void largeTest() { // Extra test for large input size
        int n = 100000; // Large array size to test performance
        int[] arr = new int[n]; // Create large array
        int value = 1; // Start non-zero values from 1
        int nonZeroCount = 0; // Track how many non-zero values are inserted

        for (int i = 0; i < n; i++) { // Fill array with zeros at some places and non-zeros at others
            if (i % 10 == 0) { // Every 10th position is zero
                arr[i] = 0; // Put zero
            } else { // All other positions are non-zero
                arr[i] = value++; // Put increasing non-zero value
                nonZeroCount++; // Count non-zero values
            }
        }

        moveZerosToEnd(arr); // Apply logic on large array

        boolean pass = true; // Assume pass unless any issue is found

        for (int i = 0; i < nonZeroCount; i++) { // Check that first part has no zeros
            if (arr[i] == 0) { // If zero appears before expected end area, result is wrong
                pass = false; // Mark failure
                break; // No need to continue checking
            }
        }

        for (int i = nonZeroCount; i < n; i++) { // Check that last part contains only zeros
            if (arr[i] != 0) { // If non-zero appears in zero zone, result is wrong
                pass = false; // Mark failure
                break; // No need to continue checking
            }
        }

        System.out.println("Large Test: " + (pass ? "PASS" : "FAIL")); // Print final large test result
    }

    public static void main(String[] args) { // Main method to run all test cases
        runTest("Test 1", new int[]{0, 1, 3, 4, 0, 5}, new int[]{1, 3, 4, 5, 0, 0}); // Given style test
        runTest("Test 2", new int[]{0, 1, 3, 5, 0, 2}, new int[]{1, 3, 5, 2, 0, 0}); // Another sample
        runTest("Test 3", new int[]{1, 2, 3}, new int[]{1, 2, 3}); // No zeros case
        runTest("Test 4", new int[]{0, 0, 0}, new int[]{0, 0, 0}); // All zeros case
        runTest("Test 5", new int[]{5, 0, 6, 0, 7, 0, 8}, new int[]{5, 6, 7, 8, 0, 0, 0}); // Mixed values
        runTest("Test 6", new int[]{}, new int[]{}); // Empty array case
        runTest("Test 7", new int[]{0}, new int[]{0}); // Single zero
        runTest("Test 8", new int[]{9}, new int[]{9}); // Single non-zero
        largeTest(); // Run large input test
    }
}