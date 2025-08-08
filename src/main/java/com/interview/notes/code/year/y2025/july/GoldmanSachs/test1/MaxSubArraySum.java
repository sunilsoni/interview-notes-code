package com.interview.notes.code.year.y2025.july.GoldmanSachs.test1;

public class MaxSubArraySum {

    // Method to find maximum sum of contiguous subarray
    public static int findMaxSubArraySum(int[] nums) {
        // Handle edge case of empty array
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Initialize variables to track current and maximum sum
        int maxSoFar = nums[0];    // Stores the maximum sum found so far
        int currMax = nums[0];     // Stores the maximum sum ending at current position

        // Iterate through array starting from second element
        for (int i = 1; i < nums.length; i++) {
            // Either extend previous subarray or start new subarray from current element
            currMax = Math.max(nums[i], currMax + nums[i]);
            // Update maximum sum if current sum is larger
            maxSoFar = Math.max(maxSoFar, currMax);
        }

        return maxSoFar;
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[] test1 = {-2, 4, -1, 4, 7};
        System.out.println("Test Case 1: Expected: 14, Got: " +
                findMaxSubArraySum(test1) +
                (findMaxSubArraySum(test1) == 14 ? " (PASS)" : " (FAIL)"));

        // Test Case 2: All negative numbers
        int[] test2 = {-2, -3, -4, -1, -5};
        System.out.println("Test Case 2: Expected: -1, Got: " +
                findMaxSubArraySum(test2) +
                (findMaxSubArraySum(test2) == -1 ? " (PASS)" : " (FAIL)"));

        // Test Case 3: All positive numbers
        int[] test3 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 3: Expected: 15, Got: " +
                findMaxSubArraySum(test3) +
                (findMaxSubArraySum(test3) == 15 ? " (PASS)" : " (FAIL)"));

        // Test Case 4: Mixed numbers with larger dataset
        int[] test4 = new int[1000];
        for (int i = 0; i < 1000; i++) {
            test4[i] = (i % 2 == 0) ? i : -i;
        }
        System.out.println("Test Case 4 (Large Dataset): Result: " +
                findMaxSubArraySum(test4));
    }
}
