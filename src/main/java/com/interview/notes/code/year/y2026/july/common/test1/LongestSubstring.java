package com.interview.notes.code.year.y2026.july.common.test1;

import java.util.HashMap;
import java.util.stream.Stream;

public class LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        // Map tracks each character and the exact index where we last saw it to avoid nested loops.
        var seen = new HashMap<Character, Integer>();

        // Keeps track of the longest valid window length we have found so far.
        int maxLen = 0;

        // For-loop expands the window using 'right' pointer, while 'left' marks the start boundary.
        for (int right = 0, left = 0; right < s.length(); right++) {

            // Extract the current character at the right edge of our sliding window.
            var ch = s.charAt(right);

            // If we've seen this character before in our current window, we have a duplicate.
            if (seen.containsKey(ch)) {
                // Move the left pointer just past the old duplicate to make the window valid again.
                // Math.max ensures the left pointer never moves backwards.
                left = Math.max(left, seen.get(ch) + 1);
            }

            // Calculate current window size (right - left + 1) and update global maximum if it is larger.
            maxLen = Math.max(maxLen, right - left + 1);

            // Update the map with the most recent index of the current character for future checks.
            seen.put(ch, right);
        }

        // Return the highest length recorded after checking the entire string.
        return maxLen;
    }

    public static void main(String[] args) {
        // Generate a massive string (260,000+ chars) to verify large data handling doesn't time out.
        var largeInput = "abcdefghijklmnopqrstuvwxyz".repeat(10000) + "1234567890";

        // Stream API (Java 8+) to process test cases clearly without writing boilerplate loops.
        Stream.of(
            new TestCase("abcabcbb", 3), // Standard case with repeating characters scattered
            new TestCase("bbbbb", 1),    // Edge case: all identical characters (max length is 1)
            new TestCase("pwwkew", 3),   // Case where the longest string is in the middle, not edges
            new TestCase("", 0),         // Edge case: empty string must return 0 without crashing
            new TestCase(" ", 1),        // Edge case: single space character is a valid length of 1
            new TestCase("dvdf", 3),     // Edge case: duplicate character forces left pointer to jump correctly
            new TestCase(largeInput, 36) // Large data case checking O(N) performance and execution speed
        ).forEach(test -> {

            // Execute our sliding window logic against the current test input.
            int result = lengthOfLongestSubstring(test.input());

            // Compare the actual result with the expected integer to determine PASS or FAIL state.
            var status = (result == test.expected()) ? "PASS" : "FAIL";

            // Keep console output clean by truncating the massive input string for display purposes.
            var displayInput = test.input().length() > 20 ? "LARGE_DATA_STRING" : test.input();

            // Print the formatted test result directly to the console for easy verification.
            System.out.printf("[%s] Input: '%s' | Expected: %d | Got: %d%n", status, displayInput, test.expected(), result);
        });
    }

    // Record (Java 14+) creates an immutable class in one line to hold test data cleanly.
    record TestCase(String input, int expected) {}
}