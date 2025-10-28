package com.interview.notes.code.year.y2025.october.common.test12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    private static String runCase(int N, int K, int expected) {
        Solution s = new Solution();
        long t1 = System.nanoTime();
        int out = s.solution(N, K);
        long t2 = System.nanoTime();
        return "N=" + N + " K=" + K + " | Output=" + out + " | Expected=" + expected + " | TimeMs=" + ((t2 - t1) / 1_000_000) + " | Result=" + (out == expected ? "PASS" : "FAIL");
    }

    private static int exactMinGlassesDP(int N, int K) {
        int INF = 1_000_000_000;
        int[] dp = new int[K + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int s = K; s >= i; s--) {
                if (dp[s - i] != INF) dp[s] = Math.min(dp[s], dp[s - i] + 1);
            }
        }
        return dp[K] == INF ? -1 : dp[K];
    }

    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        lines.add(runCase(5, 8, 2));
        lines.add(runCase(4, 10, 4));
        lines.add(runCase(1, 2, -1));
        lines.add(runCase(10, 5, 1));
        lines.add(runCase(1_000_000, 1_000_000, 1));
        lines.forEach(System.out::println);

        Random rnd = new Random(123);
        List<String> randomResults = IntStream.range(0, 50).mapToObj(t -> {
            int N = 1 + rnd.nextInt(30);
            int K = 1 + rnd.nextInt(N * (N + 1) / 2 + 5);
            Solution s = new Solution();
            int out = s.solution(N, K);
            int exp = K > N * (N + 1) / 2 ? -1 : exactMinGlassesDP(N, Math.min(K, N * (N + 1) / 2));
            return "N=" + N + " K=" + K + " | Output=" + out + " | Expected=" + exp + " | Result=" + (out == exp ? "PASS" : "FAIL");
        }).collect(Collectors.toList());
        randomResults.forEach(System.out::println);

        int N = 1_000_000;
        long sum = (long) N * (N + 1) / 2;
        int K = (int) (sum % Integer.MAX_VALUE);
        long t1 = System.nanoTime();
        int out = new Solution().solution(N, K);
        long t2 = System.nanoTime();
        System.out.println("Large data test | N=" + N + " K=" + K + " | Output=" + out + " | TimeMs=" + ((t2 - t1) / 1_000_000));
    }

    public int solution(int N, int K) {
        long sum = (long) N * (N + 1) / 2;
        if (K > sum) return -1;
        int cnt = 0;
        int i = (int) Math.min(N, (long) K);
        while (K > 0 && i > 0) {
            if (i <= K) {
                K -= i;
                cnt++;
            }
            i--;
        }
        return K == 0 ? cnt : -1;
    }
}
