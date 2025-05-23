package com.interview.notes.code.year.y2025.may.amazon.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinimizeSystemCost {
    public static long minimizeSystemCost(int k, List<Integer> machines) {
        int n = machines.size();
        // If only 0 or 1 machine remains, cost is 0
        if (n - k <= 1) return 0;

        // 1. diff[i] = |a[i+1] - a[i]|
        long[] diff = IntStream.range(0, n - 1)
                .mapToLong(i -> Math.abs(machines.get(i + 1) - machines.get(i)))
                .toArray();

        // 2. totalCost
        long totalCost = Arrays.stream(diff).sum();

        // 3. prefix sums of diff
        long[] prefix = new long[diff.length + 1];
        for (int i = 0; i < diff.length; i++) {
            prefix[i + 1] = prefix[i] + diff[i];
        }

        // 4. slide window of size k
        long minCost = Long.MAX_VALUE;
        for (int l = 0; l <= n - k; l++) {
            int r = l + k - 1;
            long removedCost = prefix[r] - prefix[l];
            long newCost = totalCost - removedCost;
            // bridge gap if both sides exist
            if (l > 0 && r < n - 1) {
                newCost += Math.abs(
                        machines.get(r + 1) - machines.get(l - 1)
                );
            }
            minCost = Math.min(minCost, newCost);
        }
        return minCost;
    }

    public static void main(String[] args) {
        class Test {
            int k;
            List<Integer> machines;
            long expected;

            Test(int k, List<Integer> m, long e) {
                this.k = k;
                machines = m;
                expected = e;
            }
        }

        List<Test> tests = Arrays.asList(
                new Test(2, Arrays.asList(3, 7, 1, 11, 8), 5),
                new Test(3, Arrays.asList(3, 2, 6, 1), 0),
                // additional edge cases
                new Test(1, Arrays.asList(5, 10), 0),   // remove one of two → one remains
                new Test(2, Arrays.asList(1, 3, 6), 0),   // remove middle two → one remains
                new Test(3, Arrays.asList(1, 2, 3, 4), 0)    // remove 3 → one remains
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            long result = minimizeSystemCost(t.k, t.machines);
            String status = (result == t.expected) ? "PASS" : "FAIL";
            System.out.printf("Test %d: got=%d expected=%d → %s%n",
                    i, result, t.expected, status);
        }

        // Simple large-data check (n=200_000, k=100_000, ascending list)
        int n = 200_000, k = 100_000;
        List<Integer> large = IntStream.rangeClosed(1, n)
                .boxed().collect(Collectors.toList());
        long start = System.currentTimeMillis();
        long res = minimizeSystemCost(k, large);
        long time = System.currentTimeMillis() - start;
        System.out.printf("Large test: result=%d (computed in %d ms)%n", res, time);
    }
}