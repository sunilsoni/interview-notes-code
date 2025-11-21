package com.interview.notes.code.year.y2024.nov24.test15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Amazon: Given a list of objects, return all possible subsets of these objects.
public class IntegerSubsetGenerator {

    // Recursive Subset Generation for Integers
    public static List<List<Integer>> generateSubsets(List<Integer> numbers) {
        List<List<Integer>> subsets = new ArrayList<>();
        backtrackSubsets(numbers, 0, new ArrayList<>(), subsets);
        return subsets;
    }

    // Recursive Backtracking Method
    private static void backtrackSubsets(
            List<Integer> numbers,
            int start,
            List<Integer> currentSubset,
            List<List<Integer>> allSubsets
    ) {
        // Add current subset to results
        allSubsets.add(new ArrayList<>(currentSubset));

        // Generate further subsets
        for (int i = start; i < numbers.size(); i++) {
            currentSubset.add(numbers.get(i));
            backtrackSubsets(numbers, i + 1, currentSubset, allSubsets);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }

    // Iterative Subset Generation
    public static List<List<Integer>> generateSubsetsIterative(List<Integer> numbers) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>()); // Empty subset

        for (Integer num : numbers) {
            int currentSize = subsets.size();
            for (int i = 0; i < currentSize; i++) {
                List<Integer> newSubset = new ArrayList<>(subsets.get(i));
                newSubset.add(num);
                subsets.add(newSubset);
            }
        }

        return subsets;
    }

    // Advanced Subset Generation with Filtering
    public static List<List<Integer>> generateSubsetsWithFilter(
            List<Integer> numbers,
            int minSize,
            int maxSize
    ) {
        List<List<Integer>> allSubsets = generateSubsets(numbers);
        return allSubsets.stream()
                .filter(subset ->
                        subset.size() >= minSize &&
                                subset.size() <= maxSize
                )
                .collect(Collectors.toList());
    }

    // Performance and Stress Testing
    public static void performanceTest() {
        // Test with increasing input sizes
        int[] testSizes = {5, 10, 15, 20};

        for (int size : testSizes) {
            List<Integer> input = IntStream.rangeClosed(1, size)
                    .boxed()
                    .collect(Collectors.toList());

            long startTime = System.nanoTime();
            List<List<Integer>> subsets = generateSubsets(input);
            long endTime = System.nanoTime();

            System.out.printf("Input Size: %d, Total Subsets: %d, Time: %d ms%n",
                    size,
                    subsets.size(),
                    (endTime - startTime) / 1_000_000
            );
        }
    }

    // Comprehensive Test Cases
    public static void runTests() {
        // Test Case 1: Empty List
        testSubsetGeneration(Collections.emptyList(), "Empty List");

        // Test Case 2: Single Element
        testSubsetGeneration(List.of(42), "Single Element");

        // Test Case 3: Multiple Elements
        testSubsetGeneration(Arrays.asList(1, 2, 3), "Basic Multiple Elements");

        // Test Case 4: Larger Input
        testSubsetGeneration(Arrays.asList(10, 20, 30, 40), "Larger Input");

        // Test Case 5: Filtered Subsets
        testFilteredSubsets();

        // Performance Test
        performanceTest();
    }

    // Generic Subset Test Method
    private static void testSubsetGeneration(List<Integer> input, String testName) {
        System.out.println("\nTest Case: " + testName);

        List<List<Integer>> recursiveSubsets = generateSubsets(input);
        List<List<Integer>> iterativeSubsets = generateSubsetsIterative(input);

        // Validate results
        boolean isValid = recursiveSubsets.size() == iterativeSubsets.size() &&
                recursiveSubsets.containsAll(iterativeSubsets) &&
                iterativeSubsets.containsAll(recursiveSubsets);

        System.out.println("Input: " + input);
        System.out.println("Total Subsets: " + recursiveSubsets.size());
        System.out.println("Validation: " + (isValid ? "PASS" : "FAIL"));

        // Print subsets for small inputs
        if (input.size() <= 4) {
            System.out.println("Subsets: " + recursiveSubsets);
        }
    }

    // Test Filtered Subset Generation
    private static void testFilteredSubsets() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);

        List<List<Integer>> filteredSubsets = generateSubsetsWithFilter(input, 2, 3);

        System.out.println("\nFiltered Subset Test");
        System.out.println("Input: " + input);
        System.out.println("Filtered Subsets (size 2-3): " + filteredSubsets);
        System.out.println("Total Filtered Subsets: " + filteredSubsets.size());
    }

    public static void main(String[] args) {
        runTests();
    }
}
