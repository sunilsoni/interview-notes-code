package com.interview.notes.code.year.y2025.august.common.test5;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*

**Question 1:** Given an array of sorted integers, sort the array in ascending order of squares of its elements.

**Example:**

```
[1, 5, 7, 7, 8, 10] --> [1, 5, 7, 8, 10]
[-5, -3, -3, 2, 4, 4, 8] --> [2, -3, -3, 4, 4, -5, 8]
```

 */
public class SortBySquares {                    // Define a public class to hold our methods and tests

    // --- Core Method 1: Reorder original numbers by ascending absolute value (matches your examples) ---
    public static int[] reorderByAbsoluteValue(int[] nums) {     // Public API: takes sorted array, returns reordered by |x|
        // Edge-case guard: if array is null or length <= 1, it's already "sorted by abs" trivially
        if (nums == null || nums.length <= 1)
            return nums;       // Return original reference for performance; safe when size <= 1

        int n = nums.length;                                     // Cache length for repeated use
        int[] out = new int[n];                                  // Output buffer to store the merged result
        int k = 0;                                               // Write pointer for 'out'

        // Find the split between negatives and non-negatives:
        // i = last negative index; j = first non-negative index
        int j = 0;                                               // Start scanning to find first non-negative
        while (j < n && nums[j] < 0) {                           // Advance while elements are negative
            j++;                                                 // Move j forward
        }
        int i = j - 1;                                           // i now points to last negative element (may be -1 if none)

        // Merge by absolute value:
        // negatives from i downwards (|value| increasing as i decreases),
        // non-negatives from j upwards (|value| increasing as j increases)
        while (i >= 0 && j < n) {                                // While both sides have elements
            int leftAbs = Math.abs(nums[i]);                     // abs value on the negative side
            int rightAbs = Math.abs(nums[j]);                    // abs value on the non-negative side

            if (leftAbs <= rightAbs) {                           // If negative side is smaller or equal by abs
                out[k++] = nums[i--];                            // Choose the negative (keeps -x before +x on ties)
            } else {                                             // Otherwise
                out[k++] = nums[j++];                            // Choose the non-negative
            }
        }

        // Drain remaining negatives (if any)
        while (i >= 0) {                                         // If negatives remain
            out[k++] = nums[i--];                                // Append them (still increasing by abs)
        }

        // Drain remaining non-negatives (if any)
        while (j < n) {                                          // If non-negatives remain
            out[k++] = nums[j++];                                // Append them
        }

        return out;                                              // Return the reordered-by-abs array
    }

    // --- Core Method 2: Classic variant: return squares sorted ascending (sometimes asked in interviews) ---
    public static int[] sortedSquares(int[] nums) {              // Public API: returns sorted squares
        if (nums == null || nums.length == 0) return new int[0]; // Handle null/empty safely

        int n = nums.length;                                     // Cache length
        int[] out = new int[n];                                  // Output for squares
        int k = 0;                                               // Write pointer

        // Same two-pointer split as above
        int j = 0;                                               // Seek first non-negative index
        while (j < n && nums[j] < 0) {                           // Advance until nums[j] >= 0
            j++;                                                 // Move forward
        }
        int i = j - 1;                                           // Last negative index

        // Merge by squares (equivalent to abs comparison) and write squared values
        while (i >= 0 && j < n) {                                // While both sides have elements
            int leftAbs = Math.abs(nums[i]);                     // abs on negative side
            int rightAbs = Math.abs(nums[j]);                    // abs on non-negative side

            if (leftAbs <= rightAbs) {                           // If negative side smaller/equal
                out[k++] = leftAbs * leftAbs;                    // Square and put into out
                i--;                                             // Move negative pointer left
            } else {                                             // Otherwise pick non-negative side
                out[k++] = rightAbs * rightAbs;                  // Square and put into out
                j++;                                             // Move non-negative pointer right
            }
        }

        // Drain remaining negatives (squared)
        while (i >= 0) {                                         // If negatives remain
            int v = Math.abs(nums[i]);                           // Take abs
            out[k++] = v * v;                                    // Square
            i--;                                                 // Move left
        }

        // Drain remaining non-negatives (squared)
        while (j < n) {                                          // If non-negatives remain
            int v = Math.abs(nums[j]);                           // Take abs (optional for >=0 but symmetric)
            out[k++] = v * v;                                    // Square
            j++;                                                 // Move right
        }

        return out;                                              // Return sorted squares
    }

    // --- Utility: pretty print arrays as a String (for readable PASS/FAIL logs) using Java 8 streams ---
    private static String arrToString(int[] a) {                 // Convert int[] to "[x, y, z]" string
        if (a == null) return "null";                            // Null-safe
        return IntStream.of(a)                                   // Stream over ints
                .mapToObj(Integer::toString)            // Convert each to String
                .collect(Collectors.joining(", ", "[", "]")); // Join with commas and bracket
    }

    // --- Utility: check equality of two int arrays ---
    private static boolean equals(int[] a, int[] b) {            // Compare element-wise equality
        return Arrays.equals(a, b);                              // Use JDK's safe, fast comparison
    }

    // --- Test harness: run test cases and print PASS/FAIL with details ---
    public static void main(String[] args) {                     // Entry point to run all tests without JUnit
        // Helper lambda to run and report a single test for reorderByAbsoluteValue
        java.util.function.BiConsumer<int[], int[]> testReorder = (input, expected) -> {
            int[] actual = reorderByAbsoluteValue(input);        // Run the method under test
            boolean pass = equals(actual, expected);             // Compare with expected
            System.out.println("reorderByAbsoluteValue");        // Print which method
            System.out.println("  input    = " + arrToString(input));
            System.out.println("  expected = " + arrToString(expected));
            System.out.println("  actual   = " + arrToString(actual));
            System.out.println("  RESULT   = " + (pass ? "PASS" : "FAIL"));
            System.out.println();
        };

        // Helper lambda to run and report a single test for sortedSquares (classic variant)
        java.util.function.BiConsumer<int[], int[]> testSquares = (input, expected) -> {
            int[] actual = sortedSquares(input);                 // Run classic variant
            boolean pass = equals(actual, expected);             // Compare
            System.out.println("sortedSquares");                 // Print which method
            System.out.println("  input    = " + arrToString(input));
            System.out.println("  expected = " + arrToString(expected));
            System.out.println("  actual   = " + arrToString(actual));
            System.out.println("  RESULT   = " + (pass ? "PASS" : "FAIL"));
            System.out.println();
        };

        // --- Small & edge tests for reorderByAbsoluteValue (your interpretation & examples) ---
        testReorder.accept(new int[]{}, new int[]{});                               // Empty -> Empty
        testReorder.accept(new int[]{7}, new int[]{7});                             // Single element
        testReorder.accept(new int[]{1, 5, 7, 7, 8, 10},                           // All positive
                new int[]{1, 5, 7, 8, 10});                              // Note: example shows dedup of 7, but sorting by abs keeps both 7s adjacent;
        // if you truly want dedup, we can add it—here we keep stable order by |x|
        testReorder.accept(new int[]{-5, -3, -3, 2, 4, 4, 8},                      // Mixed negatives/positives
                new int[]{2, -3, -3, 4, 4, -5, 8});                      // Matches your example (abs order, negatives first on ties)

        testReorder.accept(new int[]{-4, -1, 0, 3, 10},                             // Common textbook case
                new int[]{0, -1, 3, -4, 10});                             // |0|=0, | -1 |=1, |3|=3, | -4 |=4, |10|=10

        testReorder.accept(new int[]{-2, -2, -2}, new int[]{-2, -2, -2});          // All negatives same value

        testReorder.accept(new int[]{0, 0, 1, -1},                                  // Zeros, tie abs(1)=abs(-1)
                new int[]{0, 0, -1, 1});                                  // Pick -1 before +1 on tie

        // --- Classic sortedSquares tests for completeness (many platforms ask this variant) ---
        testSquares.accept(new int[]{-4, -1, 0, 3, 10}, new int[]{0, 1, 9, 16, 100});
        testSquares.accept(new int[]{-7, -3, 2, 3, 11}, new int[]{4, 9, 9, 49, 121});
        testSquares.accept(new int[]{}, new int[]{});
        testSquares.accept(new int[]{5}, new int[]{25});

        // --- Large data test (performance & robustness) ---
        int size = 500_000;                                                       // Half a million elements for a realistic perf check
        int[] big = new int[size];                                                // Allocate big array
        // Fill with a smooth ramp from negatives to positives:
        // [-250000, -249999, ..., -1, 0, 1, ..., 249999]
        IntStream.range(0, size)                                                  // 0..size-1
                .forEach(idx -> big[idx] = idx - (size / 2));                    // Center around zero; keeps input sorted

        long t0 = System.currentTimeMillis();                                     // Start timing
        int[] bigOut = reorderByAbsoluteValue(big);                               // Run abs reordering
        long t1 = System.currentTimeMillis();                                     // End timing

        // Quick correctness spot-checks on big result (monotone non-decreasing abs values)
        boolean nonDecreasingAbs = IntStream.range(1, bigOut.length)              // For each adjacent pair
                .allMatch(p -> Math.abs(bigOut[p - 1]) <= Math.abs(bigOut[p]));   // Check |prev| <= |curr|

        System.out.println("Large Test (reorderByAbsoluteValue): size=" + size);
        System.out.println("  timeMillis = " + (t1 - t0));
        System.out.println("  nonDecreasingAbs = " + nonDecreasingAbs);
        System.out.println("  RESULT = " + (nonDecreasingAbs ? "PASS" : "FAIL"));
    }
}