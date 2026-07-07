package com.interview.notes.code.year.y2026.june.google.test1;

public class CoinChange {

    // Method to calculate the total combinations to reach the target amount.
    public static long countWays(int[] coins, int target) {
        
        // Immediately return 0 if the target is negative because negative amounts are impossible.
        if (target < 0) return 0;
        
        // Initialize a 1D DP array of type long to prevent integer overflow on large data inputs.
        long[] dp = new long[target + 1];
        
        // Set the base case: there is exactly 1 way to make a target of 0 (by using zero coins).
        dp[0] = 1;
        
        // Iterate over each coin denomination one by one to build combinations without duplicates.
        for (int coin : coins) {
            
            // Loop from the current coin's value up to the target, as we can't make amounts smaller than the coin.
            for (int i = coin; i <= target; i++) {
                
                // Add the previously calculated ways to make the remainder (i - coin) to the current amount (i).
                dp[i] += dp[i - coin];
                
            }
        }
        
        // Return the last index of the array, which holds the total combinations for our target.
        return dp[target];
    }

    // Main method to execute standard tests without requiring external dependencies like JUnit.
    public static void main(String[] args) {
        
        // Define a Java 21 record to cleanly and concisely structure our test case inputs and expected outputs.
        record TestCase(int[] coins, int target, long expected) {}

        // Use var and List.of to initialize an immutable collection of test cases with minimal syntax.
        var tests = java.util.List.of(
            
            // Base test case provided in the initial interviewer requirements.
            new TestCase(new int[]{2, 5, 6}, 10, 3),
            
            // Edge case: Target is 0; the system should successfully return 1.
            new TestCase(new int[]{1, 2}, 0, 1),
            
            // Edge case: It is impossible to reach the target with the given coins; should return 0.
            new TestCase(new int[]{2}, 3, 0),
            
            // Edge case: Empty coins array; should return 0 since no combinations can be formed.
            new TestCase(new int[]{}, 5, 0),
            
            // Large data case: High target value to ensure the long data type prevents capacity overflow.
            new TestCase(new int[]{1, 2, 5}, 500, 126251)
            
        );

        // Iterate through the structured list of test cases to process and validate each one.
        for (int i = 0; i < tests.size(); i++) {
            
            // Extract the specific test case object for the current iteration using var.
            var tc = tests.get(i);
            
            // Execute the core logic method using the current test case parameters.
            long result = countWays(tc.coins, tc.target);
            
            // Evaluate if the actual result matches the expected result to flag as PASS or FAIL.
            String status = (result == tc.expected) ? "PASS" : "FAIL";
            
            // Print the formatted execution results to the standard output console.
            System.out.printf("Test %d: %s (Expected: %d, Got: %d)%n", i + 1, status, tc.expected, result);
            
        }
    }
}