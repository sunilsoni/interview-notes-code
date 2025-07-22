package com.interview.notes.code.year.y2025.july.common.test4;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PivotIndexFinder {
    // Function to find pivot index
    public static int pivotIndex(int[] nums) {
        int totalSum = Arrays.stream(nums).sum();
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    // Simple method to print result of a test case
    private static void runTest(int[] nums, int expected) {
        int actual = pivotIndex(nums);
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Expected: " + expected + ", Actual: " + actual);
        if (actual == expected) {
            System.out.println("Result: PASS\n");
        } else {
            System.out.println("Result: FAIL\n");
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Example cases
        runTest(new int[]{7, 6, 3, 0, 2, 3, 1, 4, 8, 7, -2}, 5);
        runTest(new int[]{1, 7, 3, 6, 5, 6}, 3);

        // Edge cases
        runTest(new int[]{}, -1);               // Empty array
        runTest(new int[]{1}, 0);               // Single element
        runTest(new int[]{0, 0, 0, 0}, 0);      // All zeros
        runTest(new int[]{2, 1, -1}, 0);        // First element pivot

        // Large data case
        int[] large = IntStream.concat(
                IntStream.generate(() -> 1).limit(1000000),
                IntStream.of(0)
        ).toArray();
        runTest(large, 1000000);                // Large array, pivot at last

        // No pivot
        runTest(new int[]{2, 3, 4, 1, 2}, -1);
    }
}
