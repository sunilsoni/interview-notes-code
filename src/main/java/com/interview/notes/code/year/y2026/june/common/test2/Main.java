package com.interview.notes.code.year.y2026.june.common.test2;

import java.util.*; // Imports List, Map, HashMap and other collection classes

public class Main { // Main class where program execution starts

    static Stats processTransactions(List<Transaction> requests, Map<String, Transaction> database) { // Method to process all transactions

        Map<String, Transaction> redisCache = new HashMap<>(); // Simulated Redis cache using HashMap

        Set<String> processedTransactions = new HashSet<>(); // Stores already processed transaction IDs

        int cacheHits = 0; // Counts how many requests were served from cache

        int databaseHits = 0; // Counts how many requests went to database

        int duplicateCount = 0; // Counts duplicate transaction requests

        for (Transaction request : requests) { // Loops through every transaction request

            String txnId = request.txnId(); // Extracts transaction ID from current request

            if (redisCache.containsKey(txnId)) { // Checks whether transaction already exists in cache

                cacheHits++; // Increases cache hit count because cache returned the data

                duplicateCount++; // Increases duplicate count because same transaction came again

                continue; // Skips database call to avoid unnecessary DB hit
            }

            Transaction dbTransaction = database.get(txnId); // Fetches transaction from simulated database

            if (dbTransaction != null) { // Checks if transaction exists in database

                databaseHits++; // Increases DB hit count because database was called

                redisCache.put(txnId, dbTransaction); // Stores new transaction in cache for future duplicate requests

                processedTransactions.add(txnId); // Marks transaction as processed
            }
        }

        return new Stats(cacheHits, databaseHits, duplicateCount); // Returns final statistics
    }

    static void test(String testName, Stats actual, Stats expected) { // Simple test method without JUnit

        boolean passed = actual.equals(expected); // Compares actual result with expected result

        System.out.println(testName + " : " + (passed ? "PASS" : "FAIL")); // Prints PASS or FAIL

        if (!passed) { // Runs only when test fails

            System.out.println("Expected: " + expected); // Prints expected result

            System.out.println("Actual  : " + actual); // Prints actual result
        }
    }

    public static void main(String[] args) { // Main method to run all test cases

        Map<String, Transaction> database = Map.of( // Simulated database response map
                "TX101", new Transaction("TX101", 2000), // Database record for TX101
                "TX102", new Transaction("TX102", 5000), // Database record for TX102
                "TX103", new Transaction("TX103", 1000)  // Database record for TX103
        );

        List<Transaction> givenInput = List.of( // Given transaction request list
                new Transaction("TX101", 2000), // First TX101 request, should hit DB
                new Transaction("TX102", 5000), // First TX102 request, should hit DB
                new Transaction("TX101", 2000), // Duplicate TX101 request, should hit cache
                new Transaction("TX103", 1000)  // First TX103 request, should hit DB
        );

        test("Given Test Case", processTransactions(givenInput, database), new Stats(1, 3, 1)); // Validates given case

        test("Empty Request List", processTransactions(List.of(), database), new Stats(0, 0, 0)); // Validates empty input

        test("All Same Transaction", processTransactions(List.of( // Tests repeated same transaction
                new Transaction("TX101", 2000), // First request goes to DB
                new Transaction("TX101", 2000), // Second request goes to cache
                new Transaction("TX101", 2000)  // Third request goes to cache
        ), database), new Stats(2, 1, 2)); // Expected 2 cache hits, 1 DB hit, 2 duplicates

        test("Unknown Transaction", processTransactions(List.of( // Tests transaction not found in DB
                new Transaction("TX999", 9000) // Unknown transaction
        ), database), new Stats(0, 0, 0)); // No cache hit, no DB success, no duplicate

        List<Transaction> largeInput = java.util.stream.IntStream.range(0, 100000) // Creates 100000 records
                .mapToObj(i -> new Transaction("TX101", 2000)) // Creates repeated TX101 requests
                .toList(); // Converts stream into list

        test("Large Input Test", processTransactions(largeInput, database), new Stats(99999, 1, 99999)); // Validates large input
    }

    record Transaction(String txnId, int amount) {} // Represents one transaction request

    record Stats(int cacheHits, int databaseHits, int duplicateCount) {} // Stores final processing statistics
}