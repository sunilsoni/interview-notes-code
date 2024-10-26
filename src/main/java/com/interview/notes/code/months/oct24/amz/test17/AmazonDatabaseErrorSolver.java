package com.interview.notes.code.months.oct24.amz.test17;

import java.util.Arrays;

public class AmazonDatabaseErrorSolver {

    private static final int MOD = 1000000007;

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        int[][][] dp = new int[n + 1][2][2];

        // Initialize dp array with large values
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE / 2);
            }
        }

        // Base case: empty string
        dp[0][0][0] = dp[0][1][1] = 0;

        for (int i = 1; i <= n; i++) {
            char c = errorString.charAt(i - 1);
            for (int prev = 0; prev < 2; prev++) {
                for (int curr = 0; curr < 2; curr++) {
                    if (c != '!' && c - '0' != curr) continue;

                    for (int prevPrev = 0; prevPrev < 2; prevPrev++) {
                        int errors = dp[i - 1][prevPrev][prev];
                        if (prev == 0 && curr == 1) errors = (errors + x) % MOD;
                        if (prev == 1 && curr == 0) errors = (errors + y) % MOD;
                        dp[i][prev][curr] = Math.min(dp[i][prev][curr], errors);
                    }
                }
            }
        }

        int minErrors = Integer.MAX_VALUE;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                minErrors = Math.min(minErrors, dp[n][i][j]);
            }
        }

        return minErrors;
    }

    public static void main(String[] args) {
        // Test cases
        String[] testStrings = {
                "101!1",
                "!0!!1!",
                "1!0!1",
                "!!!!!",
                "0000!1111",
                "1" + "0!".repeat(100000)  // Large input test
        };
        int[][] testParams = {
                {2, 3},
                {5, 3},
                {7, 6},
                {1, 1},
                {10, 12},
                {1000000, 999999}
        };
        int[] expectedOutputs = {9, 0, 13, 0, 20, (int) (999999000001L % MOD)};

        for (int i = 0; i < testStrings.length; i++) {
            long startTime = System.nanoTime();
            int result = getMinErrors(testStrings[i], testParams[i][0], testParams[i][1]);
            long endTime = System.nanoTime();

            boolean passed = result == expectedOutputs[i];
            System.out.printf("Test Case %d: %s (Execution time: %.3f ms)\n",
                    i + 1, passed ? "PASS" : "FAIL", (endTime - startTime) / 1e6);

            if (!passed) {
                System.out.printf("  Expected: %d, Got: %d\n", expectedOutputs[i], result);
            }
        }
    }
}