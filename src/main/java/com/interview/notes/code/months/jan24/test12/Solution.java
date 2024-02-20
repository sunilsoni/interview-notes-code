package com.interview.notes.code.months.jan24.test12;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static int efficientTrek(List<Integer> trails, int record) {
        // Write your code here
        int n = trails.size();
        int[][] dp = new int[n + 1][record + 1];

        // Base case: No trails or no days
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        // Build the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= record; j++) {
                // Skip this trail if we don't have enough days
                if (i - j < 0) {
                    continue;
                }

                // Consider including this trail on the current day
                dp[i][j] = Math.min(dp[i][j], Math.max(trails.get(i - 1), dp[i - j][j - 1]));

                // Consider not including this trail on the current day
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
            }
        }

        return dp[n][record];
    }

    public static void main(String[] args) {
        List<Integer> trails = new ArrayList<>();
        trails.add(150);
        trails.add(200);
        trails.add(400);
        trails.add(350);
        trails.add(250);
        int record = 3;
        int result = efficientTrek(trails, record);
        System.out.println(result);  // Output: 750

        trails = new ArrayList<>();
        trails.add(78);
        trails.add(45);
        trails.add(12);
        trails.add(56);
        trails.add(85);
        trails.add(45);
        record = 1;
        result = efficientTrek(trails, record);
        System.out.println(result);  // Output: 85
    }
}
