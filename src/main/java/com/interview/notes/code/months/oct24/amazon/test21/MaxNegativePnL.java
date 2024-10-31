package com.interview.notes.code.months.oct24.amazon.test21;

import java.util.Arrays;

public class MaxNegativePnL {

    public static int getMaxNegativePnL(int[] PnL) {
        int totalSum = Arrays.stream(PnL).sum(); // Initial total sum
        Arrays.sort(PnL); // Sort the array in ascending order

        int cumulativeSum = totalSum;
        int negativeCount = 0;

        // Traverse sorted PnL and flip values from smallest to largest
        for (int pnl : PnL) {
            if (cumulativeSum - 2 * pnl >= 0) { // Check if flip keeps cumulative sum non-negative
                cumulativeSum -= 2 * pnl; // Flip pnl to negative
                negativeCount++;
            } else {
                break; // Stop if flipping would make cumulative sum negative
            }
        }

        return negativeCount;
    }

    // Method for testing cases
    public static void main(String[] args) {
        // Define your test cases here
        test(new int[]{5, 3, 1, 2}, 2);
        test(new int[]{1, 1, 1, 1, 1}, 2);
        test(new int[]{5, 2, 3, 5, 2, 3}, 3);

        // Testing large input
        int n = 100000;
        int[] largePnL = new int[n];
        Arrays.fill(largePnL, 1); // All values are 1 for simplicity
        test(largePnL, n / 2); // Expected to be roughly half as negatives
    }

    // Method to run each test case
    public static void test(int[] PnL, int expected) {
        int result = getMaxNegativePnL(PnL);
        if (result == expected) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL - Expected: " + expected + " but got: " + result);
        }
    }
}
