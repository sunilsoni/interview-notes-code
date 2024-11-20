package com.interview.notes.code.months.nov24.amazon.test24;

public class DominantStringFinder {

    public static long getDominantStringCount(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        long dominantCount = 0;
        int n = s.length();

        // For each possible substring length (even only)
        for (int len = 2; len <= n; len += 2) {
            // For each possible starting position
            for (int i = 0; i <= n - len; i++) {
                // Check if the substring from i to i+len is dominant
                if (isDominantSubstring(s, i, i + len)) {
                    dominantCount++;
                }
            }
        }

        return dominantCount;
    }

    private static boolean isDominantSubstring(String s, int start, int end) {
        int len = end - start;
        int targetFreq = len / 2;  // The frequency we're looking for

        // Count frequencies of characters in this substring
        int[] freq = new int[26];  // Assuming lowercase a-j only
        for (int i = start; i < end; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Check if any character appears exactly half the length times
        for (int f : freq) {
            if (f == targetFreq) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Test Cases with detailed output
        runDetailedTest("aabb", 4);
        runDetailedTest("aaaa", 6);
        runDetailedTest("abab", 4);
        runDetailedTest("aaaaid", 3);
        runDetailedTest("idafddfii", 13);
        runDetailedTest("", 0);
        runDetailedTest("a", 0);

        // Optional: Test with larger input
        testLargeInput();
    }

    private static void runDetailedTest(String input, long expected) {
        System.out.println("\nTesting string: " + input);
        System.out.println("Expected count: " + expected);

        long result = getDominantStringCount(input);
        boolean passed = (result == expected);

        System.out.println("Actual result: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));

        if (!passed) {
            System.out.println("Dominant substrings found:");
            printDominantSubstrings(input);
        }
    }

    private static void printDominantSubstrings(String s) {
        int n = s.length();
        for (int len = 2; len <= n; len += 2) {
            for (int i = 0; i <= n - len; i++) {
                if (isDominantSubstring(s, i, i + len)) {
                    System.out.println(s.substring(i, i + len));
                }
            }
        }
    }

    private static void testLargeInput() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("ab");
        }
        String largeInput = sb.toString();
        System.out.println("\nTesting large input (length: " + largeInput.length() + ")");
        long startTime = System.currentTimeMillis();
        long result = getDominantStringCount(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Result: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
    }
}
