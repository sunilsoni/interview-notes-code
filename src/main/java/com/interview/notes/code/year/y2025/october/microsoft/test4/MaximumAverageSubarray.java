package com.interview.notes.code.year.y2025.october.microsoft.test4;

public class MaximumAverageSubarray {

    // Main method to test our solution with different test cases
    public static void main(String[] args) {
        // Test Case 1: Basic positive numbers
        testMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4);

        // Test Case 2: All negative numbers
        testMaxAverage(new int[]{-1, -2, -3, -4, -5}, 3);

        // Test Case 3: Single element subarray
        testMaxAverage(new int[]{5}, 1);

        // Test Case 4: Large numbers
        testMaxAverage(new int[]{10000, 10001, 10002, 10003}, 2);

        // Test Case 5: Zeros and negative numbers
        testMaxAverage(new int[]{0, 0, 0, -1, 0, 0}, 3);
    }

    // Method to find maximum average of subarray of size k
    public static double findMaxAverage(int[] nums, int k) {
        // Handle invalid input
        if (nums == null || nums.length < k) {
            return 0.0;
        }

        // Calculate sum of first k elements
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        // Initialize maxSum with the sum of first window
        double maxSum = sum;

        // Slide the window and update maxSum if we find a larger sum
        for (int i = k; i < nums.length; i++) {
            // Add next element and remove first element of previous window
            sum = sum + nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }

        // Return the maximum average
        return maxSum / k;
    }

    // Helper method to test and print results
    private static void testMaxAverage(int[] nums, int k) {
        double result = findMaxAverage(nums, k);
        System.out.println("Test Case: " + java.util.Arrays.toString(nums));
        System.out.println("K = " + k);
        System.out.println("Maximum Average = " + result);
        System.out.println("------------------------");
    }
}
