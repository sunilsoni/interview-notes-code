package com.interview.notes.code.year.y2025.June.common.test9;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    /**
     * On buying one item you get another (≤ price) for free.
     * Return the minimum total amount to acquire all N items.
     */
    public static int offerTime(int N, int[] A) {
        // 1) sort ascending
        int[] sorted = Arrays.stream(A)
                .sorted()
                .toArray();

        // 2) pay for every other from the end
        int cost = 0;
        for (int i = N - 1; i >= 0; i -= 2) {
            cost += sorted[i];
        }
        return cost;
    }

    //----------------------------------------------------------------
    // simple tests (PASS/FAIL) + large-N sanity check
    //----------------------------------------------------------------
    public static void main(String[] args) {
        class Test {
            final int[] in;
            final int want;

            Test(int[] i, int w) {
                in = i;
                want = w;
            }
        }

        List<Test> tests = Arrays.asList(
                new Test(new int[]{8, 2, 2, 9, 1, 9}, 19),
                new Test(new int[]{1, 2, 3}, 4),  // pay 3 + 1
                new Test(new int[]{5}, 5),  // single item
                new Test(new int[]{2, 2, 2, 2}, 4),  // pay two of them
                new Test(new int[]{9, 1, 9, 1, 9}, 19)  // pay 9 + 9 + 1
        );

        boolean allPass = true;
        for (Test t : tests) {
            int got = offerTime(t.in.length, t.in);
            if (got == t.want) {
                System.out.printf("PASS: %s → %d%n",
                        Arrays.toString(t.in), got);
            } else {
                System.out.printf("FAIL: %s → got %d, want %d%n",
                        Arrays.toString(t.in), got, t.want);
                allPass = false;
            }
        }
        System.out.println(allPass
                ? "All small tests passed."
                : "Some small tests failed.");

        // large-N check (random data, N=100 000)
        int N = 100_000;
        Random rnd = new Random(123);
        int[] big = rnd.ints(N, 1, 10_001).toArray();

        long t0 = System.currentTimeMillis();
        int res = offerTime(N, big);
        long t1 = System.currentTimeMillis();
        System.out.printf("Random N=%d → cost=%d (in %d ms)%n",
                N, res, (t1 - t0));
    }
}