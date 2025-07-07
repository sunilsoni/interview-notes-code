package com.interview.notes.code.year.y2025.June.common.test1;

import java.util.*;

public class WordBreak {
    
    // Main method to solve word break problem using dynamic programming
    public static boolean wordBreak(String s, List<String> wordDict) {
        // Create a Set from wordDict for O(1) lookup time
        Set<String> dictionary = new HashSet<>(wordDict);
        
        // dp[i] represents if substring(0,i) can be segmented into dictionary words
        boolean[] dp = new boolean[s.length() + 1];
        
        // Empty string is always valid
        dp[0] = true;
        
        // Iterate through all possible substrings
        for (int i = 1; i <= s.length(); i++) {
            // Check all possible splits of substring(0,i)
            for (int j = 0; j < i; j++) {
                // If substring(0,j) is valid and substring(j,i) is in dictionary
                // then substring(0,i) is valid
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
        // Test Case 1: Basic case
        test("leetcode", Arrays.asList("leet", "code"), true);
        
        // Test Case 2: Reusing words
        test("applepenapple", Arrays.asList("apple", "pen"), true);
        
        // Test Case 3: Invalid segmentation
        test("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"), false);
        
        // Test Case 4: Empty string
        test("", Arrays.asList(""), true);
        
        // Test Case 5: Single character
        test("a", Arrays.asList("a"), true);
        
        // Test Case 6: Large input
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            sb.append('a');
        }
        test(sb.toString(), Arrays.asList("a", "aa", "aaa"), true);
    }
    
    // Helper method to run test cases
    private static void test(String s, List<String> wordDict, boolean expected) {
        boolean result = wordBreak(s, wordDict);
        System.out.println("Input: s = \"" + s + "\", wordDict = " + wordDict);
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println("Test " + (result == expected ? "PASSED" : "FAILED"));
        System.out.println();
    }
}
