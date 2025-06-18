package com.interview.notes.code.year.y2025.June.common.test1;

import java.util.*;
import java.util.stream.Collectors;

public class NameCounter {
    public static void main(String[] args) {
        // Test case 1: Normal list with duplicates
        List<String> names1 = Arrays.asList("John", "Jane", "John", "Bob", "Alice", "Bob");
        System.out.println("Test Case 1 - Normal list:");
        countAndPrintNames(names1);

        // Test case 2: Empty list
        List<String> names2 = new ArrayList<>();
        System.out.println("\nTest Case 2 - Empty list:");
        countAndPrintNames(names2);

        // Test case 3: Single name
        List<String> names3 = Arrays.asList("John");
        System.out.println("\nTest Case 3 - Single name:");
        countAndPrintNames(names3);

        // Test case 4: Large dataset
        List<String> names4 = generateLargeDataset(1000000);
        System.out.println("\nTest Case 4 - Large dataset (1M records):");
        long startTime = System.currentTimeMillis();
        countAndPrintNames(names4);
        long endTime = System.currentTimeMillis();
        System.out.println("Processing time: " + (endTime - startTime) + "ms");
    }

    // Main method to count and print names using Stream API
    public static void countAndPrintNames(List<String> names) {
        try {
            // Group names by their value and count occurrences
            Map<String, Long> nameCount = names.stream()
                .collect(Collectors.groupingBy(
                    name -> name,    // Key is the name itself
                    Collectors.counting()  // Count occurrences
                ));

            // Check if the list is empty
            if (nameCount.isEmpty()) {
                System.out.println("No names in the list");
                return;
            }

            // Print each name and its count
            nameCount.forEach((name, count) -> 
                System.out.println(name + ": " + count));

        } catch (Exception e) {
            System.out.println("Error processing names: " + e.getMessage());
        }
    }

    // Helper method to generate large test dataset
    private static List<String> generateLargeDataset(int size) {
        List<String> largeList = new ArrayList<>();
        String[] sampleNames = {"John", "Jane", "Bob", "Alice", "Mike"};
        Random random = new Random();
        
        for (int i = 0; i < size; i++) {
            largeList.add(sampleNames[random.nextInt(sampleNames.length)]);
        }
        return largeList;
    }
}
