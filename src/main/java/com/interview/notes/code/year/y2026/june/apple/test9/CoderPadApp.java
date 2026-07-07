package com.interview.notes.code.year.y2026.june.apple.test9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; // Imports Collectors for stream aggregations

public class CoderPadApp { // Defines the main execution class

    public static Result findMostCommonPair(List<Map<String, Object>> configs) { // Accepts the list of parsed JSON objects
        var frequencyMap = configs.stream() // Java 10+ var infers type; opens a sequential stream
            .flatMap(map -> map.entrySet().stream()) // Extracts entries from all maps and flattens into one stream
            .collect(Collectors.groupingBy(Map.Entry::copyOf, Collectors.counting())); // Creates immutable copies of pairs to group and count them

        return frequencyMap.entrySet().stream() // Opens a stream over the aggregated frequency counts
            .max(Map.Entry.comparingByValue()) // Locates the entry with the highest numeric count value
            .map(e -> new Result(e.getKey().getKey(), e.getKey().getValue(), e.getValue())) // Maps the winning Map.Entry into our custom Result record
            .orElse(null); // Safely handles empty inputs without throwing exceptions
    }

    public static void main(String[] args) { // Main method serves as our custom testing framework
        var testCases = List.of( // Java 9+ List.of creates an immutable list of our test data
            Map.<String, Object>of("status", "active", "region", "us-west", "version", "2.1", "retries", 3), // Config row 1
            Map.<String, Object>of("status", "active", "region", "us-east", "version", "2.1", "retries", 3), // Config row 2
            Map.<String, Object>of("status", "active", "region", "us-west", "version", "2.0"), // Config row 3
            Map.<String, Object>of("status", "inactive", "region", "us-west", "version", "2.1", "retries", 3), // Config row 4
            Map.<String, Object>of("status", "active", "region", "eu-west", "version", "2.1", "retries", 3) // Config row 5
        ); // Closes the data collection

        var result = findMostCommonPair(testCases); // Executes the core logic against the sample data
        var isPass = result != null && result.count() == 4; // Validates the count (status=active, version=2.1, retries=3 all tie at 4)
        System.out.println("Test 1 (Standard Dataset): " + (isPass ? "PASS" : "FAIL")); // Prints standard output result

        var largeConfigs = new ArrayList<Map<String, Object>>(); // Initializes a resizable list for performance testing
        for (int i = 0; i < 1_000_000; i++) { largeConfigs.add(Map.of("key", "val")); } // Injects one million identical records
        var largeResult = findMostCommonPair(largeConfigs); // Executes the core logic against the massive dataset
        var isLargePass = largeResult != null && largeResult.count() == 1_000_000; // Verifies the logic successfully grouped 1M items
        System.out.println("Test 2 (Large Dataset): " + (isLargePass ? "PASS" : "FAIL")); // Prints stress test output result
    } // Closes main method

    public record Result(String key, Object val, long count) {} // Java 16+ Record concisely holds immutable return data
} // Closes class definition