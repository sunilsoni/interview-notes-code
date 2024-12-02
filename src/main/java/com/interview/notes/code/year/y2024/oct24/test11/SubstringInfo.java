package com.interview.notes.code.year.y2024.oct24.test11;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class SubstringInfo {

    // Test cases mapping input string to expected results: [start index, length]
    private static final Map<String, int[]> testCases = new LinkedHashMap<>();

    // Method to get substring information
    public static int[] getSubstringInfo(String input) {
        int start = -1;
        int length = 0;

        // Logic to find the first sequence of repeating characters
        for (int i = 0; i < input.length(); i++) {
            int currentLength = 1;
            for (int j = i + 1; j < input.length(); j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    currentLength++;
                } else {
                    break;
                }
            }
            // Update start and length if current sequence is longer than previous
            if (currentLength > length) {
                start = i;
                length = currentLength;
            }
        }

        return new int[]{start, length};
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
