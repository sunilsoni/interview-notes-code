package com.interview.notes.code.year.y2026.march.snowflake.test1;

import java.util.stream.Stream;

public class StringPatternCombinations {

    public static int calculateWays(int wordLen, int maxVowels) {
        var dp = new long[maxVowels + 1];
        dp[0] = 1;
        long sum = 1;
        long mod = 1000000007;

        for (var i = 0; i < wordLen; i++) {
            var nextDp = new long[maxVowels + 1];
            nextDp[0] = (sum * 21) % mod;
            sum = nextDp[0];

            for (var j = 1; j <= maxVowels; j++) {
                nextDp[j] = (dp[j - 1] * 5) % mod;
                sum = (sum + nextDp[j]) % mod;
            }
            dp = nextDp;
        }

        return (int) sum;
    }

    public static void main(String[] args) {
        record TestCase(int w, int m, int e) {}

        Stream.of(
            new TestCase(2, 1, 651),
            new TestCase(2, 2, 676),
            new TestCase(2, 0, 441),
            new TestCase(4, 1, 412776),
            new TestCase(4, 2, 451101),
            new TestCase(1, 1, 26)
        ).forEach(t -> {
            if (calculateWays(t.w(), t.m()) == t.e()) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            }
        });

        long start = System.currentTimeMillis();
        calculateWays(2500, 2500);
        if ((System.currentTimeMillis() - start) < 1000) {
            System.out.println("PASS_LARGE_DATA");
        } else {
            System.out.println("FAIL_LARGE_DATA");
        }
    }
}