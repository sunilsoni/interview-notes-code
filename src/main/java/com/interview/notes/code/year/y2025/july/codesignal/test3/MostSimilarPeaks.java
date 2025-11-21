package com.interview.notes.code.year.y2025.july.codesignal.test3;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class MostSimilarPeaks {

    /**
     * Returns the minimum absolute height-difference between any two peaks
     * whose indices differ by at least viewingGap.
     *
     * @throws IllegalArgumentException if viewingGap >= heights.length
     */
    public static int solution(int[] heights, int viewingGap) {
        int n = heights.length;
        if (viewingGap >= n) {
            throw new IllegalArgumentException(
                    "viewingGap must be less than number of peaks");
        }

        TreeSet<Integer> seen = new TreeSet<>();
        int minDiff = Integer.MAX_VALUE;

        // Start at index = viewingGap, so the first valid pair is
        // (0, viewingGap). As i increases we add heights[i - viewingGap]
        // into the sorted set and compare heights[i] against it.
        for (int i = viewingGap; i < n; i++) {
            seen.add(heights[i - viewingGap]);
            int h = heights[i];

            // closest not above h
            Integer lower = seen.floor(h);
            if (lower != null) {
                minDiff = Math.min(minDiff, h - lower);
            }

            // closest not below h
            Integer higher = seen.ceiling(h);
            if (higher != null) {
                minDiff = Math.min(minDiff, higher - h);
            }
        }

        return minDiff;
    }

    // Simple main for PASS/FAIL testing (no JUnit)
    public static void main(String[] args) {
        class Test {
            final int[] heights;
            final int gap;
            final int expected;

            Test(int[] h, int g, int e) {
                heights = h;
                gap = g;
                expected = e;
            }
        }

        List<Test> tests = Arrays.asList(
                new Test(new int[]{1, 5, 4, 10, 9}, 3, 4),
                new Test(new int[]{3, 10, 5, 8}, 1, 2),
                new Test(new int[]{7, 7}, 1, 0),  // same heights
                new Test(new int[]{2, 100, 3, 101}, 2, 1)   // (100,101) or (2,3)
        );

        int passCount = 0;
        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            int result = solution(t.heights, t.gap);
            boolean pass = result == t.expected;
            System.out.printf("Test %d: %s (expected=%d, got=%d)%n",
                    i + 1, pass ? "PASS" : "FAIL",
                    t.expected, result);
            if (pass) passCount++;
        }
        System.out.printf("Passed %d/%d basic tests.%n",
                passCount, tests.size());

        // Large random test to check performance
        final int N = 200_000;
        Random rnd = new Random(123);
        int[] large = new int[N];
        for (int i = 0; i < N; i++) {
            large[i] = rnd.nextInt(1_000_000_000);
        }
        int gap = 5000;

        long t0 = System.nanoTime();
        int ans = solution(large, gap);
        long t1 = System.nanoTime();
        System.out.printf("Large test (n=%d, gap=%d) → result=%d, time=%.3f ms%n",
                N, gap, ans, (t1 - t0) / 1e6);
    }
}