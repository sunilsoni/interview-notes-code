package com.interview.notes.code.year.y2026.april.common.test4;// Import List to hold our collection of transactions.

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Import ArrayList to build our large dataset efficiently.
// Import BigDecimal for highly accurate financial calculations.

// Define the main class that holds our business logic and tests.
public class TransactionAnalyzer {

    // Define the method to calculate lost money, now returning a exact BigDecimal instead of a double.
    public static BigDecimal calculateLostMoney(List<Transaction> transactions) {

        // Check if the list is null to prevent the application from crashing.
        if (transactions == null) {

            // Return exactly zero if the list is null.
            return BigDecimal.ZERO;

        // Close the null check block.
        }

        // Convert the list into a Stream for sequential, functional processing.
        return transactions.stream()

            // Keep only transactions where the status is "failed", ignoring case sensitivity.
            .filter(t -> "failed".equalsIgnoreCase(t.status()))

            // Extract the BigDecimal amount from each failed transaction.
            .map(Transaction::amount)

            // Add all the BigDecimal amounts together, starting from ZERO.
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    // Close the calculateLostMoney method.
    }

    // Helper method to verify if our tests pass or fail using BigDecimal comparison.
    private static void runTest(String testName, BigDecimal expected, BigDecimal actual) {

        // Compare the expected and actual BigDecimals; compareTo returns 0 if they are mathematically equal.
        if (expected.compareTo(actual) == 0) {

            // Print a success message if the values match perfectly.
            System.out.println("PASS: " + testName);

        // If they do not match exactly:
        } else {

            // Print a failure message showing both values to help with debugging.
            System.out.println("FAIL: " + testName + " - Expected: " + expected + ", Actual: " + actual);

        // Close the if-else block.
        }

    // Close the test helper method.
    }

    // The main entry point to run our application and our tests.
    public static void main(String[] args) {

        // Create an unmodifiable list of normal transactions.
        List<Transaction> normalData = List.of(

            // Failed transaction worth 100.50 (String constructor is safest for BigDecimal).
            new Transaction("1", "A1", "2023-10-01", new BigDecimal("100.50"), "failed"),

            // Successful transaction worth 50.00.
            new Transaction("2", "A2", "2023-10-01", new BigDecimal("50.00"), "success"),

            // Failed transaction in uppercase worth 200.00.
            new Transaction("3", "A3", "2023-10-01", new BigDecimal("200.00"), "FAILED")

        // Close the list creation.
        );

        // Test normal data expecting 300.50 (100.50 + 200.00).
        runTest("Normal Mixed Data", new BigDecimal("300.50"), calculateLostMoney(normalData));

        // Test an empty list expecting exactly 0.
        runTest("Empty List", BigDecimal.ZERO, calculateLostMoney(List.of()));

        // Test a null input expecting exactly 0.
        runTest("Null Input", BigDecimal.ZERO, calculateLostMoney(null));

        // Create a list with only successful transactions.
        List<Transaction> allSuccessData = List.of(

            // Add one successful transaction.
            new Transaction("4", "A4", "2023-10-01", new BigDecimal("500.00"), "success")

        // Close the list.
        );

        // Test all success data expecting 0 since nothing failed.
        runTest("All Success Data", BigDecimal.ZERO, calculateLostMoney(allSuccessData));

        // Initialize a list to hold 1 million records for performance testing.
        List<Transaction> largeData = new ArrayList<>();

        // Create a BigDecimal to track the expected total for our large dataset.
        BigDecimal expectedLargeTotal = BigDecimal.ZERO;

        // Standard amount used for the large dataset test.
        BigDecimal oneDollar = new BigDecimal("1.00");

        // Loop one million times to generate bulk data.
        for (int i = 0; i < 1000000; i++) {

            // Check if the loop index is an even number.
            if (i % 2 == 0) {

                // Add a failed transaction worth $1.00.
                largeData.add(new Transaction(String.valueOf(i), "ACC", "2023-10-01", oneDollar, "failed"));

                // Add $1.00 to our expected total.
                expectedLargeTotal = expectedLargeTotal.add(oneDollar);

            // Otherwise, for odd numbers:
            } else {

                // Add a successful transaction worth $1.00.
                largeData.add(new Transaction(String.valueOf(i), "ACC", "2023-10-01", oneDollar, "success"));

            // Close the if-else block.
            }

        // Close the loop.
        }

        // Run the large dataset test to ensure the program scales without freezing or overflowing.
        runTest("Large Data (1 Million Records)", expectedLargeTotal, calculateLostMoney(largeData));

    // Close the main method.
    }

    // Define a Java 21 record to hold transaction data immutably; changed 'amount' to BigDecimal.
    public record Transaction(String id, String accountNumber, String date, BigDecimal amount, String status) {}
    
// Close the class.
}