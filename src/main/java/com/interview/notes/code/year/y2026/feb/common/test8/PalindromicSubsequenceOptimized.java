package com.interview.notes.code.year.y2026.feb.common.test8;

import java.util.stream.IntStream;

public class PalindromicSubsequenceOptimized {

    private static final int MOD = 1000000007;

    public static int numSubseq(int N, String S) {
        long k = S.chars().distinct().count();
        return (int) ((k + 1) * pow(2, N - k) % MOD);
    }

    private static long pow(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        runTestCase(1, 2, "bb", 4);
        runTestCase(2, 3, "bab", 6);
        runTestCase(3, 5, "aaccb", 16);

        int largeN = 100000;
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, largeN).forEach(i -> sb.append((char) ('a' + (i % 3))));
        long distinct = 3; 
        int expectedLarge = (int) ((distinct + 1) * pow(2, largeN - distinct) % MOD);
        runTestCase(4, largeN, sb.toString(), expectedLarge);
    }

    private static void runTestCase(int id, int n, String s, int expected) {
        try {
            long start = System.nanoTime();
            int actual = numSubseq(n, s);
            long end = System.nanoTime();
            String result = (actual == expected) ? "PASS" : "FAIL";
            System.out.printf("Test Case %d: %s | Input N: %d | Expected: %d | Actual: %d | Time: %.3f ms%n",
                    id, result, n, expected, actual, (end - start) / 1e6);
        } catch (Exception e) {
            System.out.printf("Test Case %d: FAIL (Exception: %s)%n", id, e.getMessage());
        }
    }
}