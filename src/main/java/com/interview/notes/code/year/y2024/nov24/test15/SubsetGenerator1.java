package com.interview.notes.code.year.y2024.nov24.test15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubsetGenerator1 {

    // Recursive approach to generate all subsets
    public static <T> List<List<T>> generateSubsets(List<T> objects) {
        List<List<T>> subsets = new ArrayList<>();
        backtrack(objects, 0, new ArrayList<>(), subsets);
        return subsets;
    }

    // Backtracking algorithm to generate subsets
    private static <T> void backtrack(
            List<T> objects,
            int start,
            List<T> current,
            List<List<T>> subsets
    ) {
        // Add current subset to results
        subsets.add(new ArrayList<>(current));

        // Generate further subsets
        for (int i = start; i < objects.size(); i++) {
            current.add(objects.get(i));
            backtrack(objects, i + 1, current, subsets);
            current.remove(current.size() - 1);
        }
    }

    // Iterative approach (alternative implementation)
    public static <T> List<List<T>> generateSubsetsIterative(List<T> objects) {
        List<List<T>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>()); // Empty subset

        for (T obj : objects) {
            int size = subsets.size();
            for (int i = 0; i < size; i++) {
                List<T> newSubset = new ArrayList<>(subsets.get(i));
                newSubset.add(obj);
                subsets.add(newSubset);
            }
        }

        return subsets;
    }

    // Performance test method
    public static void performanceTest() {
        // Large input test
        List<Integer> largeInput = IntStream.rangeClosed(1, 20)
                .boxed()
                .collect(Collectors.toList());

        long startTime = System.nanoTime();
        List<List<Integer>> subsets = generateSubsets(largeInput);
        long endTime = System.nanoTime();

        System.out.println("Large Input Test:");
        System.out.println("Total Subsets: " + subsets.size());
        System.out.println("Time Taken: " + (endTime - startTime) / 1_000_000 + " ms");
    }

    // Comprehensive test cases
    public static void runTests() {
        // Test Case 1: Empty List
        testSubsetGeneration(Collections.emptyList(), "Empty List");

        // Test Case 2: Single Element
        testSubsetGeneration(Arrays.asList(1), "Single Element");

        // Test Case 3: Multiple Elements
        testSubsetGeneration(Arrays.asList(1, 2, 3), "Multiple Elements");

        // Test Case 4: Large Input Performance
        performanceTest();
    }

    // Generic test method for subset generation
    private static <T> void testSubsetGeneration(List<T> input, String testName) {
        System.out.println("\nTest Case: " + testName);

        List<List<T>> recursiveSubsets = generateSubsets(input);
        List<List<T>> iterativeSubsets = generateSubsetsIterative(input);

        // Validate results match
        boolean isValid = recursiveSubsets.size() == iterativeSubsets.size() &&
                recursiveSubsets.containsAll(iterativeSubsets) &&
                iterativeSubsets.containsAll(recursiveSubsets);

        System.out.println("Input: " + input);
        System.out.println("Total Subsets: " + recursiveSubsets.size());
        System.out.println("Validation: " + (isValid ? "PASS" : "FAIL"));

        // Optional: Print subsets for small inputs
        if (input.size() <= 3) {
            System.out.println("Subsets: " + recursiveSubsets);
        }
    }

    public static void main(String[] args) {
        runTests();
    }
}
