package com.interview.notes.code.year.y2025.september.assesment.test1;

import java.util.*;
import java.util.stream.*;

public class MoveZerosInPlace {
    static void moveZeroes(int[] a) {
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                int t = a[j];
                a[j] = a[i];
                a[i] = t;
                j++;
            }
        }
    }

    static boolean eq(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

    static void run(String name, int[] input, int[] expected) {
        moveZeroes(input);
        System.out.println(name + ": " + (eq(input, expected) ? "PASS" : "FAIL"));
    }

    static int[] expectedBySpec(int[] a) {
        int[] nz = IntStream.of(a).filter(x -> x != 0).toArray();
        int[] res = Arrays.copyOf(nz, a.length);
        if (nz.length < a.length) Arrays.fill(res, nz.length, a.length, 0);
        return res;
    }

    public static void main(String[] args) {
        run("Example1", new int[]{0, 1, 0, 3, 12}, new int[]{1, 3, 12, 0, 0});
        run("Example2", new int[]{0}, new int[]{0});
        run("AllZeros", new int[]{0, 0, 0}, new int[]{0, 0, 0});
        run("NoZeros", new int[]{4, 5, 6}, new int[]{4, 5, 6});
        run("MixedNeg", new int[]{0, -1, 0, -2, 3, 0}, new int[]{-1, -2, 3, 0, 0, 0});
        run("SingleNonZero", new int[]{5}, new int[]{5});

        Random r = new Random(7);
        int n = 1_000_000;
        int[] big = new int[n];
        for (int i = 0; i < n; i++) {
            int v = r.nextInt(9) - 4;
            if (r.nextInt(5) == 0) v = 0;
            big[i] = v;
        }
        int[] expected = expectedBySpec(Arrays.copyOf(big, big.length));

        long t1 = System.nanoTime();
        moveZeroes(big);
        long t2 = System.nanoTime();

        System.out.println("LargeData: " + (eq(big, expected) ? "PASS" : "FAIL"));
        System.out.println("LargeDataInPlace(ms): " + ((t2 - t1) / 1_000_000));
    }
}
