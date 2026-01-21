package com.interview.notes.code.year.y2026.jan.apple;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 1. DATA MODEL: Using Java 'record' to minimize code words (Available Java 14+)
// A record automatically creates constructor, getters, equals(), and hashCode()
record CustomerInteraction(String customerEmail, LocalDate interactionDate, String preferredChannel) {
}

// 2. PROCESSOR: Contains the business logic
class CustomerInteractionProcessor {

    public Map<String, List<String>> getInactiveCustomersByChannel(List<CustomerInteraction> interactions) {
        // Validation: If list is null, return empty map to prevent crash
        if (interactions == null) return Map.of();

        // Logic: Calculate date 30 days ago. 'var' infers type automatically (Java 10+)
        var cutoffDate = LocalDate.now().minusDays(30);

        // Stream Pipeline: functional approach to process data
        return interactions.stream()
                // Filter: Keep only interactions strictly older than 30 days
                .filter(interaction -> interaction.interactionDate().isBefore(cutoffDate))
                // Collector: Grouping results by Channel
                .collect(Collectors.groupingBy(
                        CustomerInteraction::preferredChannel,      // Key: Group by "SMS" or "EMAIL"
                        Collectors.mapping(                         // Value Transformation
                                CustomerInteraction::customerEmail,     // Extract just the email string
                                Collectors.toList()                     // Store emails in a List
                        )
                ));
    }
}

// 3. TESTING: Simple Main Method (No JUnit)
public class Solution {

    public static void main(String[] args) {
        var processor = new CustomerInteractionProcessor();
        var today = LocalDate.now();

        System.out.println("--- STARTING TESTS ---\n");

        // --- TEST CASE 1: Standard Mixed Data ---
        // Setup: Create a list with specific old (inactive) and new (active) dates
        var data1 = List.of(
                new CustomerInteraction("inactive1@test.com", today.minusDays(45), "EMAIL"), // Inactive
                new CustomerInteraction("active1@test.com", today.minusDays(15), "SMS"),   // Active
                new CustomerInteraction("inactive2@test.com", today.minusDays(35), "SMS")    // Inactive
        );

        // Execution
        var result1 = processor.getInactiveCustomersByChannel(data1);

        // Validation: Check if correct users were found and grouped
        boolean test1Pass = result1.containsKey("EMAIL")
                && result1.get("EMAIL").contains("inactive1@test.com")
                && !result1.containsKey("SMS") // Should be false if logic is correct? Wait, inactive2 is SMS.
                || (result1.containsKey("SMS") && result1.get("SMS").contains("inactive2@test.com"));

        // Refined validation for exact match
        boolean exactMatch = result1.get("EMAIL").size() == 1 && result1.get("SMS").size() == 1;
        printResult("1. Standard Mixed Data", exactMatch);


        // --- TEST CASE 2: Edge Case (All Active Users) ---
        // Setup: List where everyone interacted yesterday
        var data2 = List.of(
                new CustomerInteraction("user1@test.com", today.minusDays(1), "EMAIL")
        );
        var result2 = processor.getInactiveCustomersByChannel(data2);

        // Validation: Map should be completely empty
        printResult("2. All Active Users (Empty Result)", result2.isEmpty());


        // --- TEST CASE 3: Large Data Load (1 Million Records) ---
        System.out.println("Generating 1,000,000 records... (Please wait)");

        // Setup: Generate 1M records. Even numbers = Inactive (45 days ago), Odd = Active (10 days ago)
        var largeData = IntStream.range(0, 1_000_000)
                .mapToObj(i -> new CustomerInteraction(
                        "user" + i + "@test.com",
                        (i % 2 == 0) ? today.minusDays(45) : today.minusDays(10),
                        "EMAIL"
                ))
                .toList(); // Java 16+ concise collector

        // Execution & Timing
        long start = System.currentTimeMillis();
        var result3 = processor.getInactiveCustomersByChannel(largeData);
        long end = System.currentTimeMillis();

        // Validation: Should find exactly 500,000 inactive users
        boolean sizePass = result3.containsKey("EMAIL") && result3.get("EMAIL").size() == 500_000;

        printResult("3. Large Data Performance", sizePass);
        System.out.println("   -> Time taken: " + (end - start) + "ms");
    }

    // Helper to print nice PASS/FAIL messages
    private static void printResult(String testName, boolean isPassed) {
        if (isPassed) {
            System.out.println("[PASS] " + testName);
        } else {
            System.out.println("[FAIL] " + testName);
        }
    }
}