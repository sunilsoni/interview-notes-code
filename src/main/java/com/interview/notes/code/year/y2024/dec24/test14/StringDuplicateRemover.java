package com.interview.notes.code.year.y2024.dec24.test14;

import java.util.stream.Collectors;

public class StringDuplicateRemover {

    // Method to remove duplicate characters
    public static String removeDuplicates(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        return input.chars()                    // Convert to IntStream
                .mapToObj(ch -> String.valueOf((char) ch))  // Convert each char to String
                .distinct()                  // Remove duplicates
                .collect(Collectors.joining()); // Join back to string
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test cases
        runTest("hello", "helo", "Test 1: Basic case");
        runTest("", "", "Test 2: Empty string");
        runTest(null, "", "Test 3: Null input");
        runTest("aaaaaa", "a", "Test 4: All same characters");
        runTest("Hello World", "Helo Wrd", "Test 5: With spaces");
        runTest("12344321", "1234", "Test 6: Numbers");

        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append("abcdef");
        }
        long startTime = System.currentTimeMillis();
        String result = removeDuplicates(largeInput.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("Large input test (600000 chars) - Time taken: " +
                (endTime - startTime) + "ms");
        System.out.println("Result length: " + result.length() +
                " (Expected: 6)");
    }

    // Helper method to run tests
    private static void runTest(String input, String expected, String testName) {
        String result = removeDuplicates(input);
        boolean passed = result.equals(expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }
}
