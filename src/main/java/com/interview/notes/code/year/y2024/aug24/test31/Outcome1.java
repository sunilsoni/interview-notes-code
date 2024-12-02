package com.interview.notes.code.year.y2024.aug24.test31;

import java.util.Arrays;
import java.util.List;

class Outcome1 {
    public static int solve(List<Integer> ar) {
        int n = ar.size();
        if (n <= 2) return 0;

        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i < n; i++) {
            // Option 1: Remove current element
            int opt1 = 1 + dp[i - 1];

            // Option 2: Remove previous element
            int opt2 = 1 + dp[i - 2];

            // Option 3: Remove element two positions ago
            int opt3 = (i >= 3 ? 1 + dp[i - 3] : 1);

            // Check if current three elements are strictly increasing or decreasing
            if ((ar.get(i) > ar.get(i - 1) && ar.get(i - 1) > ar.get(i - 2)) ||
                    (ar.get(i) < ar.get(i - 1) && ar.get(i - 1) < ar.get(i - 2))) {
                dp[i] = Math.min(opt1, Math.min(opt2, opt3));
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        // Test case 1
        List<Integer> test1 = Arrays.asList(1, 2, 4, 1, 2);
        int result1 = solve(test1);
        System.out.println("Test case 1:");
        System.out.println("Input: " + test1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: 1");
        System.out.println("Pass: " + (result1 == 1));
        System.out.println();

        // Test case 2
        List<Integer> test2 = Arrays.asList(1, 2, 3, 5);
        int result2 = solve(test2);
        System.out.println("Test case 2:");
        System.out.println("Input: " + test2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: 2");
        System.out.println("Pass: " + (result2 == 2));
    }
}
