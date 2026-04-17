package com.interview.notes.code.year.y2026.april.assessments.test10;

import java.util.Arrays;

public class BridgeWeightOptimizer {
    public static void main(String[] args) {
        var opt = new BridgeWeightOptimizer();

        test(opt, 9, new int[]{5, 3, 8, 1, 8, 7, 7, 6}, 4);
        test(opt, 7, new int[]{7, 6, 5, 2, 7, 4, 5, 4}, 5);
        test(opt, 7, new int[]{3, 4, 3, 1}, 0);
        test(opt, 2, new int[]{3, 7, 5, 5, 6, 3, 9, 10, 8, 4}, 10);

        int[] largeData = new int[100000];
        Arrays.fill(largeData, 1);
        test(opt, 2, largeData, 0);

        int[] largeOverload = new int[100000];
        Arrays.fill(largeOverload, 10);
        test(opt, 5, largeOverload, 100000);
    }

    private static void test(BridgeWeightOptimizer opt, int U, int[] w, int exp) {
        System.out.println(opt.solution(U, w) == exp ? "PASS" : "FAIL");
    }

    public int solution(int U, int[] weight) {
        int[] v = Arrays.stream(weight).filter(w -> w <= U).distinct().sorted().toArray();
        int[] bit = new int[v.length + 1];
        int max = 0;

        for (int w : weight) {
            if (w > U) continue;
            int idx = Arrays.binarySearch(v, U - w);
            idx = idx >= 0 ? idx + 1 : -idx - 1;
            int len = 0;

            for (int i = idx; i > 0; i -= i & -i) {
                len = Math.max(len, bit[i]);
            }

            max = Math.max(max, ++len);

            for (int i = Arrays.binarySearch(v, w) + 1; i <= v.length; i += i & -i) {
                bit[i] = Math.max(bit[i], len);
            }
        }

        return weight.length - max;
    }
}