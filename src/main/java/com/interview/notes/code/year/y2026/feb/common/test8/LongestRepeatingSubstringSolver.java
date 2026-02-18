package com.interview.notes.code.year.y2026.feb.common.test8;

import java.util.stream.IntStream;

public class LongestRepeatingSubstringSolver {

    public static int solve(String S) {
        int n = S.length();
        int[][] dp = new int[n + 1][n + 1];
        int maxLen = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (S.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }

        return maxLen > 0 ? maxLen : -1;
    }

    public static void main(String[] args) {
        // Provided Sample Cases
        runTestCase(1, "kipkipl", 3);
        runTestCase(2, "ghijhil", 2);
        runTestCase(3, "tsssjgjsltsssjgj", 7);

        // Edge Cases
        runTestCase(4, "abcdef", -1); // No repeats
        runTestCase(5, "aaaaa", 4);    // Overlapping repeats allowed ("aaaa" appears twice)
        runTestCase(6, "banana", 3);   // "ana"

        // Large Data Test Case (N = 1000)
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, 500).forEach(i -> sb.append("abc")); // Pattern "abc" repeated
        // "abc" repeated 500 times. Length 1500 (but constraint is 1000, let's limit to 1000)
        String largeInput = sb.substring(0, 1000);
        // In "abcabc...", the longest repeating substring is almost the whole string minus the period
        int expectedLarge = 997; 
        runTestCase(7, largeInput, expectedLarge);
    }

    private static void runTestCase(int id, String s, int expected) {
        long start = System.nanoTime();
        int actual = solve(s);
        long end = System.nanoTime();
        String status = (actual == expected) ? "PASS" : "FAIL";
        System.out.printf("Test Case %d: %s | Expected: %d | Actual: %d | Time: %.3f ms%n",
                id, status, expected, actual, (end - start) / 1e6);
    }
}