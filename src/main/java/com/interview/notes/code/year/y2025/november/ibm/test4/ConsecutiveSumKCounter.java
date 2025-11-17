package com.interview.notes.code.year.y2025.november.ibm.test4;

import java.util.List;

public class ConsecutiveSumKCounter {

    public static long getKCount(long s) {
        long n = s;
        while ((n & 1L) == 0L) {
            n >>= 1;
        }
        long ans = 1;
        for (long p = 3; p * p <= n; p += 2) {
            if (n % p == 0L) {
                int e = 0;
                while (n % p == 0L) {
                    n /= p;
                    e++;
                }
                ans *= (e + 1L);
            }
        }
        if (n > 1L) {
            ans <<= 1;
        }
        return ans;
    }

    private static long slowCount(long s) {
        long c = 0;
        for (long k = 1; k <= s; k++) {
            long sum = 0;
            for (long x = k; sum < s; x++) {
                sum += x;
                if (sum == s) {
                    c++;
                    break;
                }
            }
        }
        return c;
    }

    public static void main(String[] args) {
        record TestCase(long s) {}
        List<TestCase> tests = List.of(
                new TestCase(5L),
                new TestCase(10L),
                new TestCase(125L),
                new TestCase(1L),
                new TestCase(2L),
                new TestCase(15L),
                new TestCase(100L)
        );

        tests.stream().forEach(t -> {
            long expected = slowCount(t.s());
            long got = getKCount(t.s());
            String status = got == expected ? "PASS" : "FAIL";
            System.out.println("s=" + t.s() + " Expected=" + expected + " Got=" + got + " -> " + status);
        });

        long large = 1_000_000_000_000L;
        System.out.println("LargeInput s=" + large + " Result=" + getKCount(large));
    }
}
