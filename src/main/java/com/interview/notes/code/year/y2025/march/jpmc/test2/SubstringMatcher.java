package com.interview.notes.code.year.y2025.march.jpmc.test2;

import java.util.*;

public class SubstringMatcher {

    // Method to find the longest substring with total change cost <= K
    public static int sameSubstring(String s, String t, int K) {
        int maxLength = 0; // longest substring length
        int cost = 0; // total cost of current substring
        int left = 0; // left index of sliding window

        // Iterate through characters with sliding window technique
        for (int right = 0; right < s.length(); right++) {
            // cost to change current character in s to match t
            cost += Math.abs(s.charAt(right) - t.charAt(right));

            // If total cost exceeds K, move the left pointer to reduce cost
            while (cost > K) {
                cost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            // Update maximum length found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Simple test method to check test cases (no JUnit)
    public static void main(String[] args) {
        runTest("uaccd", "gbbeg", 4, 3);
        runTest("hffk", "larb", 3, 0);
        runTest("adpgki", "cdmxki", 6, 3);

        // Large test case
        String largeS = "a".repeat(200000);
        String largeT = "b".repeat(200000);
        runTest(largeS, largeT, 200000, 200000);
    }

    // Helper method to run and check a test case
    private static void runTest(String s, String t, int K, int expected) {
        int result = sameSubstring(s, t, K);
        if (result == expected) {
            System.out.println("Test PASSED (Expected: " + expected + " Got: " + result + ")");
        } else {
            System.out.println("Test FAILED (Expected: " + expected + " Got: " + result + ")");
        }
    }
}
