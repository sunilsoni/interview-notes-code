package com.interview.notes.code.year.y2025.June.common.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NestedListCalculator {

    /**
     * Compute the product of all elements in a possibly nested list.
     * Integers stay as-is; lists get replaced by the sum of their contents.
     */
    public static int computeProduct(List<?> items) {
        // Stream over each item, map to its numeric value, then multiply all together.
        return items.stream()
                .mapToInt(item -> {
                    // If the item is an Integer, return its value directly
                    if (item instanceof Integer) {
                        return (Integer) item;
                    }
                    // Otherwise assume it's a List<?> and compute its sum recursively
                    @SuppressWarnings("unchecked")
                    List<?> nested = (List<?>) item;
                    return sumNested(nested);
                })
                // Start multiplication from 1, multiply each mapped value
                .reduce(1, (a, b) -> a * b);
    }

    /**
     * Recursively sum all integers in a possibly nested list.
     */
    private static int sumNested(List<?> items) {
        // Stream over each item, map to int, then sum them all.
        return items.stream()
                .mapToInt(item -> {
                    if (item instanceof Integer) {
                        // Direct integer
                        return (Integer) item;
                    }
                    // Nested list: recurse
                    @SuppressWarnings("unchecked")
                    List<?> deeper = (List<?>) item;
                    return sumNested(deeper);
                })
                .sum();
    }

    /**
     * Simple main method to test multiple cases without JUnit.
     * Prints PASS or FAIL for each.
     */
    public static void main(String[] args) {
        // Define test cases: list + expected result
        Object[][] tests = new Object[][]{
                // Provided example
                {Arrays.asList(1, 2, 3, Arrays.asList(1, 2), 3, Arrays.asList(1, Arrays.asList(2, 4)), 3, 4), 4536},
                // Single integer
                {Arrays.asList(5), 5},
                // Single nested list
                {Arrays.asList(Arrays.asList(1, 2, 3)), 6},
                // Mixed nesting
                {Arrays.asList(2, Arrays.asList(3, Arrays.asList(4)), 5), 70},
                // Empty outer list → product = 1 by convention
                {Collections.emptyList(), 1}
        };

        // Run each test
        for (int i = 0; i < tests.length; i++) {
            @SuppressWarnings("unchecked")
            List<?> input = (List<?>) tests[i][0];
            int expected = (Integer) tests[i][1];
            int actual = computeProduct(input);
            // Print PASS or FAIL
            System.out.printf("Test %d: expected=%d, actual=%d → %s%n",
                    i + 1, expected, actual, (expected == actual ? "PASS" : "FAIL"));
        }

        // Example large-data check (all ones, should be 1)
        List<Integer> large = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            large.add(1);
        }
        System.out.printf("Large test: expected=1, actual=%d → %s%n",
                computeProduct(large), computeProduct(large) == 1 ? "PASS" : "FAIL");
    }
}