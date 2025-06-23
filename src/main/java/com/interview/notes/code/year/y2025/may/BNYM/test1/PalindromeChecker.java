package com.interview.notes.code.year.y2025.may.BNYM.test1;

public class PalindromeChecker {

    /**
     * Checks if a given string is a palindrome
     *
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

        // Handle empty string case
        if (cleanStr.isEmpty()) {
            return true;
        }

        // Use two pointers approach - more reliable than Stream for large strings
        int left = 0;
        int right = cleanStr.length() - 1;

        while (left < right) {
            if (cleanStr.charAt(left) != cleanStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
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
                new TestCase("!@#$%^&*()", true),  // Only special characters
                new TestCase("a", true),           // Single character
                new TestCase("ab", false),         // Two different characters
                new TestCase("aba", true),         // Three characters
                // Large input test (safely constructed)
                new TestCase(createLargePalindrome(10000), true)
        };

        // Run all test cases
        int passed = 0;
        int total = testCases.length;

        for (TestCase test : testCases) {
            try {
                boolean result = isPalindrome(test.input);
                boolean passed_test = result == test.expected;

                System.out.printf("Input: %s | Expected: %b | Got: %b | %s%n",
                        test.input == null ? "null" :
                                test.input.length() > 50 ? test.input.substring(0, 47) + "..." :
                                        "'" + test.input + "'",
                        test.expected,
                        result,
                        passed_test ? "PASS" : "FAIL");

                if (passed_test) passed++;

            } catch (Exception e) {
                System.out.printf("Error testing input: %s | %s%n",
                        test.input, e.getMessage());
            }
        }

        // Print summary
        System.out.printf("%n%d/%d tests passed (%d%%)%n",
                passed, total, (passed * 100) / total);
    }

    /**
     * Creates a large palindrome string for testing
     *
     * @param size The approximate size of the palindrome
     * @return A palindrome string
     */
    private static String createLargePalindrome(int size) {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size / 2; i++) {
            sb.append('a');
        }
        sb.append('x');
        for (int i = 0; i < size / 2; i++) {
            sb.append('a');
        }
        return sb.toString();
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
