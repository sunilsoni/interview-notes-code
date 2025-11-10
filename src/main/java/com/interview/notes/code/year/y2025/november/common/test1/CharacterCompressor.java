package com.interview.notes.code.year.y2025.november.common.test1;

import java.util.Arrays;

public class CharacterCompressor {

    /**
     * Compresses the input string by counting consecutive characters.
     * Example: "aaabbcc" -> "a3b2c2"
     *
     * @param input The string to compress
     * @return Compressed string
     */
    public static String compress(String input) {
        // Check for null or empty input
        if (input == null || input.isEmpty()) return "";

        // Use StringBuilder for efficient string concatenation
        StringBuilder result = new StringBuilder();

        // Initialize first character and count
        char currentChar = input.charAt(0); // Start with first character
        int count = 1; // Count starts at 1

        // Loop through the string starting from second character
        for (int i = 1; i < input.length(); i++) {
            char ch = input.charAt(i); // Get current character

            if (ch == currentChar) {
                count++; // Same character, increment count
            } else {
                // Different character, append previous character and count
                result.append(currentChar).append(count);

                // Reset currentChar and count
                currentChar = ch;
                count = 1;
            }
        }

        // Append the last character and its count
        result.append(currentChar).append(count);

        return result.toString(); // Return the final compressed string
    }

    /**
     * Main method to test the compress logic with multiple cases.
     */
    public static void main(String[] args) {
        // Define test cases
        String[] testCases = {
            "aaabbcc",        // Expected: a3b2c2
            "a",              // Expected: a1
            "abc",            // Expected: a1b1c1
            "aaaaaaa",        // Expected: a7
            "",               // Expected: (empty)
            "aabbaa",         // Expected: a2b2a2
            generateLargeInput('x', 1000000) // Large input test
        };

        // Expected outputs for comparison
        String[] expectedOutputs = {
            "a3b2c2",
            "a1",
            "a1b1c1",
            "a7",
            "",
            "a2b2a2",
            "x1000000"
        };

        // Run each test case and print PASS/FAIL
        for (int i = 0; i < testCases.length; i++) {
            String input = testCases[i];
            String expected = expectedOutputs[i];
            String actual = compress(input);

            // Compare actual output with expected
            if (actual.equals(expected)) {
                System.out.println("Test " + (i + 1) + " PASSED");
            } else {
                System.out.println("Test " + (i + 1) + " FAILED");
                System.out.println("Input: " + input);
                System.out.println("Expected: " + expected);
                System.out.println("Actual: " + actual);
            }
        }
    }

    /**
     * Utility method to generate large input strings for testing.
     *
     * @param ch Character to repeat
     * @param count Number of times to repeat
     * @return Generated string
     */
    private static String generateLargeInput(char ch, int count) {
        // Create a char array of given size and fill with ch
        char[] array = new char[count];
        Arrays.fill(array, ch); // Fill array with character
        return new String(array); // Convert to string
    }
}
