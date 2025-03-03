package com.interview.notes.code.year.y2025.jan.amazon.test8;

public class MinPalindromeSwapsSolution {
    public static int minPalindromeSwaps(String s) {
        // Validate input
        if (s == null || s.isEmpty()) {
            return -1;
        }

        int n = s.length();
        char[] chars = s.toCharArray();

        // Check if palindrome is possible
        if (!isPalindromePossible(chars)) {
            return -1;
        }

        return calculateMinSwaps(chars);
    }

    // Check if palindrome is possible based on character distribution
    private static boolean isPalindromePossible(char[] chars) {
        int[] count = new int[2];
        for (char c : chars) {
            count[c - '0']++;
        }

        // Odd length palindrome requires at most one character with odd count
        int oddCount = 0;
        for (int cnt : count) {
            if (cnt % 2 != 0) {
                oddCount++;
            }
        }

        return oddCount <= 1;
    }

    // Calculate minimum swaps to create palindrome
    private static int calculateMinSwaps(char[] chars) {
        int swaps = 0;
        int n = chars.length;

        // Create a copy to manipulate
        char[] copy = chars.clone();

        // Two-pointer approach to create palindrome
        for (int left = 0, right = n - 1; left < right; ) {
            // If characters match, move pointers
            if (copy[left] == copy[right]) {
                left++;
                right--;
                continue;
            }

            // Find the matching character to swap
            int matchIndex = findMatchIndex(copy, left, right);

            // If no match found, it's impossible
            if (matchIndex == -1) {
                return -1;
            }

            // Perform swap
            while (matchIndex > left) {
                swap(copy, matchIndex, matchIndex - 1);
                matchIndex--;
                swaps++;
            }

            // Move pointers
            left++;
            right--;
        }

        return swaps;
    }

    // Find matching character index
    private static int findMatchIndex(char[] chars, int start, int end) {
        for (int i = end; i > start; i--) {
            if (chars[i] == chars[start]) {
                return i;
            }
        }
        return -1;
    }

    // Swap characters
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // Comprehensive test method
    public static void main(String[] args) {
        // Test cases: {input, expected output}
        Object[][] testCases = {
                {"101000", 1},     // Should be 1 swap
                {"1110", -1},      // Should be -1 (impossible)
                {"0100101", 2},    // Should be 2 swaps
                {"1001001", 0},    // Should be 0 swaps (already palindrome)
                {"", -1},          // Empty string
                {"10101", -1},     // Impossible to make palindrome
                {"111000", 3}      // Should be 3 swaps
        };

        int passedTests = 0;
        int totalTests = testCases.length;

        for (Object[] testCase : testCases) {
            String input = (String) testCase[0];
            int expectedOutput = (int) testCase[1];

            int result = minPalindromeSwaps(input);

            boolean testPassed = result == expectedOutput;
            passedTests += testPassed ? 1 : 0;

            System.out.println(
                    "Input: " + input +
                            ", Minimum Swaps: " + result +
                            ", Expected: " + expectedOutput +
                            ", Status: " + (testPassed ? "PASS" : "FAIL")
            );
        }

        // Final test summary
        System.out.println("\nTest Summary:");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed Tests: " + passedTests);
        System.out.println("Failed Tests: " + (totalTests - passedTests));
        System.out.println("Pass Rate: " +
                String.format("%.2f%%", (passedTests * 100.0 / totalTests)));
    }
}
