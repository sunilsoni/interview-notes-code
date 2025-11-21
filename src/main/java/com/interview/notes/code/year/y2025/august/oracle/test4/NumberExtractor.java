package com.interview.notes.code.year.y2025.august.oracle.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*

Given an array of unique non-negative integers `{A1, A2, …, An}` and a positive integer `X`, write a function that finds all pairs of numbers in the array that sum to `X`.

 */
public class NumberExtractor {

    // Main function to extract contiguous numbers from array of strings
    public static List<String> extractNumbers(String[] input) {
        // Using Stream API to process the input array
        return Arrays.stream(input)
                // Split each string into characters
                .flatMap(str -> str.chars()
                        // Convert each char to String for processing
                        .mapToObj(ch -> String.valueOf((char) ch)))
                // Filter to keep only digit characters
                .filter(ch -> Character.isDigit(ch.charAt(0)))
                // Collect consecutive digits together
                .reduce(new ArrayList<String>(), (list, digit) -> {
                    // If list is empty or last number doesn't end with a digit, add new number
                    if (list.isEmpty() || !Character.isDigit(list.get(list.size() - 1).charAt(list.get(list.size() - 1).length() - 1))) {
                        list.add(digit);
                    } else {
                        // Append digit to last number
                        int lastIndex = list.size() - 1;
                        list.set(lastIndex, list.get(lastIndex) + digit);
                    }
                    return list;
                }, (list1, list2) -> list1)
                // Sort the numbers naturally
                .stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Basic test
        testCase(
                new String[]{"A1B", "C2", "34B", "5F6", "7"},
                Arrays.asList("1", "2", "34", "5", "6", "7"),
                "Basic Test"
        );

        // Test Case 2: Empty array
        testCase(
                new String[]{},
                List.of(),
                "Empty Array Test"
        );

        // Test Case 3: No numbers
        testCase(
                new String[]{"ABC", "DEF"},
                List.of(),
                "No Numbers Test"
        );

        // Test Case 4: Large numbers
        testCase(
                new String[]{"A123B", "456C", "789"},
                Arrays.asList("123", "456", "789"),
                "Large Numbers Test"
        );

        // Test Case 5: Special characters
        testCase(
                new String[]{"@1#", "$2%", "^3&"},
                Arrays.asList("1", "2", "3"),
                "Special Characters Test"
        );
    }

    // Helper method to run test cases
    private static void testCase(String[] input, List<String> expected, String testName) {
        List<String> result = extractNumbers(input);
        boolean passed = result.equals(expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }
}
