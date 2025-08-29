package com.interview.notes.code.year.y2025.august.common.test17;

import java.util.*;

public class LongestUniqueSubstring {

    // Method to find the longest substring without repeating characters
    public static String longestSubstring(String s) {
        // Store characters currently in the window
        Set<Character> window = new HashSet<>();
        
        int left = 0;  // left boundary of sliding window
        int maxLength = 0; // length of longest substring
        int startIndex = 0; // starting index of longest substring found
        
        // Traverse the string with right pointer
        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            // If duplicate found, shrink window from left
            while (window.contains(current)) {
                window.remove(s.charAt(left));
                left++;
            }

            // Add current char to window
            window.add(current);

            // Update longest substring if current window is larger
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                startIndex = left;
            }
        }

        // Return the longest substring
        return s.substring(startIndex, startIndex + maxLength);
    }

    // Testing main method
    public static void main(String[] args) {
        // Test cases
        String[] inputs = {
            "abcabcbb",
            "bbbbb",
            "pwwkew",
            "abcdefg",
            "aab",
            "dvdf",
            "a",          // single char
            "",           // empty string
            "abcdefghijabcdefghij" // large input with repetition
        };

        String[] expected = {
            "abc",
            "b",
            "wke",
            "abcdefg",
            "ab",
            "vdf",
            "a",
            "",
            "abcdefghij"
        };

        // Run all test cases
        for (int i = 0; i < inputs.length; i++) {
            String result = longestSubstring(inputs[i]);
            System.out.println("Input: " + inputs[i]);
            System.out.println("Output: " + result);
            System.out.println(result.equals(expected[i]) ? "PASS" : "FAIL");
            System.out.println("-------------------");
        }

        // Large random input test (100 characters)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        System.out.println("Large Input Test Result: " + longestSubstring(sb.toString()));
    }
}