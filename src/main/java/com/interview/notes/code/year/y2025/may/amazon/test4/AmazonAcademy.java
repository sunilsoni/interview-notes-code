package com.interview.notes.code.year.y2025.may.amazon.test4;

import java.io.*;
import java.util.*;

/**
 * Amazon Academy – minimum days to finish all chapters.
 * Uses a greedy O(n) sweep with a difference array.
 */
public class AmazonAcademy {

    /** Core routine -------------------------------------------------------- */
    public static long findMinimumDays(List<Integer> pages, int k, int p) {
        int n = pages.size();

        /* 1 — Convert pages → need (ceil division) */
        long[] need = new long[n];
        long maxNeed = 0;
        for (int i = 0; i < n; i++) {
            need[i] = (pages.get(i) + (long) p - 1) / p;   // ⟡ ceil(pages/p)
            if (need[i] > maxNeed) maxNeed = need[i];
        }

        /* 2 — Whole array read every day → answer = max need */
        if (k == n) return maxNeed;

        /* 3 — Greedy sweep with a difference array */
        long[] diff = new long[n + 1];          // diff[x] = windows that end at x
        long active = 0;                        // windows covering current index
        long days   = 0;                        // answer accumulator

        for (int i = 0; i < n; i++) {
            active += diff[i];                  // drop windows expiring here

            long deficit = need[i] - active;    // still missing for chapter i
            if (deficit <= 0) continue;         // already fully covered

            if (i > n - k)                      // no valid window can start here
                throw new IllegalStateException("Input unreachable – check data");

            /* Start ‘deficit’ new windows at position i */
            days       += deficit;              // count extra days
            active     += deficit;              // they cover the *current* index
            diff[i + k] -= deficit;             // ...and expire after i+k-1
        }
        return days;
    }

    /** --------------------------------------------------------------------- */
    /* Minimal reproducible example + quick sanity tests.                     */
    /* No JUnit – a plain main method prints PASS / FAIL per test case.       */
    /** --------------------------------------------------------------------- */
    public static void main(String[] args) throws Exception {

        /* If data arrives on stdin, solve that single instance and exit. */
        if (System.in.available() > 0) {
            try (Scanner sc = new Scanner(System.in)) {
                int n = sc.nextInt();
                List<Integer> pages = new ArrayList<>(n);
                for (int i = 0; i < n; i++) pages.add(sc.nextInt());
                int k = sc.nextInt();
                int p = sc.nextInt();
                System.out.println(findMinimumDays(pages, k, p));
            }
            return;
        }

        /* Otherwise run a tiny self-test suite -------------------------------- */
        class Case {
            final List<Integer> pages; final int k, p; final long expect;
            Case(int[] pg, int k, int p, long exp) {
                List<Integer> tmp = new ArrayList<>(pg.length);
                for (int v : pg) tmp.add(v);
                this.pages = tmp; this.k = k; this.p = p; this.expect = exp;
            }
        }

        List<Case> tests = Arrays.asList(
            /* sample 0 */ new Case(new int[]{3, 4},            1, 2, 4),
            /* sample 1 */ new Case(new int[]{5, 1, 2},         3, 3, 2),
            /* visual  */ new Case(new int[]{3, 1, 4},          2, 2, 4),
            /* k == n  */ new Case(new int[]{1, 2},             2, 1, 2),
            /* k == 1  */ new Case(new int[]{2, 2, 2, 2, 2},    1, 1, 10),
            /* big     */ new Case(new int[]{1_000_000_000},    1, 3, 333_333_334)
        );

        int pass = 0;
        for (int i = 0; i < tests.size(); i++) {
            Case c = tests.get(i);
            long got = findMinimumDays(c.pages, c.k, c.p);
            boolean ok = got == c.expect;
            System.out.printf("Case %-2d expected=%-10d got=%-10d  %s%n",
                              i, c.expect, got, ok ? "PASS" : "FAIL");
            if (ok) pass++;
        }
        System.out.printf("%nSummary: %d / %d cases passed%n", pass, tests.size());
    }
}
