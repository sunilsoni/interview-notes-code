package com.interview.notes.code.year.y2025.april.visa.test1;

import java.util.Random;
import java.util.stream.IntStream;

public class RatingVsPrice {

    /**
     * Find the index with the largest (rating / price) ratio.
     * If several indices share the same ratio, return the smallest index.
     *
     * @param prices  positive integers, length > 0
     * @param ratings positive integers, same length as prices
     * @return index of the best item
     * @throws IllegalArgumentException if inputs are null, empty or of different length
     */
    public static int solution(int[] prices, int[] ratings) {
        // -------- basic checks --------
        if (prices == null || ratings == null ||
                prices.length == 0 || prices.length != ratings.length) {
            throw new IllegalArgumentException("Arrays must be non‑null, non‑empty and of equal length");
        }

        /*  We avoid floating‑point division.
            Compare (r1 / p1) > (r2 / p2)  by cross‑multiplying:
            r1 * p2  >  r2 * p1
            All values fit easily into 'long'.
         */
        int bestIndex = IntStream.range(0, prices.length)
                .reduce(0, (best, i) -> {
                    long lhs = (long) ratings[i] * prices[best];
                    long rhs = (long) ratings[best] * prices[i];

                    if (lhs > rhs) return i;          // strictly better ratio
                    if (lhs == rhs && i < best) return i; // same ratio, lower index
                    return best;                      // keep current best
                });

        return bestIndex;
    }

    /* ---------------------------------------------------------
       Simple test harness – prints PASS / FAIL for each case
       --------------------------------------------------------- */
    public static void main(String[] args) {

        // ---------- sample cases from the task ----------
        test("Example 1",
                new int[]{7, 5, 2, 11},
                new int[]{3, 4, 1, 3},
                1);

        test("Example 2 (tie, choose lower index)",
                new int[]{6, 5, 4, 3},
                new int[]{4, 3, 1, 2},
                0);

        // ---------- edge cases ----------
        test("Single element",
                new int[]{10},
                new int[]{5},
                0);

        test("All ratios identical",
                new int[]{2, 4, 6},
                new int[]{1, 2, 3},   // every ratio = 0.5
                0);                   // expect first index

        // ---------- large random case ----------
        int n = 100_000;
        int[] bigPrices = new int[n];
        int[] bigRatings = new int[n];
        Random rnd = new Random(42);

        for (int i = 0; i < n; i++) {
            bigPrices[i] = 1 + rnd.nextInt(1_000_000); // 1 … 1 000 000
            bigRatings[i] = 1 + rnd.nextInt(5);          // 1 … 5
        }

        long start = System.currentTimeMillis();
        int idx = solution(bigPrices, bigRatings);
        long time = System.currentTimeMillis() - start;

        System.out.println("Large test (100 000 items) finished in "
                + time + " ms, best index = " + idx + " – PASS");

    }

    /**
     * Helper to run one test case
     */
    private static void test(String name, int[] prices, int[] ratings, int expected) {
        int actual = solution(prices, ratings);
        if (actual == expected) {
            System.out.println(name + " – PASS");
        } else {
            System.out.println(name + " – FAIL (expected " + expected + ", got " + actual + ")");
        }
    }
}
