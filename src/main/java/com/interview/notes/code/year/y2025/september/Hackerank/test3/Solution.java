package com.interview.notes.code.year.y2025.september.Hackerank.test3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    public static long getMaximumScore(List<Integer> stockPrice) {
        Map<Long, Long> sumByKey = new HashMap<>();
        long best = Long.MIN_VALUE;
        for (int i = 0; i < stockPrice.size(); i++) {
            long key = (long) stockPrice.get(i) - i;          // same key ⇒ balanced
            long newSum = sumByKey.getOrDefault(key, 0L) + stockPrice.get(i);
            sumByKey.put(key, newSum);
            if (newSum > best) best = newSum;
        }
        return best;
    }

    // simple main with PASS/FAIL + large-data run
    public static void main(String[] args) {
        run(Arrays.asList(1, 2, 3), 6, "Sample0");
        run(Arrays.asList(3, 2, 1), 3, "Sample1");
        run(Arrays.asList(1, 5, 3, 7, 8), 20, "Example");
        run(List.of(5), 5, "Single");
        run(Arrays.asList(2, 1, 4, 3, 6), 12, "SkipDays");

        int n = 100_000;
        Random rnd = new Random(1);
        List<Integer> big = IntStream.range(0, n)
                .map(i -> 1 + rnd.nextInt(1_000_000_000))
                .boxed().collect(Collectors.toList());
        long t0 = System.currentTimeMillis();
        long res = getMaximumScore(big);
        System.out.println("Large Test: DONE | Result=" + res + " | Time=" + (System.currentTimeMillis() - t0) + "ms");
    }

    private static void run(List<Integer> arr, long expected, String name) {
        long got = getMaximumScore(arr);
        System.out.println(name + ": " + (got == expected ? "PASS" : "FAIL")
                + " | Expected=" + expected + ", Got=" + got);
    }
}