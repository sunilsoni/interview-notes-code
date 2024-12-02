package com.interview.notes.code.year.y2024.oct24.amazon.test18;

public class AmazonDatabaseErrorMinimizer {

    public static int getMinErrors(String errorString, int x, int y) {
        final int MOD = 1_000_000_007;
        int n = errorString.length();
        char[] s = errorString.toCharArray();
        int INF = Integer.MAX_VALUE / 2;  // Avoid integer overflow

        // dp[i][0]: minimum errors for first i characters, ending with '0'
        // dp[i][1]: minimum errors for first i characters, ending with '1'
        int[][] dp = new int[n][2];

        // Initialization
        if (s[0] == '0' || s[0] == '!') {
            dp[0][0] = 0;
        } else {
            dp[0][0] = INF;
        }
        if (s[0] == '1' || s[0] == '!') {
            dp[0][1] = 0;
        } else {
            dp[0][1] = INF;
        }

        for (int i = 1; i < n; i++) {
            char c = s[i];
            // Initialize dp[i][0] and dp[i][1] to INF
            dp[i][0] = dp[i][1] = INF;

            if (c == '0' || c == '!') {
                // If current character is '0' or can be '0'
                dp[i][0] = Math.min(
                        dp[i - 1][0],            // Previous char is '0', no new error
                        dp[i - 1][1] + y         // Previous char is '1', '10' formed, add y errors
                );
            }
            if (c == '1' || c == '!') {
                // If current character is '1' or can be '1'
                dp[i][1] = Math.min(
                        dp[i - 1][1],            // Previous char is '1', no new error
                        dp[i - 1][0] + x         // Previous char is '0', '01' formed, add x errors
                );
            }
        }

        int result = Math.min(dp[n - 1][0], dp[n - 1][1]);
        return result % MOD;
    }

    // Main method for testing
    public static void main(String[] args) {
        testGetMinErrors();
    }

    // Testing method
    public static void testGetMinErrors() {
        String[] errorStrings = {
                "101!1",
                "!!",
                "0!1!",
                "!0!1!0!1!",
                // Edge cases
                "!!!!!",
                "0101010101",
                "0000000",
                "1111111",
                "0!1!0!1!0!1!0!1!0!1!0!1!0!1!0!1!",
        };
        int[] xValues = {
                2,
                1,
                3,
                2,
                1000000000,
                5,
                7,
                7,
                1,
        };
        int[] yValues = {
                3,
                2,
                4,
                3,
                1000000000,
                5,
                7,
                7,
                1,
        };
        int[] expectedResults = {
                9,
                0,
                7,
                18,
                0,
                125,
                0,
                0,
                56,
        };

        boolean allPassed = true;
        for (int i = 0; i < errorStrings.length; i++) {
            int result = getMinErrors(errorStrings[i], xValues[i], yValues[i]);
            if (result == expectedResults[i]) {
                System.out.println("Test case " + (i + 1) + " PASSED");
            } else {
                System.out.println("Test case " + (i + 1) + " FAILED");
                System.out.println("Input: errorString = " + errorStrings[i] + ", x = " + xValues[i] + ", y = " + yValues[i]);
                System.out.println("Expected: " + expectedResults[i]);
                System.out.println("Got: " + result);
                allPassed = false;
            }
        }
        if (allPassed) {
            System.out.println("All test cases PASSED");
        } else {
            System.out.println("Some test cases FAILED");
        }
    }
}
