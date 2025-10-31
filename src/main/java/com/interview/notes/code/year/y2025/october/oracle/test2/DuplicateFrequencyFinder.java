package com.interview.notes.code.year.y2025.october.oracle.test2;

import java.util.*;
import java.util.stream.Collectors;

public class DuplicateFrequencyFinder {

    // This method takes a list of integers and returns a map of duplicates with their frequency
    public static Map<Integer, Long> findDuplicatesWithFrequency(List<Integer> numbers) {
        // Step 1: Group numbers and count how many times each appears
        Map<Integer, Long> frequencyMap = numbers.stream()
            .collect(Collectors.groupingBy(
                num -> num,                      // Group by the number itself
                LinkedHashMap::new,              // Use LinkedHashMap to keep order
                Collectors.counting()            // Count how many times each number appears
            ));

        // Step 2: Filter only those numbers that appear more than once (duplicates)
        Map<Integer, Long> duplicates = frequencyMap.entrySet().stream()
            .filter(entry -> entry.getValue() > 1) // Keep only entries with count > 1
            .collect(Collectors.toMap(
                Map.Entry::getKey,                 // Key is the number
                Map.Entry::getValue,               // Value is the frequency
                (e1, e2) -> e1,                    // Merge function (not needed here)
                LinkedHashMap::new                 // Keep insertion order
            ));

        // Step 3: Return the final map of duplicates with their frequency
        return duplicates;
    }

    // Main method to test the solution with multiple test cases
    public static void main(String[] args) {
        // Test Case 1: Simple input with duplicates
        List<Integer> test1 = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
        Map<Integer, Long> result1 = findDuplicatesWithFrequency(test1);
        System.out.println("Test 1 Result: " + result1);
        System.out.println(result1.equals(Map.of(2, 2L, 3, 3L)) ? "PASS" : "FAIL");

        // Test Case 2: No duplicates
        List<Integer> test2 = Arrays.asList(5, 6, 7, 8);
        Map<Integer, Long> result2 = findDuplicatesWithFrequency(test2);
        System.out.println("Test 2 Result: " + result2);
        System.out.println(result2.isEmpty() ? "PASS" : "FAIL");

        // Test Case 3: All elements are the same
        List<Integer> test3 = Arrays.asList(9, 9, 9, 9);
        Map<Integer, Long> result3 = findDuplicatesWithFrequency(test3);
        System.out.println("Test 3 Result: " + result3);
        System.out.println(result3.equals(Map.of(9, 4L)) ? "PASS" : "FAIL");

        // Test Case 4: Large input with random duplicates
        List<Integer> test4 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            test4.add(i % 100); // Will repeat numbers 0–99 many times
        }
        Map<Integer, Long> result4 = findDuplicatesWithFrequency(test4);
        System.out.println("Test 4 Result Size: " + result4.size());
        System.out.println(result4.size() == 100 ? "PASS" : "FAIL");

        // Test Case 5: Edge case with empty list
        List<Integer> test5 = Collections.emptyList();
        Map<Integer, Long> result5 = findDuplicatesWithFrequency(test5);
        System.out.println("Test 5 Result: " + result5);
        System.out.println(result5.isEmpty() ? "PASS" : "FAIL");
    }
}
