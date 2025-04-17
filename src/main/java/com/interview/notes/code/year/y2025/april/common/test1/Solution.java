package com.interview.notes.code.year.y2025.april.common.test1;

import java.util.*;
import java.util.stream.*;
//WORKING 100%
public class Solution {

    public static int bestSumDownwardTreePath(List<Integer> parent, List<Integer> values) {
        int n = parent.size();
        List<List<Integer>> children = IntStream.range(0, n)
            .mapToObj(i -> new ArrayList<Integer>())
            .collect(Collectors.toList());

        IntStream.range(0, n).forEach(i -> {
            int p = parent.get(i);
            if (p >= 0) children.get(p).add(i);
        });

        int[] deg = children.stream().mapToInt(List::size).toArray();
        long[] best = new long[n];
        long[] maxChild = new long[n];
        Arrays.fill(maxChild, Long.MIN_VALUE);

        Deque<Integer> q = new ArrayDeque<>();
        IntStream.range(0, n).filter(i -> deg[i] == 0).forEach(q::add);

        long res = Long.MIN_VALUE;
        while (!q.isEmpty()) {
            int u = q.poll();
            long m = (maxChild[u] == Long.MIN_VALUE ? 0 : Math.max(0, maxChild[u]));
            best[u] = values.get(u) + m;
            res = Math.max(res, best[u]);
            int p = parent.get(u);
            if (p >= 0) {
                maxChild[p] = Math.max(maxChild[p], best[u]);
                if (--deg[p] == 0) q.add(p);
            }
        }
        return (int) res;
    }

    public static void main(String[] args) {
        class Test {
            List<Integer> p, v;
            int e;
            String d;
            Test(List<Integer> p, List<Integer> v, int e, String d) { this.p = p; this.v = v; this.e = e; this.d = d; }
        }

        List<Test> tests = Arrays.asList(
            new Test(Arrays.asList(-1, 0, 1, 2, 0), Arrays.asList(-2, 10, 10, -3, 10), 20, "Sample"),
            new Test(Collections.singletonList(-1), Collections.singletonList(5), 5, "Single"),
            new Test(Arrays.asList(-1, 0, 0, 1, 1, 2), Arrays.asList(-5, -2, -3, -1, -4, -6), -1, "AllNegative"),
            new Test(Arrays.asList(-1, 0, 1, 2, 3), Arrays.asList(1, 2, 3, 4, 5), 15, "Chain"),
            new Test(
                IntStream.range(0, 100000).map(i -> i - 1).boxed().collect(Collectors.toList()),
                Collections.nCopies(100000, 1),
                100000, "Large")
        );

        tests.stream().forEach(t -> {
            int got = bestSumDownwardTreePath(t.p, t.v);
            System.out.println((got == t.e ? "PASS" : "FAIL") + ": " + t.d + " expected=" + t.e + " got=" + got);
        });
    }
}