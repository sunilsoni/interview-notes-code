package com.interview.notes.code.year.y2025.June.common.test8;

import java.util.Arrays;
import java.util.TreeMap;

public class ViewingGapTester {

    /**
     * Returns the smallest |heights[a] - heights[b]|
     * for all pairs with |a - b| >= viewingGap.
     */
    public static int solution(int[] heights, int viewingGap) {
        int n = heights.length;
        // multiset of all heights[j] with j >= viewingGap
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int j = viewingGap; j < n; j++) {
            map.put(heights[j], map.getOrDefault(heights[j], 0) + 1);
        }

        int best = Integer.MAX_VALUE;
        // slide i from 0 .. n - viewingGap - 1
        for (int i = 0; i + viewingGap < n; i++) {
            int h = heights[i];
            // check nearest >= h
            Integer hi = map.ceilingKey(h);
            if (hi != null) best = Math.min(best, hi - h);
            // check nearest <= h
            Integer lo = map.floorKey(h);
            if (lo != null) best = Math.min(best, h - lo);

            // remove heights[i + viewingGap] before next i
            int out = heights[i + viewingGap];
            int cnt = map.get(out);
            if (cnt == 1) map.remove(out);
            else map.put(out, cnt - 1);
        }

        return best;
    }

    public static void main(String[] args) {
        // each test: {heights}, viewingGap, expected
        Object[][] tests = {
                {new int[]{1, 5, 4, 10, 9}, 3, 4},
                {new int[]{3, 10, 5, 8}, 1, 2},
                {new int[]{2, 2, 2, 2}, 2, 0},
                {new int[]{5, 1}, 1, 4}
        };

        int pass = 0;
        for (int t = 0; t < tests.length; t++) {
            int[] h = (int[]) tests[t][0];
            int gap = (int) tests[t][1];
            int exp = (int) tests[t][2];
            int got = solution(h, gap);
            boolean ok = got == exp;
            System.out.printf(
                    "Test %02d: %-4s  gap=%d  exp=%d  got=%d  heights=%s%n",
                    t + 1, ok ? "PASS" : "FAIL", gap, exp, got, Arrays.toString(h)
            );
            if (ok) pass++;
        }
        System.out.printf("Passed %d/%d%n", pass, tests.length);
    }
}