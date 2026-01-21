package com.interview.notes.code.year.y2025.december.microsoft.test4;

import java.util.Arrays;

public class CoinChange {

    /**
     * Find minimum coins needed to make the target value
     * Uses Dynamic Programming (DP) approach
     *
     * @param coins  - available coin types
     * @param target - value we want to make
     * @return minimum coins needed, -1 if not possible
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
        // We use target+1 because we never need more than target coins
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

                // Only use coin if it fits (coin <= current value)
                if (coin <= value) {

                    // Check if using this coin gives better result
                    // dp[value - coin] = coins needed for remaining value
                    // +1 because we use 1 coin now
                    int coinsNeeded = dp[value - coin] + 1;

                    // Keep the smaller value
                    if (coinsNeeded < dp[value]) {
                        dp[value] = coinsNeeded;
                    }
                }
            }
        }

        // If dp[target] is still big, we couldn't make the value
        if (dp[target] > target) {
            return -1;
        }

        return dp[target];
    }

    /**
     * Run one test and print PASS or FAIL
     */
    public static void runTest(int testNum, int[] coins, int target, int expected) {

        // Get start time
        long start = System.currentTimeMillis();

        // Run solution
        int result = findMinCoins(coins, target);

        // Get end time
        long end = System.currentTimeMillis();

        // Check if passed
        boolean passed = (result == expected);

        // Print results
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
        // 25 = 25 = 1 coin
        runTest(2,
                new int[]{1, 5, 10, 25},
                25,
                1);

        // Test 3: Small value
        // 3 = 1+1+1 = 3 coins
        runTest(3,
                new int[]{1, 5, 10},
                3,
                3);

        // Test 4: Zero value
        // 0 = 0 coins
        runTest(4,
                new int[]{1, 5, 10},
                0,
                0);

        // Test 5: Impossible case
        // 3 cannot be made with [2,5]
        runTest(5,
                new int[]{2, 5},
                3,
                -1);

        // Test 6: Value equals coin
        // 1 = 1 coin
        runTest(6,
                new int[]{1, 5, 10},
                1,
                1);

        // Test 7: Greedy would fail
        // 11 = 6+5 = 2 coins (not 6+1+1+1+1+1)
        runTest(7,
                new int[]{1, 5, 6},
                11,
                2);

        // Test 8: Multiple solutions
        // 30 = 10+20 or 25+5 = 2 coins
        runTest(8,
                new int[]{1, 5, 10, 20, 25},
                30,
                2);

        // Test 9: Single coin type
        // 15 = 5+5+5 = 3 coins
        runTest(9,
                new int[]{5},
                15,
                3);

        // Test 10: Single coin - impossible
        // 14 cannot be made with only [5]
        runTest(10,
                new int[]{5},
                14,
                -1);

        // ====== LARGE DATA TESTS ======
        System.out.println("====== LARGE DATA TESTS ======");
        System.out.println();

        // Test 11: Large value
        // 1000 / 25 = 40 coins
        runTest(11,
                new int[]{1, 5, 10, 25},
                1000,
                40);

        // Test 12: Very large value
        // 5000 / 100 = 50 coins
        runTest(12,
                new int[]{1, 5, 10, 25, 50, 100},
                5000,
                50);

        // Test 13: Large with many coins
        // 9999 with [1,5,10,25,50,100]
        runTest(13,
                new int[]{1, 5, 10, 25, 50, 100},
                9999,
                107);

        // Test 14: Stress test
        runTest(14,
                new int[]{1, 5, 10, 25, 50, 100},
                10000,
                100);

        // Test 15: Large odd value
        // 8763 with standard coins
        runTest(15,
                new int[]{1, 5, 10, 25, 50, 100},
                8763,
                93);

        System.out.println("====== ALL TESTS DONE ======");
    }
}