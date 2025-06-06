package com.interview.notes.code.year.y2025.may.common.test14;

import java.util.ArrayList;
import java.util.List;

public class EditDistance {

    /**
     * Compute the minimum edit distance (Levenshtein distance) between word1 and word2.
     *
     * @param word1 The source string.
     * @param word2 The target string.
     * @return The minimum number of single-character edits to transform word1 into word2.
     */
    public static int minDistance(String word1, String word2) {
        // If word1 is null, treat it as empty.
        if (word1 == null) {
            word1 = "";
        }
        // If word2 is null, treat it as empty.
        if (word2 == null) {
            word2 = "";
        }

        int m = word1.length(); // length of source
        int n = word2.length(); // length of target

        // Create a DP table of size (m+1) x (n+1).
        // dp[i][j] = min edits to convert first i chars of word1 to first j chars of word2.
        int[][] dp = new int[m + 1][n + 1];

        // Base case: converting empty word1 prefix to word2 prefix of length j -> j inserts
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // cost of inserting all j characters
        }

        // Base case: converting word1 prefix of length i to empty word2 -> i deletions
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // cost of deleting all i characters
        }

        // Fill the DP table row by row
        for (int i = 1; i <= m; i++) {
            // Character at index (i-1) in word1
            char c1 = word1.charAt(i - 1);

            for (int j = 1; j <= n; j++) {
                // Character at index (j-1) in word2
                char c2 = word2.charAt(j - 1);

                if (c1 == c2) {
                    // If the two characters are the same, no extra cost.
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Otherwise, consider the three operations:
                    // 1. Delete from word1 (dp[i-1][j] + 1)
                    // 2. Insert into word1 (dp[i][j-1] + 1)
                    // 3. Replace in word1 (dp[i-1][j-1] + 1)
                    int deleteCost = dp[i - 1][j] + 1;      // delete c1
                    int insertCost = dp[i][j - 1] + 1;      // insert c2
                    int replaceCost = dp[i - 1][j - 1] + 1; // replace c1->c2

                    // Take the minimum of the three possible operations
                    dp[i][j] = Math.min(deleteCost, Math.min(insertCost, replaceCost));
                }
            }
        }

        // The bottom-right cell holds the final answer: distance between full strings
        return dp[m][n];
    }

    /**
     * Simple main method to run multiple test cases and print PASS/FAIL.
     * Uses a simple loop; does not rely on any testing framework.
     */
    public static void main(String[] args) {
        // Define a list of test cases: each entry is {word1, word2, expectedDistance}
        List<TestCase> tests = new ArrayList<>();

        // Provided example
        tests.add(new TestCase("horse", "ros", 3));
        // Edge cases: empty vs empty
        tests.add(new TestCase("", "", 0));
        // Edge: one empty, one non-empty
        tests.add(new TestCase("", "abc", 3));
        tests.add(new TestCase("abc", "", 3));
        // Identical strings
        tests.add(new TestCase("abc", "abc", 0));
        // Single-character difference
        tests.add(new TestCase("a", "b", 1));
        // No common subsequence
        tests.add(new TestCase("abc", "def", 3));
        // Partial overlap
        tests.add(new TestCase("intention", "execution", 5)); // classic example
        // Strings with repeated patterns
        tests.add(new TestCase("aaaa", "aaa", 1));
        tests.add(new TestCase("abcd", "azcd", 1));
        // Large test: both are length 1000, all 'a' (distance should be 0)
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb1.append('a');
            sb2.append('a');
        }
        tests.add(new TestCase(sb1.toString(), sb2.toString(), 0));

        // Run each test
        int testNumber = 1;
        for (TestCase tc : tests) {
            // Compute actual distance
            int actual = minDistance(tc.word1, tc.word2);
            // Compare with expected
            boolean passed = (actual == tc.expected);
            // Print result
            if (passed) {
                System.out.println("Test " + testNumber + ": PASS (\""
                        + tc.word1 + "\" -> \"" + tc.word2 + "\" => " + actual + ")");
            } else {
                System.out.println("Test " + testNumber + ": FAIL (\""
                        + tc.word1 + "\" -> \"" + tc.word2 + "\" => expected "
                        + tc.expected + ", but got " + actual + ")");
            }
            testNumber++;
        }
    }

    /**
     * Helper class to store a single test case.
     */
    static class TestCase {
        String word1;
        String word2;
        int expected;

        TestCase(String w1, String w2, int exp) {
            this.word1 = w1;
            this.word2 = w2;
            this.expected = exp;
        }
    }
}