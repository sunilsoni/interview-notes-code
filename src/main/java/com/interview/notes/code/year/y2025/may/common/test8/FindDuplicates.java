package com.interview.notes.code.year.y2025.may.common.test8;

import java.util.*;
import java.util.stream.Collectors;

public class FindDuplicates {
    
    // Method 1: Using Stream to find duplicates
    public static List<Integer> findDuplicatesUsingStream(List<Integer> numbers) {
        // Group by number and count occurrences, filter counts > 1, and get the numbers
        return numbers.stream()
                .collect(Collectors.groupingBy(
                        number -> number,    // group by the number itself
                        Collectors.counting() // count occurrences
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1) // keep only entries with count > 1
                .map(Map.Entry::getKey)      // get just the numbers
                .collect(Collectors.toList());
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Normal case with duplicates
        List<Integer> test1 = Arrays.asList(1, 2, 3, 2, 4, 3, 5);
        testAndPrint("Test 1 - Normal case", test1);

        // Test Case 2: No duplicates
        List<Integer> test2 = Arrays.asList(1, 2, 3, 4, 5);
        testAndPrint("Test 2 - No duplicates", test2);

        // Test Case 3: Empty list
        List<Integer> test3 = new ArrayList<>();
        testAndPrint("Test 3 - Empty list", test3);

        // Test Case 4: All duplicates
        List<Integer> test4 = Arrays.asList(1, 1, 1, 1);
        testAndPrint("Test 4 - All same numbers", test4);

        // Test Case 5: Large dataset
        List<Integer> test5 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            test5.add(i % 1000); // This will create many duplicates
        }
        testAndPrint("Test 5 - Large dataset", test5);
    }

    // Helper method to run tests and print results
    private static void testAndPrint(String testName, List<Integer> input) {
        System.out.println("\n" + testName);
        System.out.println("Input: " + (input.size() > 20 ? 
                          input.subList(0, 20) + "... (size: " + input.size() + ")" : 
                          input));
        
        long startTime = System.currentTimeMillis();
        List<Integer> duplicates = findDuplicatesUsingStream(input);
        long endTime = System.currentTimeMillis();

        System.out.println("Duplicates found: " + 
                         (duplicates.size() > 20 ? 
                         duplicates.subList(0, 20) + "... (total: " + duplicates.size() + ")" : 
                         duplicates));
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
        System.out.println("Test Result: " + (validateTest(input, duplicates) ? "PASS" : "FAIL"));
    }

    // Validate test results
    private static boolean validateTest(List<Integer> input, List<Integer> duplicates) {
        // Check if each number in duplicates appears at least twice in input
        return duplicates.stream()
                .allMatch(num -> 
                    input.stream().filter(x -> x.equals(num)).count() > 1
                );
    }
}
