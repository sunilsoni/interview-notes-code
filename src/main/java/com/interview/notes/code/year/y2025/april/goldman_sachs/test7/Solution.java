package com.interview.notes.code.year.y2025.april.goldman_sachs.test7;

class Solution {
    /**
     * Brute Force Approach:
     * For each element, find the maximum height on left and right side
     * The water stored at current position = min(maxLeft, maxRight) - currentHeight
     * Time Complexity: O(n²) - For each element, we traverse left and right
     * Space Complexity: O(1) - Only using constant extra space
     */
    public static int computeSnowpackBruteForce(int[] arr) {
        if (arr == null || arr.length < 3) return 0;
        
        int totalSnow = 0;
        
        // Skip first and last element as they can't store water
        for (int i = 1; i < arr.length - 1; i++) {
            int maxLeft = 0;
            int maxRight = 0;
            
            // Find maximum height on left
            for (int j = 0; j < i; j++) {
                maxLeft = Math.max(maxLeft, arr[j]);
            }
            
            // Find maximum height on right
            for (int j = i + 1; j < arr.length; j++) {
                maxRight = Math.max(maxRight, arr[j]);
            }
            
            // Calculate water at current position
            int minHeight = Math.min(maxLeft, maxRight);
            if (minHeight > arr[i]) {
                totalSnow += minHeight - arr[i];
            }
        }
        return totalSnow;
    }

    /**
     * Optimized Two-Pointer Approach:
     * Use two pointers to track maximum heights from left and right
     * Time Complexity: O(n) - Single pass through array
     * Space Complexity: O(1) - Constant extra space
     */
    public static int computeSnowpack(int[] arr) {
        if (arr == null || arr.length < 3) return 0;
        
        int left = 0;
        int right = arr.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int totalSnow = 0;
        
        while (left < right) {
            // If left wall is smaller, process left side
            if (arr[left] <= arr[right]) {
                if (arr[left] >= maxLeft) {
                    maxLeft = arr[left];
                } else {
                    totalSnow += maxLeft - arr[left];
                }
                left++;
            } 
            // If right wall is smaller, process right side
            else {
                if (arr[right] >= maxRight) {
                    maxRight = arr[right];
                } else {
                    totalSnow += maxRight - arr[right];
                }
                right--;
            }
        }
        return totalSnow;
    }

    /**
     * Comprehensive test method covering various scenarios
     */
    public static void runTests() {
        // Test cases
        int[][] testCases = {
            {0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0},  // Regular case
            {2, 0, 2},                              // Simple valley
            {3, 0, 0, 2, 0, 4},                     // Multiple valleys
            {0, 1, 0},                              // Minimal case
            {2, 1, 2},                              // Small hill
            {},                                      // Empty array
            {1},                                     // Single element
            {5, 4, 3, 2, 1},                        // Decreasing
            {1, 2, 3, 4, 5},                        // Increasing
            new int[10000]                          // Large array
        };
        
        int[] expectedResults = {13, 2, 9, 0, 1, 0, 0, 0, 0, 0};
        
        // Run tests
        for (int i = 0; i < testCases.length; i++) {
            int result = computeSnowpack(testCases[i]);
            int expected = i < expectedResults.length ? expectedResults[i] : 0;
            
            System.out.printf("Test Case %d: %s\n", 
                i + 1,
                result == expected ? "PASS" : "FAIL"
            );
            
            if (result != expected) {
                System.out.printf("Expected: %d, Got: %d\n", expected, result);
            }
        }
    }

    public static void main(String[] args) {
        runTests();
    }
}
