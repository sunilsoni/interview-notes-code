package com.interview.notes.code.months.year2023.nov23.test5;

import java.util.Arrays;
import java.util.List;

/**
 * String segmentation
 * You are given a string S and a dictionary of strings
 * wordDict. Write a program that returns true if Scan
 * be segmented into a space-separated sequence of
 * one or more dictionary words, else return false.
 * Note: The same word in the dictionary may be
 * reused multiple times in the segmentation.
 * For Example:
 * Input:      S = "applepenapple", wordDict =
 * ("apple","pen"]
 * Output: true
 * This should return true because "applepenapple"
 * can be segmented as "apple pen apple". Since you
 * are allowed to reuse a dictionary word.
 * Input
 * The first line of input contains a string S.
 * The second line of input contains an integer N,
 * representing the size of the wordDict.
 * The third line of input contains N space-separated
 * strings, representing the words in the dictionary.
 * Output
 * Print true if Scan be segmented into a space-
 * separated sequence, otherwise print false.
 */
public class StringSegmentationSolver {

    // Method to check if the string can be segmented into the words from the dictionary
    public static boolean canSegmentString(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // base case - an empty string is always "segmentable"

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // If the string up to j can be segmented and the substring from j to i is in the dictionary
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true; // the string up to i can also be segmented
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    // Main method to test the function
    public static void main(String[] args) {
        List<String> wordDict = Arrays.asList("apple", "pen", "cats", "dog", "sand", "and", "cat");
        String s = "catsandog";
        System.out.println(canSegmentString(s, wordDict)); // Output: false
    }
}
