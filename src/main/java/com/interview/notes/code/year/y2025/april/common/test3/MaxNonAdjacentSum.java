package com.interview.notes.code.year.y2025.april.common.test3;

import java.util.stream.IntStream;

public class MaxNonAdjacentSum {

    public static int findMaxSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int include = nums[0], exclude = 0;

        for (int i = 1; i < nums.length; i++) {
            int newExclude = Math.max(include, exclude);
            include = exclude + nums[i];
            exclude = newExclude;
        }

        return Math.max(include, exclude);
    }

    // Simple main method for testing
    public static void main(String[] args) {
        runTest(new int[]{1, 3, 5, 1}, 6);          // provided ✅
        runTest(new int[]{1, 3, 5, 1, 1}, 7);       // additional ✅
        runTest(new int[]{3, 1, 1, 5, 1, 1}, 9);    // additional ✅

        // Edge cases
        runTest(new int[]{}, 0);                    // edge ✅
        runTest(new int[]{7}, 7);                   // single element ✅
        runTest(new int[]{2, 4}, 4);                // two elements ✅
        runTest(new int[]{5, 5, 10, 100, 10, 5}, 110); // varied elements ✅

        // Performance large input case
        int[] largeArray = IntStream.generate(() -> 1).limit(1_000_000).toArray();
        runTest(largeArray, 500_000);               // large input ✅
    }

    private static void runTest(int[] nums, int expected) {
        int result = findMaxSum(nums);
        if (result == expected) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL - Expected: " + expected + ", Got: " + result);
        }
    }
}
