package com.interview.notes.code.year.y2025.jan.meta.test1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ShortestSubstringWithAlphabet {

    /**
     * Find the shortest substring containing all alphabet letters
     *
     * @param input    Input string to search
     * @param alphabet Target alphabet letters
     * @return Shortest substring containing all alphabet letters
     */
    public static String findShortestSubstring(String input, String alphabet) {
        // Validate inputs
        if (input == null || input.isEmpty() ||
                alphabet == null || alphabet.isEmpty()) {
            return "";
        }

        // Convert alphabet to set for efficient lookup
        Set<Character> requiredChars = new HashSet<>();
        for (char c : alphabet.toCharArray()) {
            requiredChars.add(c);
        }

        int minLength = Integer.MAX_VALUE;
        String shortestSubstring = "";

        // Sliding window approach
        for (int start = 0; start < input.length(); start++) {
            // Track characters in current window
            Set<Character> currentChars = new HashSet<>();

            for (int end = start; end < input.length(); end++) {
                currentChars.add(input.charAt(end));

                // Check if current window contains all required characters
                if (currentChars.containsAll(requiredChars)) {
                    String currentSubstring = input.substring(start, end + 1);

                    // Update shortest substring if needed
                    if (currentSubstring.length() < minLength) {
                        minLength = currentSubstring.length();
                        shortestSubstring = currentSubstring;
                    }
                    break;  // Found shortest substring starting from this index
                }
            }
        }

        return shortestSubstring;
    }

    // Comprehensive test method
    public static void main(String[] args) {
        // Test cases
        testCase("Basic case", "aacbc", "abc", "accb");
        testCase("No match", "xyz", "abc", "");
        testCase("Exact match", "abc", "abc", "abc");
        testCase("Repeated characters", "aabbccddee", "abcd", "abccdd");
        testCase("Large input", generateLargeInput(), "xyz", "");

        // Performance test
        performanceTest();
    }

    // Helper method to run test cases
    private static void testCase(String testName, String input,
                                 String alphabet, String expected) {
        try {
            String result = findShortestSubstring(input, alphabet);
            boolean passed = result.equals(expected);

            System.out.println("Test: " + testName);
            System.out.println("Input: " + input);
            System.out.println("Alphabet: " + alphabet);
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + result);
            System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
            System.out.println();

            if (!passed) {
                throw new AssertionError("Test case failed");
            }
        } catch (Exception e) {
            System.err.println("Error in test case: " + testName);
            e.printStackTrace();
        }
    }

    // Generate large input for stress testing
    private static String generateLargeInput() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // Create a large input string
        for (int i = 0; i < 100000; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }

        return sb.toString();
    }

    // Performance test to measure execution time
    private static void performanceTest() {
        String largeInput = generateLargeInput();
        String alphabet = "xyz";

        long startTime = System.nanoTime();
        findShortestSubstring(largeInput, alphabet);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("Performance Test:");
        System.out.println("Large Input Size: " + largeInput.length());
        System.out.println("Execution Time: " + duration + " ms");
    }
}