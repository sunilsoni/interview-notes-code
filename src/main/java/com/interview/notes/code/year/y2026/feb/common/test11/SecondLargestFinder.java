package com.interview.notes.code.year.y2026.feb.common.test11;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class SecondLargestFinder {   // Define class based on problem

    // Method to find second largest distinct number
    public static Optional<Integer> findSecondLargest(List<Integer> numbers) {
        
        // Check if list is null or size less than 2
        if (numbers == null || numbers.size() < 2) {
            return Optional.empty();  // Return empty if not enough elements
        }

        return numbers.stream()       // Convert list to stream
                .distinct()           // Remove duplicate values
                .sorted(Comparator.reverseOrder()) // Sort in descending order
                .skip(1)              // Skip the largest element
                .findFirst();         // Get second largest safely
    }

    // Simple test method using main (No JUnit)
    public static void main(String[] args) {

        // Define test cases
        List<List<Integer>> testCases = List.of(
                List.of(10, 20, 30, 40, 50),     // Normal case
                List.of(5, 5, 5, 5),             // All same values
                List.of(100),                    // Single element
                List.of(-1, -5, -3, -1),         // Negative numbers
                IntStream.rangeClosed(1, 1000000).boxed().toList() // Large input
        );

        // Expected results
        List<Optional<Integer>> expected = List.of(
                Optional.of(40),
                Optional.empty(),
                Optional.empty(),
                Optional.of(-3),
                Optional.of(999999)
        );

        // Run tests
        for (int i = 0; i < testCases.size(); i++) {

            Optional<Integer> result = findSecondLargest(testCases.get(i));

            boolean pass = Objects.equals(result, expected.get(i));

            System.out.println("Test Case " + (i + 1) + ": " + (pass ? "PASS" : "FAIL")
                    + " | Result: " + result);
        }
    }
}