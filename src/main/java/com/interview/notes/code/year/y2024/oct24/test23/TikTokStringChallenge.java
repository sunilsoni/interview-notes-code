package com.interview.notes.code.year.y2024.oct24.test23;

public class TikTokStringChallenge {

    public static void main(String[] args) {
        // Sample test cases
        String[] testCaptions = {
                "aca",
                "abab",
                "abcdef"
        };
        int[] expectedResults = {
                2,
                2,
                3
        };

        boolean allPassed = true;
        for (int i = 0; i < testCaptions.length; i++) {
            int result = getMinTransformations(testCaptions[i]);
            if (result == expectedResults[i]) {
                System.out.println("Test case " + (i + 1) + " passed.");
            } else {
                System.out.println("Test case " + (i + 1) + " failed. Expected " + expectedResults[i] + ", got " + result);
                allPassed = false;
            }
        }

        // Handling large input
        StringBuilder largeCaptionBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeCaptionBuilder.append('a' + (i % 26));
        }
        String largeCaption = largeCaptionBuilder.toString();
        long startTime = System.currentTimeMillis();
        getMinTransformations(largeCaption);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input processed in " + (endTime - startTime) + " ms");

        if (allPassed) {
            System.out.println("All test cases passed.");
        }
    }

    public static int getMinTransformations(String caption) {
        int n = caption.length();
        char[] s = caption.toCharArray();
        int[][] dp = new int[n][26]; // dp[i][c]: min cost to make up to i with s[i] = c
        int INF = Integer.MAX_VALUE / 2;

        // Initialize dp[0][c]
        for (int c = 0; c < 26; c++) {
            int cost = Math.abs(s[0] - ('a' + c));
            dp[0][c] = cost;
        }

        for (int i = 1; i < n; i++) {
            int[][] newDp = new int[26][26];
            for (int c = 0; c < 26; c++) {
                dp[i][c] = INF;
            }
            for (int c1 = 0; c1 < 26; c1++) {
                for (int c2 = 0; c2 < 26; c2++) {
                    // s[i - 1] = c1, s[i] = c2
                    int cost = dp[i - 1][c1] + Math.abs(s[i] - ('a' + c2));
                    if (dp[i][c2] > cost) {
                        dp[i][c2] = cost;
                    }
                }
            }
        }

        int minTotalCost = INF;
        for (int c = 0; c < 26; c++) {
            minTotalCost = Math.min(minTotalCost, dp[n - 1][c]);
        }

        // Now, we need to backtrack to ensure that each character has at least one same neighbor
        // This is complex due to the nature of the problem
        // Alternatively, we can simulate the process

        // Simulate from the beginning
        int totalCost = 0;
        char[] result = new char[n];
        result[0] = s[0];
        for (int i = 1; i < n; i++) {
            if (result[i - 1] == s[i]) {
                result[i] = s[i];
            } else {
                // Try to change s[i] to result[i - 1]
                int costChangeCurrent = Math.abs(s[i] - result[i - 1]);
                // Or, change result[i - 1] to s[i]
                int costChangePrev = Math.abs(result[i - 1] - s[i]);

                if (costChangeCurrent <= costChangePrev) {
                    result[i] = result[i - 1];
                    totalCost += costChangeCurrent;
                } else {
                    result[i - 1] = s[i];
                    result[i] = s[i];
                    totalCost += costChangePrev;
                }
            }
        }

        // Ensure that the last character has at least one neighbor equal to it
        if (n > 1 && result[n - 1] != result[n - 2]) {
            int costChangeLast = Math.abs(result[n - 1] - result[n - 2]);
            totalCost += costChangeLast;
            result[n - 1] = result[n - 2];
        }

        return totalCost;
    }

}
