package com.interview.notes.code.year.y2025.march.common.test1;

import java.util.Arrays;

public class TwoSumSimple {

    // Approach 1: Brute Force Method
    // Time Complexity: O(n²)
    // Space Complexity: O(1)
    public static int[] findTwoSumBruteForce(int[] nums, int target) {
        // Check each pair of numbers
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{}; // No solution found
    }

    // Approach 2: Two-Pointer Method (requires sorted array)
    // Time Complexity: O(n log n) due to sorting
    // Space Complexity: O(1)
    public static int[] findTwoSumTwoPointer(int[] nums, int target) {
        // Create copy of array with indices
        int[][] numWithIndex = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            numWithIndex[i] = new int[]{nums[i], i};
        }

        // Sort based on values
        Arrays.sort(numWithIndex, (a, b) -> a[0] - b[0]);

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = numWithIndex[left][0] + numWithIndex[right][0];

            if (sum == target) {
                return new int[]{numWithIndex[left][1], numWithIndex[right][1]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }

    // Test method
    public static void testTwoSum(int[] nums, int target) {
        System.out.println("\nInput Array: " + Arrays.toString(nums));
        System.out.println("Target: " + target);

        // Test Brute Force
        int[] resultBrute = findTwoSumBruteForce(nums, target);
        System.out.println("Brute Force Result: " + Arrays.toString(resultBrute));
        if (resultBrute.length == 2) {
            System.out.println("Found numbers: " + nums[resultBrute[0]] + " + "
                    + nums[resultBrute[1]] + " = " + target);
        }

        // Test Two Pointer
        int[] resultTwoPointer = findTwoSumTwoPointer(nums, target);
        System.out.println("Two Pointer Result: " + Arrays.toString(resultTwoPointer));
        if (resultTwoPointer.length == 2) {
            System.out.println("Found numbers: " + nums[resultTwoPointer[0]] + " + "
                    + nums[resultTwoPointer[1]] + " = " + target);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        testTwoSum(new int[]{2, 7, 11, 15}, 9);

        // Test Case 2: Numbers not in sequence
        testTwoSum(new int[]{3, 2, 4}, 6);

        // Test Case 3: Same number twice
        testTwoSum(new int[]{3, 3}, 6);

        // Test Case 4: Negative numbers
        testTwoSum(new int[]{-1, -2, -3, -4}, -5);

        // Test Case 5: No solution
        testTwoSum(new int[]{1, 2, 3}, 7);
    }
}
