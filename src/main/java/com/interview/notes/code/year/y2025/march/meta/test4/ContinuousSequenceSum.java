package com.interview.notes.code.year.y2025.march.meta.test4;

import java.util.stream.IntStream;

public class ContinuousSequenceSum {

    // Main method to demonstrate the solution with test cases
    public static void main(String[] args) {
        // Test cases from the problem statement
        System.out.println("Test Case 1: " + hasContiguousSum(new int[]{1, 3, 1, 4, 231, 8}, 8));  // Should return true (3 + 1 + 4 = 8)
        System.out.println("Test Case 2: " + hasContiguousSum(new int[]{1, 3, 1, 4, 231, 7}, 7));  // Should return false

        // Additional test cases for edge cases and validation
        System.out.println("Empty Array: " + hasContiguousSum(new int[]{}, 5));  // Should return false
        System.out.println("Single Element Matching: " + hasContiguousSum(new int[]{5}, 5));  // Should return true
        System.out.println("Single Element Not Matching: " + hasContiguousSum(new int[]{4}, 5));  // Should return false
        System.out.println("Negative Numbers: " + hasContiguousSum(new int[]{1, -2, 3, 4, -5}, 5));  // Should return true (1 + -2 + 3 + 4 = 6 - 1 = 5)
        System.out.println("Zero Target: " + hasContiguousSum(new int[]{1, 2, 3, 0, 4}, 0));  // Should return true (0 by itself)
        System.out.println("Large Array: " + hasContiguousSum(generateLargeArray(100000), 50000));  // Testing performance with large array
    }

    /**
     * Checks if there's a continuous sequence in the array that sums to target
     *
     * @param nums   array of integers
     * @param target the sum to find
     * @return true if a continuous sequence exists that sums to target, false otherwise
     */
    public static boolean hasContiguousSum(int[] nums, int target) {
        // Edge case: empty array can't sum to anything (unless target is 0, but that's ambiguous)
        if (nums.length == 0) {
            return false;
        }

        // Using the sliding window approach
        int start = 0;  // Start index of the window
        int currentSum = 0;  // Sum of elements in the current window

        for (int end = 0; end < nums.length; end++) {
            // Add the current element to our running sum
            currentSum += nums[end];

            // Shrink the window from the left while our sum exceeds the target
            while (currentSum > target && start < end) {
                currentSum -= nums[start];
                start++;
            }

            // Check if we've found a sequence that sums to target
            if (currentSum == target) {
                return true;
            }
        }

        // No sequence found
        return false;
    }

    /**
     * Helper method to generate a large array for performance testing
     *
     * @param size the size of array to generate
     * @return an array of the specified size with random values
     */
    private static int[] generateLargeArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100) - 50;  // Random values between -50 and 49
        }
        return array;
    }

    /**
     * More efficient Java 8 Streams implementation - for comparison
     * Note: For this specific problem, the sliding window approach is generally more efficient
     */
    public static boolean hasContiguousSumStream(int[] nums, int target) {
        if (nums.length == 0) return false;

        // For each possible starting position
        return IntStream.range(0, nums.length)
                .anyMatch(start -> {
                    int sum = 0;
                    // Try all possible ending positions
                    for (int end = start; end < nums.length; end++) {
                        sum += nums[end];
                        if (sum == target) return true;
                    }
                    return false;
                });
    }
}
