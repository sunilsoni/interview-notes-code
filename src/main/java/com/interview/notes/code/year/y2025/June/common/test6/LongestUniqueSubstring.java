package com.interview.notes.code.year.y2025.June.common.test6;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {

    // Method to find length of longest substring without repeating characters
    public static int lengthOfLongestUniqueSubstring(String s) {
        // This map stores the last seen index of each character
        Map<Character, Integer> seen = new HashMap<>();

        int maxLength = 0; // Final result: stores the length of longest substring
        int start = 0;     // Start index of current window (substring)

        // Loop through the characters of the string
        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end); // Current character in the loop

            // If character is seen before and is within the current window
            if (seen.containsKey(currentChar) && seen.get(currentChar) >= start) {
                // Move the start to the index after the last occurrence
                start = seen.get(currentChar) + 1;
            }

            // Update the last seen index of the current character
            seen.put(currentChar, end);

            // Calculate the max length by comparing current window size
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    // Method to test cases with expected output
    public static void runTests() {
        test("akspa", 4);        // Expected: "kspa" → 4
        test("abcabcbb", 3);     // Expected: "abc" → 3
        test("bbbbb", 1);        // Expected: "b" → 1
        test("pwwkew", 3);       // Expected: "wke" → 3
        test("", 0);             // Empty string
        test("abcdef", 6);       // All unique
        test("abba", 2);         // "ab" or "ba"
        test(generateLargeInput(), 26);  // Edge case large input
    }

    // Utility method to generate large input with 26 unique chars
    private static String generateLargeInput() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append((char) ('a' + (i % 26))); // repeat a-z
        }
        return sb.toString();
    }

    // Simple test method to check pass/fail
    private static void test(String input, int expected) {
        int actual = lengthOfLongestUniqueSubstring(input);
        if (actual == expected) {
            System.out.println("PASS | Input: " + input + " | Output: " + actual);
        } else {
            System.out.println("FAIL | Input: " + input + " | Expected: " + expected + " | Got: " + actual);
        }
    }

    public static void main(String[] args) {
        runTests(); // Run all test cases
    }
}