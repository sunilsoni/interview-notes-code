package com.interview.notes.code.year.y2025.august.common.test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SortedSquares {              // Define a public class to hold our solution and tests

    // ------------------------------------------------------------
    // Optimal O(n) two-pointer solution
    // ------------------------------------------------------------
    public static int[] sortedSquaresTwoPointers(int[] nums) { // Method that returns sorted squares using two pointers
        // Handle trivial null input defensively; return empty array to avoid NPEs
        if (nums == null) return new int[0];

        // n holds the length so we don't repeatedly call nums.length
        final int n = nums.length;

        // Result array of the same size to place squared values in sorted order
        int[] res = new int[n];

        // Left pointer starts at the beginning (smallest value, possibly most negative)
        int l = 0;

        // Right pointer starts at the end (largest value)
        int r = n - 1;

        // We will fill res from right to left with the largest squares first
        int k = n - 1;

        // Iterate until pointers cross; each iteration places exactly one number into res[k]
        while (l <= r) {
            // Take absolute values to compare magnitudes without sign
            int leftAbs = Math.abs(nums[l]);
            int rightAbs = Math.abs(nums[r]);

            // If left magnitude is larger, its square should go at position k
            if (leftAbs > rightAbs) {
                res[k] = leftAbs * leftAbs; // Square and place
                l++;                         // Move left pointer inward
            } else {
                // Otherwise right magnitude is >=; place its square
                res[k] = rightAbs * rightAbs;
                r--;                         // Move right pointer inward
            }
            k--;                             // Move the write index leftward for the next largest square
        }

        // At this point res is fully populated in ascending order
        return res;                           // Return the sorted squares
    }

    // ------------------------------------------------------------
    // Baseline O(n log n) using Java 8 Streams (for validation)
    // ------------------------------------------------------------
    public static int[] sortedSquaresStreamAndSort(int[] nums) {    // Alternative method using streams
        if (nums == null) return new int[0];                        // Defensive null handling
        // Stream the array, map to squares (careful with overflow for large ints),
        // and sort the result; then collect back to int[]
        return Arrays.stream(nums)                                  // Create an IntStream over the array
                .map(x -> x * x)                               // Square each element
                .sorted()                                      // Sort the squared values
                .toArray();                                    // Materialize into a new int[]
    }

    // ------------------------------------------------------------
    // Helper: check arrays equality (both length and element-wise)
    // ------------------------------------------------------------
    private static boolean arraysEqual(int[] a, int[] b) {          // Utility to compare two int arrays
        return Arrays.equals(a, b);                                 // Use JDK's safe equality check
    }

    // ------------------------------------------------------------
    // Helper: pretty-print arrays for debugging
    // ------------------------------------------------------------
    private static String arrToString(int[] a) {                    // Convert int[] to String for logs
        return Arrays.toString(a);                                  // Reuse Arrays' string formatting
    }

    // ------------------------------------------------------------
    // Helper: verify ascending order and non-negative squares
    // ------------------------------------------------------------
    private static boolean isNonDecreasing(int[] a) {               // Ensure result is sorted ascending
        for (int i = 1; i < a.length; i++) {                        // Scan adjacent pairs
            if (a[i] < a[i - 1]) return false;                      // If any inversion found, fail
        }
        return true;                                                // Otherwise it's sorted
    }

    // ------------------------------------------------------------
    // Simple test harness in main (no JUnit), prints PASS/FAIL
    // ------------------------------------------------------------
    public static void main(String[] args) {                         // Entry point for running tests
        // Build a list of test cases for clarity and coverage
        List<int[]> inputs = new ArrayList<>();                      // Will store various input arrays

        // Core scenarios covering negatives, positives, zeros, duplicates, and edge cases
        inputs.add(new int[]{});                                     // Empty
        inputs.add(new int[]{0});                                    // Single zero
        inputs.add(new int[]{-1});                                   // Single negative
        inputs.add(new int[]{2});                                    // Single positive
        inputs.add(new int[]{-4, -1, 0, 3, 10});                     // Mixed classic case
        inputs.add(new int[]{-7, -3, 2, 3, 11});                     // Mixed with larger negatives
        inputs.add(new int[]{-5, -3, -2, -2, -1});                   // All negatives (sorted)
        inputs.add(new int[]{0, 0, 0});                              // All zeros
        inputs.add(new int[]{1, 1, 1});                              // All equal positives
        inputs.add(new int[]{-3, -3, -1, 0, 0, 2, 2});               // Duplicates cross zero
        inputs.add(new int[]{-10, -9, -2, -1, 0, 0, 1, 3, 7});        // Larger spread

        // Large data test: generate a sorted range with negatives to positives
        final int N = 200_000;                                       // Choose large but reasonable for quick run
        int[] large = IntStream.rangeClosed(-N, N)                   // Stream from -N to N inclusive
                .toArray();                           // Materialize to array (naturally sorted)
        inputs.add(large);                                           // Add to test set

        // Run tests
        System.out.println("Running tests for sortedSquaresTwoPointers vs baseline stream+sort...");
        int pass = 0;                                                // Counter for passed tests
        int fail = 0;                                                // Counter for failed tests
        int caseNum = 1;                                             // Case numbering for readability

        for (int[] in : inputs) {                                    // Iterate over each test case
            long t1 = System.nanoTime();                             // Start timer for two-pointer
            int[] got = sortedSquaresTwoPointers(in);                // Run optimal method
            long t2 = System.nanoTime();                             // Stop timer

            long t3 = System.nanoTime();                             // Start timer for baseline
            int[] expected = sortedSquaresStreamAndSort(in);         // Compute baseline to compare
            long t4 = System.nanoTime();                             // Stop timer

            boolean ok = arraysEqual(got, expected) && isNonDecreasing(got); // Validate correctness & monotonicity

            // Print detailed result per case, including timing for both approaches
            System.out.printf(
                    "Case %02d: %-35s  -> %s | two-pointer: %.2f ms, stream+sort: %.2f ms%n",
                    caseNum++,
                    in.length <= 12 ? arrToString(in) : ("[size=" + in.length + "]"),
                    ok ? "PASS" : "FAIL",
                    (t2 - t1) / 1_000_000.0,
                    (t4 - t3) / 1_000_000.0
            );

            if (ok) pass++;
            else {
                fail++;
                // If failed, print the mismatch to aid debugging
                if (in.length <= 30) {
                    System.out.println("  got     : " + arrToString(got));
                    System.out.println("  expected: " + arrToString(expected));
                } else {
                    // For very large cases, avoid spamming output; only show edge samples
                    System.out.println("  [Large output suppressed]");
                }
            }
        }

        // Summary of results after all cases
        System.out.printf("%nSummary: %d PASS, %d FAIL%n", pass, fail); // Final summary line
    }
}