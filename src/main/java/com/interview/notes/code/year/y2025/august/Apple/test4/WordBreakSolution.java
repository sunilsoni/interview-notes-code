package com.interview.notes.code.year.y2025.august.Apple.test4;

import java.util.*;

public class WordBreakSolution {

    // Main method to solve word break problem using dynamic programming
    public static boolean wordBreak(String s, List<String> wordDict) {
        // Create a HashSet for O(1) lookup of dictionary words
        Set<String> dictionary = new HashSet<>(wordDict);

        // dp[i] represents if substring(0,i) can be segmented into dictionary words
        boolean[] dp = new boolean[s.length() + 1];

        // Empty string is always valid
        dp[0] = true;

        // Iterate through each position in string
        for (int i = 1; i <= s.length(); i++) {
            // Check all possible substrings ending at position i
            for (int j = 0; j < i; j++) {
                // If previous part is valid and current substring exists in dictionary
                if (dp[j] && dictionary.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        // Return if entire string can be segmented
        return dp[s.length()];
    }

    // Test method to verify solution
    public static void main(String[] args) {
        // Test case 1: Basic case
        test("leetcode", Arrays.asList("leet", "code"), true);

        // Test case 2: Multiple words
        test("applepenapple", Arrays.asList("apple", "pen"), true);

        // Test case 3: Invalid segmentation
        test("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"), false);

        // Test case 4: Empty string
        test("", Arrays.asList(""), true);

        // Test case 5: Large input
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("a");
        }
        List<String> largeDict = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            largeDict.add("a".repeat(i));
        }
        test(sb.toString(), largeDict, true);
    }

    // Helper method to run test cases
    private static void test(String s, List<String> wordDict, boolean expected) {
        boolean result = wordBreak(s, wordDict);
        System.out.println("Input: s = \"" + s + "\", wordDict = " + wordDict);
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println("Test " + (result == expected ? "PASSED" : "FAILED"));
        System.out.println("---");
    }
}
