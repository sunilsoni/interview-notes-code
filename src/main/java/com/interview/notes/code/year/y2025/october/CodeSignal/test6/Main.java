package com.interview.notes.code.year.y2025.october.CodeSignal.test6;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    static int solution(int[] prices, int[] ratings) {
        Comparator<Integer> cmp = (i, j) -> {
            int left = ratings[i] * prices[j];
            int right = ratings[j] * prices[i];
            if (left != right) return Integer.compare(left, right);
            return Integer.compare(j, i);
        };
        return IntStream.range(0, prices.length).boxed().max(cmp).get();
    }

    static int baseline(int[] prices, int[] ratings) {
        int best = 0;
        for (int i = 1; i < prices.length; i++) {
            int left = ratings[i] * prices[best];
            int right = ratings[best] * prices[i];
            if (left > right || (left == right && i < best)) best = i;
        }
        return best;
    }

    static void run(String name, int[] prices, int[] ratings, Integer expected) {
        int ans = solution(prices, ratings);
        boolean ok = expected == null ? ans == baseline(prices, ratings) : ans == expected;
        System.out.println(name + ": " + (ok ? "PASS" : "FAIL") + " -> " + ans);
    }

    static int[] arr(int... a) { return a; }

    public static void main(String[] args) {
        run("Ex1", arr(7, 5, 2, 11), arr(3, 4, 1, 3), 1);
        run("Ex2", arr(6, 5, 4, 3), arr(4, 3, 1, 2), 0);
        run("Single", arr(10), arr(5), 0);
        run("TieLowIndex", arr(2, 4), arr(1, 2), 0);
        run("RandomSmall", arr(1, 2, 3, 4, 5), arr(5, 4, 3, 2, 1), null);

        Random rng = new Random(1);
        int n = 100000;
        int[] prices = new int[n];
        int[] ratings = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = 1 + rng.nextInt(100);
            ratings[i] = 1 + rng.nextInt(5);
        }
        run("LargeRandom", prices, ratings, null);
    }
}
