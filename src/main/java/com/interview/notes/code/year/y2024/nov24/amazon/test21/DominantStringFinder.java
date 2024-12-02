package com.interview.notes.code.year.y2024.nov24.amazon.test21;

import java.util.HashSet;
import java.util.Set;

public class DominantStringFinder {

    public static int getDominantStringCount(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        // Use Set to store unique dominant substrings
        Set<String> dominantStrings = new HashSet<>();
        int n = s.length();

        // Consider all possible substrings of even length
        for (int len = 2; len <= n; len += 2) {
            for (int i = 0; i <= n - len; i++) {
                String substr = s.substring(i, i + len);
                if (isDominant(substr)) {
                    dominantStrings.add(substr);
                }
            }
        }

        return dominantStrings.size();
    }

    private static boolean isDominant(String str) {
        int[] freq = new int[26];
        int len = str.length();
        int target = len / 2;

        // Count frequency of each character
        for (char c : str.toCharArray()) {
            freq[c - 'a']++;
            if (freq[c - 'a'] > target) {
                return false; // Optimization: early exit if frequency exceeds target
            }
        }

        // Check if any character has frequency equal to half the length
        for (int f : freq) {
            if (f == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Test cases
        testCase("aaaaaid", 3, "Test Case 1");
        testCase("aidfg", 4, "Test Case 2");
        testCase("dfdffdfi", 13, "Test Case 3");

        // Edge cases
        testCase("", 0, "Empty String");
        testCase("a", 0, "Single Character");
        testCase("aa", 1, "Two Same Characters");

        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append((char) ('a' + (i % 26)));
        }
        long startTime = System.currentTimeMillis();
        testCase(largeInput.toString(), -1, "Large Input Test");
        long endTime = System.currentTimeMillis();
        System.out.println("Large input processing time: " + (endTime - startTime) + "ms");
    }

    private static void testCase(String input, int expected, String testName) {
        try {
            int result = getDominantStringCount(input);
            if (expected == -1 || result == expected) {
                System.out.println("PASS: " + testName);
            } else {
                System.out.println("FAIL: " + testName);
                System.out.println("Expected: " + expected + ", Got: " + result);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + testName);
            System.out.println("Exception: " + e.getMessage());
        }
    }
}