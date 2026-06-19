package com.interview.notes.code.year.y2026.june.common.test7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution { // Main class for running and testing the solution.

    static final long MOD = 1_000_000_007L; // Modulo value given in the problem.

    public static int calculateMaximumConsecutiveSum(int k, List<List<Integer>> segment) { // Required function.

        List<Seg> segs = segment.stream() // Convert input list into stream.
                .map(a -> new Seg(a.get(0), a.get(1), a.get(2))) // Convert each row to Seg object.
                .sorted(Comparator.comparingLong(Seg::l)) // Sort by start index.
                .toList(); // Store sorted segments in a list.

        int n = segs.size(); // Store number of segments.

        long[] starts = new long[n]; // Stores all segment starts for binary search.

        long[] ends = new long[n]; // Stores all segment ends for binary search.

        long[] pref = new long[n + 1]; // Prefix sum of complete segment money.

        for (int i = 0; i < n; i++) { // Build helper arrays.
            Seg s = segs.get(i); // Get current segment.
            starts[i] = s.l(); // Store current start.
            ends[i] = s.r(); // Store current end.
            pref[i + 1] = pref[i] + s.coins(); // Store prefix money till this segment.
        }

        long best = 0; // Stores maximum money before modulo.

        long len = k; // Convert k to long to avoid overflow.

        for (Seg s : segs) { // Check every segment as possible boundary.

            long leftStart = s.l(); // Case 1: window starts at segment start.

            long leftEnd = leftStart + len - 1; // Find window end.

            best = Math.max(best, sum(leftStart, leftEnd, segs, starts, ends, pref)); // Update best.

            long rightStart = Math.max(1, s.r() - len + 1); // Case 2: window ends at segment end.

            long rightEnd = rightStart + len - 1; // Find window end.

            best = Math.max(best, sum(rightStart, rightEnd, segs, starts, ends, pref)); // Update best.
        }

        return (int) (best % MOD); // Return maximum money modulo 10^9 + 7.
    }

    static long sum(long left, long right, List<Seg> segs, long[] starts, long[] ends, long[] pref) { // Finds money in range [left, right].

        int first = lowerBound(ends, left); // First segment whose end is >= left.

        int last = upperBound(starts, right) - 1; // Last segment whose start is <= right.

        if (first >= segs.size() || first > last) return 0; // No overlap means zero money.

        long total = pref[last + 1] - pref[first]; // Add complete overlapping segment totals.

        Seg firstSeg = segs.get(first); // First overlapping segment.

        Seg lastSeg = segs.get(last); // Last overlapping segment.

        if (left > firstSeg.l()) total -= (left - firstSeg.l()) * firstSeg.v(); // Remove extra left part.

        if (right < lastSeg.r()) total -= (lastSeg.r() - right) * lastSeg.v(); // Remove extra right part.

        return total; // Return final range sum.
    }

    static int lowerBound(long[] a, long x) { // Finds first index where a[index] >= x.

        int l = 0; // Start pointer.

        int r = a.length; // End pointer.

        while (l < r) { // Binary search loop.

            int m = l + (r - l) / 2; // Middle index.

            if (a[m] < x) l = m + 1; // Move right if middle is too small.

            else r = m; // Move left if middle can be answer.
        }

        return l; // Return first valid index.
    }

    static int upperBound(long[] a, long x) { // Finds first index where a[index] > x.

        int l = 0; // Start pointer.

        int r = a.length; // End pointer.

        while (l < r) { // Binary search loop.

            int m = l + (r - l) / 2; // Middle index.

            if (a[m] <= x) l = m + 1; // Move right if value is still <= x.

            else r = m; // Move left if value is greater than x.
        }

        return l; // Return first greater index.
    }

    static List<List<Integer>> list(int[][] a) { // Converts int[][] to List<List<Integer>> for testing.

        return Arrays.stream(a) // Stream rows.

                .map(r -> Arrays.stream(r).boxed().toList()) // Convert each int[] row to List<Integer>.

                .toList(); // Convert all rows to List<List<Integer>>.
    }

    static void check(String name, int k, int[][] a, long expected) { // Simple PASS/FAIL test method.

        int actual = calculateMaximumConsecutiveSum(k, list(a)); // Run solution.

        int exp = (int) (expected % MOD); // Apply modulo to expected answer.

        System.out.println(name + " : " + (actual == exp ? "PASS" : "FAIL")
                + " | expected=" + exp + " actual=" + actual); // Print test result.
    }

    public static void main(String[] args) { // Main method for local testing.

        check("Sample 0", 3, new int[][]{{1, 9, 5}, {10, 20, 5}}, 15); // Given sample 0.

        check("Sample 1", 1, new int[][]{{1, 1, 10}, {2, 2, 20}, {3, 3, 30}}, 30); // Given sample 1.

        check("Question Example", 5, new int[][]{{1, 4, 2}, {6, 6, 5}, {7, 7, 7}, {9, 10, 1}}, 16); // Given example.

        check("One Long Segment", 3, new int[][]{{1, 10, 5}}, 15); // Best window is inside one segment.

        check("Gap Case", 3, new int[][]{{1, 1, 5}, {10, 10, 10}}, 10); // Handles gaps with zero money.

        check("Large Data", 1_000_000_000, new int[][]{{1, 1_000_000_000, 1_000_000}}, 1_000_000_000_000_000L); // Large value test.
    }

    record Seg(long l, long r, long v) { // Java 21 record to store segment start, end, and money.
        long coins() { return (r - l + 1) * v; } // Calculates full money inside this segment.
    }
}