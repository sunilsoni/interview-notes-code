package com.interview.notes.code.year.y2025.april.meta.test1;

import java.util.Arrays;

public class FirstGreaterElement {

    // Method to find first element greater than K using brute force
    public static int findFirstGreater(int[] nums, int k) {
        // Handle edge case: empty array
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // Iterate through array linearly
        for (int i = 0; i < nums.length; i++) {
            // Return first index where element is greater than k
            if (nums[i] > k) {
                return i;
            }
        }

        // If no element found, return array length
        return nums.length;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{1, 3, 5, 6}, 4, 2);
        runTest(new int[]{1, 3, 5, 6}, 2, 1);
        runTest(new int[]{1, 3, 5, 6}, 7, 4);
        runTest(new int[]{}, 1, -1);
        runTest(new int[]{1}, 0, 0);

        // Large data test
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i * 2;
        }
        runTest(largeArray, 500, 251);
    }

    // Helper method to run tests
    private static void runTest(int[] arr, int k, int expected) {
        int result = findFirstGreater(arr, k);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("K: " + k);
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Test: " + (result == expected ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }
}
