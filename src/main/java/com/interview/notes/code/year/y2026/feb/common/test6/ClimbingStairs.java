package com.interview.notes.code.year.y2026.feb.common.test6;

import java.util.List;

public class ClimbingStairs {

    // Main method acts as our test runner
    public static void main(String[] args) {

        // Define test cases using Java 21 List.of() for simplicity
        // Format: List of inputs to test
        var testInputs = List.of(1, 2, 3, 4, 5, 10, 45);

        // Corresponding expected outputs for verification
        // 45 stairs = 1,836,311,903 ways (close to Integer.MAX_VALUE)
        var expectedOutputs = List.of(1L, 2L, 3L, 5L, 8L, 89L, 1836311903L);

        // Header for the output table
        System.out.printf("%-10s %-15s %-15s %-10s%n", "Input", "Expected", "Actual", "Status");
        System.out.println("-------------------------------------------------------");

        // Loop through inputs to run tests
        for (int i = 0; i < testInputs.size(); i++) {
            int input = testInputs.get(i);          // Get current input n
            long expected = expectedOutputs.get(i); // Get expected result

            // Execute the solution logic
            long actual = countWays(input);

            // Determine PASS/FAIL status
            String status = (actual == expected) ? "PASS" : "FAIL";

            // Print formatted row for this test case
            System.out.printf("%-10d %-15d %-15d %-10s%n", input, expected, actual, status);
        }

        // Edge case: Large Input Test (n = 50)
        // This demonstrates handling data larger than standard int capacity
        long largeInputResult = countWays(50);
        System.out.println("\nLarge Input (n=50) Result: " + largeInputResult);
    }

    /**
     * Calculates the number of ways to climb stairs.
     * Uses Iterative approach for O(n) time and O(1) space.
     */
    public static long countWays(int n) {
        // Validation: If 0 or negative stairs, 0 ways to climb.
        if (n <= 0) return 0; // Guard clause for invalid input

        // Base Case: If only 1 stair, only 1 way to climb (1 step).
        if (n == 1) return 1; // Immediate return for n=1

        // Base Case: If 2 stairs, 2 ways (1+1 or 2).
        if (n == 2) return 2; // Immediate return for n=2

        // 'oneStepBefore' tracks ways to reach the step immediately before current.
        // Initially represents ways to reach step 2.
        long oneStepBefore = 2;

        // 'twoStepsBefore' tracks ways to reach the step 2 steps back.
        // Initially represents ways to reach step 1.
        long twoStepsBefore = 1;

        // 'currentWays' will store the calculated ways for the current loop iteration.
        long currentWays = 0;

        // Loop from 3 up to n (since we already handled 1 and 2).
        for (int i = 3; i <= n; i++) {

            // Core Logic: Ways(i) = Ways(i-1) + Ways(i-2)
            currentWays = oneStepBefore + twoStepsBefore; // Sum previous two distinct counts

            // Shift the window for the next iteration:
            // The current 'oneStepBefore' becomes 'twoStepsBefore' for the next step.
            twoStepsBefore = oneStepBefore; // Move pointer forward

            // The current calculated result becomes 'oneStepBefore' for the next step.
            oneStepBefore = currentWays; // Update most recent value
        }

        // Return the final calculated result for n stairs.
        return currentWays; // Final answer
    }
}