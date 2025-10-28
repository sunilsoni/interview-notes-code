package com.interview.notes.code.year.y2025.october.common.test13;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Solution {
    private static int reference(int[] A, int[] B) {
        int n = A.length;
        long sumA = Arrays.stream(A).asLongStream().sum();
        long sumB = Arrays.stream(B).asLongStream().sum();
        if (sumA != sumB || (sumA & 1L) == 1L) return 0;
        long half = sumA / 2L;
        long[] prefA = new long[n + 1];
        long[] prefB = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefA[i + 1] = prefA[i] + A[i];
            prefB[i + 1] = prefB[i] + B[i];
        }
        return (int) IntStream.range(1, n).filter(k -> prefA[k] == half && prefB[k] == half).count();
    }

    private static String runCase(int[] A, int[] B, int expected) {
        Solution s = new Solution();
        long t1 = System.nanoTime();
        int out = s.solution(A, B);
        long t2 = System.nanoTime();
        boolean pass = out == expected;
        return "Output=" + out + " | Expected=" + expected + " | TimeMs=" + ((t2 - t1) / 1_000_000) + " | Result=" + (pass ? "PASS" : "FAIL");
    }

    public static void main(String[] args) {
        System.out.println(runCase(new int[]{0, 4, -1, 0, 3}, new int[]{0, -2, 5, 0, 3}, 2));
        System.out.println(runCase(new int[]{2, -2, -3, 3}, new int[]{0, 0, 4, -4}, 1));
        System.out.println(runCase(new int[]{4, -1, 0, 3}, new int[]{-2, 6, 0, 4}, 0));
        System.out.println(runCase(new int[]{3, 2, 6}, new int[]{4, 1, 6}, 0));
        System.out.println(runCase(new int[]{1, 4, 2, -2, 5}, new int[]{7, -2, -2, 2, 5}, 2));
        System.out.println(runCase(new int[]{-1, 1}, new int[]{-1, 1}, 1));
        System.out.println(runCase(new int[]{1, 2}, new int[]{1, 2}, 0));

        Random rnd = new Random(7);
        for (int t = 0; t < 50; t++) {
            int n = 2 + rnd.nextInt(50);
            int[] A = new int[n];
            int[] B = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = rnd.nextInt(21) - 10;
                B[i] = rnd.nextInt(21) - 10;
            }
            long sa = Arrays.stream(A).asLongStream().sum();
            long sb = Arrays.stream(B).asLongStream().sum();
            int shift = rnd.nextInt(11) - 5;
            B[0] += (int) (sa - sb) + shift;
            A[0] += shift;
            sa = Arrays.stream(A).asLongStream().sum();
            if ((sa & 1L) == 1L) {
                A[0]++;
                B[0]++;
            }
            int out1 = new Solution().solution(A, B);
            int out2 = reference(A, B);
            System.out.println("RandomTest Result=" + (out1 == out2 ? "PASS" : "FAIL") + " | Ans=" + out1);
        }

        int n = 100_000;
        int[] A = new int[n];
        int[] B = new int[n];
        Random r = new Random(123);
        for (int i = 0; i < n; i++) {
            A[i] = r.nextInt(2001) - 1000;
            B[i] = r.nextInt(2001) - 1000;
        }
        long sa = Arrays.stream(A).asLongStream().sum();
        long sb = Arrays.stream(B).asLongStream().sum();
        B[0] += (int) (sa - sb);
        sa = Arrays.stream(A).asLongStream().sum();
        if ((sa & 1L) == 1L) {
            A[0]++;
            B[0]++;
        }
        long t1 = System.nanoTime();
        int outFast = new Solution().solution(A, B);
        long t2 = System.nanoTime();
        long t3 = System.nanoTime();
        int outRef = reference(A, B);
        long t4 = System.nanoTime();
        System.out.println("Large data test | FastAns=" + outFast + " | RefAns=" + outRef + " | Verified=" + (outFast == outRef) + " | FastMs=" + ((t2 - t1) / 1_000_000) + " | RefMs=" + ((t4 - t3) / 1_000_000));
    }

    public int solution(int[] A, int[] B) {
        if (A.length != B.length) return 0;
        long sumA = Arrays.stream(A).asLongStream().sum();
        long sumB = Arrays.stream(B).asLongStream().sum();
        if (sumA != sumB || (sumA & 1L) == 1L) return 0;
        long half = sumA / 2L;
        long pa = 0, pb = 0;
        int n = A.length, ans = 0;
        for (int i = 0; i < n - 1; i++) {
            pa += A[i];
            pb += B[i];
            if (pa == half && pb == half) ans++;
        }
        return ans;
    }
}
