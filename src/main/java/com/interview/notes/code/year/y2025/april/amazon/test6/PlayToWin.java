package com.interview.notes.code.year.y2025.april.amazon.test6;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayToWin {

    public static int getMaximumCount(List<Integer> arr, int k) {
        int n = arr.size();

        // frequency map
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : arr) {
            freq.put(v, freq.getOrDefault(v, 0) + 1);
        }
        int originalK = freq.getOrDefault(k, 0);

        int threshold = (int) Math.sqrt(n);
        List<Integer> heavy = new ArrayList<>();
        List<Integer> light = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int v = e.getKey(), cnt = e.getValue();
            if (v == k) continue;
            if (cnt > threshold) heavy.add(v);
            else light.add(v);
        }

        int maxDelta = 0;
        // heavy values: Kadane over +1 for v, -1 for k
        for (int v : heavy) {
            int currentSum = 0;
            for (int x : arr) {
                if (x == v) currentSum += 1;
                else if (x == k) currentSum -= 1;
                if (currentSum < 0) currentSum = 0;
                maxDelta = Math.max(maxDelta, currentSum);
            }
        }

        // prefix sums of k
        int[] prefixK = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixK[i + 1] = prefixK[i] + (arr.get(i) == k ? 1 : 0);
        }

        // light values: brute over pairs of occurrences
        for (int v : light) {
            List<Integer> pos = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (arr.get(i) == v) pos.add(i);
            }
            int m = pos.size();
            for (int i = 0; i < m; i++) {
                for (int j = i; j < m; j++) {
                    int left = pos.get(i), right = pos.get(j);
                    int vCount = j - i + 1;
                    int kCount = prefixK[right + 1] - prefixK[left];
                    maxDelta = Math.max(maxDelta, vCount - kCount);
                }
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

        // simple extra
        runTest("AllEqualK", Arrays.asList(1, 1, 1), 1, 3);

        // large-data case
        int n = 200_000;
        int kVal = 100;
        List<Integer> largeArr = IntStream.range(0, n)
                .map(i -> kVal - 1)
                .boxed()
                .collect(Collectors.toList());
        runTest("LargeAllKminus1", largeArr, kVal, n);
    }
}
