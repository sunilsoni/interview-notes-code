package com.interview.notes.code.year.y2025.november.common.test4;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseWords {

    // Method to reverse words in a given string
    public static String reverseWords(String s) {
        // Step 1: Trim the input string to remove leading/trailing spaces
        s = s.trim(); // ensures no extra space at start/end

        // Step 2: Split string by one or more spaces using regex "\\s+"
        // This handles multiple spaces between words
        String[] words = s.split("\\s+");

        // Step 3: Reverse the array using Stream API
        // IntStream.range lets us access indexes in reverse order
        return IntStream.range(0, words.length)
                .mapToObj(i -> words[words.length - 1 - i]) // reverse access
                .collect(Collectors.joining(" ")); // join words with a single space
    }

    // Main method for testing
    public static void main(String[] args) {
        // Define test cases with expected outputs
        String[][] testCases = {
                {"the sky is blue", "blue is sky the"},
                {"  hello world  ", "world hello"},
                {"a good   example", "example good a"},
                {"   singleWord   ", "singleWord"},
                {"", ""}, // edge case: empty string
                {"     ", ""} // edge case: only spaces
        };

        // Process each test case and print PASS/FAIL
        for (String[] test : testCases) {
            String input = test[0];
            String expected = test[1];
            String actual = reverseWords(input);

            // Print test results in readable format
            System.out.println("Input: \"" + input + "\"");
            System.out.println("Output: \"" + actual + "\"");
            System.out.println("Expected: \"" + expected + "\"");

            // Validate and show test result
            if (actual.equals(expected)) {
                System.out.println("Result: PASS ✅");
            } else {
                System.out.println("Result: FAIL ❌");
            }
            System.out.println("-----------------------------");
        }

        // Large data input test to ensure performance and stability
        String largeInput = String.join(" ", Collections.nCopies(100000, "word"));
        String output = reverseWords(largeInput);
        System.out.println("Large input test passed: " +
                (output.startsWith("word") && output.endsWith("word")));
    }
}
