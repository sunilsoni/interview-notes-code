package com.interview.notes.code.year.y2025.may.common.test1;

import java.util.stream.IntStream;

public class PalindromeChecker {
    
    /**
     * Checks if a given string is a palindrome using Java 8 features
     * @param input String to check
     * @return boolean indicating if string is palindrome
     */
    public static boolean isPalindrome(String input) {
        // Handle null input
        if (input == null) {
            return false;
        }
        
        // Convert to lowercase and remove non-alphanumeric characters
        String cleanStr = input.toLowerCase()
                             .replaceAll("[^a-zA-Z0-9]", "");
        
        // Handle empty string or single character
        if (cleanStr.length() <= 1) {
            return true;
        }
        
        // Convert string to char array and use Java 8 IntStream
        return IntStream.range(0, cleanStr.length() / 2)
                       .allMatch(i -> cleanStr.charAt(i) == 
                                    cleanStr.charAt(cleanStr.length() - 1 - i));
    }

    public static void main(String[] args) {
        // Test cases array with expected results
        TestCase[] testCases = {
            new TestCase("A man, a plan, a canal: Panama", true),
            new TestCase("race a car", false),
            new TestCase("", true),
            new TestCase("a", true),
            new TestCase(null, false),
            new TestCase("12321", true),
            new TestCase("Never odd or even", true),
            // Large input test
            new TestCase("a".repeat(1000000) + "b" + "a".repeat(1000000), false),
            new TestCase("a".repeat(1000000) + "a".repeat(1000000), true)
        };

        // Run all test cases
        for (TestCase test : testCases) {
            boolean result = isPalindrome(test.input);
            boolean passed = result == test.expected;
            
            System.out.printf("Input: \"%s\"%n", test.input);
            System.out.printf("Expected: %b, Got: %b, Test: %s%n",
                    test.expected, result, passed ? "PASS" : "FAIL");
            System.out.println("-------------------");
        }
    }

    // Simple class to hold test cases
    static class TestCase {
        String input;
        boolean expected;

        TestCase(String input, boolean expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
