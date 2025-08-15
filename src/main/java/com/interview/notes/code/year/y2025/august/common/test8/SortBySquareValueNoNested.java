package com.interview.notes.code.year.y2025.august.common.test8;

public class SortBySquareValueNoNested {
    // Sort array elements by ascending square values, returning the original numbers (not squares).
    public static int[] sortBySquares(int[] arr) {
        int n = arr.length;                         // length of input
        int[] res = new int[n];                     // result array to fill
        int left = 0;                               // pointer to start (most negative)
        int right = n - 1;                          // pointer to end (most positive)
        int k = n - 1;                              // fill index from the end with the LARGEST |value| first

        // Key idea:
        // Choose the element with larger absolute value from either end and place it at res[k],
        // then move inward. This builds the result in DESCENDING |value| from the end,
        // so the final array is ASCENDING by |value| from the front.
        while (left <= right) {                     // single pass, linear time
            int lv = arr[left];                     // current left value
            int rv = arr[right];                    // current right value
            int la = lv < 0 ? -lv : lv;            // abs(left) without Math.abs (basic ops)
            int ra = rv < 0 ? -rv : rv;            // abs(right)

            if (la > ra) {                          // left has larger |value|
                res[k] = lv;                        // place left value at the current highest slot
                left++;                             // move left pointer forward
            } else {                                // right has larger or equal |value|
                // Choosing right on tie (la == ra) preserves stability for pairs like -x ... x
                // and yields the correct global ascending-by-abs order when filling from the end.
                res[k] = rv;                        // place right value
                right--;                            // move right pointer backward
            }
            k--;                                    // move fill index leftward
        }
        return res;                                  // res is now sorted by ascending squares
    }

    // Compare two int arrays for equality (length + element-wise)
    private static boolean arraysEqual(int[] a, int[] b) {
        if (a.length != b.length) return false;     // different sizes => not equal
        int i = 0;                                  // index for scan
        while (i < a.length) {                      // linear compare
            if (a[i] != b[i]) return false;        // mismatch => not equal
            i++;                                    // next index
        }
        return true;                                // all matched
    }

    // Print array nicely for debugging
    private static void printArray(int[] arr) {
        System.out.print("[");                      // open bracket
        for (int i = 0; i < arr.length; i++) {      // iterate elements
            System.out.print(arr[i]);               // print value
            if (i < arr.length - 1) System.out.print(", "); // commas between
        }
        System.out.print("]");                      // close bracket
    }

    public static void main(String[] args) {
        // Test inputs
        int[][] tests = new int[][] {
            {1, 5, 7, 7, 8, 10},                    // already ascending by abs
            {-5, -3, -3, 2, 4, 4, 8},               // mix negatives/positives
            {},                                     // empty
            {-2, -1, 0, 1, 2},                      // symmetric around 0
            {-10, -5, -2, 0, 3, 6}                  // varied range
        };

        // Expected outputs (ascending by |value|, stable among equals)
        int[][] expected = new int[][] {
            {1, 5, 7, 7, 8, 10},
            {2, -3, -3, 4, 4, -5, 8},
            {},
            {0, -1, 1, -2, 2},
            {0, -2, 3, -5, 6, -10}
        };

        // Run and report PASS/FAIL
        for (int t = 0; t < tests.length; t++) {
            int[] out = sortBySquares(tests[t]);    // run sorting
            boolean pass = arraysEqual(out, expected[t]); // compare to expected
            System.out.print("Test " + (t + 1) + ": ");
            printArray(out);                        // print actual
            System.out.print(" -> " + (pass ? "PASS" : "FAIL"));
            if (!pass) {                            // on failure, show expected for quick debug
                System.out.print(" | Expected: ");
                printArray(expected[t]);
            }
            System.out.println();
        }

        // Large data performance sanity check (no nested loops → linear time)
        int size = 1_000_00;                        // 100k elements
        int[] big = new int[size];                  // allocate big array
        int mid = size / 2;                         // mid point
        int i = 0;                                  // index build
        while (i < size) {                          // fill with sorted values from -mid..(size-mid-1)
            big[i] = i - mid;                       // increasing sequence centered at 0
            i++;                                    // next
        }
        long start = System.currentTimeMillis();    // start timer
        int[] outBig = sortBySquares(big);          // run sort
        long time = System.currentTimeMillis() - start; // elapsed

        // Quick integrity check on large result: verify non-decreasing absolute values
        boolean nonDecreasingAbs = true;            // flag
        int j = 1;                                  // check from second element
        while (j < outBig.length) {                 // single pass check
            int prev = outBig[j - 1];               // previous value
            int curr = outBig[j];                   // current value
            int ap = prev < 0 ? -prev : prev;       // |prev|
            int ac = curr < 0 ? -curr : curr;       // |curr|
            if (ap > ac) {                          // if abs decreased anywhere → fail
                nonDecreasingAbs = false;           // mark bad
                break;                              // stop checking
            }
            j++;                                    // next
        }

        System.out.println("Large dataset processed in " + time + " ms -> " +
                (nonDecreasingAbs ? "PASS" : "FAIL"));
    }
}