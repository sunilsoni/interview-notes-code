package com.interview.notes.code.year.y2024.nov24.test14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetGenerator {

    /**
     * Generates all possible subsets of the given list of objects.
     *
     * @param <T>  the type of objects in the input list
     * @param list the input list of objects
     * @return a list of all possible subsets
     */
    public static <T> List<List<T>> generateSubsets(List<T> list) {
        List<List<T>> subsets = new ArrayList<>();
        int n = list.size();
        long totalSubsets = 1L << n; // 2^n subsets

        for (long i = 0; i < totalSubsets; i++) {
            List<T> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1L << j)) != 0) {
                    subset.add(list.get(j));
                }
            }
            subsets.add(subset);
        }

        return subsets;
    }

    /**
     * Runs all test cases and prints pass/fail results.
     */
    public static void runTests() {
        int passed = 0;
        int failed = 0;

        // Test Case 1: Empty list
        List<Integer> input1 = new ArrayList<>();
        List<List<Integer>> expected1 = Arrays.asList(new ArrayList<>());
        if (generateSubsets(input1).equals(expected1)) {
            System.out.println("Test Case 1 Passed");
            passed++;
        } else {
            System.out.println("Test Case 1 Failed");
            failed++;
        }

        // Test Case 2: Single element
        List<String> input2 = Arrays.asList("a");
        List<List<String>> expected2 = Arrays.asList(
                new ArrayList<>(),
                Arrays.asList("a")
        );
        if (generateSubsets(input2).equals(expected2)) {
            System.out.println("Test Case 2 Passed");
            passed++;
        } else {
            System.out.println("Test Case 2 Failed");
            failed++;
        }

        // Test Case 3: Multiple elements
        List<Integer> input3 = Arrays.asList(1, 2, 3);
        List<List<Integer>> expected3 = Arrays.asList(
                new ArrayList<>(),
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(1, 2),
                Arrays.asList(3),
                Arrays.asList(1, 3),
                Arrays.asList(2, 3),
                Arrays.asList(1, 2, 3)
        );
        if (generateSubsets(input3).equals(expected3)) {
            System.out.println("Test Case 3 Passed");
            passed++;
        } else {
            System.out.println("Test Case 3 Failed");
            failed++;
        }

        // Test Case 4: Large input
        List<Integer> input4 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            input4.add(i);
        }
        try {
            List<List<Integer>> subsets = generateSubsets(input4);
            if (subsets.size() == (1 << 20)) {
                System.out.println("Test Case 4 Passed");
                passed++;
            } else {
                System.out.println("Test Case 4 Failed");
                failed++;
            }
        } catch (Exception e) {
            System.out.println("Test Case 4 Failed with Exception: " + e.getMessage());
            failed++;
        }

        // Additional Edge Cases can be added here

        // Summary
        System.out.println("Total Passed: " + passed);
        System.out.println("Total Failed: " + failed);
    }

    public static void main(String[] args) {
        runTests();
    }
}
