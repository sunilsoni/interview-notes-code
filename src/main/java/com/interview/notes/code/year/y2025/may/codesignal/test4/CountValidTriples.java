package com.interview.notes.code.year.y2025.may.codesignal.test4;

import java.util.*;
import java.util.stream.*;

public class CountValidTriples {

    // ---------- core algorithm ----------
    // O(1) per query, O(Q) total, Q ≤ 1e5
    public static int[] solution(String[] queries, int diff) {
        Map<Long, Long> freq = new HashMap<>(); // value → multiplicity
        long triples = 0;                       // current triple count
        int[] ans = new int[queries.length];
        long d = diff;                          // promote to long once

        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            char op = q.charAt(0);
            long v = Long.parseLong(q.substring(1));

            if (op == '+') {                             // ---------------- add v
                long a = freq.getOrDefault(v - d, 0L);
                long b = freq.getOrDefault(v - 2*d, 0L);
                long c = freq.getOrDefault(v + d, 0L);
                long e = freq.getOrDefault(v + 2*d, 0L);

                triples += a*b;          // v as x
                triples += c*a;          // v as y
                triples += e*c;          // v as z

                freq.merge(v, 1L, Long::sum);
            } else {                                     // -------------- remove v
                long k = freq.getOrDefault(v, 0L);
                if (k > 0) {
                    long a = freq.getOrDefault(v - d, 0L);
                    long b = freq.getOrDefault(v - 2*d, 0L);
                    long c = freq.getOrDefault(v + d, 0L);
                    long e = freq.getOrDefault(v + 2*d, 0L);

                    long gone = k*a*b + k*c*a + k*e*c;   // all triples holding v
                    triples -= gone;
                    freq.remove(v);
                }
            }
            ans[i] = (int) triples;       // fits typical interview limits
        }
        return ans;
    }

    // ---------- simple test harness ----------
    public static void main(String[] args) {
        List<TestCase> tests = new ArrayList<>();

        // 1. sample from problem statement
        tests.add(new TestCase(
            new String[]{"+4","+5","+6","+4","+3","-4"},
            1,
            new int[]{0,0,1,2,4,0},
            "Sample scenario"));

        // 2. increasing progression diff = 2
        tests.add(new TestCase(
            new String[]{"+10","+12","+14","+16"},
            2,
            new int[]{0,0,0,1},
            "Simple progression"));

        // 3. duplicates create many triples
        tests.add(new TestCase(
            new String[]{"+1","+3","+5","+1","+3","+5"},
            2,
            new int[]{0,0,1,2,5,10},
            "Duplicates"));

        // 4. add then remove everything
        tests.add(new TestCase(
            new String[]{"+7","+6","+5","-6","-7","-5"},
            1,
            new int[]{0,0,1,0,0,0},
            "Add-remove all"));

        // 5. large random test – just proves it runs fast
        int BIG = 50000;
        String[] big = IntStream.range(0, BIG)
                                .mapToObj(i -> "+" + (i % 100_000))
                                .toArray(String[]::new);
        tests.add(new TestCase(big, 3, null, "Stress x50 000"));

        // run everything
        tests.forEach(CountValidTriples::run);
    }

    private static void run(TestCase t) {
        long start = System.currentTimeMillis();
        int[] out = solution(t.queries, t.diff);
        long ms = System.currentTimeMillis() - start;

        boolean ok = (t.expected == null) ? (out.length == t.queries.length)
                                          : Arrays.equals(out, t.expected);

        System.out.printf("%-20s : %s (%d ms)%n",
                          t.name, ok ? "PASS" : "FAIL", ms);
        if (!ok && t.expected != null) {
            System.out.println("expected " + Arrays.toString(t.expected));
            System.out.println("got      " + Arrays.toString(out));
        }
    }

    // ---------- tiny holder ----------
    private static class TestCase {
        String[] queries; int diff; int[] expected; String name;
        TestCase(String[] q,int d,int[] e,String n){queries=q;diff=d;expected=e;name=n;}
    }
}
