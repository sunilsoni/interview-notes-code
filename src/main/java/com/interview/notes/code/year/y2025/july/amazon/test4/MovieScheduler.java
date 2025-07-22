package com.interview.notes.code.year.y2025.july.amazon.test4;

import java.util.*;
import java.util.stream.*;

public class MovieScheduler {
    static boolean canFinish(int n, int[][] pre) {
        List<List<Integer>> g = IntStream.range(0, n)
                                         .mapToObj(i -> new ArrayList<Integer>())
                                         .collect(Collectors.toList());
        int[] in = new int[n];
        for (int[] p : pre) { g.get(p[1]).add(p[0]); in[p[0]]++; }
        Queue<Integer> q = new ArrayDeque<>();
        IntStream.range(0, n).filter(i -> in[i] == 0).forEach(q::add);
        int seen = 0;
        while (!q.isEmpty()) {
            int u = q.poll(); seen++;
            for (int v : g.get(u)) if (--in[v] == 0) q.add(v);
        }
        return seen == n;
    }

    public static void main(String[] args) {
        int n5 = 10_000;
        int[][]
            t1 = {{1,0},{2,1},{3,0}},
            t2 = {{1,0},{0,1}},
            t3 = {},
            t4 = {{0,0}},
            t5 = IntStream.range(1, n5)
                          .mapToObj(i -> new int[]{i, i - 1})
                          .toArray(int[][]::new),
            t6 = Stream.concat(Arrays.stream(t5),
                               Stream.of(new int[]{0, n5 - 1}))
                       .toArray(int[][]::new);

        int[]       ns  = {4, 2, 5, 1, n5, n5};
        int[][][]   ms  = {t1, t2, t3, t4, t5, t6};
        boolean[]   exp = {true, false, true, false, true, false};

        for (int i = 0; i < ns.length; i++) {
            System.out.printf("Test%d %s%n",
                i + 1,
                canFinish(ns[i], ms[i]) == exp[i] ? "PASS" : "FAIL"
            );
        }
    }
}