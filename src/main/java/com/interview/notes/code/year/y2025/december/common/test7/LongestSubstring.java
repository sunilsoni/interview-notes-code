package com.interview.notes.code.year.y2025.december.common.test7;

import java.util.HashSet;

public class LongestSubstring {

    // Method to find longest substring without repeating chars
    static int findLongest(String s) {
        // Handle null or empty string edge case
        if (s == null || s.isEmpty()) return 0;

        // Set to store chars in current window
        var seen = new HashSet<Character>();

        // Left pointer of sliding window
        int left = 0;

        // Track maximum length found
        int maxLen = 0;

        // Right pointer moves through string
        for (int right = 0; right < s.length(); right++) {

            // Current character at right pointer
            char curr = s.charAt(right);

            // If char already in window, shrink from left
            while (seen.contains(curr)) {
                // Remove leftmost char from set
                seen.remove(s.charAt(left));
                // Move left pointer forward
                left++;
            }

            // Add current char to window
            seen.add(curr);

            // Update max length (window size = right - left + 1)
            maxLen = Math.max(maxLen, right - left + 1);
        }

        // Return the maximum length found
        return maxLen;
    }

    // Test helper - checks if result matches expected
    static void test(String name, String input, int expected) {
        // Get actual result from our method
        int actual = findLongest(input);

        // Compare and print PASS or FAIL
        String status = (actual == expected) ? "PASS" : "FAIL";

        // Print test result with details
        System.out.println(status + " | " + name + " | Input: \"" + input +
                "\" | Expected: " + expected + " | Got: " + actual);
    }

    public static void main(String[] args) {

        System.out.println("=== Testing Longest Substring Without Repeating ===\n");

        // Basic test cases
        test("Basic 1", "abc", 3);           // All unique
        test("Basic 2", "cde", 3);           // All unique
        test("Basic 3", "abcabcbb", 3);      // "abc" is longest
        test("Basic 4", "bbbbb", 1);         // All same char
        test("Basic 5", "pwwkew", 3);        // "wke" or "kew"

        // Edge cases
        test("Empty", "", 0);                // Empty string
        test("Single", "a", 1);              // One char
        test("Two Same", "aa", 1);           // Two same chars
        test("Two Diff", "ab", 2);           // Two different

        // Special cases
        test("Space", "a b c", 3);           // With spaces
        test("Numbers", "12321", 3);         // Numeric chars
        test("Mixed", "a1b2c3", 6);          // All unique mixed

        // Repeating patterns
        test("Pattern", "abba", 2);          // "ab" or "ba"
        test("Long unique", "abcdefghij", 10); // 10 unique

        // Large data test
        StringBuilder large = new StringBuilder();
        // Build string with repeating pattern
        for (int i = 0; i < 100000; i++) {
            large.append((char) ('a' + i % 26)); // a-z repeating
        }
        test("Large 100K", large.toString(), 26); // Max 26 unique letters

        // Very large - all unique using extended chars
        StringBuilder allUnique = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            allUnique.append((char) (i + 256)); // Unique chars
        }
        test("Large Unique 10K", allUnique.toString(), 10000);

        System.out.println("\n=== All Tests Completed ===");
    }
}