package com.interview.notes.code.year.y2025.march.meta.test5;

import java.util.Arrays;

public class SequenceSum {
    // Main method to test the solution
    public static void main(String[] args) {
        // Test cases
        test(new int[]{1, 3, 1, 4, 231, 8}, 8);  // Should return true
        test(new int[]{1, 3, 1, 4, 231, 7}, 7);  // Should return false
        test(new int[]{}, 0);                     // Edge case: empty array
        test(new int[]{5}, 5);                    // Edge case: single element
    }

    // Core logic: Uses sliding window technique
    public static boolean hasSequenceSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        
        int currentSum = 0;
        int start = 0;
        
        // Iterate through the array using sliding window
        for (int end = 0; end < nums.length; end++) {
            currentSum += nums[end];
            
            // Shrink window if sum exceeds target
            while (currentSum > target && start < end) {
                currentSum -= nums[start];
                start++;
            }
            
            // Check if we found the target sum
            if (currentSum == target) return true;
        }
        return false;
    }

    // Test helper method
    private static void test(int[] arr, int target) {
        boolean result = hasSequenceSum(arr, target);
        System.out.printf("Array: %s, Target: %d, Result: %b%n", 
            Arrays.toString(arr), target, result);
    }
}
