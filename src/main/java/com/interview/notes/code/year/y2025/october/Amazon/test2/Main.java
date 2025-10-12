package com.interview.notes.code.year.y2025.october.Amazon.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static final long MOD = 1_000_000_007L;

    public static int getMinErrors(String s, int x, int y) {
        int n = s.length();
        int[] p0 = new int[n + 1], p1 = new int[n + 1], pq = new int[n + 1];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            p0[i + 1] = p0[i] + (c == '0' ? 1 : 0);
            p1[i + 1] = p1[i] + (c == '1' ? 1 : 0);
            pq[i + 1] = pq[i] + (c == '!' ? 1 : 0);
        }
        int tot0 = p0[n], tot1 = p1[n], totQ = pq[n];

        long base = 0, z = 0, o = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '0') { base += (long) y * o; z++; }
            else if (c == '1') { base += (long) x * z; o++; }
        }

        long[] prefC0 = new long[n + 1], prefC1 = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefC0[i + 1] = prefC0[i];
            prefC1[i + 1] = prefC1[i];
            if (s.charAt(i) == '!') {
                int b0 = p0[i], b1 = p1[i];
                int a0 = tot0 - p0[i], a1 = tot1 - p1[i];
                long c0 = (long) y * b1 + (long) x * a1;
                long c1 = (long) x * b0 + (long) y * a0;
                prefC0[i + 1] += c0;
                prefC1[i + 1] += c1;
            }
        }

        long ans = Long.MAX_VALUE;
        if (x <= y) {
            for (int t = 0; t <= n; t++) {
                long leftQ = pq[t], rightQ = totQ - leftQ;
                long cost = base + prefC0[t] + (prefC1[n] - prefC1[t]) + (long) x * leftQ * rightQ;
                if (cost < ans) ans = cost;
            }
        } else {
            for (int t = 0; t <= n; t++) {
                long leftQ = pq[t], rightQ = totQ - leftQ;
                long cost = base + prefC1[t] + (prefC0[n] - prefC0[t]) + (long) y * leftQ * rightQ;
                if (cost < ans) ans = cost;
            }
        }
        return (int) (ans % MOD);
    }

    private static long brute(String s, int x, int y) {
        List<Integer> qs = new ArrayList<>();
        char[] a = s.toCharArray();
        for (int i = 0; i < a.length; i++) if (a[i] == '!') qs.add(i);
        long best = Long.MAX_VALUE;
        int m = qs.size();
        for (int mask = 0; mask < (1 << m); mask++) {
            for (int i = 0; i < m; i++) a[qs.get(i)] = ((mask >> i) & 1) == 1 ? '1' : '0';
            long zeros = 0, ones = 0, cost = 0;
            for (char c : a) {
                if (c == '0') { cost += (long) y * ones; zeros++; }
                else { cost += (long) x * zeros; ones++; }
            }
            best = Math.min(best, cost);
        }
        return best % MOD;
    }

    private static String randString(int n, Random r) {
        char[] cs = new char[n];
        for (int i = 0; i < n; i++) {
            int v = r.nextInt(3);
            cs[i] = v == 0 ? '0' : v == 1 ? '1' : '!';
        }
        return new String(cs);
    }

    public static void main(String[] args) {
        String s1 = "0!1!1!";
        int x1 = 2, y1 = 3, e1 = 10;
        System.out.println(getMinErrors(s1, x1, y1) == e1 ? "PASS" : "FAIL");

        String s2 = "!!!!!!!";
        int x2 = 23, y2 = 47, e2 = 0;
        System.out.println(getMinErrors(s2, x2, y2) == e2 ? "PASS" : "FAIL");

        String s3 = "01";
        int x3 = 5, y3 = 9, e3 = 5;
        System.out.println(getMinErrors(s3, x3, y3) == e3 ? "PASS" : "FAIL");

        boolean ok = true;
        Random r = new Random(1);
        for (int t = 0; t < 60; t++) {
            String s = randString(18, r);
            int x = 1 + r.nextInt(50), y = 1 + r.nextInt(50);
            long fast = getMinErrors(s, x, y);
            long brute = brute(s, x, y);
            if (fast != brute) { ok = false; break; }
        }
        System.out.println(ok ? "PASS" : "FAIL");

        int N = 100000;
        String big = IntStream.range(0, N).mapToObj(i -> r.nextInt(3) == 0 ? "0" : r.nextInt(2) == 0 ? "1" : "!").collect(Collectors.joining());
        long res = getMinErrors(big, 100000, 100000);
        System.out.println("Large input test passed: " + (res >= 0));
    }
}