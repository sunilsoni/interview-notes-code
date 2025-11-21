package com.interview.notes.code.year.y2025.march.common.test16;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FilterStringsByLength {

    // Method to filter out strings of given length 'n'
    public static List<String> filterStrings(String[] input, int n) {
        return Arrays.stream(input) // Convert array to stream for functional processing
                .filter(str -> str.length() != n) // Filter condition to remove strings matching length 'n'
                .collect(Collectors.toList()); // Collect filtered elements into a List
    }

    // Test method to validate logic
    public static void runTests() {
        // Simple test
        test(new String[]{"test", "bar", "brown"}, 4, Arrays.asList("bar", "brown"));

        // Edge case: Empty input array
        test(new String[]{}, 3, List.of());

        // Edge case: No matching length
        test(new String[]{"apple", "orange", "banana"}, 2, Arrays.asList("apple", "orange", "banana"));

        // Edge case: All elements removed
        test(new String[]{"one", "two", "six"}, 3, List.of());

        // Large data input
        String[] largeInput = IntStream.range(0, 100000)
                .mapToObj(i -> "test" + i)
                .toArray(String[]::new);
        List<String> largeOutput = filterStrings(largeInput, 6); // Removing strings of length 6
        System.out.println("Large Test Passed? " + (largeOutput.size() == 90000 ? "PASS" : "FAIL"));
    }

    // Helper method for test validation
    private static void test(String[] input, int n, List<String> expectedOutput) {
        List<String> actualOutput = filterStrings(input, n);
        System.out.println("Test with input: " + Arrays.toString(input) + ", n = " + n);
        System.out.println(actualOutput.equals(expectedOutput) ? "PASS" : "FAIL");
    }

    public static void main(String[] args) {
        runTests();
    }
}
