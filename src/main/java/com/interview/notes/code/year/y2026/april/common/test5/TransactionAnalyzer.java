package com.interview.notes.code.year.y2026.april.common.test5;// Import the List interface to use ordered collections.

import java.util.ArrayList;
import java.util.List;

// Import ArrayList to create dynamic array implementations of List.

// Define the main class that contains our business logic and tests.
public class TransactionAnalyzer {

    // Define a static method that takes a List of Transactions and returns the total double amount.
    public static double calculateLostMoney(List<Transaction> transactions) {

        // Check if the provided list is null to prevent NullPointerException during runtime.
        if (transactions == null) {

            // Return 0.0 because a null list implies no transactions and therefore zero money lost.
            return 0.0;

        // Close the if statement block.
        }

        // Convert the list of transactions into a sequential Stream for functional-style processing.
        return transactions.stream()

            // Filter the stream to only allow transactions where the status ignores case and equals "failed".
            .filter(t -> "failed".equalsIgnoreCase(t.status()))

            // Map the remaining "failed" transaction objects specifically to their primitive double amount values.
            .mapToDouble(Transaction::amount)

            // Sum up all the primitive double values to get the final total lost amount.
            .sum();

    // Close the calculateLostMoney method block.
    }

    // Define a helper method to evaluate our test cases and print PASS or FAIL.
    private static void runTest(String testName, double expected, double actual) {

        // Calculate the absolute difference to handle floating-point precision comparisons.
        double difference = Math.abs(expected - actual);

        // Check if the difference is less than a tiny threshold (0.001) to consider them equal.
        if (difference < 0.001) {

            // Print a PASS message to the console with the test name if the values match.
            System.out.println("PASS: " + testName);

        // Provide an alternative block if the values do not match.
        } else {

            // Print a FAIL message showing both the expected and actual values for debugging.
            System.out.println("FAIL: " + testName + " - Expected: " + expected + ", Actual: " + actual);

        // Close the if-else block.
        }

    // Close the runTest helper method block.
    }

    // Define the main method which serves as the entry point for our application and custom testing framework.
    public static void main(String[] args) {

        // Create an unmodifiable list of standard transactions containing both failed and success statuses.
        List<Transaction> normalData = List.of(

            // Instantiate a failed transaction worth 100.50.
            new Transaction("1", "A1", "2023-10-01", 100.50, "failed"),

            // Instantiate a successful transaction worth 50.00 (should be ignored).
            new Transaction("2", "A2", "2023-10-01", 50.00, "success"),

            // Instantiate a failed transaction with uppercase status "FAILED" worth 200.00.
            new Transaction("3", "A3", "2023-10-01", 200.00, "FAILED")

        // Close the List.of declaration.
        );

        // Run the first test case expecting 300.50 (100.50 + 200.00).
        runTest("Normal Mixed Data", 300.50, calculateLostMoney(normalData));

        // Run the second test case passing an empty list, expecting 0.0.
        runTest("Empty List", 0.0, calculateLostMoney(List.of()));

        // Run the third test case passing a null value, expecting 0.0.
        runTest("Null Input", 0.0, calculateLostMoney(null));

        // Create an unmodifiable list of only successful transactions.
        List<Transaction> allSuccessData = List.of(

            // Instantiate a successful transaction worth 500.00.
            new Transaction("4", "A4", "2023-10-01", 500.00, "success")

        // Close the List.of declaration.
        );

        // Run the fourth test case expecting 0.0 since no transactions failed.
        runTest("All Success Data", 0.0, calculateLostMoney(allSuccessData));

        // Initialize a new ArrayList to construct a large dataset for performance testing.
        List<Transaction> largeData = new ArrayList<>();

        // Set an expected total variable to keep track of the math for the large dataset.
        double expectedLargeTotal = 0;

        // Create a standard for-loop to iterate one million times to generate large data.
        for (int i = 0; i < 1000000; i++) {

            // Check if the current loop index is an even number.
            if (i % 2 == 0) {

                // Add a failed transaction worth 1.0 to the large dataset list.
                largeData.add(new Transaction(String.valueOf(i), "ACC", "2023-10-01", 1.0, "failed"));

                // Increment our expected total by 1.0 for every failed transaction added.
                expectedLargeTotal += 1.0;

            // Provide an alternative block for odd numbers.
            } else {

                // Add a successful transaction worth 1.0 to the large dataset list.
                largeData.add(new Transaction(String.valueOf(i), "ACC", "2023-10-01", 1.0, "success"));

            // Close the if-else block.
            }

        // Close the for-loop block.
        }

        // Run the final test case to ensure the stream can handle 1,000,000 records without memory or stack overflow issues.
        runTest("Large Data (1 Million Records)", expectedLargeTotal, calculateLostMoney(largeData));

    // Close the main method block.
    }

    // Declare a record to hold transaction data; records are immutable data carriers introduced in recent Java versions.
    // We define fields: id, accountNumber, date, amount, and status to match the transcript requirements.
    public record Transaction(String id, String accountNumber, String date, double amount, String status) {}
    
// Close the TransactionAnalyzer class block.
}