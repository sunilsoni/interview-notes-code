package com.interview.notes.code.year.y2026.june.common.test3;

import java.util.HashMap; // Imports the HashMap class to simulate our fast Redis cache
import java.util.List; // Imports the List interface to hold our input collections
import java.util.Map; // Imports the Map interface for general map operations
import java.util.stream.IntStream; // Imports IntStream to easily generate large sets of test data

public class TransactionProcessor { // Main class to encapsulate our logic and tests

    // Core method that processes the data and returns the final statistics
    public static Stats processTransactions(List<Transaction> transactions) { // Accepts the list of transactions
        var stats = new Stats(); // Uses Java 'var' keyword to neatly initialize our statistics tracker
        Map<String, Integer> cache = new HashMap<>(); // Simulates Redis using a fast key-value map

        // Uses Java 8 Collection forEach API to iterate through the data cleanly
        transactions.forEach(txn -> { // Starts a lambda expression for each transaction
            if (cache.containsKey(txn.txnId())) { // Checks if the cache already has this transaction ID
                stats.cacheHits++; // We found it, so increment the cache hit metric
                stats.duplicates++; // Because we found it, it is also a duplicate request
            } else { // Triggered if the transaction ID is completely new
                stats.dbHits++; // We simulate a DB call, so increment the DB hit metric
                cache.put(txn.txnId(), txn.amount()); // Save it to the cache to prevent future DB calls
            } // Ends the if-else block
        }); // Ends the iteration

        return stats; // Returns the final populated tracker object
    } // Ends the process method

    // The main method serves as our custom testing framework
    public static void main(String[] args) { // Entry point of the program
        runBaseTest(); // Executes the exact test case provided in the requirements
        runLargeDataTest(); // Executes a stress test to prove scalability
    } // Ends the main method

    // Tests the primary scenario outlined in the problem description
    private static void runBaseTest() { // Private helper method for organizing tests
        // Create an immutable list of transactions matching the provided image data
        var txns = List.of( // Uses List.of to quickly build the list
            new Transaction("TX101", 2000), // First transaction, hits DB
            new Transaction("TX102", 5000), // Second transaction, hits DB
            new Transaction("TX101", 2000), // Duplicate of first, hits Cache
            new Transaction("TX103", 1000)  // Third unique transaction, hits DB
        ); // Ends list initialization

        var result = processTransactions(txns); // Calls our logic to process the list

        // Check if our results match the logical expectation (1 hit, 3 db, 1 dup)
        boolean passed = result.cacheHits == 1 && result.dbHits == 3 && result.duplicates == 1; // Evaluates condition

        // Prints PASS or FAIL to the console based on the boolean result
        System.out.println("Base Test: " + (passed ? "PASS" : "FAIL")); // Uses ternary operator for clean output
    } // Ends the base test method

    // Tests the system with 100,000 records to ensure performance doesn't degrade
    private static void runLargeDataTest() { // Private helper method for edge cases
        // Generate a massive list of transactions dynamically using Java Streams
        var largeTxns = IntStream.range(0, 100_000) // Creates a stream of numbers from 0 to 99,999
            // Maps each number to a transaction, using modulo to force duplicates (only 1,000 unique IDs)
            .mapToObj(i -> new Transaction("TX" + (i % 1000), 500))
            .toList(); // Collects the generated objects into a standard List

        var result = processTransactions(largeTxns); // Processes the massive list

        // With 100,000 items and 1,000 unique IDs, we expect exactly 1,000 DB hits and 99,000 cache hits
        boolean passed = result.cacheHits == 99_000 && result.dbHits == 1000 && result.duplicates == 99_000; // Evaluates logic

        // Prints the final validation result to the console
        System.out.println("Large Data Test (100k records): " + (passed ? "PASS" : "FAIL")); // Ternary output
    } // Ends the large data test method

    // Record is a modern Java feature that creates a concise, immutable data class automatically
    record Transaction(String txnId, int amount) {} // Represents a transaction with an ID and amount

    // A simple class to hold our counting metrics so we can update them inside a lambda
    static class Stats { // Declared static so it can be used without instantiating the outer class
        int cacheHits = 0; // Counter for times the transaction is found in the cache
        int dbHits = 0; // Counter for times we simulate querying the database
        int duplicates = 0; // Counter for duplicate transaction occurrences
    } // End of Stats class
} // Ends the main class