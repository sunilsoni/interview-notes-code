package com.interview.notes.code.year.y2026.jan.common.test1;

import java.util.stream.IntStream;

public class SmallestMissingPositive {
    // line: method to find smallest missing positive; we keep it short and clear
    public static int find(int[] a) {
        // line: n is length; answer lies in [1..n+1]
        int n = a.length;
        // line: boolean present[i] means value i exists; size n+2 to allow index n+1
        boolean[] present = new boolean[n + 2];
        // line: mark only values in range [1..n+1] to keep work O(n)
        for (int v : a) if (v > 0 && v <= n + 1) present[v] = true;
        // line: scan from 1; first missing is answer
        for (int i = 1; i <= n + 1; i++) if (!present[i]) return i;
        // line: fallback; not reached because loop returns; return 1 to satisfy compiler
        return 1;
    }

    // line: main tests all cases; prints PASS/FAIL
    public static void main(String[] args) {
        // line: helper to run one test; prints case name, expected, got, PASS/FAIL
        var test = new Object() {
            void run(String name, int[] a, int expected) {
                int got = find(a);
                System.out.println(name + " | expected=" + expected + " got=" + got + " | " + (got == expected ? "PASS" : "FAIL"));
            }
        };

        // line: given tests from prompt
        test.run("case1", new int[]{1, 3, 6, 4, 1, 2}, 5);      // line: missing 5
        test.run("case2", new int[]{1, 2, 3}, 4);               // line: next after 3
        test.run("case3", new int[]{-1, -3}, 1);                // line: all negative → 1

        // line: edge: empty array → 1
        test.run("empty", new int[]{}, 1);

        // line: edge: duplicates and zeros → still find 2
        test.run("dupsZeros", new int[]{0, 0, 1, 1, 1}, 2);

        // line: edge: large values out of range → ignore; answer 1
        test.run("largeValues", new int[]{1000, 2000, -5}, 1);

        // line: edge: continuous 1..n → answer n+1
        test.run("contiguous", new int[]{1, 2, 3, 4, 5}, 6);

        // line: large data: n=1_000_000, array contains 1..n except missing m
        int n = 1_000_000;
        int missing = 123_456;
        // line: build array [1..n] but skip 'missing'; use streams for clarity
        int[] large = IntStream.rangeClosed(1, n)
                .filter(x -> x != missing)
                .toArray();
        // line: test large input performance and correctness
        test.run("largeMissing", large, missing);

        // line: large data: all 1..n present → answer n+1
        int[] largeFull = IntStream.rangeClosed(1, n).toArray();
        test.run("largeFull", largeFull, n + 1);
    }
}
