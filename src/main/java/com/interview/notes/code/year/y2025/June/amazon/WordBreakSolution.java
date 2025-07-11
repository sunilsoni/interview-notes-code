package com.interview.notes.code.year.y2025.June.amazon;

import java.util.*;
import java.util.stream.IntStream;

public class WordBreakSolution {

    /**
     * Returns true if the string s can be segmented into a sequence of
     * one or more dictionary words from wordDict.
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        // Convert the list to a HashSet for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordDict);

        // dp[i] will be true if s[0..i) can be segmented into dict words
        boolean[] dp = new boolean[s.length() + 1];

        // Base case: empty string is always segmentable
        dp[0] = true;

        // For each prefix length i from 1 up to s.length()
        for (int i = 1; i <= s.length(); i++) {
            // We need an effectively final copy of i for the lambda
            final int end = i;

            // Use a stream over 0..end-1 to see if any split works
            dp[i] = IntStream.range(0, end)
                    .anyMatch(j ->
                            dp[j]                           // prefix [0..j) is valid
                                    && wordSet.contains(            // and
                                    s.substring(j, end)         // substring [j..end)
                            )
                    );
        }

        // The whole string is segmentable if dp[n] is true
        return dp[s.length()];
    }

    /**
     * Simple main method to run test cases and print PASS/FAIL.
     */
    public static void main(String[] args) {
        // Each test: { input string, dictionary list, expected boolean }
        Object[][] tests = {
                {"leetcode", Arrays.asList("leet", "code"), true},
                {"applepenapple", Arrays.asList("apple", "pen"), true},
                {"catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"), false},
                {"", Collections.emptyList(), true},  // edge: empty string
                // large test: 300 'a's with dict = {"a"}
                {String.join("", Collections.nCopies(300, "a")),
                        Collections.singletonList("a"), true}
        };

        // Execute each test and report result
        for (int i = 0; i < tests.length; i++) {
            String s = (String) tests[i][0];
            @SuppressWarnings("unchecked")
            List<String> dict = (List<String>) tests[i][1];
            boolean expected = (boolean) tests[i][2];

            boolean result = wordBreak(s, dict);
            String status = (result == expected) ? "PASS" : "FAIL";
            System.out.printf(
                    "Test %2d: %s (got=%b, expected=%b)%n",
                    i + 1, status, result, expected
            );
        }
    }
}