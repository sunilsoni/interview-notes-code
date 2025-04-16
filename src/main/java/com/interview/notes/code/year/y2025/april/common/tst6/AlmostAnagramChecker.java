package com.interview.notes.code.year.y2025.april.common.tst6;

import java.util.Arrays;

public class AlmostAnagramChecker {
    public static boolean areAlmostAnagrams(String str1, String str2) {
        // Handle null cases
        if (str1 == null || str2 == null) return false;

        // Convert to lowercase and remove spaces
        str1 = str1.toLowerCase().replaceAll("\\s", "");
        str2 = str2.toLowerCase().replaceAll("\\s", "");

        // Check length difference - should be 0 or 1
        int lengthDiff = Math.abs(str1.length() - str2.length());
        if (lengthDiff > 1) return false;

        // Make str1 always the shorter (or equal) string
        if (str1.length() > str2.length()) {
            String temp = str1;
            str1 = str2;
            str2 = temp;
        }

        // Convert to char arrays and sort
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);

        // Two pointers comparison
        int i = 0, j = 0;
        int diffCount = 0;

        while (i < chars1.length && j < chars2.length) {
            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            } else {
                diffCount++;
                if (diffCount > 1) return false;
                // Skip character in longer string
                j++;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Test cases
        testAlmostAnagram("abc", "abcd", true);    // One extra 'd'
        testAlmostAnagram("abcd", "abc", true);    // One extra 'd'
        testAlmostAnagram("abc", "abd", false);    // Different characters
        testAlmostAnagram("abc", "abcde", false);  // Too many extra characters
        testAlmostAnagram("silent", "listen", true); // Perfect anagram
        testAlmostAnagram("hello", "hello", true);  // Same strings
        testAlmostAnagram("", "a", true);          // One char difference
        testAlmostAnagram("a", "", true);          // One char difference
        testAlmostAnagram("", "", true);           // Empty strings
        testAlmostAnagram(null, "test", false);    // Null case
        testAlmostAnagram("test", null, false);    // Null case
    }

    private static void testAlmostAnagram(String str1, String str2, boolean expectedResult) {
        boolean result = areAlmostAnagrams(str1, str2);
        System.out.printf("Test: '%s' and '%s' -> %s%n",
                str1, str2, result == expectedResult ? "PASS" : "FAIL");
    }
}
