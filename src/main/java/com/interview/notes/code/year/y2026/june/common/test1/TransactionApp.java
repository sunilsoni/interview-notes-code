package com.interview.notes.code.year.y2026.june.common.test1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TransactionApp {
    public static void main(String[] args) {
        // Sample data provided in your screenshots
        var input = List.of(
            new Transaction("TX101", 2000),
            new Transaction("TX102", 5000),
            new Transaction("TX101", 2000),
            new Transaction("TX103", 1000)
        );

        // Run processor and print output
        processTransactions(input);
    }

    public static void processTransactions(List<Transaction> requests) {
        var cache = new HashSet<String>(); // O(1) space, tracks unique txnIds already processed
        var stats = new HashMap<String, Integer>(); // Stores final count metrics
        stats.put("cacheHits", 0); // Initialize counters
        stats.put("dbHits", 0);
        stats.put("duplicates", 0);

        requests.forEach(txn -> { // Stream-like iteration over list
            if (!cache.add(txn.txnId())) { // HashSet.add() returns false if item already exists
                stats.put("cacheHits", stats.get("cacheHits") + 1); // Increment cache hit
                stats.put("duplicates", stats.get("duplicates") + 1); // Increment duplicate count
            } else { // New transaction, simulate DB call
                stats.put("dbHits", stats.get("dbHits") + 1); // Simulate DB hit for new unique IDs
            }
        });

        // Print final stats
        System.out.println("Processing Complete: " + stats);

        // Simple manual validation for the specific test case
        boolean testPassed = (stats.get("cacheHits") == 1 && stats.get("dbHits") == 3);
        System.out.println("Test Case Status: " + (testPassed ? "PASS" : "FAIL"));
    }

    // Define an immutable record for cleaner data structure in Java 21
    record Transaction(String txnId, int amount) {}
}