package com.interview.notes.code.year.y2026.april.common.test5;// Import the List interface to define our standard collection type.

import java.util.ArrayList;
import java.util.List;

// Import ArrayList to build our massive test dataset dynamically.

// Define the class that contains our business logic and test suite.
public class TransactionIdCounter {

    // Define the new method to count transactions matching a specific target ID.
    public static long countTransactionsById(List<Transaction> transactions, String targetId) {

        // Immediately check if the transaction list or the target ID is null.
        if (transactions == null || targetId == null) {

            // Return 0 because we cannot find matches in a null list or with a null target.
            return 0;

        // Close the null-check block.
        }

        // Convert the list into a Java 8 Stream to process the records sequentially.
        return transactions.stream()

            // Filter step 1: Ensure the transaction object itself and its ID field are not null.
            .filter(t -> t != null && t.id() != null)

            // Filter step 2: Keep only records where the exact target ID matches the transaction's ID.
            .filter(t -> targetId.equals(t.id().trim()))

            // Terminal operation: Count all remaining elements in the stream and return as a long.
            .count();

    // Close the countTransactionsById method.
    }

    // Define a helper method to evaluate our test cases by comparing expected and actual long values.
    private static void runTest(String testName, long expected, long actual) {

        // Check if the expected count exactly equals the mathematically actual count.
        if (expected == actual) {

            // Print a PASS message to the console if the test succeeded.
            System.out.println("PASS: " + testName);

        // Provide an alternative block if the numbers do not match.
        } else {

            // Print a FAIL message showing both values to make debugging much easier.
            System.out.println("FAIL: " + testName + " - Expected: " + expected + ", Actual: " + actual);

        // Close the evaluation if-else block.
        }

    // Close the runTest helper method.
    }

    // Define the main method to run our application and execute the self-contained test cases.
    public static void main(String[] args) {

        // Create an unmodifiable list containing several duplicate IDs to test the counting logic.
        List<Transaction> duplicateData = List.of(

            // Transaction with target ID "TXN-100".
            new Transaction("TXN-100", "A1", "date", "10.00", "success"),

            // Another transaction with target ID "TXN-100" (Duplicate 1).
            new Transaction("TXN-100", "A1", "date", "10.00", "failed"),

            // Transaction with a different ID "TXN-101".
            new Transaction("TXN-101", "A2", "date", "50.00", "success"),

            // Transaction missing an ID completely (null).
            new Transaction(null, "A3", "date", "20.00", "failed"),

            // A third transaction with target ID "TXN-100" (Duplicate 2).
            new Transaction("TXN-100", "A1", "date", "10.00", "success")

        // Close the list declaration.
        );

        // Test counting an ID that exists 3 times, expecting a result of 3.
        runTest("Count Existing ID", 3, countTransactionsById(duplicateData, "TXN-100"));

        // Test counting an ID that does not exist at all in the list, expecting 0.
        runTest("Count Non-Existent ID", 0, countTransactionsById(duplicateData, "TXN-999"));

        // Test how the method handles a null target ID search, expecting 0 without crashing.
        runTest("Null Target ID Search", 0, countTransactionsById(duplicateData, null));

        // Test how the method handles a completely null list, expecting 0.
        runTest("Null List Input", 0, countTransactionsById(null, "TXN-100"));

        // Initialize a large ArrayList to test memory efficiency and performance at scale.
        List<Transaction> largeData = new ArrayList<>();

        // Define the specific target ID we will hide inside the massive dataset.
        String hiddenTargetId = "TARGET-555";

        // Set a variable to keep track of exactly how many times we inject the target ID.
        long expectedTargetCount = 0;

        // Loop one million times to simulate a heavy database pull.
        for (int i = 0; i < 1000000; i++) {

            // Every 100,000 iterations, inject our target ID into the dataset.
            if (i % 100000 == 0) {

                // Add the transaction with our specific hidden target ID.
                largeData.add(new Transaction(hiddenTargetId, "ACC", "date", "1.00", "success"));

                // Increment our expected count tracking variable by 1.
                expectedTargetCount++;

            // For all other iterations:
            } else {

                // Add a transaction with a generic, unique ID based on the loop index.
                largeData.add(new Transaction("TXN-" + i, "ACC", "date", "1.00", "success"));

            // Close the if-else block.
            }

        // Close the million-record loop.
        }

        // Run the large dataset test expecting our target ID to be found exactly 10 times out of 1,000,000.
        runTest("Large Data ID Count (1 Million Records)", expectedTargetCount, countTransactionsById(largeData, hiddenTargetId));

    // Close the main method.
    }

    // Define the Transaction record to hold raw data, serving as our immutable data carrier.
    public record Transaction(String id, String acc, String date, String rawAmount, String status) {}
    
// Close the TransactionIdCounter class.
}