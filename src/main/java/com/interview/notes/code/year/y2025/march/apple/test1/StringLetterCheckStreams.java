package com.interview.notes.code.year.y2025.march.apple.test1;

import java.util.Set;
import java.util.stream.Collectors;

public class StringLetterCheckStreams {

    /**
     * Checks if string B can be formed using letters from string A.
     * Each letter from A can be used as many times as needed.
     * Uses Java 8 streams for processing.
     *
     * @param a The source string containing available letters
     * @param b The target string to be formed
     * @return true if B can be formed using letters from A, false otherwise
     */
    public static boolean canFormString(String a, String b) {
        // Edge cases
        if (b == null || b.isEmpty()) {
            return true; // Empty string can always be formed
        }

        if (a == null || a.isEmpty()) {
            return false; // Cannot form any non-empty string from empty source
        }

        // Create a set of unique characters in string A
        Set<Character> charSet = a.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());

        // Check if each character in B exists in A's character set
        return b.chars()
                .mapToObj(c -> (char) c)
                .allMatch(charSet::contains);
    }

    public static void main(String[] args) {
        // Same test framework as in the previous implementation
        testCase("northern lights", "night", true);
        testCase("traveler", "arave", true);
        testCase("never mind", "tidy", false);

        // Additional test cases
        testCase("abcdefg", "abcdefg", true);
        testCase("abcdefg", "abcdefgh", false);
        testCase("xyz", "", true);
        testCase("", "a", false);
        testCase("abc", "aaa", true);
        testCase("abc", "aaabbbccc", true);

        // Large data input test
        StringBuilder largeA = new StringBuilder();
        StringBuilder largeB = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeA.append("abcdefghijklmnopqrstuvwxyz");
            if (i % 2 == 0) {
                largeB.append("ababababab");
            } else {
                largeB.append("cdcdcdcdcd");
            }
        }

        long startTime = System.nanoTime();
        boolean result = canFormString(largeA.toString(), largeB.toString());
        long endTime = System.nanoTime();

        System.out.println("Large data test result: " + (result ? "PASS" : "FAIL") +
                " (Expected: PASS, Processing time: " +
                (endTime - startTime) / 1_000_000.0 + " ms)");
    }

    private static void testCase(String a, String b, boolean expected) {
        boolean result = canFormString(a, b);
        String status = (result == expected) ? "PASS" : "FAIL";
        System.out.println("Test: A = '" + a + "', B = '" + b +
                "' => " + result + " (" + status + ", Expected: " + expected + ")");
    }
}
