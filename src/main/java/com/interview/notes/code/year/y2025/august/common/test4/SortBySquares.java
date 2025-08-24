package com.interview.notes.code.year.y2025.august.common.test4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class SortBySquares {                 // Define a public class to hold all methods and the main test

    // -----------------------------
    // Primary: O(n) stable two-pointer method that returns ORIGINAL numbers ordered by ascending |x|
    // -----------------------------
    public static int[] sortBySquareStableTwoPointers(int[] arr) {
        // Defensive copy not needed for reading; we'll produce a new result array.
        final int n = arr.length;            // Store array length for reuse
        int[] out = new int[n];              // Allocate output array for the reordered original values

        // Find first index whose value is >= 0 (boundary between negatives and non-negatives)
        int firstNonNeg = firstIndexOfNonNegative(arr); // Run a linear (or could be binary) search due to sorted input

        int left = firstNonNeg - 1;          // 'left' starts at the last negative number index
        int right = firstNonNeg;             // 'right' starts at the first non-negative number index
        int k = 0;                           // 'k' is the write index for the output array

        // Merge while either side still has elements
        while (left >= 0 || right < n) {     // Continue until both halves are exhausted
            if (left < 0) {                  // If no more elements on the left (negatives done) ...
                out[k++] = arr[right++];     // ... take from the right side only
            } else if (right >= n) {         // If no more elements on the right (non-negatives done) ...
                out[k++] = arr[left--];      // ... take from the left side only
            } else {
                int absLeft = Math.abs(arr[left]);   // Compute absolute value for left element
                int absRight = Math.abs(arr[right]); // Compute absolute value for right element

                if (absLeft < absRight) {    // If left has smaller absolute value ...
                    out[k++] = arr[left--];  // ... take left element
                } else if (absLeft > absRight) { // If right has smaller absolute value ...
                    out[k++] = arr[right++]; // ... take right element
                } else {
                    // absLeft == absRight → tie on absolute value
                    // STABILITY requirement: keep original relative order of equal-abs elements
                    // Since original index of 'left' (< right) appears earlier in the array,
                    // we choose the left element first to preserve stability across the boundary.
                    out[k++] = arr[left--];  // Take left first on tie (stable)
                }
            }
        }
        return out;                          // Return the fully merged array
    }

    // Helper to find first index i where arr[i] >= 0 in a sorted array.
    // Linear scan is O(n), but small constant and n is needed anyway; binary search could be used too.
    private static int firstIndexOfNonNegative(int[] arr) {
        // Optional: use Arrays.binarySearch for 0, then adjust; here we keep it simple and clear.
        for (int i = 0; i < arr.length; i++) { // Scan from start to first non-negative
            if (arr[i] >= 0) return i;         // Return index as soon as a non-negative is found
        }
        return arr.length;                    // If none found, return n (all elements are negative)
    }

    // -----------------------------
    // Simpler baseline using Java 8 Streams (O(n log n)): stable by default for equal keys
    // -----------------------------
    public static int[] sortBySquareStream(int[] arr) {
        // Box, sort by absolute value using Comparator.comparingInt, then unbox back to int[]
        return Arrays.stream(arr)             // Create an IntStream over the array
                .boxed()                 // Box to Stream<Integer> to use Comparator
                .sorted(Comparator.comparingInt(Math::abs)) // Sort by absolute value (ascending)
                .mapToInt(Integer::intValue) // Unbox back to primitive int
                .toArray();              // Collect into new int[]
    }

    // -----------------------------
    // (Optional utility) Classic “sorted squares” output: returns squares sorted ascending
    // -----------------------------
    public static int[] sortedSquaresTwoPointers(int[] arr) {
        final int n = arr.length;            // Cache length
        int[] out = new int[n];              // Allocate output array for squares
        int left = 0;                        // Pointer to the start
        int right = n - 1;                   // Pointer to the end
        int write = n - 1;                   // We will fill from the back with largest squares first

        while (left <= right) {              // Process until pointers cross
            int lv = arr[left];              // Value at left
            int rv = arr[right];             // Value at right
            int l2 = lv * lv;                // Square of left value
            int r2 = rv * rv;                // Square of right value

            if (l2 > r2) {                   // If left square is larger ...
                out[write--] = l2;           // ... place it at current write position and move write left
                left++;                      // ... advance left pointer
            } else {                         // Else right square is larger or equal
                out[write--] = r2;           // ... place right square at write position
                right--;                     // ... move right pointer left
            }
        }
        return out;                          // Return squares in ascending order
    }

    // -----------------------------
    // Test harness: simple main (no JUnit) printing PASS/FAIL + large data check
    // -----------------------------
    public static void main(String[] args) {
        // Define a small helper lambda to compare two int[] arrays for equality
        java.util.function.BiFunction<int[], int[], Boolean> eq =
                (a, b) -> Arrays.equals(a, b);   // Uses Arrays.equals for concise, reliable comparison

        // A helper to pretty print arrays (for readable diagnostics)
        java.util.function.Function<int[], String> p =
                arr -> Arrays.toString(arr);     // Convert int[] to string like "[1, 2, 3]"

        // ------------- Core sample tests (stable by |x| ordering) -------------
        int[][] inputs = {
                {1, 5, 7, 7, 8, 10},          // Already in ascending |x|
                {-5, -3, -3, 2, 4, 4, 8},     // Mixed negatives and positives
                {-7, -3, 0, 2, 4},            // Includes zero in the middle
                {-4, -3, -2, -1},             // All negatives
                {0, 0, 1, 2},                  // Zeros with positives
                {-1, -1, 1, 1},               // Equal absolute values, check stability
        };

        // Expected using the two-pointer logic (or we can derive via the stream ground truth below)
        int[][] expected = {
                {1, 5, 7, 7, 8, 10},          // NOTE: both 7s remain (typo fixed from prompt)
                {2, -3, -3, 4, 4, -5, 8},     // Matches your second example
                {0, 2, -3, 4, -7},             // |0|=0, |2|=2, |−3|=3, |4|=4, |−7|=7
                {-1, -2, -3, -4},             // By |x| asc: -1, -2, -3, -4
                {0, 0, 1, 2},                  // Already correct
                {-1, 1, -1, 1},               // Stability: original order of equals preserved
        };

        // Test each case using our O(n) method against the expected outcome
        for (int i = 0; i < inputs.length; i++) {
            int[] got = sortBySquareStableTwoPointers(inputs[i]);
            boolean pass = eq.apply(got, expected[i]);
            System.out.println("Case " + (i + 1) + " two-pointers: "
                    + (pass ? "PASS" : "FAIL")
                    + "  input=" + p.apply(inputs[i])
                    + "  expected=" + p.apply(expected[i])
                    + "  got=" + p.apply(got));
        }

        // Cross-check against the Java 8 Stream baseline for random small arrays
        // (We only do a few here to keep runtime small if executed)
        Random rnd = new Random(1);          // Seed for reproducibility
        for (int t = 0; t < 5; t++) {        // Run 5 random trials
            int n = 20;                      // Size of the random array
            int[] arr = rnd.ints(n, -50, 51)// Generate ints in [-50, 50]
                    .sorted()         // Make sure precondition "sorted array" holds
                    .toArray();       // Materialize

            int[] got = sortBySquareStableTwoPointers(arr);           // Our O(n) result
            int[] truth = sortBySquareStream(arr);                    // Baseline O(n log n)
            boolean pass = eq.apply(got, truth);                      // Compare
            System.out.println("Random cross-check " + (t + 1) + ": " // Print PASS/FAIL diagnostics
                    + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("  arr     = " + p.apply(arr));
                System.out.println("  twoPtr  = " + p.apply(got));
                System.out.println("  stream  = " + p.apply(truth));
            }
        }

        // ------------- Optional: "sorted squares" classic test -------------
        int[] sqIn = {-7, -3, 0, 2, 4};    // Input for squared output
        int[] sqGot = sortedSquaresTwoPointers(sqIn);                 // Squares in ascending order
        int[] sqExp = Arrays.stream(sqIn).map(x -> x * x).sorted().toArray(); // Stream ground truth
        System.out.println("Sorted squares test: " + (eq.apply(sqGot, sqExp) ? "PASS" : "FAIL")
                + "  got=" + p.apply(sqGot));

        // ------------- Large data performance sanity -------------
        // Create a large sorted array with a wide negative-to-positive range.
        int bigN = 2_000_00;                 // 200k elements (adjust up/down as needed)
        int[] big = new int[bigN];           // Allocate big array
        for (int i = 0; i < bigN; i++) {     // Fill with a smooth ramp from -100000 to +99999
            big[i] = i - (bigN / 2);         // Ensures monotonic sorted order
        }

        long t1 = System.currentTimeMillis();                    // Start time
        int[] bigOut = sortBySquareStableTwoPointers(big);       // O(n) run
        long t2 = System.currentTimeMillis();                    // End time
        // Quick correctness spot-check: result must be non-decreasing by |x|
        boolean nonDecreasingAbs = true;                         // Flag for verification
        for (int i = 1; i < bigOut.length; i++) {                // Check monotonicity by |x|
            if (Math.abs(bigOut[i - 1]) > Math.abs(bigOut[i])) { // If any adjacent pair violates order ...
                nonDecreasingAbs = false;                        // ... flag false
                break;                                           // ... and break early
            }
        }
        System.out.println("Large data (n=" + bigN + "): "       // Print size and timing
                + (nonDecreasingAbs ? "PASS" : "FAIL")
                + ", time=" + (t2 - t1) + " ms");
    }
}