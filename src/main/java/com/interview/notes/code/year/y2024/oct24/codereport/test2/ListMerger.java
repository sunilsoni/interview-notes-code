package com.interview.notes.code.year.y2024.oct24.codereport.test2;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class ListMerger {
    public static void main(String[] args) {
        // Test Case Runner
        boolean allTestsPassed = true;

        // Test Case 1: Basic test with example data
        allTestsPassed &= runTest(
                new int[]{1, 4, 2, 5},
                new int[]{7, 9, 0, 8},
                new int[]{0, 1, 2, 4, 5, 7, 8, 9},
                "Basic Test"
        );

        // Test Case 2: Empty lists
        allTestsPassed &= runTest(
                new int[]{},
                new int[]{},
                new int[]{},
                "Empty Lists"
        );

        // Test Case 3: One empty list
        allTestsPassed &= runTest(
                new int[]{1, 2, 3},
                new int[]{},
                new int[]{1, 2, 3},
                "One Empty List"
        );

        // Test Case 4: Duplicate numbers
        allTestsPassed &= runTest(
                new int[]{1, 1, 2, 2},
                new int[]{2, 3, 3},
                new int[]{1, 2, 3},
                "Duplicates"
        );

        // Test Case 5: Large data test
        int[] list1 = generateLargeList(50000);
        int[] list2 = generateLargeList(50000);
        int[] expectedResult = mergeSortAndDedupe(list1, list2);
        allTestsPassed &= runTest(
                list1,
                list2,
                expectedResult,
                "Large Data Test"
        );

        // Final result
        System.out.println("\nOverall Test Result: " +
                (allTestsPassed ? "PASS" : "FAIL"));
    }

    // Main logic to merge lists and create ordered set
    public static int[] mergeAndOrder(int[] list1, int[] list2) {
        Set<Integer> mergedSet = new TreeSet<>();

        for (int num : list1) {
            mergedSet.add(num);
        }

        for (int num : list2) {
            mergedSet.add(num);
        }

        return mergedSet.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    // Test runner helper method
    private static boolean runTest(int[] list1, int[] list2,
                                   int[] expected, String testName) {
        long startTime = System.currentTimeMillis();
        int[] result = mergeAndOrder(list1, list2);
        long endTime = System.currentTimeMillis();

        boolean passed = Arrays.equals(result, expected);

        System.out.printf("Test: %s - %s (Time: %dms)\n",
                testName,
                passed ? "PASS" : "FAIL",
                endTime - startTime
        );

        if (!passed) {
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Got: " + Arrays.toString(result));
        }

        return passed;
    }

    // Helper method to generate large test data
    private static int[] generateLargeList(int size) {
        Random rand = new Random();
        return rand.ints(size).toArray();
    }

    // Helper method to create expected result for large data test
    private static int[] mergeSortAndDedupe(int[] arr1, int[] arr2) {
        return Arrays.stream(
                        ArrayUtils.addAll(arr1, arr2))
                .distinct()
                .sorted()
                .toArray();
    }
}