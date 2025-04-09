package com.interview.notes.code.year.y2025.march.common.test2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateNumbersFinder {

    /**
     * Finds all integers that appear more than once in the list.
     * Returns them in ascending order.
     *
     * @param numbers a list of integers
     * @return a list of duplicate integers in ascending order
     */
    public static List<Integer> findDuplicates(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return Collections.emptyList();
        }

        // Step 1: Build frequency map
        Map<Integer, Long> frequencyMap = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Step 2: Filter entries with count > 1, sort, collect to list
        return frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Main method to test the findDuplicates functionality.
     * We print "PASS" or "FAIL" based on the expected results.
     */
    public static void main(String[] args) {
        // ----- Test Case 1 -----
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 3, 2, 6, 7, 7, 8);
        List<Integer> expected1 = Arrays.asList(2, 3, 7);
        List<Integer> result1 = findDuplicates(numbers1);
        checkTestResult("Test Case 1", result1, expected1);

        // ----- Test Case 2 (No duplicates) -----
        List<Integer> numbers2 = Arrays.asList(10, 20, 30, 40, 50);
        List<Integer> expected2 = Collections.emptyList(); // No duplicates
        List<Integer> result2 = findDuplicates(numbers2);
        checkTestResult("Test Case 2", result2, expected2);

        // ----- Test Case 3 (All duplicates) -----
        List<Integer> numbers3 = Arrays.asList(5, 5, 5, 5);
        List<Integer> expected3 = Collections.singletonList(5);
        List<Integer> result3 = findDuplicates(numbers3);
        checkTestResult("Test Case 3", result3, expected3);

        // ----- Test Case 4 (Empty list) -----
        List<Integer> numbers4 = Collections.emptyList();
        List<Integer> expected4 = Collections.emptyList();
        List<Integer> result4 = findDuplicates(numbers4);
        checkTestResult("Test Case 4", result4, expected4);

        // ----- Test Case 5 (Single element) -----
        List<Integer> numbers5 = Collections.singletonList(42);
        List<Integer> expected5 = Collections.emptyList();
        List<Integer> result5 = findDuplicates(numbers5);
        checkTestResult("Test Case 5", result5, expected5);

        // ----- Large Input Test (Stress Test) -----
        // Here we generate a large list with some duplicates intentionally.
        // For demonstration, we’ll use a moderately sized list. 
        // In a real scenario, you could increase the size to millions.
        List<Integer> largeList = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            largeList.add(i % 1000); // repeated pattern every 1000 numbers
        }
        // We know duplicates exist (because many values repeat)
        // We won't specify the entire expected list, but we ensure it runs quickly.
        List<Integer> largeResult = findDuplicates(largeList);
        System.out.println("Large Input Test -> Duplicates Found Count: " + largeResult.size());
    }

    /**
     * Helper method to compare result and expected lists, then print PASS/FAIL.
     */
    private static void checkTestResult(String testCaseName,
                                        List<Integer> result,
                                        List<Integer> expected) {
        if (result.equals(expected)) {
            System.out.println(testCaseName + " -> PASS | Result: " + result);
        } else {
            System.out.println(testCaseName + " -> FAIL");
            System.out.println("   Expected: " + expected);
            System.out.println("   Found:    " + result);
        }
    }
}
