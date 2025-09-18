package com.interview.notes.code.year.y2025.september.common.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ThirdHighestNumberFinder {

    // Main method to find third highest number from a list of strings
    public static Double findThirdHighest(List<String> inputList) {
        // Convert string list to double list, handling invalid inputs
        List<Double> numbers = inputList.stream()
                .map(String::trim) // Remove leading/trailing spaces
                .filter(s -> s.matches("-?\\d+(\\.\\d+)?")) // Keep only valid numbers
                .map(Double::parseDouble) // Convert strings to doubles
                .distinct() // Remove duplicates
                .sorted(Collections.reverseOrder()) // Sort in descending order
                .collect(Collectors.toList()); // Collect results to list

        // Return third highest if exists, otherwise null
        return numbers.size() >= 3 ? numbers.get(2) : null;
    }

    public static void main(String[] args) {
        // Test Case 1: Original list
        List<String> test1 = Arrays.asList("  ", "2000 ", "apple", " 12",
                "  200", " 15", "2 8", "-249", " 25", "98", ".22",
                "-22", "32", "22", "-29", " 21");
        testCase(test1, 98.0, "Original List");

        // Test Case 2: Empty list
        List<String> test2 = List.of();
        testCase(test2, null, "Empty List");

        // Test Case 3: List with less than 3 numbers
        List<String> test3 = Arrays.asList("10", "20");
        testCase(test3, null, "Less than 3 numbers");

        // Test Case 4: List with duplicates
        List<String> test4 = Arrays.asList("100", "100", "50", "50", "25");
        testCase(test4, 25.0, "List with duplicates");

        // Test Case 5: List with large numbers
        List<String> test5 = Arrays.asList("999999999", "888888888",
                "777777777", "666666666");
        testCase(test5, 777777777.0, "Large numbers");
    }

    // Helper method to run test cases
    private static void testCase(List<String> input, Double expected,
                                 String testName) {
        Double result = findThirdHighest(input);
        boolean passed = (result == null && expected == null) ||
                (result != null && result.equals(expected));

        System.out.println("Test Case: " + testName);
        System.out.println("Input: " + input);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }
}
