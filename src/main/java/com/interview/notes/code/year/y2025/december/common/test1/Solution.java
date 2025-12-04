package com.interview.notes.code.year.y2025.december.common.test1;

import java.util.Arrays;

class Solution {
    
    /**
     * Calculates how many units of snow can be captured between hills.
     * 
     * Approach:
     * 1. Find max height to the LEFT of each position
     * 2. Find max height to the RIGHT of each position
     * 3. Snow at each position = min(maxLeft, maxRight) - groundHeight
     * 
     * @param groundHeightAt - array of hill heights
     * @return total units of snow that can be captured
     */
    public static int computeSnowpack(int[] groundHeightAt) {
        
        // Handle empty or null array
        if (groundHeightAt == null || groundHeightAt.length == 0) {
            return 0;
        }
        
        // Get the length of array
        int n = groundHeightAt.length;
        
        // Array to store max height to the LEFT of each position
        int[] maxHeightToLeftOf = new int[n];
        
        // Array to store max height to the RIGHT of each position
        int[] maxHeightToRightOf = new int[n];
        
        // Variable to track total snow captured
        int total = 0;
        
        // ========== STEP 1: Fill maxHeightToLeftOf array ==========
        // Go from LEFT to RIGHT
        // Track the maximum height seen so far
        int currentMaxLeftHeight = 0;
        
        for (int i = 0; i < n; i++) {
            
            // Update max if current ground is higher
            currentMaxLeftHeight = Math.max(currentMaxLeftHeight, groundHeightAt[i]);
            
            // Store max height to left (including current position)
            maxHeightToLeftOf[i] = currentMaxLeftHeight;
        }
        
        // ========== STEP 2: Fill maxHeightToRightOf array ==========
        // Go from RIGHT to LEFT
        // Track the maximum height seen so far
        int currentMaxRightHeight = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            
            // Update max if current ground is higher
            currentMaxRightHeight = Math.max(currentMaxRightHeight, groundHeightAt[i]);
            
            // Store max height to right (including current position)
            maxHeightToRightOf[i] = currentMaxRightHeight;
        }
        
        // ========== STEP 3: Calculate snow at each position ==========
        for (int i = 0; i < n; i++) {
            
            // Snow level = minimum of left and right max heights
            // (water can only rise to the lower wall)
            int snowHeight = Math.min(maxHeightToLeftOf[i], maxHeightToRightOf[i]);
            
            // Snow captured = snowHeight - ground height (if positive)
            if (snowHeight > groundHeightAt[i]) {
                total += snowHeight - groundHeightAt[i];
            }
        }
        
        // Print for debugging
        System.out.println(Arrays.toString(groundHeightAt) + " -> " + total);
        
        // Return total snow captured
        return total;
    }
    
    /**
     * Runs all test cases
     * @return true if all tests pass
     */
    public static boolean doTestsPass() {
        
        // Track overall result
        boolean result = true;
        
        // Test 1: Main example from problem
        // Expected: 13 units of snow
        result &= computeSnowpack(new int[] {0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0}) == 13;
        
        // Test 2: Two walls at ends with empty space
        // Snow fills between two walls of height 1, width 10
        // Expected: 10 units
        result &= computeSnowpack(new int[] {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}) == 10;
        
        // Test 3: All zeros - no walls to hold snow
        // Expected: 0
        result &= computeSnowpack(new int[] {0, 0, 0, 0, 0}) == 0;
        
        // Test 4: Single hill in middle - snow slides off
        // Expected: 0
        result &= computeSnowpack(new int[] {0, 0, 1, 0, 0}) == 0;
        
        // Test 5: Single element - cannot hold snow
        // Expected: 0
        result &= computeSnowpack(new int[] {1}) == 0;
        
        // Test 6: Empty array
        // Expected: 0
        result &= computeSnowpack(new int[] {}) == 0;
        
        // Test 7: Two elements only
        // Expected: 0
        result &= computeSnowpack(new int[] {2, 3}) == 0;
        
        // Test 8: Simple valley
        // [3, 0, 3] -> snow = 3 at middle position
        result &= computeSnowpack(new int[] {3, 0, 3}) == 3;
        
        // Test 9: Decreasing heights - no snow captured
        result &= computeSnowpack(new int[] {5, 4, 3, 2, 1}) == 0;
        
        // Test 10: Increasing heights - no snow captured
        result &= computeSnowpack(new int[] {1, 2, 3, 4, 5}) == 0;
        
        return result;
    }
    
    /**
     * Main method - entry point
     */
    public static void main(String[] args) {
        
        // Print header
        System.out.println("========================================");
        System.out.println("Snowpack Problem - Test Results");
        System.out.println("========================================\n");
        
        // Run tests
        if (doTestsPass()) {
            System.out.println("\nAll tests pass");
        } else {
            System.out.println("\nTests fail.");
        }
    }
}