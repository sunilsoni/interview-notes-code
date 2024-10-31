package com.interview.notes.code.months.oct24.amazon.test2;

import java.util.HashMap;
import java.util.Map;

public class DominantString {

    public static long getDominantStringCount(String s) {
        long count = 0;

        // Iterate through all even-length substrings
        for (int length = 2; length <= s.length(); length += 2) {
            for (int i = 0; i <= s.length() - length; i++) {
                String substring = s.substring(i, i + length);
                if (isDominant(substring)) {
                    count++;
                }
            }
        }
        return count;
    }

    // Helper function to check if a substring is dominant
    private static boolean isDominant(String substring) {
        Map<Character, Integer> charFrequency = new HashMap<>();
        int halfLength = substring.length() / 2;

        // Count character frequencies
        for (char c : substring.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        // Check if any character has frequency equal to half the length
        for (int freq : charFrequency.values()) {
            if (freq == halfLength) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Test cases
        String testCase1 = "aaaaaid";
        String testCase2 = "aidfg";
        String testCase3 = "dfdffdfi";

        System.out.println("Test Case 1 (Expected: 3): " + getDominantStringCount(testCase1));  // Output: 3
        System.out.println("Test Case 2 (Expected: 4): " + getDominantStringCount(testCase2));  // Output: 4
        System.out.println("Test Case 3 (Expected: 13): " + getDominantStringCount(testCase3)); // Output: 13
    }
}
