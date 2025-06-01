package com.interview.notes.code.year.y2025.may.common.test8;

public class ReverseWordsInStringPrimitive {

    /**
     * Reverses words in the string, removes extra spaces.
     * @param s input string
     * @return reversed words string, single-spaced
     */
    public static String reverseWords(String s) {
        // List to collect words found in the input string
        String[] words = new String[s.length()]; // Max possible words = length of string
        int wordCount = 0; // Number of words found

        int i = 0, n = s.length();

        while (i < n) {
            // Skip any leading spaces (ignore all spaces until next word)
            while (i < n && s.charAt(i) == ' ') i++;

            if (i >= n) break; // End of string

            // Build the current word character by character
            StringBuilder word = new StringBuilder();

            // Collect characters until next space or end of string
            while (i < n && s.charAt(i) != ' ') {
                word.append(s.charAt(i));
                i++;
            }

            // Only add non-empty words
            if (word.length() > 0) {
                words[wordCount] = word.toString();
                wordCount++;
            }
            // Loop continues to process next word
        }

        // If no words found, return empty string
        if (wordCount == 0) return "";

        // Build the final string by joining words in reverse order
        StringBuilder result = new StringBuilder();

        for (int j = wordCount - 1; j >= 0; j--) {
            result.append(words[j]);
            if (j != 0) result.append(' '); // Add space except after last word
        }

        return result.toString();
    }

    // --- Minimal main method for testing ---
    public static void main(String[] args) {
        String[][] testCases = {
            {"  the sky   is blue  ", "blue is sky the"},
            {"hello world", "world hello"},
            {"  a   good   example ", "example good a"},
            {"   ", ""},
            {"", ""},
            {"one", "one"},
            {"   singleWord   ", "singleWord"},
            {generateLargeInput(10000), generateLargeOutput(10000)}
        };

        int passCount = 0;
        for (int i = 0; i < testCases.length; i++) {
            String input = testCases[i][0];
            String expected = testCases[i][1];
            String actual = reverseWords(input);
            boolean pass = expected.equals(actual);
            System.out.println("Test Case " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("  Input:    \"" + input + "\"");
                System.out.println("  Expected: \"" + expected + "\"");
                System.out.println("  Actual:   \"" + actual + "\"");
            } else {
                passCount++;
            }
        }
        System.out.println("\nSummary: " + passCount + "/" + testCases.length + " test cases passed.");
    }

    // Helpers for large input test (generates a string with 10,000 words)
    private static String generateLargeInput(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append("word").append(i);
            if (i < n) sb.append(i % 2 == 0 ? "  " : "   ");
        }
        return sb.toString();
    }

    private static String generateLargeOutput(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            sb.append("word").append(i);
            if (i > 1) sb.append(" ");
        }
        return sb.toString();
    }
}
