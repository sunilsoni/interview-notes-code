package com.interview.notes.code.year.y2025.april.common.test10;

public class StringReverser {
    public static String reverseWords(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }

        // Split input into words
        String[] words = input.trim().split("\\s+");
        if (words.length == 0) return "";

        // Start with last word
        StringBuilder result = new StringBuilder(words[words.length - 1]);

        // Add other words in order, reversed
        for (int i = 0; i < words.length - 1; i++) {
            result.append(" ").append(reverseString(words[i]));
        }

        return result.toString();
    }

    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static void main(String[] args) {
        // Test cases
        testCase("my name is mahendra", "mahendra ym eman si");
        testCase("", "");
        testCase("hello", "hello");
        testCase("abc def", "def cba");
        testCase("a b c d", "d a b c");
        
        // Edge cases
        testCase(null, "");
        testCase("   ", "");
        testCase("hello   world", "world olleh");
    }

    private static void testCase(String input, String expected) {
        String result = reverseWords(input);
        boolean passed = result.equals(expected);
        System.out.println("Test Case: " + (input == null ? "null" : "\"" + input + "\""));
        System.out.println("Expected: \"" + expected + "\"");
        System.out.println("Got: \"" + result + "\"");
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("-------------------");
    }
}
