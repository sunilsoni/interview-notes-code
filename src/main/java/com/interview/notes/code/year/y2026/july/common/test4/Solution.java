package com.interview.notes.code.year.y2026.july.common.test4;

public class Solution {

    // This method converts input string into safe run length encoded format
    public static String rle(String input) {

        // If input is null or empty, return empty string because there is nothing to encode
        if (input == null || input.isEmpty()) return "";

        // StringBuilder is used to build output efficiently for small and large inputs
        StringBuilder result = new StringBuilder();

        // Store first character as current character because counting starts from first char
        char current = input.charAt(0);

        // Count starts from 1 because first character is already counted
        int count = 1;

        // Start from index 1 because index 0 is already stored in current
        for (int i = 1; i < input.length(); i++) {

            // Read the current character from input
            char ch = input.charAt(i);

            // If current character is same as previous character, increase count
            if (ch == current) {

                // Increase count because same character is repeated
                count++;

            } else {

                // Add character, separator, count, and group separator to avoid confusion with digits
                result.append(current).append(":").append(count).append("|");

                // Change current character to new character
                current = ch;

                // Reset count because new character group starts
                count = 1;
            }
        }

        // Add last character group because loop only adds when character changes
        result.append(current).append(":").append(count);

        // Convert StringBuilder to String and return final encoded output
        return result.toString();
    }

    // Simple test method to check PASS or FAIL without JUnit
    static void test(String input, String expected) {

        // Call rle method and store actual result
        String actual = rle(input);

        // Compare expected output with actual output
        boolean pass = expected.equals(actual);

        // Print PASS or FAIL with input, expected, and actual result
        System.out.println((pass ? "PASS" : "FAIL")
                + " | input=" + input
                + " | expected=" + expected
                + " | actual=" + actual);
    }

    // Main method to run test cases
    public static void main(String[] args) {

        // Normal repeated character test
        test("aaa", "a:3");

        // Normal mixed character test
        test("aaabbc", "a:3|b:2|c:1");

        // Input contains digits, so separator avoids confusion
        test("a11v22c1", "a:1|1:2|v:1|2:2|c:1|1:1");

        // Single character test
        test("a", "a:1");

        // Empty input test
        test("", "");

        // Null input test
        test(null, "");

        // Large input test
        test("a".repeat(100000), "a:100000");
    }
}