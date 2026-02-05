package com.interview.notes.code.year.y2026.jan.common.test1;

import java.util.List;
import java.util.stream.IntStream;

public class UniqueChecker {
    public static void main(String[] args) {
        // Test Case 1: Standard small list
        runTest("Small List", List.of("A", "B", "A", "C"), List.of("A", "B", "C"));

        // Test Case 2: Large data input (1 million items)
        List<String> largeInput = IntStream.range(0, 1_000_000)
                .mapToObj(i -> "Item" + (i % 10)) // Repeats 10 items
                .toList();
        runTest("Large Input", largeInput, List.of("Item0", "Item1", "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item8", "Item9"));
    }

    /**
     * Core logic using Java 21 and Streams
     */
    public static List<String> getUnique(List<String> input) {
        return input.stream()          // Convert list to a flow of data
                .distinct()       // Remove duplicates using internal Hash hashing
                .toList();        // Java 16+ shorthand to collect into unmodifiable list
    }

    /**
     * Simple manual test runner to check PASS/FAIL
     */
    private static void runTest(String name, List<String> input, List<String> expected) {
        // Process the input
        List<String> result = getUnique(input);

        // Check if result matches expectation (order matters for List.equals)
        boolean passed = result.equals(expected);

        // Print result with simple pass/fail flag
        System.out.println(name + " -> " + (passed ? "PASS" : "FAIL"));
    }
}