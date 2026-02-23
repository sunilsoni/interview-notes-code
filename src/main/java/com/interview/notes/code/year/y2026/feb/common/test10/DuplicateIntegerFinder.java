package com.interview.notes.code.year.y2026.feb.common.test10;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateIntegerFinder {        // Creating main class with proper naming

    // Method to find duplicates
    static List<Integer> findDuplicates(List<Integer> list) {   // Method takes list and returns duplicate list
        
        return list.stream()                                   // Convert list to stream for processing
                .collect(Collectors.groupingBy(                 // Group elements
                        n -> n,                                 // Key = number itself
                        Collectors.counting()))                 // Count occurrences of each number
                .entrySet()                                     // Convert map to entry set
                .stream()                                       // Stream over map entries
                .filter(e -> e.getValue() > 1)                  // Keep only elements with count > 1
                .map(Map.Entry::getKey)                         // Extract the duplicate number
                .toList();                                      // Collect results into list
    }

    public static void main(String[] args) {                    // Main method for testing
        
        // Test Case 1: Normal case with duplicates
        List<Integer> test1 = List.of(1,2,3,4,5,2,3,6,7,3);    // Creating test list
        List<Integer> expected1 = List.of(2,3);                 // Expected duplicates
        System.out.println("Test1: " +                         // Print result
                (findDuplicates(test1).containsAll(expected1) ? "PASS" : "FAIL"));

        // Test Case 2: No duplicates
        List<Integer> test2 = List.of(1,2,3,4,5);               // No duplicate list
        System.out.println("Test2: " +                         // Should return empty
                (findDuplicates(test2).isEmpty() ? "PASS" : "FAIL"));

        // Test Case 3: All same elements
        List<Integer> test3 = List.of(9,9,9,9);                 // All duplicates
        System.out.println("Test3: " +                         // Expect 9
                (findDuplicates(test3).equals(List.of(9)) ? "PASS" : "FAIL"));

        // Test Case 4: Empty list
        List<Integer> test4 = List.of();                        // Empty input
        System.out.println("Test4: " +                         // Expect empty
                (findDuplicates(test4).isEmpty() ? "PASS" : "FAIL"));

        // Test Case 5: Large data input
        List<Integer> large = new ArrayList<>();                // Create large list
        for(int i=0;i<1000000;i++) large.add(i);                // Add 1M unique numbers
        large.add(500000);                                      // Add duplicate
        System.out.println("Test5: " +                         // Check large data handling
                (findDuplicates(large).contains(500000) ? "PASS" : "FAIL"));
    }
}