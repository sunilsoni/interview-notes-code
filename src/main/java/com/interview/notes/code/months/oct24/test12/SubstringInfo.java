package com.interview.notes.code.months.oct24.test12;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class SubstringInfo {

    // Test cases mapping input string to expected results: [start index, length]
    private static final Map<String, int[]> testCases = new LinkedHashMap<>();

    // Method to get substring information in O(n) time complexity
    public static int[] getSubstringInfo(String input) {
        if (input == null || input.length() == 0) {
            return new int[]{-1, 0}; // Edge case: empty string
        }

        int maxStart = 0;  // The start index of the longest repeating sequence
        int maxLength = 1; // The length of the longest repeating sequence
        int currentStart = 0; // The start index of the current repeating sequence

        // Iterate through the string to find the longest contiguous sequence of repeating characters
        for (int end = 1; end < input.length(); end++) {
            if (input.charAt(end) != input.charAt(end - 1)) {
                // If current character doesn't match the previous one, calculate the length of the current sequence
                int currentLength = end - currentStart;
                if (currentLength > maxLength) {
                    maxStart = currentStart;
                    maxLength = currentLength;
                }
                // Start a new window for the next sequence
                currentStart = end;
            }
        }

        // Check the last sequence after the loop ends
        if (input.length() - currentStart > maxLength) {
            maxStart = currentStart;
            maxLength = input.length() - currentStart;
        }

        return new int[]{maxStart, maxLength};
    }

    public static void main(String[] args) {
        // Define test cases
        testCases.put("", new int[]{-1, 0}); // Empty string
        testCases.put("addddCdAA", new int[]{1, 4}); // "dddd" starts at index 1
        testCases.put("111000111", new int[]{0, 3}); // "111" starts at index 0
        testCases.put("abcde", new int[]{0, 1}); // Each character is unique, take the first one
        testCases.put("aaaaaa", new int[]{0, 6}); // "aaaaaa" is the longest sequence
        testCases.put("aabbaacc", new int[]{0, 2}); // "aa" starts at index 0
        testCases.put("bbccddaa", new int[]{0, 2}); // "bb" starts at index 0
        testCases.put("aabbcccc", new int[]{4, 4}); // "cccc" starts at index 4
        testCases.put("abcdabcd", new int[]{0, 1}); // All are unique, take the first one
        testCases.put("zxyyyyyx", new int[]{2, 5}); // "yyyyy" starts at index 2

        // Test large data inputs
        String largeInput = "a".repeat(1000000); // A million 'a's
        testCases.put(largeInput, new int[]{0, 1000000});

        // Flag to check if all tests pass
        boolean allPass = true;

        // Iterate through each test case and validate the output
        for (Map.Entry<String, int[]> testCase : testCases.entrySet()) {
            int[] result = getSubstringInfo(testCase.getKey());
            boolean testPassed = Arrays.equals(result, testCase.getValue());
            allPass = allPass && testPassed;

            // Output each test case's result
            System.out.println("Test Case: " + (testCase.getKey().length() > 20 ? "[Large Input]" : "\"" + testCase.getKey() + "\""));
            System.out.println("Expected: " + Arrays.toString(testCase.getValue()));
            System.out.println("Got: " + Arrays.toString(result));
            System.out.println("Result: " + (testPassed ? "PASS" : "FAIL"));
            System.out.println();
        }

        // Output the final result
        if (allPass) {
            System.out.println("All tests pass!");
        } else {
            System.out.println("Some tests failed! :(");
        }
    }
}
