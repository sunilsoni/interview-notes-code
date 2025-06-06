package com.interview.notes.code.year.y2025.may.BNYM.test2;

public class PalindromeChecker {
    
    /**
     * Checks if a given string is a palindrome
     * @param input String to check
     * @return boolean true if palindrome, false otherwise
     */
    public static boolean isPalindrome(String input) {
        // Handle null input case
        if (input == null) {
            return false;
        }

        // Convert to lowercase and remove non-alphanumeric characters
        String cleanStr = input.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        
        // Use Java 8 IntStream to compare characters from start and end
        return cleanStr.chars()
                .limit(cleanStr.length() / 2)
                .allMatch(i -> cleanStr.charAt(i) == 
                        cleanStr.charAt(cleanStr.length() - i - 1));
    }

    public static void main(String[] args) {
        // Test cases array with expected results
        TestCase[] testCases = {
            new TestCase("race car", true),
            new TestCase("A man, a plan, a canal: Panama", true),
            new TestCase("hello", false),
            new TestCase("", true),
            new TestCase("12321", true),
            new TestCase(null, false),
            new TestCase("Race Car", true),
            // Large input test
            new TestCase("a".repeat(1000000) + "b" + "a".repeat(1000000), false)
        };

        // Run all test cases
        for (TestCase test : testCases) {
            boolean result = isPalindrome(test.input);
            boolean passed = result == test.expected;
            
            System.out.printf("Input: '%s' | Expected: %b | Got: %b | %s%n",
                    test.input,
                    test.expected,
                    result,
                    passed ? "PASS" : "FAIL");
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
