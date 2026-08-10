package com.interview.notes.code.year.y2026.august.assessments.test3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class CranePackageMover {

    public static void main(String[] args) {
        var solver = new CranePackageMover();

        test(solver, new int[]{2, 1}, new int[]{5, 1}, 3, 6, true);
        test(solver, new int[]{2, 1}, new int[]{5, 1}, 2, 6, false);
        test(solver, new int[]{1, 4, 2}, new int[]{10, 4, 7}, 11, 1, true);
        test(solver, new int[]{5, 5, 1}, new int[]{3, 3, 6}, 4, 8, true);
        test(solver, new int[]{1, 3}, new int[]{2, 6}, 1, 5, true);

        var largeA = new int[100000];
        var largeP = new int[100000];
        Arrays.fill(largeA, 5);
        for (int i = 0; i < 100000; i++) {
            largeP[i] = i * 10;
        }
        test(solver, largeA, largeP, 0, 999900, true);
        test(solver, largeA, largeP, 0, 1000000, false);
    }

    private static void test(CranePackageMover solver, int[] A, int[] P, int B, int E, boolean expected) {
        if (solver.solution(A, P, B, E) == expected) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }

    public boolean solution(int[] A, int[] P, int B, int E) {
        int min = Math.min(B, E);
        int max = Math.max(B, E);

        var intervals = IntStream.range(0, A.length)
                .mapToObj(i -> new int[]{P[i] - A[i], P[i] + A[i]})
                .sorted(Comparator.comparingInt(a -> a[0]))
                .toArray(int[][]::new);

        int s = intervals[0][0];
        int e = intervals[0][1];

        for (var iv : intervals) {
            if (iv[0] <= e) {
                e = Math.max(e, iv[1]);
            } else {
                if (min >= s && max <= e) {
                    return true;
                }
                s = iv[0];
                e = iv[1];
            }
        }

        return min >= s && max <= e;
    }
}