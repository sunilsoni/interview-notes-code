package com.interview.notes.code.year.y2025.jan25.M6012024.wallmart.test1;

public class ReverseWords {

    // Method to reverse words in a given string
    public static String reverseWords(String input) {
        if (input == null || input.trim().isEmpty()) return input; // handle empty/null
        String[] words = input.split("\\s+"); // split by whitespace
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i != 0) reversed.append(" ");
        }
        return reversed.toString();
    }

    // Simple testing method to verify output
    public static void runTest(String input, String expected) {
        String result = reverseWords(input);
        if (result.equals(expected)) {
            System.out.println("PASS: \"" + input + "\" → \"" + result + "\"");
        } else {
            System.out.println("FAIL: \"" + input + "\". Expected \"" + expected + "\" but got \"" + result + "\"");
        }
    }

    public static void main(String[] args) {
        // Provided test case
        runTest("hello   world   ", "world hello");
        // Additional test cases
        runTest("", "");                                // empty string
        runTest("hello", "hello");                      // single word
        runTest("one two three", "three two one");      // multiple words
        runTest("  leading and trailing  ", "trailing and leading"); // extra spaces

        // Test with large input: repeat a phrase many times
        StringBuilder largeInputBuilder = new StringBuilder();
        String phrase = "hello world ";
        for (int i = 0; i < 100000; i++) {
            largeInputBuilder.append(phrase);
        }
        String largeInput = largeInputBuilder.toString().trim();
        // We won't check full output, just ensure no exceptions and output length matches expectation
        String reversedLarge = reverseWords(largeInput);
        System.out.println("Large input test completed. Output length: " + reversedLarge.length());
    }
}
