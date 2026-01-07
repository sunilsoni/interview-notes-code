package com.interview.notes.code.year.y2026.jan.common.test2;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class PhoneLetterCombinations {    // Main class

    // Static map for digit → letters mapping
    static final Map<Character, String> MAP = Map.of(
        '2', "abc", '3', "def",            // Phone keypad mapping
        '4', "ghi", '5', "jkl",
        '6', "mno", '7', "pqrs",
        '8', "tuv", '9', "wxyz"
    );

    // Method to generate letter combinations
    static List<String> letterCombinations(String digits) {

        // If input is empty, return empty list
        if (digits.isEmpty()) return List.of();

        // Start with one empty string
        List<String> result = List.of("");

        // Loop through each digit
        for (char d : digits.toCharArray()) {

            // Expand previous results using Stream API
            result = result.stream()                       // Stream existing strings
                    .flatMap(s -> MAP.get(d)               // Get letters for digit
                    .chars()                               // Convert letters to stream
                    .mapToObj(c -> s + (char) c))          // Append each letter
                    .toList();                              // Collect as list
        }

        return result;                                      // Return all combinations
    }

    // Helper method to test cases
    static void test(String input, List<String> expected) {

        // Get actual result
        List<String> actual = letterCombinations(input);

        // Compare as sets to ignore order
        boolean pass = new HashSet<>(actual)
                .equals(new HashSet<>(expected));

        // Print test result
        System.out.println(
            "Input: " + input + " => " +
            (pass ? "PASS" : "FAIL") +
            " | Output size: " + actual.size()
        );
    }

    // Main method (NO JUnit as requested)
    public static void main(String[] args) {

        // Provided test cases
        test("23", List.of(
            "ad","ae","af","bd","be","bf","cd","ce","cf"
        ));

        test("2", List.of("a","b","c"));

        // Edge case: minimum input
        test("9", List.of("w","x","y","z"));

        // Large input case (max length = 4)
        List<String> large = letterCombinations("2349");

        // Validate large input size (3×3×3×4 = 108)
        System.out.println(
            "Large Input Test => " +
            (large.size() == 108 ? "PASS" : "FAIL") +
            " | Size: " + large.size()
        );
    }
}
