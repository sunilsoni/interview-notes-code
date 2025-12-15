package com.interview.notes.code.year.y2025.december.common.test2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubarraySumFinder {
    
    /**
     * Method to check if a contiguous subarray with given sum exists
     * Using Sliding Window approach - works for positive numbers
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static boolean hasSubarrayWithSum(int[] arr, int targetSum) {
        // Handle edge case: empty array cannot have any subarray
        if (arr == null || arr.length == 0) {
            return false;
        }
        
        // Initialize two pointers for sliding window technique
        int windowStart = 0;  // Start index of current window
        int currentSum = 0;   // Running sum of elements in current window
        
        // Iterate through array using windowEnd as the right boundary
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            // Add current element to our running sum (expand window)
            currentSum = currentSum + arr[windowEnd];
            
            // Shrink window from left if sum exceeds target
            // This works because all numbers are positive
            while (currentSum > targetSum && windowStart <= windowEnd) {
                // Remove leftmost element from window
                currentSum = currentSum - arr[windowStart];
                // Move start pointer to right (shrink window)
                windowStart = windowStart + 1;
            }
            
            // Check if current window sum equals target
            if (currentSum == targetSum) {
                // Found a valid subarray, print its details
                System.out.println("    Subarray found from index " + windowStart + " to " + windowEnd);
                return true;
            }
        }
        
        // No subarray found with the target sum
        return false;
    }
    
    /**
     * Alternative Method using Prefix Sum with HashMap
     * Works for both positive and negative numbers
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static boolean hasSubarrayWithSumPrefixMethod(int[] arr, int targetSum) {
        // Handle edge case: empty array
        if (arr == null || arr.length == 0) {
            return false;
        }
        
        // HashMap to store prefix sums we have seen so far
        // Key: prefix sum value, Value: index where this sum occurred
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        
        // Initialize with 0 sum at index -1 (handles case when subarray starts from index 0)
        prefixSumMap.put(0, -1);
        
        // Variable to track cumulative sum from start
        int cumulativeSum = 0;
        
        // Process each element in the array
        for (int i = 0; i < arr.length; i++) {
            // Add current element to cumulative sum
            cumulativeSum = cumulativeSum + arr[i];
            
            // Calculate what prefix sum we need to find
            // If cumulativeSum - targetSum exists in map, we found our subarray
            int requiredPrefixSum = cumulativeSum - targetSum;
            
            // Check if this required prefix sum exists
            if (prefixSumMap.containsKey(requiredPrefixSum)) {
                // Get the index where required prefix sum occurred
                int startIndex = prefixSumMap.get(requiredPrefixSum) + 1;
                System.out.println("    Subarray found from index " + startIndex + " to " + i);
                return true;
            }
            
            // Store current prefix sum in map for future lookups
            prefixSumMap.put(cumulativeSum, i);
        }
        
        // No valid subarray found
        return false;
    }
    
    /**
     * Test helper method to verify expected vs actual result
     */
    public static void runTestCase(int testNumber, int[] arr, int target, boolean expected) {
        // Print test case information
        System.out.println("===========================================");
        System.out.println("Test Case " + testNumber + ":");
        System.out.println("  Array: " + Arrays.toString(arr));
        System.out.println("  Target Sum: " + target);
        System.out.println("  Expected: " + expected);
        
        // Run the sliding window method
        boolean actualResult = hasSubarrayWithSum(arr, target);
        
        // Print actual result
        System.out.println("  Actual: " + actualResult);
        
        // Compare and print PASS or FAIL
        if (actualResult == expected) {
            System.out.println("  Result: *** PASS ***");
        } else {
            System.out.println("  Result: *** FAIL ***");
        }
        System.out.println();
    }
    
    /**
     * Main method to run all test cases
     */
    public static void main(String[] args) {
        System.out.println("SUBARRAY SUM FINDER - TEST RESULTS");
        System.out.println("===================================\n");
        
        // Test Case 1: Original problem - [8,10,15,7,6,2] with target 17
        // Checking manually: 8=8, 8+10=18, 10=10, 10+15=25, 15=15, 15+7=22
        // 7=7, 7+6=13, 7+6+2=15, 6=6, 6+2=8, 2=2
        // No contiguous subarray sums to 17
        int[] arr1 = {8, 10, 15, 7, 6, 2};
        runTestCase(1, arr1, 17, false);
        
        // Test Case 2: Simple case where subarray exists
        // Array [1, 4, 20, 3, 10, 5] with target 33
        // 20+3+10 = 33 (indices 2 to 4)
        int[] arr2 = {1, 4, 20, 3, 10, 5};
        runTestCase(2, arr2, 33, true);
        
        // Test Case 3: Single element equals target
        // Array [5, 7, 17, 3] with target 17
        int[] arr3 = {5, 7, 17, 3};
        runTestCase(3, arr3, 17, true);
        
        // Test Case 4: First two elements equal target
        // Array [8, 9, 5, 7] with target 17
        // 8+9 = 17 (indices 0 to 1)
        int[] arr4 = {8, 9, 5, 7};
        runTestCase(4, arr4, 17, true);
        
        // Test Case 5: Last two elements equal target
        // Array [5, 6, 10, 7] with target 17
        // 10+7 = 17 (indices 2 to 3)
        int[] arr5 = {5, 6, 10, 7};
        runTestCase(5, arr5, 17, true);
        
        // Test Case 6: Empty array
        // Should return false as no subarray possible
        int[] arr6 = {};
        runTestCase(6, arr6, 17, false);
        
        // Test Case 7: Single element not equal to target
        int[] arr7 = {5};
        runTestCase(7, arr7, 17, false);
        
        // Test Case 8: Single element equals target
        int[] arr8 = {17};
        runTestCase(8, arr8, 17, true);
        
        // Test Case 9: Entire array sums to target
        // 2+5+10 = 17
        int[] arr9 = {2, 5, 10};
        runTestCase(9, arr9, 17, true);
        
        // Test Case 10: Target is zero (edge case)
        // No positive subarray can sum to zero
        int[] arr10 = {1, 2, 3, 4, 5};
        runTestCase(10, arr10, 0, false);
        
        // ============ LARGE DATA TEST CASES ============
        System.out.println("\n========== LARGE DATA TEST CASES ==========\n");
        
        // Test Case 11: Large array with target at beginning
        int[] arr11 = generateLargeArray(100000);
        arr11[0] = 17;  // Set first element to target
        long startTime11 = System.currentTimeMillis();
        runTestCase(11, arr11, 17, true);
        long endTime11 = System.currentTimeMillis();
        System.out.println("  Execution Time: " + (endTime11 - startTime11) + " ms\n");
        
        // Test Case 12: Large array with target at end
        int[] arr12 = generateLargeArray(100000);
        arr12[99998] = 8;   // Second last element
        arr12[99999] = 9;   // Last element, 8+9=17
        long startTime12 = System.currentTimeMillis();
        runTestCase(12, arr12, 17, true);
        long endTime12 = System.currentTimeMillis();
        System.out.println("  Execution Time: " + (endTime12 - startTime12) + " ms\n");
        
        // Test Case 13: Very large array with no valid subarray
        int[] arr13 = new int[500000];
        // Fill with value 1, so sums are 1,2,3,4...
        // Target 17 exists as sum of first 17 elements
        Arrays.fill(arr13, 1);
        long startTime13 = System.currentTimeMillis();
        runTestCase(13, arr13, 17, true);
        long endTime13 = System.currentTimeMillis();
        System.out.println("  Execution Time: " + (endTime13 - startTime13) + " ms\n");
        
        // Test Case 14: Large array where target doesn't exist
        int[] arr14 = new int[100000];
        // Fill with 100, smallest sum is 100
        Arrays.fill(arr14, 100);
        long startTime14 = System.currentTimeMillis();
        runTestCase(14, arr14, 17, false);
        long endTime14 = System.currentTimeMillis();
        System.out.println("  Execution Time: " + (endTime14 - startTime14) + " ms\n");
        
        // Print summary
        System.out.println("===========================================");
        System.out.println("ALL TEST CASES COMPLETED!");
        System.out.println("===========================================");
    }
    
    /**
     * Helper method to generate large array for performance testing
     * Creates array filled with small values to test algorithm efficiency
     */
    public static int[] generateLargeArray(int size) {
        // Create array of specified size
        int[] largeArray = new int[size];
        
        // Fill with small incremental values
        for (int i = 0; i < size; i++) {
            // Use modulo to keep values small (0-9)
            largeArray[i] = (i % 10);
        }
        
        // Return the generated array
        return largeArray;
    }
}