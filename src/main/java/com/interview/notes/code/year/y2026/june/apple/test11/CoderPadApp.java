package com.interview.notes.code.year.y2026.june.apple.test11;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; // Imports Collectors for stream aggregations

public class CoderPadApp { // Defines the main execution class

    public static List<Result> findMostCommonPairs(List<Map<String, Object>> configs) { // Returns a List to hold multiple ties
        var frequencyMap = configs.stream() // Java 10+ var infers type; opens a sequential stream
            .flatMap(map -> map.entrySet().stream()) // Extracts entries from all maps and flattens into one stream
            .collect(Collectors.groupingBy(Map.Entry::copyOf, Collectors.counting())); // Creates immutable copies of pairs to group and count them

        var maxCount = frequencyMap.values().stream() // Opens a stream of just the numeric frequency counts
            .max(Long::compareTo) // Finds the absolute highest frequency number
            .orElse(0L); // Safely defaults to 0 if the input list was completely empty

        return frequencyMap.entrySet().stream() // Re-opens a stream of the key-value pairs and their counts
            .filter(entry -> entry.getValue() == maxCount) // Keeps ONLY the entries that perfectly match the highest count
            .map(e -> new Result(e.getKey().getKey(), e.getKey().getValue(), e.getValue())) // Maps the tied winners into our custom Result record
            .toList(); // Java 16+ terminal operation to collect the stream into an immutable List
    }

    public static void main(String[] args) { // Main method serves as our custom testing framework
        var testCases = List.of( // Java 9+ List.of creates an immutable list of our test data
            Map.<String, Object>of("status", "active", "region", "us-west", "version", "2.1", "retries", 3), // Config row 1
            Map.<String, Object>of("status", "active", "region", "us-east", "version", "2.1", "retries", 3), // Config row 2
            Map.<String, Object>of("status", "active", "region", "us-west", "version", "2.0"), // Config row 3
            Map.<String, Object>of("status", "inactive", "region", "us-west", "version", "2.1", "retries", 3), // Config row 4
            Map.<String, Object>of("status", "active", "region", "eu-west", "version", "2.1", "retries", 3) // Config row 5
        ); // Closes the data collection

        var results = findMostCommonPairs(testCases); // Executes the updated core logic against the sample data
        // Validation: We expect 3 distinct pairs tied for first place, each with a count of 4
        var isPass = results.size() == 3 && results.get(0).count() == 4; // Checks if 3 items returned and their count is 4
        System.out.println("Test 1 (Standard Dataset w/ Ties): " + (isPass ? "PASS" : "FAIL")); // Prints standard output result
        results.forEach(System.out::println); // Prints out the actual tied results so you can verify them visually

        var largeConfigs = new ArrayList<Map<String, Object>>(); // Initializes a resizable list for performance testing
        for (int i = 0; i < 1_000_000; i++) { // Loops one million times
            largeConfigs.add(Map.of("keyA", "val1", "keyB", "val2")); // Injects two key-value pairs into every single config
        }
        var largeResult = findMostCommonPairs(largeConfigs); // Executes the core logic against the massive dataset
        // Validation: We expect 2 distinct pairs tied for first place, each with a count of 1,000,000
        var isLargePass = largeResult.size() == 2 && largeResult.get(0).count() == 1_000_000; // Verifies the logic successfully grouped 1M items and found the tie
        System.out.println("\nTest 2 (Large Dataset w/ Ties): " + (isLargePass ? "PASS" : "FAIL")); // Prints stress test output result
    } // Closes main method

    public record Result(String key, Object val, long count) {} // Java 16+ Record concisely holds immutable return data
} // Closes class definition