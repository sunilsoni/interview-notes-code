package com.interview.notes.code.months.nov24.amazon.test24;

public class DominantStringCounter1 {
    public static long getDominantStringCount(String s) {
        if (s == null || s.length() < 2) return 0;

        long count = 0;
        int n = s.length();

        // Try all possible even lengths
        for (int len = 2; len <= n; len += 2) {
            // Initialize frequency array for first window
            int[] freq = new int[10]; // a-j only

            // Process first window
            for (int i = 0; i < len; i++) {
                freq[s.charAt(i) - 'a']++;
            }

            // Check if first window is dominant
            if (isDominant(freq, len)) count++;

            // Slide window
            for (int i = len; i < n; i++) {
                freq[s.charAt(i - len) - 'a']--; // remove leftmost
                freq[s.charAt(i) - 'a']++; // add new rightmost
                if (isDominant(freq, len)) count++;
            }
        }

        return count;
    }

    private static boolean isDominant(int[] freq, int len) {
        int half = len / 2;
        for (int f : freq) {
            if (f == half) return true;
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
