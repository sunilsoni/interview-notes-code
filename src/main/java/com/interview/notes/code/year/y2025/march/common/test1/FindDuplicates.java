package com.interview.notes.code.year.y2025.march.common.test1;

import java.util.*;
import java.util.stream.Collectors;

public class FindDuplicates {

    /**
     * Finds duplicate numbers in the list.
     * Each duplicate is returned only once in the order of first occurrence.
     *
     * @param numbers List of integers to process.
     * @return List of integers that appear more than once.
     */
    public static List<Integer> findDuplicates(List<Integer> numbers) {
        // Group numbers by their occurrence count using a LinkedHashMap to preserve order.
        Map<Integer, Long> frequencyMap = numbers.stream()
                .collect(Collectors.groupingBy(num -> num, LinkedHashMap::new, Collectors.counting()));

        // Filter the entries to keep only those with a count greater than one and collect the keys.
        return frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * A simple test method to compare expected and actual results.
     *
     * @param testName Descriptive name of the test case.
     * @param input    The input list to test.
     * @param expected The expected duplicate list.
     */
    public static void runTest(String testName, List<Integer> input, List<Integer> expected) {
        List<Integer> result = findDuplicates(input);
        if (result.equals(expected)) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL");
            System.out.println("   Expected: " + expected);
            System.out.println("   Got:      " + result);
        }
    }

    /**
     * Main method to run various test cases.
     * <p>
     * It includes:
     * - Provided test case.
     * - Test case with no duplicates.
     * - Test case where all numbers are duplicates.
     * - Edge test case with an empty list.
     * - Large input test case.
     */
    public static void main(String[] args) {
        // Test Case 1: Provided test case
        List<Integer> test1 = Arrays.asList(1, 2, 3, 4, 5, 3, 2, 6, 7, 7, 8);
        List<Integer> expected1 = Arrays.asList(2, 3, 7);
        runTest("Test Case 1", test1, expected1);

        // Test Case 2: No duplicates
        List<Integer> test2 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected2 = new ArrayList<>();
        runTest("Test Case 2 (No Duplicates)", test2, expected2);

        // Test Case 3: All numbers are duplicates (each should appear once in the result)
        List<Integer> test3 = Arrays.asList(2, 2, 2, 2, 2);
        List<Integer> expected3 = List.of(2);
        runTest("Test Case 3 (All Duplicates)", test3, expected3);

        // Test Case 4: Edge case with an empty list
        List<Integer> test4 = new ArrayList<>();
        List<Integer> expected4 = new ArrayList<>();
        runTest("Test Case 4 (Empty List)", test4, expected4);

        // Test Case 5: Large Input Test
        // Create a large list with each number from 0 to 9999 repeated twice.
        List<Integer> largeInput = new ArrayList<>();
        List<Integer> largeExpected = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeInput.add(i);
            largeInput.add(i);
            largeExpected.add(i);
        }
        runTest("Test Case 5 (Large Input)", largeInput, largeExpected);
    }
}
