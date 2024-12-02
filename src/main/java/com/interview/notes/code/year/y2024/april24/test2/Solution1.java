package com.interview.notes.code.year.y2024.april24.test2;

import java.util.Arrays;
import java.util.List;

public class Solution1 {

    public static int solve(List<Integer> ar) {
        int n = ar.size();
        if (n < 3) {
            return 0; // No need to remove elements for arrays with less than 3 elements
        }

        // Initialize dp array to store minimum removals for each index
        int[] dp = new int[n];

        // Base cases: First two elements
        dp[0] = ar.get(1) > ar.get(0) ? 0 : 1; // 0 if increasing, 1 if decreasing
        dp[1] = dp[0] + (ar.get(2) > ar.get(1) ? (ar.get(1) > ar.get(0) ? 0 : 1) : 1);

        // Dynamic programming: Build up minimum removals for each element
        for (int i = 2; i < n; i++) {
            int removeCurrent = 1 + dp[i - 1]; // Remove current element
            int keepCurrent = dp[i - 2]; // Keep current element and consider previous two

            // Check if keeping current element creates a violation
            if (ar.get(i) > ar.get(i - 1)) {
                if (ar.get(i - 2) >= ar.get(i)) { // Violation, remove current
                    keepCurrent = removeCurrent;
                }
            } else if (ar.get(i) < ar.get(i - 1)) {
                if (ar.get(i - 2) <= ar.get(i)) { // Violation, remove current
                    keepCurrent = removeCurrent;
                }
            }

            // Choose the minimum removal option
            dp[i] = Math.min(removeCurrent, keepCurrent);
        }

        return dp[n - 1]; // Minimum removals for the last element represents the final answer
    }

    public static void main(String[] args) {
        List<Integer> ar = Arrays.asList(1, 24, 1, 2);
        System.out.println(solve(ar)); // Output: 1

        ar = Arrays.asList(12, 35);
        System.out.println(solve(ar)); // Output: 2
    }
}
