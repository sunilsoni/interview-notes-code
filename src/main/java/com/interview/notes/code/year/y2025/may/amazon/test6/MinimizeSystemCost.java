package com.interview.notes.code.year.y2025.may.amazon.test6;

import java.util.*;
import java.util.stream.*;

//WORKING 100%
public class MinimizeSystemCost {
    public static long minimizeSystemCost(int k, List<Integer> machines) {
        int n = machines.size();
        if (n - k <= 1) return 0;

        // 1. Compute diffs between adjacent machines
        long[] diff = IntStream.range(0, n - 1)
            .mapToLong(i -> Math.abs(machines.get(i + 1) - machines.get(i)))
            .toArray();

        // 2. Total initial cost
        long totalCost = Arrays.stream(diff).sum();

        // 3. Prefix sums of diffs for O(1) range sums
        long[] prefix = new long[diff.length + 1];
        for (int i = 0; i < diff.length; i++) {
            prefix[i + 1] = prefix[i] + diff[i];
        }

        // 4. Slide removal window [l..r]
        long minCost = Long.MAX_VALUE;
        for (int l = 0; l <= n - k; l++) {
            int r = l + k - 1;
            // sum of diffs inside the block
            long removed = prefix[r] - prefix[l];
            // also remove the two boundary diffs if they exist
            if (l > 0)        removed += diff[l - 1];
            if (r < n - 1)    removed += diff[r];
            long cost = totalCost - removed;
            // add new “bridge” if both sides remain
            if (l > 0 && r < n - 1) {
                cost += Math.abs(
                    machines.get(r + 1) - machines.get(l - 1)
                );
            }
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    public static void main(String[] args) {
        class Test {
            int k; List<Integer> m; long exp;
            Test(int k, List<Integer> m, long e) { this.k = k; this.m = m; this.exp = e; }
        }

        List<Test> tests = Arrays.asList(
            new Test(2, Arrays.asList(3,7,1,11,8), 5),
            new Test(3, Arrays.asList(3,2,6,1),     0),
            new Test(1, Arrays.asList(5,10),        0),
            new Test(2, Arrays.asList(1,3,6),       0),
            new Test(3, Arrays.asList(1,2,3,4),     0)
        );

        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            long res = minimizeSystemCost(t.k, t.m);
            String ok = (res == t.exp) ? "PASS" : "FAIL";
            System.out.printf("Test %d: got=%d expected=%d → %s%n",
                i, res, t.exp, ok);
        }

        // Large-data sanity check
        int n = 200_000, k = 100_000;
        List<Integer> large = IntStream.rangeClosed(1, n)
                                       .boxed().collect(Collectors.toList());
        long start = System.currentTimeMillis();
        long out = minimizeSystemCost(k, large);
        long dt = System.currentTimeMillis() - start;
        System.out.printf("Large test: result=%d (in %d ms)%n", out, dt);
    }
}