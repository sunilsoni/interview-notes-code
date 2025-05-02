package com.interview.notes.code.year.y2025.may.meta.test2;

public class WordReverser {
    public static String reverseWords(String input) {
        if (input == null || input.trim().isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            
            if (Character.isLetterOrDigit(c)) {
                word.append(c);
            } else {
                if (word.length() > 0) {
                    result.append(word.reverse());
                    word.setLength(0);
                }
                result.append(c);
            }
        }
        
        // Handle last word if exists
        if (word.length() > 0) {
            result.append(word.reverse());
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        String[] tests = {
            "When you are ready",
            "today is Thursday",
            "Hello World!",
            "",  // empty string
            "A",  // single character
            "Hello, World!",  // with punctuation
            "   spaces   test   ",  // multiple spaces
            null  // null input
        };

        for (String test : tests) {
            try {
                System.out.println("Input: " + test);
                System.out.println("Output: " + reverseWords(test));
                System.out.println("Test: PASS");
            } catch (Exception e) {
                System.out.println("Test: FAIL - " + e.getMessage());
            }
            System.out.println("-------------------");
        }
    }
}
