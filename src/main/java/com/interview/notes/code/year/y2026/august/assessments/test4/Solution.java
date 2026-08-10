package com.interview.notes.code.year.y2026.august.assessments.test4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {

    static void test(Solution s, int[] a, int[] p, int b, int e, boolean expected) {
        System.out.println(s.solution(a, p, b, e) == expected ? "PASS" : "FAIL");
    }

    public static void main(String[] args) {
        var s = new Solution();

        test(s, new int[]{2, 1}, new int[]{5, 1}, 3, 6, true);
        test(s, new int[]{2, 1}, new int[]{5, 1}, 2, 6, false);
        test(s, new int[]{1, 4, 2}, new int[]{10, 4, 7}, 11, 1, true);
        test(s, new int[]{5, 5, 1}, new int[]{3, 3, 6}, 4, 8, true);
        test(s, new int[]{1, 3}, new int[]{2, 6}, 1, 5, true);

        test(s, new int[]{1}, new int[]{5}, 4, 6, true);
        test(s, new int[]{1}, new int[]{5}, 3, 6, false);
        test(s, new int[]{1}, new int[]{5}, 10, 10, true);
        test(s, new int[]{2, 2}, new int[]{2, 6}, 0, 8, true);

        int n = 100000;
        int[] a = new int[n];
        Arrays.fill(a, 1);
        int[] p = IntStream.range(0, n).toArray();

        test(s, a, p, 0, 100000, true);
    }

    public boolean solution(int[] A, int[] P, int B, int E) {
        if (B == E) {
            return true;
        }

        var r = IntStream.range(0, A.length)
                .mapToObj(i -> new long[]{(long) P[i] - A[i], (long) P[i] + A[i]})
                .sorted(Comparator.comparingLong(x -> x[0]))
                .toArray(long[][]::new);

        long l = r[0][0], h = r[0][1];

        for (int i = 1; i <= r.length; i++) {
            if (i < r.length && r[i][0] <= h) {
                h = Math.max(h, r[i][1]);
            } else {
                if (l <= B && B <= h && l <= E && E <= h) {
                    return true;
                }

                if (i == r.length) {
                    break;
                }

                l = r[i][0];
                h = r[i][1];
            }
        }

        return false;
    }
}