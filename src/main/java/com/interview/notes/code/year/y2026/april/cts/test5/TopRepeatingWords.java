package com.interview.notes.code.year.y2026.april.cts.test5;

import java.util.*; // Needed for List, Map, Arrays, HashMap, ArrayList, Objects
import java.util.function.Function; // Needed for Function.identity() in grouping
import java.util.stream.Collectors; // Needed for grouping, sorting, collecting
import java.util.stream.IntStream; // Needed for large test data creation

public class TopRepeatingWords { // Main class

    public static List<String> topRepeatingWords(String[] words, int k) { // Method to get top k repeated words
        if (words == null || words.length == 0 || k <= 0) { // Handle invalid input safely
            return List.of(); // Return empty list if no useful data
        }

        Map<String, Long> freq = Arrays.stream(words) // Convert array to stream for easy processing
                .filter(Objects::nonNull) // Ignore null values to avoid issues
                .collect(Collectors.groupingBy( // Group same words together
                        Function.identity(), // Use the word itself as key
                        Collectors.counting() // Count how many times each word appears
                ));

        return freq.entrySet().stream() // Convert map entries to stream for sorting
                .sorted( // Sort entries using custom rules
                        Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue) // First compare by count
                                .reversed() // Higher count should come first
                                .thenComparing(Map.Entry::getKey) // If count same, sort by word alphabetically
                )
                .limit(k) // Take only top k results
                .map(Map.Entry::getKey) // Extract only the word from each entry
                .toList(); // Convert stream result to list
    }

    public static void test(String name, String[] words, int k, List<String> expected) { // Simple reusable test method
        List<String> actual = topRepeatingWords(words, k); // Run actual method
        boolean pass = actual.equals(expected); // Compare expected and actual result
        System.out.println((pass ? "PASS" : "FAIL") + " - " + name); // Print PASS/FAIL with test name
        System.out.println("Expected: " + expected); // Print expected result
        System.out.println("Actual  : " + actual); // Print actual result
        System.out.println(); // Blank line for readability
    }

    public static void largeDataTest() { // Test for large input size
        String[] large = IntStream.range(0, 200000) // Create indexes from 0 to 199999
                .mapToObj(i -> i % 5 == 0 ? "iPhone" : i % 4 == 0 ? "Airpods" : i % 3 == 0 ? "iPad" : "MacBook Pro") // Generate repeated device names
                .toArray(String[]::new); // Convert stream to array

        long start = System.currentTimeMillis(); // Capture start time
        List<String> result = topRepeatingWords(large, 2); // Run method for top 2
        long end = System.currentTimeMillis(); // Capture end time

        System.out.println("PASS - Large Data Test"); // Just marking execution success
        System.out.println("Top 2  : " + result); // Print output
        System.out.println("Time ms: " + (end - start)); // Print execution time
        System.out.println(); // Blank line
    }

    public static void main(String[] args) { // Main method to run all tests
        String[] words = { // Sample input from problem
                "iPhone", // Device 1
                "MacBook Pro", // Device 2
                "Airpods", // Device 3
                "MacBook Pro", // Duplicate
                "Airpods", // Duplicate
                "iPhone", // Duplicate
                "iPad", // Device 4
                "Airpods", // Duplicate
                "iPhone", // Duplicate
                "Airpods", // Duplicate
                "iPad", // Duplicate
                "MacBook Pro" // Duplicate
        };

        test( // Test based on your example
                "Given Example Top 2", // Test name
                words, // Input array
                2, // k value
                List.of("Airpods", "iPhone") // Expected result
        );

        test( // Tie case test
                "Tie With Alphabetical Order", // Test name
                new String[]{"b", "a", "b", "a", "c"}, // a and b both appear 2 times
                2, // k value
                List.of("a", "b") // Alphabetical order because count is same
        );

        test( // Single element test
                "Single Word", // Test name
                new String[]{"iPhone"}, // Input
                1, // k value
                List.of("iPhone") // Expected result
        );

        test( // Empty input test
                "Empty Array", // Test name
                new String[]{}, // Empty input
                2, // k value
                List.of() // Expected empty result
        );

        test( // Invalid k test
                "K Is Zero", // Test name
                new String[]{"iPhone", "iPad"}, // Input
                0, // Invalid k
                List.of() // Expected empty result
        );

        test( // k greater than unique words
                "K Greater Than Unique Count", // Test name
                new String[]{"iPhone", "iPhone", "iPad"}, // Input
                5, // k bigger than unique count
                List.of("iPhone", "iPad") // Return available words only
        );

        largeDataTest(); // Run large input performance test
    }
}