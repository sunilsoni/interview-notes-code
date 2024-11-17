package com.interview.notes.code.months.nov24.amazon.test21;

import java.util.*;

public class DominantSubstrings {

    public static int getDominantStringCount(String s) {
        int n = s.length();
        int total = 0;

        // Iterate over all possible even lengths
        for (int len = 2; len <= n; len += 2) {
            int halfLen = len / 2;
            int[] freq = new int[26];
            // Initialize frequency for the first window
            for (int i = 0; i < len; i++) {
                freq[s.charAt(i) - 'a']++;
            }
            // Check the first window
            if (hasHalfOrMoreFrequency(freq, halfLen)) {
                total++;
            }
            // Slide the window
            for (int i = len; i < n; i++) {
                // Remove the leftmost character
                freq[s.charAt(i - len) - 'a']--;
                // Add the new character
                freq[s.charAt(i) - 'a']++;
                // Check the current window
                if (hasHalfOrMoreFrequency(freq, halfLen)) {
                    total++;
                }
            }
        }

        return total;
    }

    // Helper method to check if any character has frequency >= halfLen
    private static boolean hasHalfOrMoreFrequency(int[] freq, int halfLen) {
        for (int count : freq) {
            if (count >= halfLen) {
                return true;
            }
        }
        return false;
    }

    // Method to run test cases
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = Arrays.asList(
            new TestCase("aaaaaid", 3),
            new TestCase("aidfg", 4),
            new TestCase("dfdffdfi", 13),
            new TestCase("a", 0),
            new TestCase("ab", 1),
            new TestCase("abab", 2),
            new TestCase("abcabc", 3),
            new TestCase("aabbcc", 3),
            new TestCase("abcdeffedcba", 6),
            new TestCase("a" + "b".repeat(50000) + "a", 50001) // Large input
        );

        // Run and evaluate each test case
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = getDominantStringCount(tc.input);
            if (result == tc.expected) {
                System.out.println("Test Case " + i + ": PASS");
            } else {
                System.out.println("Test Case " + i + ": FAIL (Expected " + tc.expected + ", Got " + result + ")");
            }
        }
    }

    // Inner class to represent a test case
    static class TestCase {
        String input;
        int expected;

        TestCase(String input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}