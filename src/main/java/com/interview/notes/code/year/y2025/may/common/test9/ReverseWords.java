package com.interview.notes.code.year.y2025.may.common.test9;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReverseWords {
    public static void main(String[] args) {
        // Define test cases: input string and expected output
        String[][] testCases = {
            {"  the sky   is blue  ", "blue is sky the"},            // example with extra spaces
            {"hello world", "world hello"},                          // simple two words
            {"   singleWord   ", "singleWord"},                     // single word with spaces
            {"", ""},                                              // empty string
            {"      ", ""},                                        // only spaces
        };

        // Run each test and print PASS or FAIL
        for (int i = 0; i < testCases.length; i++) {
            String input = testCases[i][0];                            // original input
            String expected = testCases[i][1];                         // expected reversed output
            String result = reverseWords(input);                       // compute reversed
            // Compare result with expected and print outcome
            if (result.equals(expected)) {
                System.out.println("Test Case " + (i+1) + ": PASS");
            } else {
                System.out.println("Test Case " + (i+1) + ": FAIL");
                System.out.println("  Input   : '" + input + "'");
                System.out.println("  Expected: '" + expected + "'");
                System.out.println("  Got     : '" + result + "'");
            }
        }

        // Additional large input test: repeat a word many times
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 100_000; i++) {
            large.append("word ");
        }
        String largeInput = large.toString();                          // create a large string of repeated words
        String largeResult = reverseWords(largeInput);                 // reverse large input to test performance
        // Print first 50 chars to confirm it ran (avoid huge output)
        System.out.println("Large test processed, output starts with: " + largeResult.substring(0, 50));
    }

    /**
     * Reverse the words in the input string, removing extra spaces.
     * @param s the original string
     * @return a new string with words in reverse order and single spaces
     */
    public static String reverseWords(String s) {
        // Step 1: Trim leading and trailing spaces
        String trimmed = s.trim();

        // Step 2: Split by one or more spaces to get words
        String[] words = trimmed.split("\\s+");

        // Step 3: Convert array to list for reversal
        List<String> wordList = Arrays.stream(words)
            .collect(Collectors.toList());                           // collect words into a List

        // Step 4: Reverse the list in-place
        Collections.reverse(wordList);                               // reverse order of words

        // Step 5: Join words with a single space between
        return String.join(" ", wordList);                         // build final string
    }
}