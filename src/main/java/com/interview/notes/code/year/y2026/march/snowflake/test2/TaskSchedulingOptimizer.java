package com.interview.notes.code.year.y2026.march.snowflake.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TaskSchedulingOptimizer {

    public static int getMinCost(List<Integer> cost, List<Integer> time) {
        var n = cost.size();
        var dp = new int[n + 1];
        Arrays.fill(dp, 1_000_000_000);
        dp[0] = 0;
        
        for (var i = 0; i < n; i++) {
            var c = cost.get(i);
            var w = time.get(i) + 1;
            for (var j = n; j >= 0; j--) {
                if (dp[j] != 1_000_000_000) {
                    var nx = Math.min(n, j + w);
                    dp[nx] = Math.min(dp[nx], dp[j] + c);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        test(List.of(1, 2, 3, 2), List.of(1, 2, 3, 2), 3);
        test(List.of(2, 3, 4, 2), List.of(1, 1, 1, 1), 4);
        
        var largeCost = IntStream.range(0, 1000).mapToObj(i -> 1_000_000).toList();
        var largeTime = IntStream.range(0, 1000).mapToObj(i -> 1).toList();
        test(largeCost, largeTime, 500_000_000);
    }

    private static void test(List<Integer> cost, List<Integer> time, int expected) {
        System.out.println(getMinCost(cost, time) == expected ? "PASS" : "FAIL");
    }
}