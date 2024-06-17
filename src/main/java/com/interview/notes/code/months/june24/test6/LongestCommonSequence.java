package com.interview.notes.code.months.june24.test6;

public class LongestCommonSequence {

    public static String findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Create a 2D array to store lengths of longest common subsequence.
        int[][] dp = new int[m + 1][n + 1];

        // Build the dp array from bottom up
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0; // Base case: if either string is empty, LCS length is 0
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // Characters match, extend LCS
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // Characters do not match
                }
            }
        }

        // dp[m][n] contains length of LCS for s1[0..m-1], s2[0..n-1]
        // Use dp array to construct the LCS string
        int lcsLength = dp[m][n];
        char[] lcsChars = new char[lcsLength];

        // Start from the bottom-right corner and fill lcsChars
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcsChars[lcsLength - 1] = s1.charAt(i - 1);
                i--;
                j--;
                lcsLength--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return new String(lcsChars);
    }

    public static void main(String[] args) {
        String s1 = "bluefish";
        String s2 = "goldfish";
        System.out.println("LCS between '" + s1 + "' and '" + s2 + "': " + findLCS(s1, s2));

        s1 = "apple";
        s2 = "pineapple";
        System.out.println("LCS between '" + s1 + "' and '" + s2 + "': " + findLCS(s1, s2));

        s1 = "pine tree";
        s2 = "pineapple";
        System.out.println("LCS between '" + s1 + "' and '" + s2 + "': " + findLCS(s1, s2));
    }
}
