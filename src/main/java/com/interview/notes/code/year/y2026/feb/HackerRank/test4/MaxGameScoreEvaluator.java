package com.interview.notes.code.year.y2026.feb.HackerRank.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MaxGameScoreEvaluator {

    public static int maxGameScore(List<Integer> cell) {
        int n = cell.size();
        int[] dp = new int[n];
        Arrays.fill(dp, -1000000000);
        dp[0] = cell.getFirst();

        List<Integer> primes = IntStream.iterate(3, i -> i <= n, i -> i + 10)
                .filter(MaxGameScoreEvaluator::isPrime)
                .boxed()
                .toList();

        for (int i = 0; i < n; i++) {
            if (dp[i] != -1000000000) {
                if (i + 1 < n) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[i] + cell.get(i + 1));
                }
                for (int p : primes) {
                    if (i + p < n) {
                        dp[i + p] = Math.max(dp[i + p], dp[i] + cell.get(i + p));
                    } else {
                        break;
                    }
                }
            }
        }
        return dp[n - 1];
    }

    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        runTest(List.of(0, -10, -20, -30, 50), 40, "Sample Case Example");
        runTest(List.of(0, -10, 100, -20), 70, "Sample Case 0");
        runTest(List.of(0, -100, -100, -1, 0, -1), -2, "Sample Case 1");

        List<Integer> largeDataTest = new ArrayList<>();
        largeDataTest.add(0);
        for (int i = 1; i < 10000; i++) {
            largeDataTest.add(1);
        }
        runTest(largeDataTest, 9999, "Large Data Case");
    }

    private static void runTest(List<Integer> input, int expected, String testName) {
        int result = maxGameScore(input);
        if (result == expected) {
            System.out.println(testName + " : PASS");
        } else {
            System.out.println(testName + " : FAIL (Expected " + expected + ", Got " + result + ")");
        }
    }
}