package com.interview.notes.code.months.april24.test2;

import java.util.Arrays;
import java.util.List;

public class Solution2 {
    public static int solve(List<Integer> ar) {
        int n = ar.size();
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            if (ar.get(i) > ar.get(i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - 1] + 1);
            }
            if (i >= 2 && ar.get(i) > ar.get(i - 2)) {
                dp[i] = Math.max(dp[i], dp[i - 2] + 1);
            }
        }

        int maxLen = 0;
        for (int len : dp) {
            maxLen = Math.max(maxLen, len);
        }

        return n - maxLen;
    }

    public static void main(String[] args) {
        // Example usage:
        List<Integer> input = Arrays.asList(1, 2, 3, 6, 5, 4, 8);
        System.out.println(solve(input)); // This should output 3
    }
}
