package com.interview.notes.code.year.y2026.feb.HackerRank.test4;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class PrimeJumpGameMaximizer {

    static int maxGameScore(List<Integer> cell) {
        int n = cell.size();
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        List<Integer> jumps = IntStream.rangeClosed(3, n)
                .filter(PrimeJumpGameMaximizer::isPrimeEnding3)
                .boxed()
                .toList();
        for (int i = 1; i < n; i++) {
            int best = dp[i - 1];
            for (int p : jumps)
                if (i - p >= 0)
                    best = Math.max(best, dp[i - p]);
            if (best != Integer.MIN_VALUE)
                dp[i] = best + cell.get(i);
        }
        return dp[n - 1];
    }

    static boolean isPrimeEnding3(int x) {
        if (x % 10 != 3 || x < 3) return false;
        if (x == 3) return true;
        if (x % 2 == 0) return false;
        for (int i = 3; i * i <= x; i += 2)
            if (x % i == 0) return false;
        return true;
    }

    static void check(String name, List<Integer> input, int expected) {
        int result = maxGameScore(input);
        System.out.println(name + ": " + (result == expected ? "PASS" : "FAIL -> " + result));
    }

    public static void main(String[] args) {
        check("Sample0", List.of(0, -10, 100, -20), 70);
        check("Sample1", List.of(0, -100, -100, -1, 0, -1), -2);
        check("Example", List.of(0, -10, -20, -30, 50), 40);

        int n = 10000;
        List<Integer> large = new Random(1).ints(n, -10000, 10001).boxed().toList();
        //large = IntStream.range(0, n).mapToObj(i -> i == 0 ? 0 : large.get(i)).toList();
      //  int res = maxGameScore(large);
       // System.out.println("LargeCase: " + (res <= Integer.MAX_VALUE ? "PASS" : "FAIL"));
    }
}
