package com.interview.notes.code.year.y2025.april.amazon.test5;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayToWin {

    public static int getMaximumCount(List<Integer> arr, int k) {
        int n = arr.size();

        // count original k's and build prefix sum of k's
        int originalK = 0;
        int[] prefixK = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (arr.get(i) == k) originalK++;
            prefixK[i + 1] = prefixK[i] + (arr.get(i) == k ? 1 : 0);
        }

        // positions of each value v != k
        Map<Integer, List<Integer>> posMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int v = arr.get(i);
            if (v == k) continue;
            posMap.computeIfAbsent(v, __ -> new ArrayList<>()).add(i);
        }

        int maxDelta = 0;
        // for each value v, compute best net gain = (#v in subarray) - (#k in same)
        for (List<Integer> pos : posMap.values()) {
            int m = pos.size();
            int minY = 0;
            int t = 0;
            // process t=0
            {
                int p = pos.get(0);
                int Y = -prefixK[p];
                int X = 1 - prefixK[p + 1];
                minY = Y;
                maxDelta = Math.max(maxDelta, X - Y);
            }
            // for t>0
            for (t = 1; t < m; t++) {
                int p = pos.get(t);
                int X = (t + 1) - prefixK[p + 1];
                maxDelta = Math.max(maxDelta, X - minY);
                int Y = t - prefixK[p];
                if (Y < minY) minY = Y;
            }
        }

        return originalK + maxDelta;
    }

    private static void runTest(String name, List<Integer> arr, int k, int expected) {
        int res = getMaximumCount(arr, k);
        System.out.println(name + ": " + (res == expected
                ? "PASS"
                : "FAIL (expected=" + expected + ", got=" + res + ")"));
    }

    public static void main(String[] args) {
        // provided examples
        runTest("Example1", Arrays.asList(2, 5, 2, 5, 2), 2, 4);
        runTest("Example2", Arrays.asList(6, 4, 4, 6, 4, 4), 6, 5);

        // extra simple
        runTest("AllEqualK", Arrays.asList(1, 1, 1), 1, 3);
        runTest("NoChange", Arrays.asList(1, 2, 3), 5, 0);

        // mixed
        runTest("Mixed1", Arrays.asList(3, 5, 3, 5, 3, 5, 3), 5, 4);

        // large-data case
        int n = 200_000;
        int kVal = 100;
        List<Integer> largeArr = IntStream.range(0, n)
                .map(i -> kVal - 1)
                .boxed()
                .collect(Collectors.toList());
        // here each element can be turned to k in one big subarray
        runTest("LargeAllKminus1", largeArr, kVal, n);
    }
}
