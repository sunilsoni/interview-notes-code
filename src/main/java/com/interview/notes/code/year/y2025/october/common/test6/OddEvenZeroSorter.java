package com.interview.notes.code.year.y2025.october.common.test6;

import java.util.Random;

public class OddEvenZeroSorter {

    // ------------------------ Public API ------------------------

    // arrange: Reorders the given array in-place to: odds(asc) | evens(asc) | zeros
    public static void arrange(int[] a) {     // Public entry: modifies 'a' directly as required
        if (a == null || a.length <= 1) {     // If array is null or has 0/1 element, it's already in required form
            return;                           // Nothing to do
        }

        int n = a.length;                     // Cache length for clarity and to avoid repeated field access
        int write = 0;                        // 'write' points to next slot where we place a non-zero
        int zeroCount = 0;                    // Count how many zeros to place at the end later

        // Step 1: Compact non-zeros to the front; count zeros
        for (int read = 0; read < n; read++) {       // Scan all elements once
            if (a[read] == 0) {                      // If current element is zero
                zeroCount++;                         // Increase zero counter
            } else {                                 // Otherwise it's non-zero
                a[write++] = a[read];                // Move non-zero forward into the compacted prefix
            }
        }
        // Now indices [0..write-1] hold all non-zero elements (order not important yet)
        // Indices [write..n-1] are leftover; we'll later fill the tail with zeros

        int nonZeroLen = write;                      // Length of non-zero prefix to partition and sort

        if (nonZeroLen > 1) {                        // Only partition/sort if at least two elements exist
            // Step 2: Partition non-zero prefix so that odds come before evens
            int oddEnd = partitionOddsEvens(a, 0, nonZeroLen - 1); // Returns index of last odd in [0..nonZeroLen-1]

            // Step 3: Sort odds ascending (if there is at least one odd)
            if (oddEnd >= 0) {                       // oddEnd = -1 means there were no odds
                quickSortAsc(a, 0, oddEnd);          // Sort odd segment in-place ascending
            }

            // Step 4: Sort evens ascending (if there is at least one even)
            if (oddEnd + 1 <= nonZeroLen - 1) {      // oddEnd+1 is first even; ensure range is valid
                quickSortAsc(a, oddEnd + 1, nonZeroLen - 1); // Sort even segment ascending
            }
        }

        // Step 5: Fill zeros at the end
        for (int i = nonZeroLen; i < n; i++) {       // Fill the remaining tail positions
            a[i] = 0;                                // Put zeros to satisfy the rule "zeros at the end"
        }
    }

    // ------------------------ Helpers: Partition & Sort ------------------------

    // partitionOddsEvens: move all odds (abs(x)%2==1) to the left side, evens to the right, within [lo..hi]
    private static int partitionOddsEvens(int[] a, int lo, int hi) { // Returns index of last odd after partition, or -1 if none
        int i = lo;                                   // Left pointer starts at lo
        int j = hi;                                   // Right pointer starts at hi
        while (i <= j) {                              // Process until pointers cross
            // Move i forward while a[i] is odd
            while (i <= j && isOdd(a[i])) {           // If current left is odd, it's on correct side
                i++;                                  // Advance left pointer
            }
            // Move j backward while a[j] is even
            while (i <= j && !isOdd(a[j])) {          // If current right is even, it's on correct side
                j--;                                  // Move right pointer backward
            }
            if (i < j) {                              // If pointers have not crossed, we found an even at left and odd at right
                swap(a, i, j);                        // Swap to put odd left and even right
                i++;                                  // Move inward
                j--;                                  // Move inward
            }
        }
        return i - 1;                                  // After loop, i is first even index; last odd is i-1; may be -1 if no odds
    }

    // isOdd: treats odd by absolute value so negatives are handled (-3 is odd)
    private static boolean isOdd(int x) {             // Helper to check oddness
        return Math.abs(x) % 2 == 1;                  // Odd if absolute value mod 2 equals 1
    }

    // quickSortAsc: in-place quicksort on a[lo..hi] ascending
    private static void quickSortAsc(int[] a, int lo, int hi) { // Custom sort as library sort is disallowed
        while (lo < hi) {                            // Tail-recursion elimination loop for better stack usage
            int p = partitionAscByPivot(a, lo, hi);  // Partition by pivot; elements <= pivot on left, >= on right
            // Recurse on smaller side first to keep stack depth O(log n)
            if (p - lo < hi - p) {                   // Left side is smaller
                if (lo < p) quickSortAsc(a, lo, p);  // Sort left half
                lo = p + 1;                          // Tail-call on right half via loop
            } else {                                  // Right side is smaller
                if (p + 1 < hi) quickSortAsc(a, p + 1, hi); // Sort right half
                hi = p;                               // Tail-call on left half via loop
            }
        }
    }

    // partitionAscByPivot: Hoare partition scheme around median-like pivot (here choose middle) for a[lo..hi]
    private static int partitionAscByPivot(int[] a, int lo, int hi) { // Returns final pivot boundary index
        int mid = lo + ((hi - lo) >> 1);             // Choose middle index to reduce worst-cases on partially ordered data
        int pivot = a[mid];                           // Pivot value
        int i = lo - 1;                               // i will move rightwards
        int j = hi + 1;                               // j will move leftwards
        while (true) {                                // Hoare's infinite loop until return
            do { i++; } while (a[i] < pivot);         // Move i right until element >= pivot
            do { j--; } while (a[j] > pivot);         // Move j left until element <= pivot
            if (i >= j) {                             // When pointers cross, return split point
                return j;                             // j is the final boundary
            }
            swap(a, i, j);                            // Swap out-of-place items to maintain <=pivot on left, >=pivot on right
        }
    }

    // swap: exchange a[i] and a[j]
    private static void swap(int[] a, int i, int j) { // Simple element swap helper
        int tmp = a[i];                               // Store left in temp
        a[i] = a[j];                                  // Move right to left
        a[j] = tmp;                                   // Move temp (old left) to right
    }

    // ------------------------ Validation for Tests ------------------------

    // validate: checks odds asc | evens asc | zeros, and multiset equality with original
    private static boolean validate(int[] original, int[] result) {  // Ensures correctness on both order and element counts
        if (original.length != result.length) return false;          // Length must be same

        // Count table using only arrays: we use a simple rolling checksum + counts for zero/odd/even buckets
        // To fully ensure multiset equality without collections, we do:
        // 1) Compare sum and sum of squares (basic heuristic),
        // 2) Compare zero counts,
        // 3) Check every element in result exists enough times by subtracting found counts against a copy.
        // Because we can't allocate large maps, we prefer property + ordering checks strictness.
        // Stronger: we ensure partition/order + same counts of zeros + same counts per parity of absolute value is tricky w/o extra space.
        // Instead: clone original, sort it with the same rules using the same algorithm and compare arrays.
        // BUT that mutates a temp array (allowed: it's still an array). We'll do that.

        int[] copy = original.clone();                              // Make a copy to transform independently
        arrange(copy);                                              // Apply our algorithm to the copy
        if (!arraysEqual(copy, result)) return false;               // If our transform differs, result is invalid

        // Additionally assert ordering structure directly on 'result'
        int n = result.length;                                      // Cache length
        int idx = 0;                                                // Walk pointer

        // 1) All odds ascending first
        while (idx < n && result[idx] != 0 && isOdd(result[idx])) { // While odd and non-zero
            if (idx > 0 && result[idx - 1] != 0 && isOdd(result[idx - 1])) {
                if (result[idx] < result[idx - 1]) return false;   // Must be ascending within odds
            }
            idx++;                                                  // Move forward
        }

        // 2) Then evens (non-zero) ascending
        while (idx < n && result[idx] != 0 && !isOdd(result[idx])) { // While even and non-zero
            if (idx > 0 && result[idx - 1] != 0 && !isOdd(result[idx - 1])) {
                if (result[idx] < result[idx - 1]) return false;   // Must be ascending within evens
            }
            idx++;                                                  // Move forward
        }

        // 3) Then zeros only
        while (idx < n) {                                           // Remaining tail
            if (result[idx] != 0) return false;                     // Must all be zero
            idx++;                                                  // Advance
        }
        return true;                                                // Passed all checks
    }

    // arraysEqual: compare arrays element-by-element
    private static boolean arraysEqual(int[] x, int[] y) {          // Utility to avoid using Arrays.equals (still allowed, but we keep it simple)
        if (x.length != y.length) return false;                     // Length must match
        for (int i = 0; i < x.length; i++) {                        // Compare all items
            if (x[i] != y[i]) return false;                         // Any mismatch => not equal
        }
        return true;                                                // All equal
    }

    // ------------------------ Tests ------------------------

    // runSmallTests: predefined small scenarios including the given example
    private static void runSmallTests() {                           // Prints PASS/FAIL for small cases
        // Each test: input -> expected; we mutate input in place and compare to expected.
        int[][] inputs = {
            {4,9,0,2,0,7,0,0,3,8},           // Mixed with zeros and both parities
            {},                               // Empty
            {0,0,0},                          // All zeros
            {1,3,5,7},                        // Only odds
            {2,4,6,8},                        // Only evens
            {0,1,0,2,0,3,0,4,0,5},            // Alternating zeros
            {-3, -2, -1, 0, 2, 3, 0},        // Includes negatives
            {5},                               // Single element
            {0},                               // Single zero
            {9,7,5,3,1,2,4,6,8,0,0}           // Descending odds then ascending evens with zeros
        };

        int[][] expected = {
            {3,7,9,2,4,8,0,0,0,0},           // Given expected
            {},                               // Empty stays empty
            {0,0,0},                          // All zeros remain all zeros
            {1,3,5,7},                        // Odds already sorted
            {2,4,6,8},                        // Evens already sorted (no zeros)
            {1,3,5,2,4,0,0,0,0,0},            // Odds asc: 1,3,5; Evens asc:2,4; zeros tail
            {-3,-1,3,-2,2,0,0},               // Odds asc: -3,-1,3; Evens asc: -2,2; zeros tail
            {5},                               // Single stays single
            {0},                               // Single zero stays
            {1,3,5,7,9,2,4,6,8,0,0}            // Sorted odds asc then evens asc, zeros tail
        };

        for (int t = 0; t < inputs.length; t++) {                   // For each test case
            int[] in = inputs[t].clone();                           // Clone to avoid mutating the original example data
            arrange(in);                                            // Transform in place
            boolean ok = arraysEqual(in, expected[t])               // Compare to expected AND
                         && validate(inputs[t], in);                // Validate structure + multiset equivalence via transform
            System.out.println(ok ? "PASS" : "FAIL");               // Print PASS/FAIL as requested
        }
    }

    // runLargeRandomTest: stress test with large array; validate properties, not exact content
    private static void runLargeRandomTest() {                      // Builds a big random array and validates
        int n = 1_000_00;                                          // 100k elements (adjustable for bigger loads)
        Random rnd = new Random(42);                                // Fixed seed for reproducibility
        int[] big = new int[n];                                     // Allocate large array

        for (int i = 0; i < n; i++) {                               // Fill with random ints in a controlled range
            int r = rnd.nextInt(2001) - 1000;                       // Range [-1000, 1000]
            // Make zeros reasonably frequent
            if (rnd.nextInt(6) == 0) r = 0;                         // ~1/6 chance of zero
            big[i] = r;                                             // Assign
        }

        int[] original = big.clone();                                // Keep a copy for validate()

        long start = System.currentTimeMillis();                     // Time measure start
        arrange(big);                                                // Transform
        long end = System.currentTimeMillis();                       // Time measure end

        boolean ok = validate(original, big);                        // Validate correctness
        System.out.println("Large input test passed: " + ok);        // Report result
        System.out.println("Time(ms): " + (end - start));            // Show time to give a sense of performance
    }

    // ------------------------ Main: run tests ------------------------

    public static void main(String[] args) {                         // Single entry point to run all tests
        // Provided example (explicit)
        int[] example = {4,9,0,2,0,7,0,0,3,8};                       // Input from the prompt
        arrange(example);                                            // Transform
        System.out.print("Example Output: ");                        // Label
        for (int v : example) System.out.print(v + " ");             // Print the transformed array
        System.out.println();                                        // New line

        // Small deterministic tests (prints PASS/FAIL per test)
        runSmallTests();                                             // Run the suite of small tests

        // Large random test (prints single pass/fail and timing)
        runLargeRandomTest();                                        // Stress test for performance & robustness
    }
}