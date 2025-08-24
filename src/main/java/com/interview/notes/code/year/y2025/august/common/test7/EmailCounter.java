package com.interview.notes.code.year.y2025.august.common.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmailCounter {

    // Main method to process email counts using Stream API
    public static Map<String, Long> countEmails(List<String> emails) {
        // Using Stream API to group emails and count occurrences
        // Collectors.groupingBy creates a map with email as key and count as value
        return emails.stream()
                .collect(Collectors.groupingBy(
                        email -> email,    // Key is the email itself
                        Collectors.counting() // Value is the count of occurrences
                ));
    }

    // Method to print results in required format
    public static void printResults(Map<String, Long> emailCounts) {
        // Iterate through map entries and print each email with its count
        emailCounts.forEach((email, count) ->
                System.out.println(email + " " + count));
    }

    // Main method containing test cases
    public static void main(String[] args) {
        // Test Case 1: Basic test with duplicate emails
        List<String> test1 = Arrays.asList(
                "pooja.mani@lseg.com",
                "abc@lseg.com",
                "xyz@lseg.com",
                "pooja.mani@lseg.com"
        );
        System.out.println("Test Case 1 - Basic Test:");
        printResults(countEmails(test1));

        // Test Case 2: All unique emails
        List<String> test2 = Arrays.asList(
                "test1@example.com",
                "test2@example.com",
                "test3@example.com"
        );
        System.out.println("\nTest Case 2 - All Unique Emails:");
        printResults(countEmails(test2));

        // Test Case 3: Large dataset simulation
        List<String> test3 = new ArrayList<>();
        // Generate 10000 emails with some duplicates
        for (int i = 0; i < 10000; i++) {
            test3.add("user" + (i % 1000) + "@example.com");
        }
        System.out.println("\nTest Case 3 - Large Dataset (first 5 results):");
        Map<String, Long> largeDatasetResults = countEmails(test3);
        largeDatasetResults.entrySet().stream()
                .limit(5)
                .forEach(entry ->
                        System.out.println(entry.getKey() + " " + entry.getValue()));

        // Test Case 4: Empty list
        List<String> test4 = new ArrayList<>();
        System.out.println("\nTest Case 4 - Empty List:");
        printResults(countEmails(test4));
    }
}
