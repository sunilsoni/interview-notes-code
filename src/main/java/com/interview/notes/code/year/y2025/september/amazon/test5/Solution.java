package com.interview.notes.code.year.y2025.september.amazon.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Solution {
    public static List<Integer> getQueryAnswers(List<Integer> starts, List<Integer> ends, List<Integer> query_process, List<Integer> query_start, List<Integer> query_end) {
        int maxEnd = ends.stream().mapToInt(Integer::intValue).max().orElse(0);
        int maxQe = query_end.stream().mapToInt(Integer::intValue).max().orElse(0);
        int T = Math.max(maxEnd, maxQe);
        int[] diff = new int[T + 3];
        for (int i = 0; i < starts.size(); i++) {
            int s = starts.get(i);
            int e = ends.get(i);
            if (s <= T) {
                diff[s] += 1;
                if (e + 1 <= T) diff[e + 1] -= 1;
            }
        }
        int[] cc = new int[T + 1];
        int cur = 0, maxC = 0;
        for (int t = 0; t <= T; t++) {
            cur += diff[t];
            cc[t] = cur;
            if (cur > maxC) maxC = cur;
        }
        List<List<Integer>> pos = new ArrayList<>(maxC + 1);
        for (int i = 0; i <= maxC; i++) pos.add(new ArrayList<>());
        for (int t = 0; t <= T; t++) pos.get(cc[t]).add(t);
        List<Integer> ans = new ArrayList<>(query_process.size());
        for (int i = 0; i < query_process.size(); i++) {
            int k = query_process.get(i);
            int L = Math.max(0, query_start.get(i));
            int R = Math.min(T, query_end.get(i));
            if (L > R || k < 0 || k > maxC) {
                ans.add(0);
                continue;
            }
            List<Integer> list = pos.get(k);
            if (list.isEmpty()) {
                ans.add(0);
                continue;
            }
            int lo = lower(list, L);
            int hi = upper(list, R);
            ans.add(Math.max(0, hi - lo));
        }
        return ans;
    }

    private static int lower(List<Integer> a, int x) {
        int l = 0, r = a.size();
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a.get(m) >= x) r = m;
            else l = m + 1;
        }
        return l;
    }

    private static int upper(List<Integer> a, int x) {
        int l = 0, r = a.size();
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a.get(m) > x) r = m;
            else l = m + 1;
        }
        return l;
    }

    private static void runTest(String name, List<Integer> starts, List<Integer> ends, List<Integer> qp, List<Integer> qs, List<Integer> qe, List<Integer> expected) {
        List<Integer> out = getQueryAnswers(starts, ends, qp, qs, qe);
        boolean ok = out.equals(expected);
        System.out.println(name + ": " + (ok ? "PASS -> " + out : "FAIL -> Expected: " + expected + ", Got: " + out));
    }

    private static void largeDataTest() {
        int maxT = 100000;
        int n = 100000;
        int q = 100000;
        Random rnd = new Random(42);
        List<Integer> s = new ArrayList<>(n);
        List<Integer> e = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int st = rnd.nextInt(maxT + 1);
            int len = rnd.nextInt(50);
            int en = Math.min(maxT, st + len);
            s.add(st);
            e.add(en);
        }
        List<Integer> qs = new ArrayList<>(q);
        List<Integer> qe = new ArrayList<>(q);
        List<Integer> qp = new ArrayList<>(q);
        for (int i = 0; i < q; i++) {
            int a = rnd.nextInt(maxT + 1);
            int b = rnd.nextInt(maxT + 1);
            int L = Math.min(a, b);
            int R = Math.max(a, b);
            qs.add(L);
            qe.add(R);
            qp.add(rnd.nextInt(n + 1));
        }
        long t0 = System.nanoTime();
        List<Integer> out = getQueryAnswers(s, e, qp, qs, qe);
        long t1 = System.nanoTime();
        System.out.println("Large Data Test: Completed in " + ((t1 - t0) / 1_000_000) + "ms, Result size = " + out.size());
    }

    public static void main(String[] args) {
        runTest(
                "Sample Case 1",
                List.of(0),
                List.of(0),
                List.of(1),
                List.of(0),
                List.of(0),
                List.of(1)
        );

        runTest(
                "Sample Case 0",
                Arrays.asList(0, 4, 10),
                Arrays.asList(10, 6, 12),
                Arrays.asList(0, 1, 2),
                Arrays.asList(0, 4, 3),
                Arrays.asList(20, 10, 20),
                Arrays.asList(8, 3, 4)
        );

        runTest(
                "Example",
                Arrays.asList(0, 1, 2),
                Arrays.asList(2, 5, 10),
                Arrays.asList(1, 2),
                Arrays.asList(1, 4),
                Arrays.asList(10, 8),
                Arrays.asList(5, 2)
        );

        largeDataTest();
    }
}