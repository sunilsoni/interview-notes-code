package com.interview.notes.code.year.y2025.october.common.test11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Solution {
    private static boolean verify(String R, int[] V, int a0, int b0) {
        long a = a0, b = b0;
        for (int i = 0; i < V.length; i++) {
            if (R.charAt(i) == 'A') { b -= V[i]; a += V[i]; }
            else { a -= V[i]; b += V[i]; }
            if (a < 0 || b < 0) return false;
        }
        return true;
    }

    private static String runCase(String R, int[] V, int[] expected) {
        Solution s = new Solution();
        long t1 = System.nanoTime();
        int[] out = s.solution(R, V);
        long t2 = System.nanoTime();
        boolean pass = Arrays.equals(out, expected);
        boolean ok = verify(R, V, out[0], out[1]);
        return "InputR=" + R + " | Output=" + Arrays.toString(out) + " | Expected=" + Arrays.toString(expected) +
               " | Verified=" + ok + " | TimeMs=" + ((t2 - t1)/1_000_000) + " | Result=" + (pass && ok ? "PASS" : "FAIL");
    }

    public static void main(String[] args) {
        List<String> results = new ArrayList<>();
        results.add(runCase("BAABA", new int[]{2,4,1,1,2}, new int[]{2,4}));
        results.add(runCase("ABAB", new int[]{10,5,10,15}, new int[]{0,15}));
        results.add(runCase("B", new int[]{100}, new int[]{100,0}));
        results.add(runCase("AAAA", new int[]{1,2,3,4}, new int[]{0,10}));
        results.add(runCase("BBBB", new int[]{1,2,3,4}, new int[]{10,0}));
        results.forEach(System.out::println);

        Random rnd = new Random(42);
        int n = 100_000;
        StringBuilder sb = new StringBuilder(n);
        int[] V = new int[n];
        IntStream.range(0, n).forEach(i -> {
            sb.append(rnd.nextBoolean() ? 'A' : 'B');
            V[i] = 1 + rnd.nextInt(10_000);
        });
        String R = sb.toString();
        Solution s = new Solution();
        long t1 = System.nanoTime();
        int[] out = s.solution(R, V);
        long t2 = System.nanoTime();
        boolean ok = verify(R, V, out[0], out[1]);
        System.out.println("Large data test | Output=" + Arrays.toString(out) + " | Verified=" + ok + " | TimeMs=" + ((t2 - t1)/1_000_000));
    }

    public int[] solution(String R, int[] V) {
        int n = V.length;
        long[] sums = new long[2];
        long[] mins = new long[]{0,0};
        IntStream.range(0, n).forEach(i -> {
            if (R.charAt(i) == 'A') {
                sums[0] += V[i];
                sums[1] -= V[i];
            } else {
                sums[0] -= V[i];
                sums[1] += V[i];
            }
            mins[0] = Math.min(mins[0], sums[0]);
            mins[1] = Math.min(mins[1], sums[1]);
        });
        int initA = (int)Math.max(0, -mins[0]);
        int initB = (int)Math.max(0, -mins[1]);
        return new int[]{initA, initB};
    }
}
