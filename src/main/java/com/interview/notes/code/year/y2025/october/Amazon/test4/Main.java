package com.interview.notes.code.year.y2025.october.Amazon.test4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static List<Integer> getMaxArray(List<Integer> data_packets) {
        int n = data_packets.size();
        int[] freq = new int[n + 2];
        data_packets.forEach(v -> freq[v]++);
        TreeSet<Integer> zeros = new TreeSet<>();
        for (int i = 0; i <= n; i++) if (freq[i] == 0) zeros.add(i);
        List<Integer> res = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int t = zeros.first();
            if (t == 0) {
                int v = data_packets.get(i);
                if (--freq[v] == 0) zeros.add(v);
                res.add(0);
                i++;
            } else {
                boolean[] seen = new boolean[t];
                int need = t, j = i;
                while (j < n && need > 0) {
                    int v = data_packets.get(j);
                    if (v < t && !seen[v]) {
                        seen[v] = true;
                        need--;
                    }
                    j++;
                }
                res.add(t);
                for (int k = i; k < j; k++) {
                    int v = data_packets.get(k);
                    if (--freq[v] == 0) zeros.add(v);
                }
                i = j;
            }
        }
        return res;
    }

    private static List<Integer> brute(List<Integer> a) {
        int n = a.size();
        return bruteRec(a, 0, n);
    }

    private static List<Integer> bruteRec(List<Integer> a, int start, int n) {
        if (start >= n) return new ArrayList<>();
        List<Integer> best = null;
        for (int k = 1; k <= n - start; k++) {
            int mex = mex(a.subList(start, start + k));
            List<Integer> rest = bruteRec(a, start + k, n);
            List<Integer> cur = new ArrayList<>();
            cur.add(mex);
            cur.addAll(rest);
            if (best == null || cmp(cur, best) > 0) best = cur;
        }
        return best;
    }

    private static int mex(List<Integer> sub) {
        int m = sub.size() + 1;
        boolean[] seen = new boolean[m + 1];
        for (int v : sub) if (v <= m) seen[v] = true;
        for (int i = 0; i <= m; i++) if (!seen[i]) return i;
        return m + 1;
    }

    private static int cmp(List<Integer> x, List<Integer> y) {
        int L = Math.min(x.size(), y.size());
        for (int i = 0; i < L; i++) {
            int d = Integer.compare(x.get(i), y.get(i));
            if (d != 0) return d;
        }
        return Integer.compare(x.size(), y.size());
    }

    private static void runTest(String name, List<Integer> input, List<Integer> expected) {
        long t1 = System.nanoTime();
        List<Integer> out = getMaxArray(input);
        long t2 = System.nanoTime();
        String res = out.equals(expected) ? "PASS" : "FAIL";
        System.out.println("Test=" + name + " | Output=" + out + " | Expected=" + expected + " | TimeMs=" + ((t2 - t1) / 1_000_000) + " | Result=" + res);
    }

    private static void runBruteCheck(String name, List<Integer> input) {
        runTest(name, input, brute(input));
    }

    public static void main(String[] args) {
        runTest("Sample0", Arrays.asList(2, 2, 3, 4, 0, 1, 2, 0), Arrays.asList(5, 1));
        runTest("Sample1", Arrays.asList(0, 1, 2, 3, 4, 6), Arrays.asList(5, 0));
        runTest("Example", Arrays.asList(0, 1, 0, 1), Arrays.asList(2, 2));
        runTest("AllZero", Arrays.asList(0, 0, 0, 0), Arrays.asList(1, 1, 1, 1));
        runTest("NoZero", Arrays.asList(3, 3, 3), Arrays.asList(0, 0, 0));
        runTest("Mixed1", Arrays.asList(1, 0, 2, 2, 1, 0), List.of(3));
        runBruteCheck("BruteSmall1", Arrays.asList(1, 2, 0));
        runBruteCheck("BruteSmall2", Arrays.asList(2, 0, 1, 2));
        runBruteCheck("BruteSmall3", Arrays.asList(0, 2, 1, 0));

        int n = 100000;
        Random rnd = new Random(7);
        List<Integer> big = IntStream.range(0, n).map(i -> rnd.nextInt(n + 1)).boxed().collect(Collectors.toList());
        long t1 = System.nanoTime();
        List<Integer> out = getMaxArray(big);
        long t2 = System.nanoTime();
        System.out.println("Large data test | N=" + n + " | ResultLen=" + out.size() + " | TimeMs=" + ((t2 - t1) / 1_000_000) + " | Result=COMPLETED");
    }
}
