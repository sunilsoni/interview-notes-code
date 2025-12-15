package com.interview.notes.code.year.y2025.december.common.test5;

import java.util.Arrays;

public class CoinChange {
    
    /**
     * Find minimum coins and return count of each coin used
     * 
     * @param coins - array of coin values
     * @param target - value to make
     * @return array where index i = count of coins[i] used
     *         returns null if not possible
     */
    public static int[] findMinCoins(int[] coins, int target) {
        
        // Edge case: target is 0
        // Return array of zeros (no coins needed)
        if (target == 0) {
            return new int[coins.length];
        }
        
        // Edge case: invalid input
        if (target < 0 || coins == null || coins.length == 0) {
            return null;
        }
        
        // dp[i] = minimum coins needed to make value i
        int[] dp = new int[target + 1];
        
        // coinUsed[i] = which coin (index) was used to make value i
        // This helps us backtrack and find all coins used
        int[] coinUsed = new int[target + 1];
        
        // Fill with big number (infinity)
        for (int i = 0; i <= target; i++) {
            dp[i] = target + 1;
            coinUsed[i] = -1;  // -1 means no coin used
        }
        
        // Base case: 0 coins for value 0
        dp[0] = 0;
        
        // Build DP table
        for (int value = 1; value <= target; value++) {
            
            // Try each coin
            for (int j = 0; j < coins.length; j++) {
                
                int coin = coins[j];
                
                // Can we use this coin?
                if (coin <= value) {
                    
                    // Is using this coin better?
                    int newCount = dp[value - coin] + 1;
                    
                    if (newCount < dp[value]) {
                        dp[value] = newCount;
                        coinUsed[value] = j;  // Store coin index
                    }
                }
            }
        }
        
        // If not possible to make target
        if (dp[target] > target) {
            return null;
        }
        
        // Backtrack to find how many of each coin was used
        int[] result = new int[coins.length];
        
        // Start from target and go backwards
        int remaining = target;
        
        while (remaining > 0) {
            
            // Get which coin was used for this value
            int coinIndex = coinUsed[remaining];
            
            // Increment count for this coin
            result[coinIndex] = result[coinIndex] + 1;
            
            // Subtract coin value from remaining
            remaining = remaining - coins[coinIndex];
        }
        
        return result;
    }
    
    /**
     * Get total number of coins from result array
     */
    public static int getTotalCoins(int[] result) {
        
        if (result == null) {
            return -1;
        }
        
        int total = 0;
        
        for (int i = 0; i < result.length; i++) {
            total = total + result[i];
        }
        
        return total;
    }
    
    /**
     * Run test and print results
     */
    public static void runTest(int testNum, int[] coins, int target, int[] expected) {
        
        long start = System.currentTimeMillis();
        int[] result = findMinCoins(coins, target);
        long end = System.currentTimeMillis();
        
        // Check if passed
        boolean passed = Arrays.equals(result, expected);
        
        System.out.println("------------------------------------------");
        
        if (passed) {
            System.out.println("Test " + testNum + ": PASS");
        } else {
            System.out.println("Test " + testNum + ": FAIL");
        }
        
        System.out.println("Coins:    " + Arrays.toString(coins));
        System.out.println("Target:   " + target);
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Got:      " + Arrays.toString(result));
        
        // Print breakdown
        if (result != null) {
            System.out.println();
            System.out.println("Breakdown:");
            int sum = 0;
            int count = 0;
            for (int i = 0; i < coins.length; i++) {
                if (result[i] > 0) {
                    System.out.println("  " + result[i] + " x " + coins[i] + " = " + (result[i] * coins[i]));
                    sum = sum + (result[i] * coins[i]);
                    count = count + result[i];
                }
            }
            System.out.println("  Total value: " + sum);
            System.out.println("  Total coins: " + count);
        }
        
        System.out.println("Time: " + (end - start) + " ms");
        System.out.println("------------------------------------------");
        System.out.println();
    }
    
    /**
     * Main method - run all tests
     */
    public static void main(String[] args) {
        
        System.out.println("====== COIN CHANGE TESTS ======");
        System.out.println("Return: Count of each coin used");
        System.out.println();
        
        // Test 1: Given example
        // 67 = 2×1 + 1×5 + 1×10 + 2×25
        runTest(1, 
            new int[]{1, 5, 10, 25}, 
            67, 
            new int[]{2, 1, 1, 2});
        
        // Test 2: Exact coin match
        // 25 = 0×1 + 0×5 + 0×10 + 1×25
        runTest(2, 
            new int[]{1, 5, 10, 25}, 
            25, 
            new int[]{0, 0, 0, 1});
        
        // Test 3: Small value
        // 3 = 3×1 + 0×5 + 0×10
        runTest(3, 
            new int[]{1, 5, 10}, 
            3, 
            new int[]{3, 0, 0});
        
        // Test 4: Zero value
        // 0 = no coins needed
        runTest(4, 
            new int[]{1, 5, 10}, 
            0, 
            new int[]{0, 0, 0});
        
        // Test 5: Impossible case
        // 3 cannot be made with [2, 5]
        runTest(5, 
            new int[]{2, 5}, 
            3, 
            null);
        
        // Test 6: Value equals coin
        // 1 = 1×1 + 0×5 + 0×10
        runTest(6, 
            new int[]{1, 5, 10}, 
            1, 
            new int[]{1, 0, 0});
        
        // Test 7: Greedy would fail
        // 11 = 0×1 + 1×5 + 0×6 ... wait
        // Actually 11 = 5+6 = 2 coins
        // [1, 5, 6] -> [0, 1, 1]
        runTest(7, 
            new int[]{1, 5, 6}, 
            11, 
            new int[]{0, 1, 1});
        
        // Test 8: Multiple ways
        // 30 = 10+20 or 25+5
        // [1, 5, 10, 20, 25] -> depends on DP order
        runTest(8, 
            new int[]{1, 5, 10, 20, 25}, 
            30, 
            new int[]{0, 0, 1, 1, 0});
        
        // Test 9: Single coin type
        // 15 = 3×5
        runTest(9, 
            new int[]{5}, 
            15, 
            new int[]{3});
        
        // Test 10: Single coin - impossible
        runTest(10, 
            new int[]{5}, 
            14, 
            null);
        
        // ====== LARGE DATA TESTS ======
        System.out.println("====== LARGE DATA TESTS ======");
        System.out.println();
        
        // Test 11: Large value
        // 1000 = 40×25
        runTest(11, 
            new int[]{1, 5, 10, 25}, 
            1000, 
            new int[]{0, 0, 0, 40});
        
        // Test 12: Very large value
        // 5000 = 50×100
        runTest(12, 
            new int[]{1, 5, 10, 25, 50, 100}, 
            5000, 
            new int[]{0, 0, 0, 0, 0, 50});
        
        // Test 13: Large with remainder
        // 9999 = ?
        runTest(13, 
            new int[]{1, 5, 10, 25, 50, 100}, 
            9999, 
            new int[]{4, 0, 0, 1, 1, 99});
        
        // Test 14: Round number
        // 10000 = 100×100
        runTest(14, 
            new int[]{1, 5, 10, 25, 50, 100}, 
            10000, 
            new int[]{0, 0, 0, 0, 0, 100});
        
        // Test 15: Odd value
        // 8763 = 87×100 + 1×50 + 1×10 + 3×1 = 92 coins
        runTest(15, 
            new int[]{1, 5, 10, 25, 50, 100}, 
            8763, 
            new int[]{3, 0, 1, 0, 1, 87});
        
        System.out.println("====== ALL TESTS DONE ======");
    }
}