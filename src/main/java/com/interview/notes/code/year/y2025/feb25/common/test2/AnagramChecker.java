package com.interview.notes.code.year.y2025.feb25.common.test2;

import java.util.Arrays;

public class AnagramChecker {

    /**
     * Checks if two strings are anagrams.
     * This method first checks for null values and equal lengths.
     * Then it sorts the characters of both strings and compares the sorted arrays.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return true if s1 and s2 are anagrams, false otherwise
     */
    public static boolean isAnagram(String s1, String s2) {
        // Check for nulls
        if (s1 == null || s2 == null) {
            return false;
        }
        // Quick length check
        if (s1.length() != s2.length()) {
            return false;
        }
        // Convert strings to character arrays
        char[] charArray1 = s1.toCharArray();
        char[] charArray2 = s2.toCharArray();

        // Sort the arrays
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        // Compare sorted arrays
        return Arrays.equals(charArray1, charArray2);
    }

    /**
     * Runs a single test case.
     *
     * @param testId   an identifier for the test case
     * @param s1       the first input string
     * @param s2       the second input string
     * @param expected the expected result (true if anagram, false otherwise)
     */
    public static void runTest(String testId, String s1, String s2, boolean expected) {
        boolean result = isAnagram(s1, s2);
        if (result == expected) {
            System.out.println("Test " + testId + " PASS");
        } else {
            System.out.println("Test " + testId + " FAIL: Expected " + expected + " but got " + result);
        }
    }

    public static void main(String[] args) {
        // 1. Basic test cases
        runTest("1", "listen", "silent", true);
        runTest("2", "triangle", "integral", true);
        runTest("3", "apple", "pabble", false);

        // 2. Edge cases
        // Both strings are empty
        runTest("4", "", "", true);
        // One string is null (should return false)
        runTest("5", null, "abc", false);
        // Different lengths
        runTest("6", "abc", "ab", false);

        // 3. Case sensitivity: "abc" vs "ABC" (this test assumes case sensitivity)
        runTest("7", "abc", "ABC", false);

        // 4. Large data input simulation:
        // Create two large strings that are anagrams of each other.
        int size = 100000; // adjust size as needed for performance testing
        StringBuilder sb1 = new StringBuilder(size);
        StringBuilder sb2 = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            // Using letters a, b, c for simplicity
            char ch = (i % 3 == 0) ? 'a' : (i % 3 == 1) ? 'b' : 'c';
            sb1.append(ch);
        }
        // Shuffle sb1's content for sb2 to guarantee they are anagrams.
        // For simplicity, we can reverse the string.
        sb2.append(sb1).reverse();
        runTest("8", sb1.toString(), sb2.toString(), true);

        // 5. Additional test: non-anagram with large data (alter one character)
        String largeStr1 = sb1.toString();
        StringBuilder sb3 = new StringBuilder(sb2.toString());
        // Alter a single character so they are no longer anagrams
        sb3.setCharAt(0, (sb3.charAt(0) == 'a') ? 'b' : 'a');
        runTest("9", largeStr1, sb3.toString(), false);
    }
}