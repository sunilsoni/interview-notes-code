package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringPermutations {

    // Main list to store all permutations
    private static final List<String> result = new ArrayList<>();

    public static List<String> findPermutations(String str) {
        // Clear previous results if any
        result.clear();

        // Convert string to char array for easier manipulation
        char[] chars = str.toCharArray();

        // Sort the array first to ensure lexicographical order
        Arrays.sort(chars);

        // Track used characters in each recursion
        boolean[] used = new boolean[chars.length];

        // StringBuilder to build each permutation
        StringBuilder currentPerm = new StringBuilder();

        // Start recursive permutation generation
        generatePermutations(chars, used, currentPerm);

        // Return sorted result using Java 8 Stream
        return result.stream().sorted().collect(Collectors.toList());
    }

    private static void generatePermutations(char[] chars, boolean[] used, StringBuilder current) {
        // Base case: if current permutation length equals original string length
        if (current.length() == chars.length) {
            result.add(current.toString());
            return;
        }

        // Try each character as the next character in permutation
        for (int i = 0; i < chars.length; i++) {
            // Skip if character is already used
            if (used[i]) continue;

            // Skip duplicate adjacent characters
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) continue;

            // Mark current character as used
            used[i] = true;

            // Add current character to permutation
            current.append(chars[i]);

            // Recursive call for next position
            generatePermutations(chars, used, current);

            // Backtrack: remove current character and mark as unused
            current.setLength(current.length() - 1);
            used[i] = false;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test case 1: Basic test
        test("ABC", Arrays.asList("ABC", "ACB", "BAC", "BCA", "CAB", "CBA"));

        // Test case 2: Two characters
        test("XY", Arrays.asList("XY", "YX"));

        // Test case 3: Duplicate characters
        test("AAA", List.of("AAA"));

        // Test case 4: Empty string
        test("", List.of(""));

        // Test case 5: Single character
        test("A", List.of("A"));

        // Test case 6: Larger input
        test("ABCD", Arrays.asList("ABCD", "ABDC", "ACBD", "ACDB", "ADBC", "ADCB",
                "BACD", "BADC", "BCAD", "BCDA", "BDAC", "BDCA",
                "CABD", "CADB", "CBAD", "CBDA", "CDAB", "CDBA",
                "DABC", "DACB", "DBAC", "DBCA", "DCAB", "DCBA"));
    }

    // Helper method to run tests
    private static void test(String input, List<String> expected) {
        List<String> actual = findPermutations(input);
        boolean passed = actual.equals(expected);
        System.out.println("Test case for input '" + input + "': " +
                (passed ? "PASSED" : "FAILED"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
    }
}
