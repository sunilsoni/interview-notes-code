package com.interview.notes.code.year.y2025.may.meta.test1;

public class WordReverser {
    public static String reverseWords(String input) {
        if (input == null || input.trim().isEmpty()) {
            return input;
        }

        char[] chars = input.toCharArray();
        String result = "";
        String word = "";
        
        for (char c : chars) {
            if (Character.isLetterOrDigit(c)) {
                word = c + word; // prepend character to build reversed word
            } else {
                result += word + c; // add reversed word and non-letter char
                word = ""; // reset word
            }
        }
        
        // Handle last word if exists
        if (!word.isEmpty()) {
            result += word;
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Test cases with expected results
        String[][] tests = {
            {"When you are ready", "nehW uoy era ydaer"},
            {"today is Thursday", "yadot si yadsruhT"},
            {"Hello World!", "olleH dlroW!"},
            {"", ""},
            {"A", "A"},
            {"Hello, World!", "olleH, dlroW!"},
            {"   spaces   test   ", "   secaps   tset   "}
        };

        for (String[] test : tests) {
            String input = test[0];
            String expected = test[1];
            String actual = reverseWords(input);
            
            System.out.println("Input: " + input);
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
            System.out.println("Test: " + (expected.equals(actual) ? "PASS" : "FAIL"));
            System.out.println("-------------------");
        }

        // Test null input
        try {
            System.out.println("Testing null input:");
            System.out.println(reverseWords(null));
            System.out.println("Null Test: PASS");
        } catch (Exception e) {
            System.out.println("Null Test: FAIL - " + e.getMessage());
        }

        // Test large input
        System.out.println("\nTesting large input:");
        String largeInput = "This is a very long string ".repeat(1000);
        long startTime = System.currentTimeMillis();
        reverseWords(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input processing time: " + (endTime - startTime) + "ms");
    }
}
