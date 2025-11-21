package com.interview.notes.code.year.y2025.april.amazon.test2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//WORKING 100%

public class PlayToWin {

    public static int getMaximumCount(List<Integer> arr, int k) {
        int n = arr.size();
        // count how many are already k
        int originalCount = (int) arr.stream().filter(x -> x == k).count();

        // prefix sum of k occurrences
        int[] prefixK = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixK[i + 1] = prefixK[i] + (arr.get(i) == k ? 1 : 0);
        }

        // map each value != k to list of positions
        Map<Integer, List<Integer>> positions = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int v = arr.get(i);
            if (v != k) {
                positions.computeIfAbsent(v, t -> new ArrayList<>()).add(i);
            }
        }

        int bestAdd = 0;
        // for each candidate value t, find best net gain
        for (List<Integer> pos : positions.values()) {
            int m = pos.size();
            int minA = Integer.MAX_VALUE;
            int bestLocal = 0;
            for (int j = 0; j < m; j++) {
                int pj = pos.get(j);
                int A = j - prefixK[pj];
                if (minA == Integer.MAX_VALUE) {
                    minA = A;
                }
                int curr = A + 1 - minA;
                if (curr > bestLocal) {
                    bestLocal = curr;
                }
                if (A < minA) {
                    minA = A;
                }
            }
            if (bestLocal > bestAdd) {
                bestAdd = bestLocal;
            }
        }

        return originalCount + bestAdd;
    }

    public static void main(String[] args) {
        class Test {
            final List<Integer> arr;
            final int k;
            final int expected;

            Test(Integer[] a, int k, int e) {
                this.arr = Arrays.asList(a);
                this.k = k;
                this.expected = e;
            }
        }

        List<Test> tests = Arrays.asList(
                new Test(new Integer[]{2, 5, 2, 5, 2}, 2, 4),
                new Test(new Integer[]{6, 4, 4, 6, 4, 4}, 6, 5),
                // edge: all already k
                new Test(new Integer[]{3, 3, 3}, 3, 3),
                // edge: no k, single value
                new Test(new Integer[]{1, 2, 3}, 5, 1),
                // mixed
                new Test(new Integer[]{1, 5, 1, 5, 1, 5, 1}, 6, 4)
        );

        int passed = 0;
        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            int result = getMaximumCount(t.arr, t.k);
            boolean ok = result == t.expected;
            System.out.printf("Test %d: %s (got=%d, expected=%d)%n",
                    i + 1, ok ? "PASS" : "FAIL", result, t.expected);
            if (ok) passed++;
        }
        System.out.printf("%d/%d tests passed.%n", passed, tests.size());

        // large data test
        int N = 200_000;
        List<Integer> large = IntStream.range(0, N)
                .map(i -> 1)
                .boxed()
                .collect(Collectors.toList());
        int largeResult = getMaximumCount(large, 2);
        System.out.printf("Large test: size=%d result=%d (expected=%d)%n",
                N, largeResult, N);
    }
}
