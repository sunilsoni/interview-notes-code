package com.interview.notes.code.year.y2025.april.amazon.test5;

import java.util.*;
import java.util.stream.*;

public class Solution {
    public static int countPossibleWinners(List<Integer> initialRewards) {
        int n = initialRewards.size();
        int max = initialRewards.stream().mapToInt(i -> i).max().orElse(0);
        long countMax = initialRewards.stream().filter(i -> i == max).count();
        int secondMax = initialRewards.stream()
                                      .mapToInt(i -> i)
                                      .filter(i -> i < max)
                                      .max()
                                      .orElse(Integer.MIN_VALUE);

        int result = 0;
        for (int x : initialRewards) {
            // When x<max or there are multiple maxes, the strongest rival has 'max' initial.
            // Otherwise (x is the unique max), the strongest rival has 'secondMax'.
            int rivalInit = (x < max || countMax > 1) ? max : secondMax;
            // Check: x + n >= rivalInit + (n - 1)
            if (x >= rivalInit - 1) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        runTest(Arrays.asList(1, 3, 4), 1, "Example1");
        runTest(Arrays.asList(8, 10, 9), 2, "Example2");
        runTest(Arrays.asList(5, 7, 9, 11), 1, "Example3");

        // Large-input sanity check: n=100_000, all same rewards → every one ties for highest
        int largeN = 100_000;
        List<Integer> large = Collections.nCopies(largeN, 1);
        runTest(large, largeN, "LargeTest");
    }

    private static void runTest(List<Integer> input, int expected, String name) {
        int got = countPossibleWinners(input);
        System.out.println(name + ": " +
            (got == expected ? "PASS" : "FAIL") +
            " (got=" + got + ", expected=" + expected + ")");
    }
}
