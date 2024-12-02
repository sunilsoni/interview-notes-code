package com.interview.notes.code.year.y2024.sept24.meta.test1;

import java.util.ArrayList;
import java.util.List;

public class PalindromicSubstrings {

    // Method to find all palindromic substrings
    public static List<String> findPalindromicSubstrings(String s) {
        List<String> result = new ArrayList<>();

        // Edge case: if the string is empty, return an empty list
        if (s == null || s.length() == 0) {
            return result;
        }

        int n = s.length();

        // Expand around each center (for both odd and even length palindromes)
        for (int i = 0; i < n; i++) {
            // Odd length palindromes (single character center)
            expandAroundCenter(s, i, i, result);
            // Even length palindromes (two characters center)
            expandAroundCenter(s, i, i + 1, result);
        }

        return result;
    }

    // Helper method to expand around the center and find palindromes
    private static void expandAroundCenter(String s, int left, int right, List<String> result) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            result.add(s.substring(left, right + 1));
            left--;
            right++;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        String input = "aabbaab";
        List<String> palindromes = findPalindromicSubstrings(input);

        // Print the result
        System.out.println("Palindromic substrings: " + palindromes);
    }
}
