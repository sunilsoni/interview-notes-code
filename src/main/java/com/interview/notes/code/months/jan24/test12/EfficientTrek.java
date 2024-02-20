package com.interview.notes.code.months.jan24.test12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EfficientTrek {

    public static int efficientTrek1(List<Integer> trails, int record) {
        int n = trails.size();

        // Base cases: no trails or no days
        if (n == 0 || record == 0) {
            return 0;
        }

        // Initialize DP table to handle edge cases
        int[][] dp = new int[n + 1][record + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= record; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // Build up the DP table iteratively
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= record; j++) {
                // If the current trail is too long for the current day, skip it
                if (trails.get(i - 1) > j) {
                    continue;
                }

                // Consider including the current trail in the current day
                dp[i][j] = Math.min(dp[i - 1][j], Math.max(dp[i - 1][j - trails.get(i - 1)], trails.get(i - 1)));

                // Consider not including the current trail in the current day
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
            }
        }

        return dp[n][record];
    }

    public static int efficientTrek(List<Integer> trails, int record) {
        // Sort the trails in descending order to ensure we cover the longest trails first
        Collections.sort(trails, Collections.reverseOrder());

        int totalDays = trails.size();
        int minSum = Integer.MAX_VALUE;

        // Iterate over possible combinations of covering the trails within 'record' days
        for (int i = 1; i <= totalDays; i++) {
            int maxSum = 0;
            int index = 0;

            // For each combination, calculate the sum of the longest trails covered each day
            for (int j = 0; j < record; j++) {
                int sum = 0;

                // Calculate the sum of trails for the current day
                for (int k = 0; k < i; k++) {
                    if (index < totalDays) {
                        sum += trails.get(index);
                        index++;
                    }
                }

                // Update the maximum sum of trails for the current combination
                maxSum = Math.max(maxSum, sum);
            }

            // Update the minimum sum of longest trails
            minSum = Math.min(minSum, maxSum);
        }

        return minSum;
    }

    public static void main(String[] args) {
        List<Integer> trails2 = new ArrayList<>(Arrays.asList(150, 200, 400, 350, 250));
        int record2 = 3;
        System.out.println(efficientTrek(trails2, record2)); // Should return 750
    }
}
