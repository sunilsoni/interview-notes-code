package com.interview.notes.code.year.y2025.may.common.test6;

public class ReverseWordsInStringPrimitive {

    /**
     * Reverses words in a string while handling multiple spaces.
     * Input: "  the sky   is blue  "
     * Output: "blue is sky the"
     *
     * @param s input string that may contain multiple spaces
     * @return string with words in reverse order, single-spaced
     */
    public static String reverseWords(String s) {
        // Initialize array to store words - size = string length as worst case scenario
        // (if every character is a single-letter word)
        String[] words = new String[s.length()];

        // Counter to track actual number of words found
        // Needed because array size != actual word count
        int wordCount = 0;

        // Loop control variables
        // i: current position in string
        // n: string length (cached to avoid repeated length calls)
        int i = 0, n = s.length();

        // Process entire string character by character
        while (i < n) {
            // Skip all consecutive spaces
            // This handles multiple spaces between words and at string ends
            while (i < n && s.charAt(i) == ' ') i++;

            // If we've reached the end after skipping spaces, exit
            // This handles trailing spaces case
            if (i >= n) break;

            // StringBuilder for efficient character concatenation
            // More efficient than String concatenation in a loop
            StringBuilder word = new StringBuilder();

            // Collect characters until we hit a space or end of string
            // This builds one complete word
            while (i < n && s.charAt(i) != ' ') {
                word.append(s.charAt(i));
                i++;
            }

            // Only add non-empty words to our array
            // This is a safety check, though with current logic it's always true
            if (word.length() > 0) {
                words[wordCount] = word.toString();
                wordCount++; // Increment count of valid words found
            }
        }

        // Handle empty string or string with only spaces
        if (wordCount == 0) return "";

        // StringBuilder for constructing final result
        // More efficient than String concatenation
        StringBuilder result = new StringBuilder();

        // Build result string by adding words in reverse order
        for (int j = wordCount - 1; j >= 0; j--) {
            result.append(words[j]);
            // Add space after each word except the last one
            // This ensures no trailing space in final result
            if (j != 0) result.append(' ');
        }

        return result.toString();
    }

    // Test harness
    public static void main(String[] args) {
        // Array of test cases, each containing input and expected output
        String[][] testCases = {
                // Normal case with multiple spaces
                {"  the sky   is blue  ", "blue is sky the"},
                // Simple two-word case
                {"hello world", "world hello"},
                // Multiple spaces between words
                {"  a   good   example ", "example good a"},
                // Edge case: only spaces
                {"   ", ""},
                // Edge case: empty string
                {"", ""},
                // Single word case
                {"one", "one"},
                // Single word with surrounding spaces
                {"   singleWord   ", "singleWord"},
                // Large input test case
                {generateLargeInput(10000), generateLargeOutput(10000)}
        };

        // Track number of passing tests
        int passCount = 0;

        // Run each test case
        for (int i = 0; i < testCases.length; i++) {
            String input = testCases[i][0];
            String expected = testCases[i][1];
            String actual = reverseWords(input);
            boolean pass = expected.equals(actual);

            // Print test results
            System.out.println("Test Case " + (i + 1) + ": " +
                    (pass ? "PASS" : "FAIL"));

            // If test failed, show detailed information
            if (!pass) {
                System.out.println("  Input:    \"" + input + "\"");
                System.out.println("  Expected: \"" + expected + "\"");
                System.out.println("  Actual:   \"" + actual + "\"");
            } else {
                passCount++;
            }
        }

        // Print summary of test results
        System.out.println("\nSummary: " + passCount + "/" +
                testCases.length + " test cases passed.");
    }

    /**
     * Generates a large test input string
     *
     * @param n number of words to generate
     * @return string with n words separated by varying spaces
     */
    private static String generateLargeInput(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append("word").append(i);
            // Add varying number of spaces between words
            if (i < n) sb.append(i % 2 == 0 ? "  " : "   ");
        }
        return sb.toString();
    }

    /**
     * Generates expected output for large input test
     *
     * @param n number of words in output
     * @return string with n words in reverse order
     */
    private static String generateLargeOutput(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            sb.append("word").append(i);
            // Add single space between words, except after last word
            if (i > 1) sb.append(" ");
        }
        return sb.toString();
    }
}
