package com.interview.notes.code.year.y2026.june.google.test2;

import java.util.ArrayList; // Stores the recovered coin values.
import java.util.List; // Represents the list of recovered coins.
import java.util.Optional; // Represents either a valid result or no solution.

public class ReverseCoinChange { // Contains the solution and simple tests.

    static Optional<List<Integer>> findCoins(int[] expected) { // Reconstructs coins from the DP array.

        if (expected == null || expected.length == 0) { // The input must contain at least amount zero.
            return Optional.empty(); // Empty input cannot describe a valid coin system.
        }

        if (expected[0] != 1) { // There must always be one way to create amount zero.
            return Optional.empty(); // Any other value makes the input invalid.
        }

        for (int value : expected) { // Check every supplied number of combinations.
            if (value < 0) { // Combination counts cannot be negative.
                return Optional.empty(); // Reject invalid negative values.
            }
        }

        long[] ways = new long[expected.length]; // Stores the DP array we reconstruct ourselves.

        ways[0] = 1; // There is one way to create zero: choose no coins.

        List<Integer> coins = new ArrayList<>(); // Stores each coin denomination we discover.

        for (int amount = 1; amount < expected.length; amount++) { // Examine every possible coin value.

            if (ways[amount] == expected[amount]) { // Existing coins already explain this amount.
                continue; // Therefore, this amount is not a new coin.
            }

            if (ways[amount] + 1 != expected[amount]) { // A new coin can increase this position only by one.
                return Optional.empty(); // Any larger or smaller difference is impossible.
            }

            coins.add(amount); // The current amount must be a coin denomination.

            for (int total = amount; total < expected.length; total++) { // Apply normal unbounded coin-change DP.

                ways[total] += ways[total - amount]; // Add combinations formed by using the new coin.

                if (ways[total] > expected[total]) { // Future coins can only increase this value further.
                    return Optional.empty(); // Exceeding the expected count means no solution exists.
                }
            }
        }

        return Optional.of(List.copyOf(coins)); // Return an immutable copy of the recovered coins.
    }

    static void test( // Runs one test without JUnit.
            int number, // Identifies the test case.
            int[] input, // DP array supplied to the solution.
            List<Integer> expected) { // Expected recovered coins, or null when impossible.

        Optional<List<Integer>> actual = findCoins(input); // Execute the reverse coin-change algorithm.

        boolean passed = expected == null // Check whether this test expects no valid result.
                ? actual.isEmpty() // Pass when the method correctly returns empty.
                : actual.isPresent() && actual.get().equals(expected); // Otherwise compare the coin lists.

        System.out.printf( // Print a readable PASS or FAIL result.
                "Test %d: %s | expected=%s, actual=%s%n", // Define the output format.
                number, // Print the test number.
                passed ? "PASS" : "FAIL", // Print the test status.
                expected == null ? "empty" : expected, // Print the expected result.
                actual.map(Object::toString).orElse("empty")); // Print the actual result.
    }

    public static void main(String[] args) { // Program execution starts here.

        test( // Test the example from the question.
                1, // Test number.
                new int[]{1, 0, 1, 0, 1, 1, 2, 1, 2, 1, 3}, // DP produced by coins 2, 5, and 6.
                List.of(2, 5, 6)); // Expected recovered coins.

        test( // Test the invalid example from the question.
                2, // Test number.
                new int[]{1, 1, 1, 3, 2}, // No coin set can create these counts.
                null); // Empty result is expected.

        test( // Test a system containing only coin 1.
                3, // Test number.
                new int[]{1, 1, 1, 1, 1}, // Exactly one combination exists for every amount.
                List.of(1)); // Coin 1 produces this DP array.

        test( // Test a system with no usable coins in the represented range.
                4, // Test number.
                new int[]{1, 0, 0, 0, 0}, // No positive amount can be produced.
                List.of()); // The correct coin set is empty.

        test( // Test coins 1 and 2.
                5, // Test number.
                new int[]{1, 1, 2, 2, 3}, // DP array generated using coins 1 and 2.
                List.of(1, 2)); // Expected denominations.

        test( // Test an invalid zero-position value.
                6, // Test number.
                new int[]{0, 1, 1}, // Amount zero must contain one way.
                null); // No valid coin set exists.

        int[] large = new int[10_001]; // Create a larger input to test scalability.

        large[0] = 1; // Amount zero always has one combination.

        for (int amount = 2; amount < large.length; amount += 2) { // Visit every positive even amount.
            large[amount] = 1; // Coin 2 creates each even amount in exactly one way.
        }

        test( // Run the large-input test.
                7, // Test number.
                large, // DP array representing coin 2 up to amount 10,000.
                List.of(2)); // Expected recovered coin.
    }
}