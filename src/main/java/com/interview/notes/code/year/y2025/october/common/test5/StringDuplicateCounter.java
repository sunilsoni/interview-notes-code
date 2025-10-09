package com.interview.notes.code.year.y2025.october.common.test5;

import java.util.*;
import java.util.stream.Collectors;

public class StringDuplicateCounter {
    
    // Main method to process string duplicates and run test cases
    public static void main(String[] args) {
        // Test Case 1: Basic list with duplicates
        List<String> test1 = Arrays.asList("apple", "banana", "apple", "cherry", "banana");
        System.out.println("Test Case 1 - Basic duplicates:");
        printDuplicateCounts(test1);
        
        // Test Case 2: Empty list
        List<String> test2 = new ArrayList<>();
        System.out.println("\nTest Case 2 - Empty list:");
        printDuplicateCounts(test2);
        
        // Test Case 3: List with no duplicates
        List<String> test3 = Arrays.asList("cat", "dog", "bird");
        System.out.println("\nTest Case 3 - No duplicates:");
        printDuplicateCounts(test3);
        
        // Test Case 4: Large dataset
        List<String> test4 = generateLargeDataset(10000);
        System.out.println("\nTest Case 4 - Large dataset (showing first 5 entries):");
        printDuplicateCountsLimited(test4, 5);
    }
    
    // Method to count and print duplicates using Stream API
    public static void printDuplicateCounts(List<String> strings) {
        // Group strings by their value and count occurrences
        Map<String, Long> countMap = strings.stream()
            // Collect items into a map where key is the string and value is count
            .collect(Collectors.groupingBy(
                str -> str,    // Key is the string itself
                Collectors.counting()  // Value is the count of occurrences
            ));
        
        // Print results in a formatted way
        countMap.forEach((string, count) -> 
            System.out.printf("'%s' appears %d time(s)%n", string, count));
    }
    
    // Method to print limited results for large datasets
    public static void printDuplicateCountsLimited(List<String> strings, int limit) {
        strings.stream()
            .collect(Collectors.groupingBy(
                str -> str,
                Collectors.counting()
            ))
            .entrySet().stream()
            .limit(limit)  // Limit output to specified number
            .forEach((entry) -> 
                System.out.printf("'%s' appears %d time(s)%n", 
                    entry.getKey(), entry.getValue()));
    }
    
    // Helper method to generate large test dataset
    private static List<String> generateLargeDataset(int size) {
        List<String> result = new ArrayList<>();
        String[] baseWords = {"apple", "banana", "cherry", "date", "elderberry"};
        Random random = new Random();
        
        // Generate random strings from base words
        for (int i = 0; i < size; i++) {
            result.add(baseWords[random.nextInt(baseWords.length)]);
        }
        return result;
    }
}
