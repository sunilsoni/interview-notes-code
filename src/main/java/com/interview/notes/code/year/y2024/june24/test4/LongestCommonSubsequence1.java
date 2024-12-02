package com.interview.notes.code.year.y2024.june24.test4;

public class LongestCommonSubsequence1 {

    public static void main(String[] args) {
        String str1 = "bluefish";
        String str2 = "goldfish";
        System.out.println("Longest Common Subsequence: " + findLCS(str1, str2));

        str1 = "apple";
        str2 = "pineapple";
        System.out.println("Longest Common Subsequence: " + findLCS(str1, str2));

        str1 = "pine tree";
        str2 = "pineapple";
        System.out.println("Longest Common Subsequence: " + findLCS(str1, str2));
    }

    public static String findLCS(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // Create a 2D array to store lengths of longest common subsequence.
        int[][] dp = new int[m + 1][n + 1];

        // Fill the dp array
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Backtrack to find the LCS
        int i = m, j = n;
        StringBuilder lcs = new StringBuilder();

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.reverse().toString();
    }
}
