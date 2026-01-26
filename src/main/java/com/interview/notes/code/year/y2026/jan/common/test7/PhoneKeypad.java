package com.interview.notes.code.year.y2026.jan.common.test7;

import java.util.List;
import java.util.Map;

public class PhoneKeypad {

    // Mapping digits to letters (phone keypad)
    static final Map<Character, String> MAP = Map.of(
        '2', "ABC", '3', "DEF", '4', "GHI",
        '5', "JKL", '6', "MNO",
        '7', "PQRS", '8', "TUV", '9', "WXYZ"
    );

    public static void main(String[] args) {

        // Test cases array (small + edge + large)
        String[] tests = { "2", "23", "79", "", "201", "234567" };

        // Loop through each test input
        for (String input : tests) {

            // Call method to generate combinations
            List<String> result = combinations(input);

            // Decide PASS or FAIL based on validity
            boolean pass = !input.matches(".*[01].*");

            // Print test input
            System.out.println("Input: " + input);

            // Print combinations
            System.out.println("Output: " + result);

            // Print total combinations
            System.out.println("Count: " + result.size());

            // Print PASS or FAIL
            System.out.println("Status: " + (pass ? "PASS" : "FAIL"));

            // Line break for readability
            System.out.println("----------------------------");
        }
    }

    // Method to generate all keypad combinations
    static List<String> combinations(String digits) {

        // If input is empty, return empty list
        if (digits == null || digits.isEmpty())
            return List.of();

        // Start with one empty string to build combinations
        List<String> result = List.of("");

        // Loop through each digit
        for (char d : digits.toCharArray()) {

            // If digit is invalid (0 or 1), return empty list
            if (!MAP.containsKey(d))
                return List.of();

            // Get letters for current digit
            String letters = MAP.get(d);

            // Combine existing strings with new letters using Stream API
            result = result.stream()
                .flatMap(s -> letters.chars()
                    .mapToObj(c -> s + (char) c))
                .toList(); // Java 21 immutable list
        }

        // Return final combinations
        return result;
    }
}
