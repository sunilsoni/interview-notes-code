package com.interview.notes.code.year.y2026.april.common.test3;// Import BigDecimal for highly accurate mathematical comparisons.
import java.math.BigDecimal;

// Define the class that contains our manual sorting algorithm and testing framework.
public class ManualTransactionSorter {

    // Define a private helper method to safely parse strings into BigDecimals without crashing.
    private static BigDecimal parseAmountSafely(String rawAmount) {

        // Return exactly zero if the raw string is null or completely empty.
        if (rawAmount == null || rawAmount.trim().isEmpty()) {

            // Defaulting to zero prevents null pointer exceptions during sorting comparisons.
            return BigDecimal.ZERO;

        // Close the null-check block.
        }

        // Open a try block to attempt mathematical conversion.
        try {

            // Parse and return the cleaned string as a precise BigDecimal object.
            return new BigDecimal(rawAmount.trim());

        // Catch the exception if the string contains alphabetical junk data.
        } catch (NumberFormatException e) {

            // Return zero so the corrupt record is simply sorted as a $0.00 transaction.
            return BigDecimal.ZERO;

        // Close the try-catch block.
        }

    // Close the safe parsing method.
    }

    // Define the method to manually sort an array of Transactions using the Bubble Sort algorithm.
    public static void sortTransactionsAscending(Transaction[] transactions) {

        // Safely exit the method immediately if the input array is null.
        if (transactions == null) {

            // Return nothing because a null array cannot be sorted.
            return;

        // Close the null-check block.
        }

        // Store the length of the array in a variable to use as our loop limit.
        int n = transactions.length;

        // Open the outer loop which guarantees we pass through the array enough times to fully sort it.
        for (int i = 0; i < n - 1; i++) {

            // Open the inner loop to compare adjacent pairs; it shrinks by 'i' because the end of the array is already sorted.
            for (int j = 0; j < n - 1 - i; j++) {

                // Safely extract and parse the mathematical amount of the current left-side transaction.
                BigDecimal leftAmount = parseAmountSafely(transactions[j].rawAmount());

                // Safely extract and parse the mathematical amount of the adjacent right-side transaction.
                BigDecimal rightAmount = parseAmountSafely(transactions[j + 1].rawAmount());

                // Use compareTo to check if the left amount is strictly greater than the right amount (> 0).
                if (leftAmount.compareTo(rightAmount) > 0) {

                    // If the left is bigger, we must swap them! Store the left transaction in a temporary variable.
                    Transaction temp = transactions[j];

                    // Overwrite the left position with the smaller right-side transaction.
                    transactions[j] = transactions[j + 1];

                    // Overwrite the right position with the larger transaction we stored in the temp variable.
                    transactions[j + 1] = temp;

                // Close the swap condition block.
                }

            // Close the inner pair-comparison loop.
            }

        // Close the outer array-traversal loop.
        }

    // Close the sorting method.
    }

    // Define a helper method to verify if our array was sorted correctly.
    private static void runTest(String testName, boolean isSorted) {

        // Check if the boolean flag indicates a successful sort.
        if (isSorted) {

            // Print a PASS message to the console.
            System.out.println("PASS: " + testName);

        // Provide the alternative block if the array is out of order.
        } else {

            // Print a FAIL message to alert the developer.
            System.out.println("FAIL: " + testName);

        // Close the if-else block.
        }

    // Close the test runner method.
    }

    // Define a helper method to mathematically verify that an array is in strictly ascending order.
    private static boolean verifySortedAscending(Transaction[] transactions) {

        // Loop through the array, stopping right before the last element.
        for (int i = 0; i < transactions.length - 1; i++) {

            // Parse the current amount safely.
            BigDecimal current = parseAmountSafely(transactions[i].rawAmount());

            // Parse the next adjacent amount safely.
            BigDecimal next = parseAmountSafely(transactions[i + 1].rawAmount());

            // If the current amount is mathematically greater than the next amount...
            if (current.compareTo(next) > 0) {

                // ...the array is out of order, so return false immediately.
                return false;

            // Close the comparison block.
            }

        // Close the verification loop.
        }

        // If the loop finishes without finding any out-of-order pairs, the array is perfectly sorted.
        return true;

    // Close the verification method.
    }

    // Define the main method to execute our logic and custom testing suite.
    public static void main(String[] args) {

        // Create a standard array of transactions in completely random order, including junk data.
        Transaction[] mixedData = new Transaction[] {

            // A transaction worth 500.00.
            new Transaction("1", "500.00"),

            // A transaction worth 10.50.
            new Transaction("2", "10.50"),

            // A transaction with junk data (will default to 0.00).
            new Transaction("3", "invalid_amount"),

            // A transaction worth 100.00.
            new Transaction("4", "100.00"),

            // A transaction with a negative amount (-50.00).
            new Transaction("5", "-50.00")

        // Close the array declaration.
        };

        // Execute our manual Bubble Sort algorithm on the mixed array.
        sortTransactionsAscending(mixedData);

        // Verify the array is sorted (Expected order: -50.00, 0.00 (junk), 10.50, 100.00, 500.00).
        runTest("Mixed Data Sort Test", verifySortedAscending(mixedData));

        // Create an array with only two elements that are already in the correct order.
        Transaction[] alreadySorted = new Transaction[] {
            new Transaction("1", "1.00"),
            new Transaction("2", "5.00")
        };

        // Execute the sort to ensure it doesn't accidentally shuffle already sorted data.
        sortTransactionsAscending(alreadySorted);

        // Verify the array remained in the correct ascending order.
        runTest("Already Sorted Array Test", verifySortedAscending(alreadySorted));

        // Initialize a large primitive array of 5,000 elements for performance testing.
        // Note: Bubble sort is O(N^2), so we use 5,000 instead of 1,000,000 so the test runs instantly.
        Transaction[] largeData = new Transaction[5000];

        // Loop backward from 5000 down to 1 to create an array that is completely backwards (worst-case scenario).
        for (int i = 0; i < 5000; i++) {

            // Assign a new transaction, making the amounts decrease as the index increases.
            largeData[i] = new Transaction(String.valueOf(i), String.valueOf(5000 - i));

        // Close the data generation loop.
        }

        // Execute the manual sort on the worst-case, reversed dataset.
        sortTransactionsAscending(largeData);

        // Verify the massive dataset was successfully inverted into ascending order.
        runTest("Large Reversed Data Sort (5,000 records)", verifySortedAscending(largeData));

    // Close the main method.
    }

    // Define the immutable Transaction record to hold our raw, unvalidated string data.
    public record Transaction(String id, String rawAmount) {}
    
// Close the ManualTransactionSorter class.
}