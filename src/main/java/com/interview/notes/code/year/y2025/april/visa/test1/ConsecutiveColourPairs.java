package com.interview.notes.code.year.y2025.april.visa.test1;

import java.util.*;
import java.util.stream.IntStream;

//WORKING 100%
public class ConsecutiveColourPairs {

    /**
     * Returns an array where result[i] is the number of equal‑colour
     * neighbouring pairs AFTER processing queries[i].
     */
    public static int[] solution(int length, int[][] queries) {
        // coordinate -> colour (only painted positions are stored)
        Map<Integer, Integer> colourAt = new HashMap<>(queries.length * 2);

        int pairs = 0;                     // running answer
        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int x   = queries[i][0];
            int col = queries[i][1];

            int old = colourAt.getOrDefault(x, 0);
            if (old == col) {              // no change – quick exit
                answer[i] = pairs;
                continue;
            }

            // ─── 1. remove old pairs that involved x ───────────────
            if (old != 0) {
                if (old == colourAt.getOrDefault(x - 1, 0)) pairs--;
                if (old == colourAt.getOrDefault(x + 1, 0)) pairs--;
            }

            // ─── 2. apply new colour ──────────────────────────────
            colourAt.put(x, col);          // col is guaranteed > 0

            // ─── 3. add new pairs that now involve x ──────────────
            if (col == colourAt.getOrDefault(x - 1, 0)) pairs++;
            if (col == colourAt.getOrDefault(x + 1, 0)) pairs++;

            answer[i] = pairs;             // record for this query
        }
        return answer;
    }

    /* -------------------------------------------------------------
       Simple test harness – NO JUnit, just run `main`.
       ------------------------------------------------------------- */
    public static void main(String[] args) {

        /* Official sample */
        test("Sample",
             7,
             new int[][]{{1,2},{0,2},{3,5},{3,2},{2,2},{6,1},{1,3}},
             new int[]   {0,   1,   1,   1,   3,   3,   1});

        /* Fill from the edges inward */
        test("Edges‑fill",
             5,
             new int[][]{{0,1},{4,1},{2,1},{3,1},{1,1}},
             new int[]   {0,   0,   1,   2,   4});

        /* Re‑painting with the same colour should not change the answer */
        test("Re‑paint same",
             3,
             new int[][]{{1,5},{1,5},{1,5}},
             new int[]   {0,   0,   0});

        /* Large random stress – verifies performance, not correctness */
        largeRandomStress();
    }

    /* ---------- helper methods ---------- */

    private static void test(String name, int len, int[][] q, int[] exp) {
        int[] out = solution(len, q);
        boolean ok = Arrays.equals(out, exp);
        System.out.println(name + ": " + (ok ? "PASS" : "FAIL"));
        if (!ok) {
            System.out.println(" expected " + Arrays.toString(exp));
            System.out.println(" got      " + Arrays.toString(out));
        }
    }

    private static void largeRandomStress() {
        final int LENGTH   = 1_000_000_000;
        final int Q        = 100_000;
        final Random rnd   = new Random(42);

        int[][] big = new int[Q][2];
        for (int i = 0; i < Q; i++) {
            big[i][0] = rnd.nextInt(LENGTH);          // coordinate
            big[i][1] = rnd.nextInt(1_000_000_000)+1; // colour ≥1
        }

        long t0 = System.currentTimeMillis();
        int[] res = solution(LENGTH, big);
        long t1 = System.currentTimeMillis();

        System.out.println("Large random: " + res.length +
                           " queries processed in " + (t1 - t0) + " ms");
    }
}
