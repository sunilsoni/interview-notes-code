package com.interview.notes.code.year.y2026.april.common.test4;// Import the List interface to use standard collections.

import java.util.ArrayList;
import java.util.List;

// Import ArrayList to build our large test dataset dynamically.

// Define the class containing our plain Java counting logic and test framework.
public class PlainJavaCounter {

    // Define the method to count transactions using standard plain Java loops.
    public static long countTransactionsById(List<Transaction> transactions, String targetId) {

        // Immediately return 0 if the provided list is null or the target search ID is null.
        if (transactions == null || targetId == null) {

            // Return 0 because we cannot search missing data.
            return 0;

        // Close the initial null check block.
        }

        // Declare a primitive long variable to keep track of our matching count.
        long matchCount = 0;

        // Open an enhanced for-loop to iterate through each Transaction in the list one by one.
        for (Transaction t : transactions) {

            // Check if the current transaction object and its ID are not null to prevent crashes.
            if (t != null && t.id() != null) {

                // Compare the exact text of our target ID against the cleaned text of the transaction's ID.
                if (targetId.equals(t.id().trim())) {

                    // If they match perfectly, increment our counter variable by exactly 1.
                    matchCount++;

                // Close the ID comparison if-block.
                }

            // Close the null-safety if-block.
            }

        // Close the enhanced for-loop.
        }

        // After fully iterating through the entire list, return the final calculated count.
        return matchCount;

    // Close the countTransactionsById method.
    }

    // Define the custom helper method to evaluate test results and print PASS or FAIL.
    private static void runTest(String testName, long expected, long actual) {

        // Check if the expected count exactly equals the actual computed count.
        if (expected == actual) {

            // Print a PASS message to the console if the values match.
            System.out.println("PASS: " + testName);

        // Provide the alternative block if the test fails.
        } else {

            // Print a FAIL message showing both values to assist with debugging.
            System.out.println("FAIL: " + testName + " - Expected: " + expected + ", Actual: " + actual);

        // Close the test evaluation if-else block.
        }

    // Close the runTest helper method.
    }

    // Define the main method to execute the plain Java logic and all test cases.
    public static void main(String[] args) {

        // Create an unmodifiable list containing duplicate and null IDs to verify robust loop logic.
        List<Transaction> testData = List.of(

            // First transaction with the target ID.
            new Transaction("TXN-100", "A1", "date", "10.00", "success"),

            // Second transaction with the target ID (Duplicate).
            new Transaction("TXN-100", "A1", "date", "10.00", "failed"),

            // Transaction with a completely different ID.
            new Transaction("TXN-101", "A2", "date", "50.00", "success"),

            // Corrupted transaction missing an ID entirely.
            new Transaction(null, "A3", "date", "20.00", "failed")

        // Close the testData list declaration.
        );

        // Test counting an ID that exists exactly 2 times in the list.
        runTest("Count Existing ID (Plain Java)", 2, countTransactionsById(testData, "TXN-100"));

        // Test counting an ID that does not exist at all, expecting 0.
        runTest("Count Non-Existent ID", 0, countTransactionsById(testData, "TXN-999"));

        // Test passing a null target ID to ensure the manual loop doesn't crash.
        runTest("Null Target Search", 0, countTransactionsById(testData, null));

        // Initialize a large ArrayList to prove the plain loop handles massive data efficiently.
        List<Transaction> largeData = new ArrayList<>();

        // Define the target ID we want to find inside the massive list.
        String hiddenId = "TARGET-555";

        // Set a tracker to manually count how many times we insert the target ID.
        long expectedTargetCount = 0;

        // Loop one million times to generate bulk production-like data.
        for (int i = 0; i < 1000000; i++) {

            // Every 100,000 records, inject the specific target ID we want to find.
            if (i % 100000 == 0) {

                // Add the record with our hidden target ID.
                largeData.add(new Transaction(hiddenId, "ACC", "date", "1.00", "success"));

                // Increment our manual tracking variable by 1.
                expectedTargetCount++;

            // Provide the alternative block for all other iterations.
            } else {

                // Add a generic record with an ID based on the loop index.
                largeData.add(new Transaction("TXN-" + i, "ACC", "date", "1.00", "success"));

            // Close the if-else block.
            }

        // Close the massive data generation loop.
        }

        // Run the large dataset test expecting our target ID to be found exactly 10 times manually.
        runTest("Large Data ID Count (1 Million Records)", expectedTargetCount, countTransactionsById(largeData, hiddenId));

    // Close the main method.
    }

    // Define the immutable Transaction record to hold raw string data.
    public record Transaction(String id, String acc, String date, String rawAmount, String status) {}
    
// Close the PlainJavaCounter class.
}