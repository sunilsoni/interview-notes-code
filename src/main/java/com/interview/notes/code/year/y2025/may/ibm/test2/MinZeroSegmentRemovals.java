package com.interview.notes.code.year.y2025.may.ibm.test2;

import java.util.*;

public class MinZeroSegmentRemovals {
    /**
     * Returns the minimum number of length-k flips to eliminate
     * all exact-m zero segments from s.
     */
    public static int getMinOperations(String s, int m, int k) {
        int n = s.length();
        // Prefix sum of zeros for O(1) window checks
        int[] zeroPref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            zeroPref[i + 1] = zeroPref[i] + (s.charAt(i) == '0' ? 1 : 0);
        }

        // Build intervals of valid flip-starts [L, R] for each forbidden window
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i + m <= n; i++) {
            if (zeroPref[i + m] - zeroPref[i] == m) {
                int L = Math.max(0, i - (k - 1));
                int R = Math.min(i + m - 1, n - k);
                if (L <= R) {
                    intervals.add(new int[]{L, R});
                }
            }
        }

        if (intervals.isEmpty()) {
            return 0;
        }

        // Greedy: sort by interval end, then pick points to cover all intervals
        intervals.sort(Comparator.comparingInt(iv -> iv[1]));
        int ops = 0;
        int coveredUpTo = -1;
        for (int[] iv : intervals) {
            int L = iv[0], R = iv[1];
            if (coveredUpTo < L) {
                ops++;
                coveredUpTo = R;
            }
        }

        return ops;
    }

    private static void runTest(String s, int m, int k, int expected) {
        int got = getMinOperations(s, m, k);
        System.out.printf(
            "s=\"%s\", m=%d, k=%d → got %d, want %d [%s]%n",
            s, m, k, got, expected, (got == expected ? "PASS" : "FAIL")
        );
    }

    public static void main(String[] args) {
        // Provided samples
        runTest("000000", 3, 2, 1);
        runTest("10101", 1, 1, 2);
        runTest("10101", 2, 3, 0);

        // The previously failing case
        runTest("000000000110011100", 2, 7, 2);

        // Additional edge & stress tests
        runTest("0000", 3, 2, 1);
        runTest("0000000", 3, 2, 2);
        runTest("11111", 1, 1, 0);
        runTest("0", 1, 1, 1);
        runTest("000", 3, 5, 1);

        // Large stress test
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 200_000; i++) sb.append('0');
        int expected = (int) Math.ceil((200_000 - 50_000 + 1) / (double)(100_000 + 50_000 - 1));
        runTest(sb.toString(), 50_000, 100_000, expected);
    }
}