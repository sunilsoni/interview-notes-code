package com.interview.notes.code.year.y2025.jan24.test14;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateFinder {

    // Method to find duplicates and their counts
    public static Map<Integer, Long> findDuplicates(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Basic list with duplicates
        List<Integer> test1 = Arrays.asList(1, 2, 3, 2, 4, 1, 5);
        testCase("Basic duplicates", test1, 2);

        // Test Case 2: No duplicates
        List<Integer> test2 = Arrays.asList(1, 2, 3, 4, 5);
        testCase("No duplicates", test2, 0);

        // Test Case 3: Empty list
        List<Integer> test3 = new ArrayList<>();
        testCase("Empty list", test3, 0);

        // Test Case 4: All duplicates
        List<Integer> test4 = Arrays.asList(1, 1, 1, 1);
        testCase("All same numbers", test4, 1);

        // Test Case 5: Large dataset
        List<Integer> test5 = generateLargeDataset(1000000);
        testCase("Large dataset", test5, 100); // Assuming some duplicates
    }

    // Helper method to run test cases
    private static void testCase(String testName, List<Integer> input, int expectedDuplicates) {
        System.out.println("\nRunning test: " + testName);
        long startTime = System.currentTimeMillis();

        Map<Integer, Long> result = findDuplicates(input);

        long endTime = System.currentTimeMillis();
        boolean passed = result.size() <= expectedDuplicates;

        System.out.println("Input size: " + input.size());
        System.out.println("Found duplicates: " + result);
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
    }

    // Helper method to generate large dataset
    private static List<Integer> generateLargeDataset(int size) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(size / 10)); // This ensures some duplicates
        }
        return list;
    }
}
