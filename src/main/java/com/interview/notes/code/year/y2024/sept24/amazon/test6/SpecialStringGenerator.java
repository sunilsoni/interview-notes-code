package com.interview.notes.code.year.y2024.sept24.amazon.test6;

import java.util.Arrays;

public class SpecialStringGenerator {

    public static void main(String[] args) {
        // Test cases
        testCase("abbd", "abca");
        testCase("abccde", "abcdab");
        testCase("zzab", "-1");
        testCase("abc", "abd");
        testCase("zzz", "-1");
        testCase("aaab", "abaa");
        testCase("a", "b");
        // Large data test case
        // testCase(generateString(1000000, 'a'), generateAlternatingString(1000000, 'a', 'b'));
    }

    /**
     * Generates a special string lexicographically greater than the input string.
     *
     * @param s The input string.
     * @return The lexicographically smallest special string greater than s, or "-1" if none exists.
     */
    public static String getSpecialString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        // Iterate from the end of the string
        for (int i = n - 1; i >= 0; i--) {
            char currentChar = chars[i];
            char nextChar = i < n - 1 ? chars[i + 1] : ' '; // Get next character, handling edge case

            // Find the smallest character greater than the current character (and different from the next)
            for (char c = (char) (currentChar + 1); c <= 'z'; c++) {
                if (c != nextChar && (i == 0 || c != chars[i - 1])) {
                    chars[i] = c;
                    return generateSpecialStringFromIndex(chars, i + 1);
                }
            }
            // If no suitable character is found, reset to 'a' and continue to the previous character
            chars[i] = 'a';
        }

        // No special string found
        return "-1";
    }

    /**
     * Generates a special string from the given index onwards, ensuring no adjacent characters are the same.
     *
     * @param chars The character array representing the string.
     * @param index The starting index for generating the special string.
     * @return The complete special string.
     */
    private static String generateSpecialStringFromIndex(char[] chars, int index) {
        for (int i = index; i < chars.length; i++) {
            char prevChar = chars[i - 1];
            chars[i] = prevChar == 'a' ? 'b' : 'a';
        }
        return new String(chars);
    }

    // Helper functions for testing:

    /**
     * Tests a single case of the getSpecialString function.
     *
     * @param input    The input string for the test case.
     * @param expected The expected output string.
     */
    private static void testCase(String input, String expected) {
        String result = getSpecialString(input);
        System.out.println("Input: " + input + ", Expected: " + expected + ", Result: " + result +
                " - " + (result.equals(expected) ? "PASS" : "FAIL"));
    }

    /**
     * Generates a string of the specified length with the specified character.
     *
     * @param length    The desired length of the string.
     * @param character The character to repeat in the string.
     * @return The generated string.
     */
    private static String generateString(int length, char character) {
        char[] chars = new char[length];
        Arrays.fill(chars, character);
        return new String(chars);
    }

    /**
     * Generates an alternating string of the specified length using the given characters.
     *
     * @param length The desired length of the string.
     * @param char1  The first character to alternate.
     * @param char2  The second character to alternate.
     * @return The generated alternating string.
     */
    private static String generateAlternatingString(int length, char char1, char char2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(i % 2 == 0 ? char1 : char2);
        }
        return sb.toString();
    }
}
