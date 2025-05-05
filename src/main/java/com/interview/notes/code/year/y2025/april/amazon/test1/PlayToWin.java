package com.interview.notes.code.year.y2025.april.amazon.test1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayToWin {

    public static int getMaximumCount(List<Integer> arr, int k) {
        int n = arr.size();

        // Count original k's and build prefix sum of k's
        int originalK = 0;
        int[] prefixK = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (arr.get(i) == k) originalK++;
            prefixK[i + 1] = prefixK[i] + (arr.get(i) == k ? 1 : 0);
        }

        // Map each value != k to its list of positions
        Map<Integer, List<Integer>> posMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int v = arr.get(i);
            if (v == k) continue;
            posMap.computeIfAbsent(v, x -> new ArrayList<>()).add(i);
        }

        int maxDelta = 0;
        // For each value's positions, compute best subarray gain
        for (List<Integer> pos : posMap.values()) {
            int m = pos.size();
            // skip singletons to avoid length-1 subarray gains
            if (m < 2) continue;
            int minY;
            int p0 = pos.get(0);
            minY = 0 - prefixK[p0];
            maxDelta = Math.max(maxDelta, (1 - prefixK[p0 + 1]) - minY);
            for (int t = 1; t < m; t++) {
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
        runTest("Example1", Arrays.asList(2, 5, 2, 5, 2), 2, 4);
        runTest("Example2", Arrays.asList(6, 4, 4, 6, 4, 4), 6, 5);
        runTest("AllEqualK", Arrays.asList(1, 1, 1), 1, 3);
        runTest("NoChange", Arrays.asList(1, 2, 3), 5, 0);
        runTest("Mixed1", Arrays.asList(3, 5, 3, 5, 3, 5, 3), 5, 4);

        // large data test
        int n = 200_000, kVal = 100;
        List<Integer> largeArr = IntStream.range(0, n)
                .map(i -> kVal - 1)
                .boxed()
                .collect(Collectors.toList());
        runTest("LargeAllKminus1", largeArr, kVal, n);
    }
}
