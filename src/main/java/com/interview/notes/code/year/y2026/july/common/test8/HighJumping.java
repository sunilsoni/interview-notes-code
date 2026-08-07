package com.interview.notes.code.year.y2026.july.common.test8;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HighJumping {

    public static int highJump(int N, int[] A) {
        if (N == 1) {
            return A[0];
        }
        int[] d = new int[10002];
        IntStream.range(0, N - 1).forEach(i -> {
            d[Math.min(A[i], A[i + 1])]++;
            d[Math.max(A[i], A[i + 1]) + 1]--;
        });
        Arrays.parallelPrefix(d, Integer::sum);
        return IntStream.range(0, d.length).reduce(0, (r, i) -> d[i] > d[r] ? i : r);
    }

    public static void main(String[] args) {
        test(4, new int[]{1, 5, 3, 6}, 3);
        test(1, new int[]{42}, 42);
        test(3, new int[]{10, 0, 10}, 0);
        
        int[] l = IntStream.range(0, 1000).map(i -> i % 2 == 0 ? 0 : 10000).toArray();
        test(1000, l, 0);
    }

    private static void test(int n, int[] a, int e) {
        System.out.println(highJump(n, a) == e ? "PASS" : "FAIL");
    }
}