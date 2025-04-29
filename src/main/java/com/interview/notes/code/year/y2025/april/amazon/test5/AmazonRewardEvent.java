package com.interview.notes.code.year.y2025.april.amazon.test5;

import java.util.*;
import java.util.stream.*;

//WORKING 100%

public class AmazonRewardEvent {

    public static int countPossibleWinners(List<Integer> initialRewards) {
        int n = initialRewards.size();
        // find global max and its count
        int max1 = initialRewards.stream().mapToInt(Integer::intValue).max().orElse(0);
        long countMax = initialRewards.stream().filter(r -> r == max1).count();
        // find second max
        int secondMax = initialRewards.stream()
                                      .filter(r -> r < max1)
                                      .mapToInt(Integer::intValue)
                                      .max()
                                      .orElse(0);

        int winners = 0;
        for (int r : initialRewards) {
            int competitorMax = (r == max1 && countMax == 1) ? secondMax : max1;
            // tie counts as “having the highest”
            if (r + n >= competitorMax + (n - 1)) {
                winners++;
            }
        }
        return winners;
    }

    private static void runTest(List<Integer> rewards, int expected, String name) {
        int result = countPossibleWinners(rewards);
        System.out.println(name + ": " +
            (result == expected ? "PASS" : "FAIL") +
            " (expected=" + expected + ", actual=" + result + ")");
    }

    public static void main(String[] args) {
        runTest(Arrays.asList(1, 3, 4),    2, "Example1");  // see note below
        runTest(Arrays.asList(8, 10, 9),   2, "Example2");
        runTest(Arrays.asList(5, 7, 9, 11),1, "Example3");

        // performance check
        List<Integer> large = new Random()
            .ints(100_000, 1, 100_001)
            .boxed()
            .collect(Collectors.toList());
        long t0 = System.currentTimeMillis();
        int res = countPossibleWinners(large);
        long dt = System.currentTimeMillis() - t0;
        System.out.println("LargeTest: size=100000 time=" + dt + "ms result=" + res);
    }
}
