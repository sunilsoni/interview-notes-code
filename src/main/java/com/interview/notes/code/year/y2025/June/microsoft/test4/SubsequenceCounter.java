package com.interview.notes.code.year.y2025.June.microsoft.test4;

import java.util.*;

public class SubsequenceCounter {
    
    /**
     * Counts how many words from the input array are subsequences of the main string
     * @param s The main string to check against
     * @param words Array of potential subsequence strings
     * @return Count of valid subsequences
     */
    public static int numMatchingSubseq(String s, String[] words) {
        // Using Java 8 Streams to count matching subsequences
        return (int) Arrays.stream(words)
                         .filter(word -> isSubsequence(s, word))
                         .count();
    }

    /**
     * Checks if word is a subsequence of main string using two-pointer technique
     * @param main The main string
     * @param sub Potential subsequence string
     * @return true if sub is a subsequence of main
     */
    private static boolean isSubsequence(String main, String sub) {
        // If sub is longer than main, it can't be a subsequence
        if (sub.length() > main.length()) return false;
        
        int mainIndex = 0;  // Pointer for main string
        int subIndex = 0;   // Pointer for subsequence string
        
        // Iterate through both strings
        while (mainIndex < main.length() && subIndex < sub.length()) {
            // If characters match, move subsequence pointer
            if (main.charAt(mainIndex) == sub.charAt(subIndex)) {
                subIndex++;
            }
            mainIndex++;  // Always move main string pointer
        }
        
        // If we processed all characters in sub, it's a valid subsequence
        return subIndex == sub.length();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic test
        runTest("abcde", 
                new String[]{"a", "bb", "ace", "aec"}, 
                2,
                "Basic Test");

        // Test Case 2: Empty words
        runTest("abc", 
                new String[]{""}, 
                1,
                "Empty Word Test");

        // Test Case 3: Repeated characters
        runTest("aaaa", 
                new String[]{"a", "aa", "aaa", "aaaa"}, 
                4,
                "Repeated Characters Test");

        // Test Case 4: Large string test
        StringBuilder largeSb = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            largeSb.append('a');
        }
        runTest(largeSb.toString(), 
                new String[]{"a", "aa", "aaa"}, 
                3,
                "Large String Test");
    }

    /**
     * Helper method to run tests and print results
     */
    private static void runTest(String s, String[] words, int expected, String testName) {
        int result = numMatchingSubseq(s, words);
        System.out.println(testName + ": " + 
                         (result == expected ? "PASS" : "FAIL") +
                         " (Expected: " + expected + ", Got: " + result + ")");
    }
}
