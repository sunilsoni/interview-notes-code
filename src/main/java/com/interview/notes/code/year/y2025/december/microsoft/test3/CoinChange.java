package com.interview.notes.code.year.y2025.december.microsoft.test3;

import java.util.Arrays;

public class CoinChange {
    
    /**
     * Find minimum coins needed to make the target value
     * Uses Dynamic Programming (DP) approach
     */
    public static int findMinCoins(int[] coins, int target) {
        
        // If target is 0, we need 0 coins
        if (target == 0) {
            return 0;
        }
        
        // If target is negative, not possible
        if (target < 0) {
            return -1;
        }
        
        // If no coins given, not possible
        if (coins == null || coins.length == 0) {
            return -1;
        }
        
        // Create DP array
        // dp[i] = minimum coins needed to make value i
        int[] dp = new int[target + 1];
        
        // Fill array with big number (like infinity)
        for (int i = 0; i <= target; i++) {
            dp[i] = target + 1;
        }
        
        // Base case: 0 coins needed for value 0
        dp[0] = 0;
        
        // Build solution for each value from 1 to target
        for (int value = 1; value <= target; value++) {
            
            // Try each coin
            for (int j = 0; j < coins.length; j++) {
                
                int coin = coins[j];
                
                // Only use coin if it fits
                if (coin <= value) {
                    
                    // Check if using this coin gives better result
                    int coinsNeeded = dp[value - coin] + 1;
                    
                    // Keep the smaller value
                    if (coinsNeeded < dp[value]) {
                        dp[value] = coinsNeeded;
                    }
                }
            }
        }
        
        // If dp[target] is still big, not possible
        if (dp[target] > target) {
            return -1;
        }
        
        return dp[target];
    }
    
    /**
     * Run one test and print PASS or FAIL
     */
    public static void runTest(int testNum, int[] coins, int target, int expected) {
        
        long start = System.currentTimeMillis();
        int result = findMinCoins(coins, target);
        long end = System.currentTimeMillis();
        
        boolean passed = (result == expected);
        
        System.out.println("------------------------------------------");
        
        if (passed) {
            System.out.println("Test " + testNum + ": PASS");
        } else {
            System.out.println("Test " + testNum + ": FAIL");
        }
        
        System.out.println("Coins: " + Arrays.toString(coins));
        System.out.println("Target: " + target);
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Time: " + (end - start) + " ms");
        System.out.println("------------------------------------------");
        System.out.println();
    }
    
    /**
     * Main method - runs all tests
     */
    public static void main(String[] args) {
        
        System.out.println("====== COIN CHANGE TESTS ======");
        System.out.println();
        
        // Test 1: Given example
        // 67 = 25+25+10+5+1+1 = 6 coins
        runTest(1, 
            new int[]{1, 5, 10, 25}, 
            67, 
            6);
        
        // Test 2: Exact coin match
        runTest(2, 
            new int[]{1, 5, 10, 25}, 
            25, 
            1);
        
        // Test 3: Small value
        runTest(3, 
            new int[]{1, 5, 10}, 
            3, 
            3);
        
        // Test 4: Zero value
        runTest(4, 
            new int[]{1, 5, 10}, 
            0, 
            0);
        
        // Test 5: Impossible case
        runTest(5, 
            new int[]{2, 5}, 
            3, 
            -1);
        
        // Test 6: Value equals coin
        runTest(6, 
            new int[]{1, 5, 10}, 
            1, 
            1);
        
        // Test 7: Greedy would fail
        runTest(7, 
            new int[]{1, 5, 6}, 
            11, 
            2);
        
        // Test 8: Multiple solutions
        runTest(8, 
            new int[]{1, 5, 10, 20, 25}, 
            30, 
            2);
        
        // Test 9: Single coin type
        runTest(9, 
            new int[]{5}, 
            15, 
            3);
        
        // Test 10: Single coin - impossible
        runTest(10, 
            new int[]{5}, 
            14, 
            -1);
        
        // ====== LARGE DATA TESTS ======
        System.out.println("====== LARGE DATA TESTS ======");
        System.out.println();
        
        // Test 11: Large value
        runTest(11, 
            new int[]{1, 5, 10, 25}, 
            1000, 
            40);
        
        // Test 12: Very large value
        runTest(12, 
            new int[]{1, 5, 10, 25, 50, 100}, 
            5000, 
            50);
        
        // Test 13: Large with many coins
        runTest(13, 
            new int[]{1, 5, 10, 25, 50, 100}, 
            9999, 
            107);
        
        // Test 14: Stress test
        runTest(14, 
            new int[]{1, 5, 10, 25, 50, 100}, 
            10000, 
            100);
        
        // Test 15: FIXED - Large odd value
        // 8763 = 87×100 + 1×50 + 1×10 + 3×1 = 92 coins
        runTest(15, 
            new int[]{1, 5, 10, 25, 50, 100}, 
            8763, 
            92);  // FIXED: was 93, should be 92
        
        System.out.println("====== ALL TESTS DONE ======");
    }
}