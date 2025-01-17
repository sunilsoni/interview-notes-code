package com.interview.notes.code.year.y2025.jan24.meta.test2;

import java.util.HashMap;
import java.util.Map;

/*
Q2

Write a function that takes an input string and an alphabet, and returns the shortest substring of the input which contains every letter of the alphabet at least once.

Example:

Input: "aaccbc"
Alphabet: "abc"
Output: "accb"

 */
public class ShortestSubstringContainingAlphabet {

    /**
     * Finds the shortest substring in 'input' that contains
     * all letters of 'alphabet' at least once.
     *
     * @param input    the main string to search
     * @param alphabet the set of characters we need to include
     * @return the shortest substring or "" if none found
     */
    public static String findShortestSubstring(String input, String alphabet) {
        if (input == null || alphabet == null) {
            return "";
        }

        // If the input is shorter than the alphabet, no solution
        if (input.length() < alphabet.length()) {
            return "";
        }

        // Count the required frequency of each letter in the alphabet
        Map<Character, Integer> required = new HashMap<>();
        for (char c : alphabet.toCharArray()) {
            required.put(c, required.getOrDefault(c, 0) + 1);
        }
        int requiredUniqueLetters = required.size();

        // This map will track how many of each letter are in the current window
        Map<Character, Integer> windowCount = new HashMap<>();

        int have = 0;  // how many unique alphabet letters we currently satisfy
        int left = 0;  // window start
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;

        // Expand with right pointer
        for (int right = 0; right < input.length(); right++) {
            char c = input.charAt(right);
            // Update window count
            windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);

            // If c is part of the required map and we now have enough of c
            if (required.containsKey(c) && windowCount.get(c).intValue() == required.get(c).intValue()) {
                have++;
            }

            // Shrink from the left if we have all required letters
            while (have == requiredUniqueLetters) {
                // Update result if this window is smaller
                int windowSize = right - left + 1;
                if (windowSize < minLength) {
                    minLength = windowSize;
                    minStart = left;
                }

                // Try to remove from the left
                char leftChar = input.charAt(left);
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);

                // If removing leftChar breaks the requirement
                if (required.containsKey(leftChar) &&
                        windowCount.get(leftChar).intValue() < required.get(leftChar).intValue()) {
                    have--;
                }
                left++;
            }
        }

        // Return result
        return (minLength == Integer.MAX_VALUE)
                ? ""
                : input.substring(minStart, minStart + minLength);
    }

    /**
     * A helper to compare strings for test results.
     */
    private static boolean stringsAreEqual(String actual, String expected) {
        if (actual == null && expected == null) return true;
        if (actual == null || expected == null) return false;
        return actual.equals(expected);
    }

    public static void main(String[] args) {
        // Test 1
        String input1 = "aacbc";
        String alphabet1 = "abc";
        String result1 = findShortestSubstring(input1, alphabet1);
        // "acb" is a valid shortest substring. If our solution finds "acb" or "cab" (somehow),
        // or even "acbc" if it doesn't shrink optimally, that might differ in length.
        // We expect length 3, so let's check that specifically:
        boolean pass1 = (result1.length() == 3 && containsAllLetters(result1, alphabet1));
        System.out.println("Test 1: " + (pass1 ? "PASS" : "FAIL")
                + " (Got: \"" + result1 + "\")");

        // Test 2 (no possible substring)
        String input2 = "aaaa";
        String alphabet2 = "abc";
        String result2 = findShortestSubstring(input2, alphabet2);
        boolean pass2 = result2.equals("");
        System.out.println("Test 2: " + (pass2 ? "PASS" : "FAIL")
                + " (Got: \"" + result2 + "\")");

        // Test 3 (alphabet is exactly the input)
        String input3 = "abc";
        String alphabet3 = "abc";
        String result3 = findShortestSubstring(input3, alphabet3);
        boolean pass3 = (result3.equals("abc"));
        System.out.println("Test 3: " + (pass3 ? "PASS" : "FAIL")
                + " (Got: \"" + result3 + "\")");

        // Test 4 (alphabet repeated letters)
        // e.g. if alphabet is "aabc" => need 'a', 'a', 'b', 'c'
        String input4 = "abbca";
        String alphabet4 = "aabc";
        // The shortest substring that has 'a','a','b','c' is "abbca" itself (length 5)
        String result4 = findShortestSubstring(input4, alphabet4);
        boolean pass4 = (result4.equals("abbca"));
        System.out.println("Test 4: " + (pass4 ? "PASS" : "FAIL")
                + " (Got: \"" + result4 + "\")");

        // Test 5 (large input test)
        // We'll create a large string with repeated pattern, then add a minimal
        // substring at the end that includes all letters from an alphabet
        StringBuilder sbInput5 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sbInput5.append("x");  // some dummy letter
        }
        // Now ensure we add "a", "b", "c" at the end so we definitely have a substring
        sbInput5.append("abc");
        String input5 = sbInput5.toString();
        String alphabet5 = "abc";
        String result5 = findShortestSubstring(input5, alphabet5);
        boolean pass5 = (result5.length() == 3);  // expecting "abc"
        System.out.println("Test 5 (Large input): " + (pass5 ? "PASS" : "FAIL")
                + " (Length: " + result5.length() + ", Got: \"" + result5 + "\")");
    }

    /**
     * Simple method to check if 'candidate' contains all letters of 'alphabet'
     */
    private static boolean containsAllLetters(String candidate, String alphabet) {
        for (char c : alphabet.toCharArray()) {
            if (candidate.indexOf(c) < 0) {
                return false;
            }
        }
        return true;
    }
}
