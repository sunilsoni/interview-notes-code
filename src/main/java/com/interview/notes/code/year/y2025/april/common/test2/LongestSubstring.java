package com.interview.notes.code.year.y2025.april.common.test2;

public class LongestSubstring {
    public static int findLongestSubstring(String str) {
        // Handle edge cases
        if (str == null || str.length() == 0) return 0;
        if (str.length() == 1) return 1;

        // Use array instead of HashMap for better performance
        // ASCII has 128 characters
        int[] lastSeen = new int[128];
        // Initialize with -1
        for (int i = 0; i < 128; i++) {
            lastSeen[i] = -1;
        }

        int maxLength = 0;
        int start = 0;

        // Iterate through string
        for (int end = 0; end < str.length(); end++) {
            char currentChar = str.charAt(end);
            // If character was seen before and its position is after start
            if (lastSeen[currentChar] >= start) {
                start = lastSeen[currentChar] + 1;
            }
            lastSeen[currentChar] = end;
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    // Simple test method
    public static void main(String[] args) {
        // Test cases
        test("abcabcbb", 3);  // abc
        test("bbbbb", 1);     // b
        test("pwwkew", 3);    // wke
        test("", 0);          // empty string
        test("a", 1);         // single char
        test("aab", 2);       // ab
        test("dvdf", 3);      // vdf
        test("abba", 2);      // ab or ba
    }

    private static void test(String input, int expected) {
        int result = findLongestSubstring(input);
        System.out.printf("Input: %s\nExpected: %d, Got: %d\nTest: %s\n\n",
                input, expected, result, result == expected ? "PASS" : "FAIL");
    }
}
