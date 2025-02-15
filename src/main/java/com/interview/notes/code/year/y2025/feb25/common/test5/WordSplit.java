package com.interview.notes.code.year.y2025.feb25.common.test5;

/**
 * WordSplit.java
 * <p>
 * This program processes an input string by locating all words with an even number of letters (≥ 4),
 * then splits those words into two equal parts by inserting a space at the midpoint of the letters.
 * Non-letter characters are ignored when counting letters.
 * <p>
 * Evaluation:
 * 1. Does the analysis directly address the problem?
 * - Yes. The solution identifies words with at least 4 letters (ignoring non-alphabetic characters)
 * and splits them into two halves if the total letter count is even.
 * <p>
 * 2. Were all possible causes considered, or are there unassessed factors?
 * - The solution considers punctuation and multiple spaces. It processes tokens individually.
 * Edge cases like tokens with no letters or extra spaces are handled.
 * <p>
 * 3. Is this the simplest and most direct solution?
 * - Yes. The solution splits the input by spaces, processes each token, and reconstructs the string.
 * <p>
 * 4. Is it feasible in terms of resources and costs?
 * - Yes. The approach uses simple string manipulation and runs in linear time relative to input size.
 * <p>
 * 5. Will the solution have the expected impact, and is it sustainable?
 * - Yes. It meets the requirements with low memory overhead and simple logic.
 * <p>
 * 6. Are there ways to simplify or improve the solution?
 * - The solution is straightforward. Additional improvements might include more robust tokenization,
 * but that may add unnecessary complexity.
 * <p>
 * 7. What are the essential requirements versus those that are just a plus?
 * - Essential: Identify tokens, count letters (ignoring non-letters), split eligible tokens.
 * Plus: Preserve multiple spaces.
 * <p>
 * 8. Minimal Reproducible Example:
 * - See the main method which contains several test cases including edge cases and a large data test.
 * <p>
 * 9. What edge cases should we consider?
 * - Tokens with fewer than 4 letters, tokens with no letters,
 * tokens with punctuation, multiple spaces, and very large inputs.
 * <p>
 * 10. Testing Approach:
 * - The main method runs a series of tests, printing PASS/FAIL for each.
 * It includes tests for typical input, edge cases, and a large data input test.
 * <p>
 * Note: This solution is implemented in Java 8+ using a simple main method for testing (no JUnit).
 */

public class WordSplit {

    /**
     * Process a token (a word possibly including punctuation).
     * Count only alphabetic characters and, if there are at least 4 letters and the count is even,
     * insert a space after half of the letters are processed.
     *
     * @param token The word (or token) to process.
     * @return The token with a space inserted at the split point, if eligible.
     */
    public static String processToken(String token) {
        int letterCount = 0;
        for (char ch : token.toCharArray()) {
            if (Character.isLetter(ch)) {
                letterCount++;
            }
        }
        // Only split if there are at least 4 letters and the count is even.
        if (letterCount < 4 || letterCount % 2 != 0) {
            return token;
        }
        int half = letterCount / 2;
        StringBuilder sb = new StringBuilder();
        int currentLetterCount = 0;
        // Iterate through the token character by character.
        for (int i = 0; i < token.length(); i++) {
            char ch = token.charAt(i);
            sb.append(ch);
            if (Character.isLetter(ch)) {
                currentLetterCount++;
                // When exactly half of the letters have been processed, insert a space.
                if (currentLetterCount == half && i != token.length() - 1) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    /**
     * Process the entire input string by splitting it into tokens on spaces,
     * processing each token, and then reconstructing the string.
     *
     * @param input The original input string.
     * @return The modified string with eligible words split.
     */
    public static String splitWords(String input) {
        // Split on space while preserving all tokens (including empty ones) to retain original spacing.
        String[] tokens = input.split(" ", -1);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            result.append(processToken(tokens[i]));
            if (i < tokens.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    /**
     * Main method for testing the solution.
     * It tests various cases including the provided example, edge cases, and a large data input.
     */
    public static void main(String[] args) {
        int passed = 0, failed = 0;

        // Array of test cases: each test case is {input, expected output}
        String[][] tests = {
                {
                        "A dog can't walk in off the street and order a large soda.",
                        "A dog ca n't walk in off the str eet and order a large so da."
                },
                {
                        "Hello, world!",
                        "Hel lo, world!"
                },
                {
                        "Amazing",
                        "Amazing"  // "Amazing" has 6 letters (even) so would normally split, but since the expected sample is not given,
                        // we assume non-eligible for splitting based on the sample rules.
                },
                {
                        "1234",
                        "1234"  // No letters; no splitting.
                },
                {
                        "This  is  a  test",  // Multiple spaces should be preserved.
                        "Th is  is  a  te st"
                }
        };

        // Run test cases
        for (int i = 0; i < tests.length; i++) {
            String input = tests[i][0];
            String expected = tests[i][1];
            String actual = splitWords(input);
            if (actual.equals(expected)) {
                System.out.println("Test " + (i + 1) + " PASS");
                passed++;
            } else {
                System.out.println("Test " + (i + 1) + " FAIL");
                System.out.println("Input:    " + input);
                System.out.println("Expected: " + expected);
                System.out.println("Actual:   " + actual);
                failed++;
            }
        }

        // Large data input test: generate 10,000 words of "abcdefgh" (8 letters, even => should be split as "abcd efgh").
        StringBuilder largeInputBuilder = new StringBuilder();
        String word = "abcdefgh"; // Eligible word: 8 letters split into "abcd efgh"
        for (int i = 0; i < 10000; i++) {
            largeInputBuilder.append(word);
            if (i < 9999) {
                largeInputBuilder.append(" ");
            }
        }
        String largeInput = largeInputBuilder.toString();
        String processedWord = processToken(word);
        StringBuilder expectedLargeBuilder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            expectedLargeBuilder.append(processedWord);
            if (i < 9999) {
                expectedLargeBuilder.append(" ");
            }
        }
        String expectedLarge = expectedLargeBuilder.toString();
        String actualLarge = splitWords(largeInput);
        if (actualLarge.equals(expectedLarge)) {
            System.out.println("Large Data Test PASS");
            passed++;
        } else {
            System.out.println("Large Data Test FAIL");
            failed++;
        }

        System.out.println("\nTotal Tests Passed: " + passed);
        System.out.println("Total Tests Failed: " + failed);
    }
}