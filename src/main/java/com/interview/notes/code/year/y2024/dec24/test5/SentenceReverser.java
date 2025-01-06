package com.interview.notes.code.year.y2024.dec24.test5;

public class SentenceReverser {

    public static String reverseWords(String sentence) {
        // Handle edge cases
        if (sentence == null || sentence.trim().isEmpty()) {
            return sentence;
        }

        // Split the sentence into words
        String[] words = sentence.trim().split("\\s+");

        // Reverse the array of words
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void testCase(String input, String expected) {
        String result = reverseWords(input);
        boolean passed = expected.equals(result);
        System.out.println("Test Case: \"" + input + "\"");
        System.out.println("Expected: \"" + expected + "\"");
        System.out.println("Got     : \"" + result + "\"");
        System.out.println("Status  : " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }

    public static void main(String[] args) {
        // Basic test cases
        testCase("This is a sentence", "sentence a is This");
        testCase("Hello World", "World Hello");

        // Edge cases
        testCase("", "");
        testCase(null, null);
        testCase("SingleWord", "SingleWord");
        testCase("   Spaces   Around   Words   ", "Words Around Spaces");

        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeInput.append("word").append(i).append(" ");
        }
        String largeResult = reverseWords(largeInput.toString());
        System.out.println("Large Input Test (1000 words):");
        System.out.println("First 50 chars: " + largeResult.substring(0, Math.min(50, largeResult.length())));
        System.out.println("Length: " + largeResult.length());
        System.out.println();

        // Special characters test
        testCase("Hello! How are you?", "you? are How Hello!");
        testCase("Tab\tand\tnewline\ntest", "test newline and Tab");
    }
}
