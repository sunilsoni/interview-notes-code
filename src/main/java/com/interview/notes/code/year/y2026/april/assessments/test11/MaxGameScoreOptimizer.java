package com.interview.notes.code.year.y2026.april.assessments.test11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class MaxGameScoreOptimizer {

    private static final int[] PRIMES = IntStream.rangeClosed(3, 10000)
            .filter(i -> i % 10 == 3 && BigInteger.valueOf(i).isProbablePrime(50))
            .toArray();

    public static int maxGameScore(List<Integer> cell) {
        int n = cell.size();
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE / 2);
        dp[0] = 0;

        for (int i = 0; i < n - 1; i++) {
            if (dp[i] == Integer.MIN_VALUE / 2) {
                continue;
            }
            dp[i + 1] = Math.max(dp[i + 1], dp[i] + cell.get(i + 1));
            for (int p : PRIMES) {
                if (i + p >= n) {
                    break;
                }
                dp[i + p] = Math.max(dp[i + p], dp[i] + cell.get(i + p));
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        runTest(List.of(0, -10, -20, -30, 50), 40);
        runTest(List.of(0, -10, 100, -20), 70);
        runTest(List.of(0, -100, -100, -1, 0, -1), -2);

        List<Integer> largeInput = new ArrayList<>(Collections.nCopies(10000, 1));
        largeInput.set(0, 0);
        runTest(largeInput, 9999);
    }

    private static void runTest(List<Integer> input, int expected) {
        System.out.println(maxGameScore(input) == expected ? "PASS" : "FAIL");
    }
}