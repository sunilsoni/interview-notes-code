package com.interview.notes.code.year.y2025.november.ibm.test2;

import java.util.List;
import java.util.stream.IntStream;

public class SumWaysCalculator {

    static final int MOD = 1_000_000_007;

    public static int ways(int total, int k) {
        int[] dp = new int[total + 1];
        dp[0] = 1;
        for (int w = 1; w <= k; w++) {
            for (int t = w; t <= total; t++) {
                dp[t] += dp[t - w];
                if (dp[t] >= MOD) {
                    dp[t] -= MOD;
                }
            }
        }
        return dp[total];
    }

    public static void main(String[] args) {
        record TestCase(int total, int k, int expected) {
        }
        List<TestCase> tests = List.of(
                new TestCase(4, 2, 3),
                new TestCase(5, 3, 5),
                new TestCase(8, 2, 5),
                new TestCase(1, 1, 1),
                new TestCase(10, 3, 14),
                new TestCase(20, 5, 192),
                new TestCase(1000, 50, 831713219),
                new TestCase(1000, 100, 222372164)
        );

        IntStream.range(0, tests.size()).forEach(i -> {
            var t = tests.get(i);
            int result = ways(t.total, t.k);
            String status = result == t.expected ? "PASS" : "FAIL";
            System.out.println(
                    "Test#" + (i + 1) +
                            " Input=(" + t.total + "," + t.k + ")" +
                            " Expected=" + t.expected +
                            " Got=" + result +
                            " -> " + status
            );
        });

        int largeTotal = 1000;
        int largeK = 100;
        System.out.println("LargeInputOnlyResult total=" + largeTotal + " k=" + largeK + " result=" + ways(largeTotal, largeK));
    }
}
