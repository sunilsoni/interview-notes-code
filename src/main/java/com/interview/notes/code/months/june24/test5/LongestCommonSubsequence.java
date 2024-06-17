package com.interview.notes.code.months.june24.test5;

public class LongestCommonSubsequence {

    // Function to find the longest common subsequence
    public static String lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Create a 2D array to store lengths of longest common subsequence.
        int[][] dp = new int[m + 1][n + 1];

        // Build the dp array in bottom-up fashion
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Following code is used to print LCS
        int index = dp[m][n];
        int temp = index;

        char[] lcs = new char[index + 1];
        lcs[index] = '\0'; // Set the terminating character

        // Start from the right-most-bottom-most corner and
        // one by one store characters in lcs[]
        int i = m, j = n;
        while (i > 0 && j > 0) {
            // If current character in s1 and s2 are same, then
            // current character is part of LCS
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // Put current character in result
                lcs[index - 1] = s1.charAt(i - 1);
                i--;
                j--;
                index--;
            }

            // If not same, then find the larger of two and
            // go in the direction of larger value
            else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return new String(lcs);
    }

    // Driver program
    public static void main(String[] args) {
        String s1 = "bluefish";
        String s2 = "goldfish";
        System.out.println("LCS of " + s1 + " and " + s2 + " is " + lcs(s1, s2));

        s1 = "apple";
        s2 = "pineapple";
        System.out.println("LCS of " + s1 + " and " + s2 + " is " + lcs(s1, s2));

        s1 = "pine tree";
        s2 = "pineapple";
        System.out.println("LCS of " + s1 + " and " + s2 + " is " + lcs(s1, s2));
    }
}
