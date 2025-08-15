package com.interview.notes.code.year.y2025.august.common.test4;

import java.util.*;
import java.util.stream.*;

public class DuplicateFinder {
    
    // Main method to find duplicates using Java 8 Stream API
    public static List<Integer> findDuplicates(ArrayList<Integer> inputItems) {
        // If input is empty or null, return [0,0]
        if (inputItems == null || inputItems.isEmpty()) {
            return Arrays.asList(0, 0);
        }

        // Group numbers by their value and count occurrences
        Map<Integer, Long> frequencyMap = inputItems.stream()
            .collect(Collectors.groupingBy(
                num -> num,    // Key: the number itself
                Collectors.counting()  // Value: count of occurrences
            ));

        // Find the maximum count of duplicates
        long maxCount = frequencyMap.values().stream()
            .mapToLong(Long::longValue)
            .max()
            .orElse(0);

        // If no duplicates found (max count is 1), return [0,0]
        if (maxCount <= 1) {
            return Arrays.asList(0, 0);
        }

        // Find the largest number with maximum duplicates
        int maxNumber = frequencyMap.entrySet().stream()
            .filter(entry -> entry.getValue() == maxCount)
            .map(Map.Entry::getKey)
            .max(Integer::compareTo)
            .orElse(0);

        return Arrays.asList(maxNumber, (int)maxCount);
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test case 1: Basic duplicate case
        testCase(new ArrayList<>(Arrays.asList(1, 2, 1)), 
                Arrays.asList(1, 2), 
                "Basic duplicate test");

        // Test case 2: No duplicates
        testCase(new ArrayList<>(Arrays.asList(1, 2, 3, 4)), 
                Arrays.asList(0, 0), 
                "No duplicates test");

        // Test case 3: Multiple duplicates with same count
        testCase(new ArrayList<>(Arrays.asList(1, 1, 1, 2, 2, 2, 2, 3)), 
                Arrays.asList(2, 4), 
                "Multiple duplicates test");

        // Test case 4: Empty array
        testCase(new ArrayList<>(), 
                Arrays.asList(0, 0), 
                "Empty array test");

        // Test case 5: Large numbers
        testCase(new ArrayList<>(Arrays.asList(1000, 1000, 999, 999)), 
                Arrays.asList(1000, 2), 
                "Large numbers test");

        // Test case 6: Large dataset
        ArrayList<Integer> largeData = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeData.add(i % 1000); // Creates many duplicates
        }
        testCase(largeData, 
                Arrays.asList(999, 100), 
                "Large dataset test");
    }

    // Helper method to run and verify test cases
    private static void testCase(ArrayList<Integer> input, List<Integer> expected, String testName) {
        List<Integer> result = findDuplicates(input);
        boolean passed = result.equals(expected);
        System.out.printf("Test: %s - %s%n", testName, passed ? "PASS" : "FAIL");
        if (!passed) {
            System.out.printf("Expected: %s, Got: %s%n", expected, result);
        }
    }
}
