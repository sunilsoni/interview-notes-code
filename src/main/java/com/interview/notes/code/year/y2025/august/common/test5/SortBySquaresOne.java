package com.interview.notes.code.year.y2025.august.common.test5;

import java.util.Arrays; // used only in main for printing

public class SortBySquaresOne {

    // ============================
    // ONE PERFECT METHOD (O(n), stable)
    // ============================
    public static int[] sortBySquareStable(int[] arr) {
        final int n = arr.length;                             // cache length for reuse
        int[] out = new int[n];                               // output will hold original numbers re-ordered by |x|

        // ---- find first index with value >= 0 using binary search (since input is sorted) ----
        int lo = 0;                                           // lower bound for binary search
        int hi = n;                                           // upper bound is exclusive
        while (lo < hi) {                                     // standard lower_bound loop
            int mid = lo + ((hi - lo) >>> 1);                 // compute mid without overflow
            if (arr[mid] >= 0) hi = mid;                      // if mid is non-negative, move left to find first
            else lo = mid + 1;                                // otherwise move right
        }
        int firstNonNeg = lo;                                  // first index i where arr[i] >= 0 (or n if none)

        // ---- two-pointer merge by absolute value (stable on ties) ----
        int left = firstNonNeg - 1;                            // left side starts at last negative index
        int right = firstNonNeg;                               // right side starts at first non-negative index
        int k = 0;                                             // write index in output

        while (left >= 0 || right < n) {                       // continue until both halves are consumed
            if (left < 0) {                                    // no more negatives left
                out[k++] = arr[right++];                       // take from right side
            } else if (right >= n) {                           // no more non-negatives left
                out[k++] = arr[left--];                        // take from left side
            } else {
                int absL = Math.abs(arr[left]);                // absolute value on left candidate
                int absR = Math.abs(arr[right]);               // absolute value on right candidate
                if (absL < absR) {                             // smaller |x| should come first
                    out[k++] = arr[left--];                    // take left value
                } else if (absL > absR) {                      // right has smaller |x|
                    out[k++] = arr[right++];                   // take right value
                } else {                                       // tie on |x|
                    // STABILITY: prefer the one with smaller original index.
                    // All left indices < right indices, so take left first to preserve order.
                    out[k++] = arr[left--];                    // take left first (stable)
                }
            }
        }
        return out;                                            // done: original numbers ordered by ascending squares
    }

    // ============================
    // Simple main: PASS/FAIL tests + large data sanity
    // ============================
    public static void main(String[] args) {
        // Given examples (first fixed to keep both 7s)
        int[][] inputs = {
            { 1, 5, 7, 7, 8, 10 },
            { -5, -3, -3, 2, 4, 4, 8 },
            { -7, -3, 0, 2, 4 },
            { -4, -3, -2, -1 },
            { 0, 0, 1, 2 },
            { -1, -1, 1, 1 },
        };

        int[][] expected = {
            { 1, 5, 7, 7, 8, 10 },          // already correct
            { 2, -3, -3, 4, 4, -5, 8 },     // matches problem’s second example
            { 0, 2, -3, 4, -7 },            // by |x|: 0,2,3,4,7 (preserve order on ties)
            { -1, -2, -3, -4 },             // by |x| asc
            { 0, 0, 1, 2 },                  // already correct
            { -1, 1, -1, 1 },               // stability across equal |x|
        };

        for (int i = 0; i < inputs.length; i++) {
            int[] got = sortBySquareStable(Arrays.copyOf(inputs[i], inputs[i].length));
            boolean pass = Arrays.equals(got, expected[i]);
            System.out.println("Case " + (i + 1) + ": " + (pass ? "PASS" : "FAIL")
                + "  input=" + Arrays.toString(inputs[i])
                + "  expected=" + Arrays.toString(expected[i])
                + "  got=" + Arrays.toString(got));
        }

        // Large data sanity (checks that |out[i]| is non-decreasing)
        int bigN = 200_000;                          // adjust as needed for your machine
        int[] big = new int[bigN];
        for (int i = 0; i < bigN; i++) big[i] = i - bigN / 2;  // sorted from negative to positive
        long t1 = System.currentTimeMillis();
        int[] bigOut = sortBySquareStable(big);
        long t2 = System.currentTimeMillis();
        boolean ok = true;
        for (int i = 1; i < bigOut.length; i++) {
            if (Math.abs(bigOut[i - 1]) > Math.abs(bigOut[i])) { ok = false; break; }
        }
        System.out.println("Large n=" + bigN + ": " + (ok ? "PASS" : "FAIL")
            + ", time=" + (t2 - t1) + " ms");
    }
}