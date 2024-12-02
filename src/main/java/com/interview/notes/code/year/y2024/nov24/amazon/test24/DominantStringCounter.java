package com.interview.notes.code.year.y2024.nov24.amazon.test24;

public class DominantStringCounter {
    public static long getDominantStringCount(String s) {
        long dominantCount = 0;
        int n = s.length();

        // Loop through all even length substrings
        for (int length = 2; length <= n; length += 2) {
            for (int start = 0; start <= n - length; start++) {
                String substring = s.substring(start, start + length);
                if (isDominant(substring)) {
                    dominantCount++;
                }
            }
        }

        return dominantCount;
    }

    private static boolean isDominant(String substring) {
        int[] frequency = new int[10]; // Since 'a' to 'j', we have 10 characters
        int halfLength = substring.length() / 2;

        // Count frequency of each character
        for (char c : substring.toCharArray()) {
            frequency[c - 'a']++;
        }

        // Check if any character has a frequency of exactly half the length
        for (int count : frequency) {
            if (count == halfLength) {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        // Test cases
        runTest("aaaaid", 3, "Sample Case 0");
        runTest("abab", 4, "Sample Case 1");
        runTest("idafddfii", 13, "Example Case");

        // Edge cases
        runTest("", 0, "Empty string");
        runTest("a", 0, "Single character");
        runTest("aa", 1, "Two same characters");

        // Additional test cases
        runTest("aabbcc", 6, "Multiple pairs");

        // Large input test
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            large.append((char) ('a' + i % 10));
        }
        long start = System.currentTimeMillis();
        getDominantStringCount(large.toString());
        long end = System.currentTimeMillis();
        System.out.println("Large input (100000 chars) processed in: " + (end - start) + "ms");
    }

    private static void runTest(String input, long expected, String testName) {
        long result = getDominantStringCount(input);
        boolean passed = result == expected;
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL") +
                " (Expected: " + expected + ", Got: " + result + ")");
    }
}
