package com.interview.notes.code.year.y2025.october.common;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public static int solve(int N, int K, int S, List<Integer> A, List<Integer> B) {
        int[][] items = IntStream.range(0, N)
                .mapToObj(i -> new int[]{A.get(i), B.get(i)})
                .sorted(Comparator.comparingInt(o -> o[0]))
                .toArray(int[][]::new);
        int ans = 0;
        for (int l = 0; l < N; l++) {
            PriorityQueue<Integer> top = new PriorityQueue<>(S);
            int sum = 0;
            for (int r = l; r < N; r++) {
                if (items[r][0] - items[l][0] > K) break;
                int v = items[r][1];
                if (top.size() < S) {
                    top.add(v);
                    sum += v;
                } else if (v > top.peek()) {
                    sum += v - top.poll();
                    top.add(v);
                }
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    private static int solveSlow(int N, int K, int S, List<Integer> A, List<Integer> B) {
        int[][] items = IntStream.range(0, N)
                .mapToObj(i -> new int[]{A.get(i), B.get(i)})
                .sorted(Comparator.comparingInt(o -> o[0]))
                .toArray(int[][]::new);
        int ans = 0;
        for (int l = 0; l < N; l++) {
            List<Integer> vals = new ArrayList<>();
            for (int r = l; r < N; r++) {
                if (items[r][0] - items[l][0] > K) break;
                vals.add(items[r][1]);
            }
            vals.sort(Comparator.reverseOrder());
            int take = Math.min(S, vals.size());
            int sum = 0;
            for (int i = 0; i < take; i++) sum += vals.get(i);
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    private static List<Integer> toList(int[] a) {
        return Arrays.stream(a).boxed().collect(Collectors.toList());
    }

    private static void runCase(int N, int K, int S, int[] AA, int[] BB, int expected) {
        int out = solve(N, K, S, toList(AA), toList(BB));
        String res = out == expected ? "PASS" : "FAIL";
        System.out.println("Input=N=" + N + ",K=" + K + ",S=" + S + ",A=" + Arrays.toString(AA) + ",B=" + Arrays.toString(BB) + " | Output=" + out + " | Expected=" + expected + " | Result=" + res);
    }

    public static void main(String[] args) {
        System.out.println("Running Test Cases...");
        runCase(5, 3, 2, new int[]{1, 12, 6, 10, 15}, new int[]{4, 8, 11, 1, 5}, 13);
        runCase(4, 3, 2, new int[]{8, 2, 6, 11}, new int[]{8, 12, 1, 2}, 12);
        runCase(5, 2, 3, new int[]{1, 0, 1, 1, 0}, new int[]{3, 5, 1, 8, 2}, 16);

        Random rnd = new Random(42);
        int N = 500;
        int[] A = IntStream.range(0, N).map(i -> rnd.nextInt(100000)).toArray();
        int[] B = IntStream.range(0, N).map(i -> rnd.nextInt(100000)).toArray();
        int K = 5000 + rnd.nextInt(100000);
        int S = 1 + rnd.nextInt(N);
        long t1 = System.nanoTime();
        int fast = solve(N, K, S, toList(A), toList(B));
        long t2 = System.nanoTime();
        int slow = solveSlow(N, K, S, toList(A), toList(B));
        long t3 = System.nanoTime();
        String res = fast == slow ? "PASS" : "FAIL";
        System.out.println("Large data test executed in " + ((t2 - t1) / 1_000_000) + " ms | CrossCheck=" + ((t3 - t2) / 1_000_000) + " ms | Result=" + res + " | Value=" + fast);

        for (int tc = 0; tc < 50; tc++) {
            int n = 1 + rnd.nextInt(15);
            int[] a = IntStream.range(0, n).map(i -> rnd.nextInt(20)).toArray();
            int[] b = IntStream.range(0, n).map(i -> rnd.nextInt(50)).toArray();
            int k = rnd.nextInt(10);
            int s = 1 + rnd.nextInt(n);
            int o1 = solve(n, k, s, toList(a), toList(b));
            int o2 = solveSlow(n, k, s, toList(a), toList(b));
            if (o1 != o2) {
                System.out.println("Random test FAIL: n=" + n + ",k=" + k + ",s=" + s + ",o1=" + o1 + ",o2=" + o2);
                return;
            }
        }
        System.out.println("Random correctness tests: PASS");
    }
}