package com.interview.notes.code.year.y2025.march.jpmc.test2;

import java.util.stream.IntStream;

public class SameSubstring {

    // Finds maximum length substring that can match with cost <= K
    public static int sameSubstring(String s, String t, int K) {
        int maxLen = 0;

        // Try all possible starting positions to compare substrings
        for (int shift = -s.length(); shift <= s.length(); shift++) {
            int cost = 0;
            int start = Math.max(0, -shift);

            // Sliding window over each shifted position
            for (int end = start; end < s.length() && end + shift < t.length(); end++) {
                cost += Math.abs(s.charAt(end) - t.charAt(end + shift));

                while (cost > K) {
                    cost -= Math.abs(s.charAt(start) - t.charAt(start + shift));
                    start++;
                }
                maxLen = Math.max(maxLen, end - start + 1);
            }
        }
        return maxLen;
    }

    // Main method for simple tests without JUnit
    public static void main(String[] args) {
        test("adpgki", "cdmxki", 6, 3);
        test("uaccd", "gbbeg", 4, 3);
        test("hffk", "larb", 3, 0);
        test("ikgpda", "ikxmdc", 6, 3);

        // Large data input test
        String largeS = IntStream.range(0, 100000).mapToObj(i -> "a").reduce("", String::concat);
        String largeT = IntStream.range(0, 100000).mapToObj(i -> "b").reduce("", String::concat);
        test(largeS, largeT, 100000, 100000);

        // Edge cases
        test("a", "a", 0, 1);
        test("a", "b", 0, 0);
    }

    // Helper method to test each case
    private static void test(String s, String t, int K, int expected) {
        int result = sameSubstring(s, t, K);
        String status = (result == expected) ? "PASS" : "FAIL";
        System.out.println(String.format("Test case s='%s', t='%s', K=%d: %s (Output: %d, Expected: %d)", s, t, K, status, result, expected));
    }
}
