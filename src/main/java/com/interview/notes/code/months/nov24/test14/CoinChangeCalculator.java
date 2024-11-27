package com.interview.notes.code.months.nov24.test14;

/*
You have an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), and pennies (1 cent), write code to calculate the number of ways of representing n cents.
 */
public class CoinChangeCalculator {

    /**
     * Counts the number of ways to make n cents using quarters, dimes, nickels, and pennies.
     *
     * @param n The total amount in cents.
     * @return The number of ways to make n cents.
     */
    public static long countWays(int n) {
        if (n < 0) {
            return 0;
        }

        // Coin denominations: 25, 10, 5, 1
        int[] coins = {25, 10, 5, 1};
        long[] dp = new long[n + 1];
        dp[0] = 1; // Base case: There's one way to make 0 cents.

        for (int coin : coins) {
            for (int amount = coin; amount <= n; amount++) {
                dp[amount] += dp[amount - coin];
            }
        }

        return dp[n];
    }

    /**
     * Runs predefined test cases and outputs PASS/FAIL for each.
     */
    public static void main(String[] args) {
        // Define test cases: array of {input, expected output}
        Object[][] testCases = {
                {0, 1L},
                {1, 1L},
                {5, 2L},
                {10, 4L},
                {25, 13L},
                {100, 242L},
                {1000, 142511L},
                {-5, 0L}, // Edge case: negative input
                {10000, 142511205L},
                // Add more test cases as needed
        };

        boolean allPassed = true;

        for (Object[] testCase : testCases) {
            int input = (int) testCase[0];
            long expected = (long) testCase[1];
            long result = countWays(input);
            if (result == expected) {
                System.out.println("Test case n = " + input + " PASSED.");
            } else {
                System.out.println("Test case n = " + input + " FAILED. Expected: " + expected + ", Got: " + result);
                allPassed = false;
            }
        }

        // Handle large input separately if needed
        int largeInput = 100000;
        long largeResult = countWays(largeInput);
        System.out.println("Test case n = " + largeInput + " Result: " + largeResult);
        // Note: The expected result for n = 100000 is not predefined here.

        if (allPassed) {
            System.out.println("All test cases PASSED.");
        } else {
            System.out.println("Some test cases FAILED.");
        }
    }
}
