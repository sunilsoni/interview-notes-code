package com.interview.notes.code.year.y2025.september.assesment.test1;

import java.util.*;
import java.util.stream.*;

public class CountPrimesLessThanN {
    static int countPrimes(int n) {
        if (n <= 2) return 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; (long) i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) isPrime[j] = false;
            }
        }
        return (int) IntStream.range(2, n).filter(i -> isPrime[i]).count();
    }

    static int countPrimesSegmented(int n) {
        if (n <= 2) return 0;
        int limit = (int) Math.sqrt(n - 1) + 1;
        boolean[] base = new boolean[limit + 1];
        Arrays.fill(base, true);
        base[0] = false; base[1] = false;
        for (int i = 2; (long) i * i <= limit; i++) if (base[i]) for (int j = i * i; j <= limit; j += i) base[j] = false;
        int[] primes = IntStream.rangeClosed(2, limit).filter(i -> base[i]).toArray();

        int seg = 1 << 16;
        int count = 0;
        for (int low = 2; low < n; low += seg) {
            int high = Math.min(low + seg, n);
            boolean[] mark = new boolean[high - low];
            Arrays.fill(mark, true);
            for (int p : primes) {
                long start = Math.max((long) p * p, ((low + p - 1L) / p) * (long) p);
                if (start >= high) continue;
                for (long j = start; j < high; j += p) mark[(int) (j - low)] = false;
            }
            if (low == 2) mark[0] = true;
            for (boolean m : mark) if (m) count++;
        }
        return count;
    }

    static boolean runExact(String name, int n, int expected) {
        int got = countPrimes(n);
        boolean ok = got == expected;
        System.out.println(name + ": " + (ok ? "PASS" : "FAIL") + " | expected=" + expected + " got=" + got);
        return ok;
    }

    static boolean runCrossCheck(String name, int n) {
        long t1 = System.nanoTime();
        int a = countPrimes(n);
        long t2 = System.nanoTime();
        int b = countPrimesSegmented(n);
        long t3 = System.nanoTime();
        boolean ok = a == b;
        System.out.println(name + ": " + (ok ? "PASS" : "FAIL") + " | sieve=" + a + " segmented=" + b +
                " | t1(ms)=" + ((t2 - t1) / 1_000_000) + " t2(ms)=" + ((t3 - t2) / 1_000_000));
        return ok;
    }

    static boolean runSmallStreamCheck(String name, int n) {
        int expected = (int) IntStream.range(2, n)
                .filter(x -> IntStream.rangeClosed(2, (int) Math.sqrt(x)).allMatch(d -> x % d != 0))
                .count();
        int got = countPrimes(n);
        boolean ok = expected == got;
        System.out.println(name + ": " + (ok ? "PASS" : "FAIL") + " | expected=" + expected + " got=" + got);
        return ok;
    }

    public static void main(String[] args) {
        runExact("Example1", 10, 4);
        runExact("Example2", 0, 0);
        runExact("Example3", 1, 0);
        runExact("Edge_2", 2, 0);
        runExact("Edge_3", 3, 1);
        runExact("n=100", 100, 25);
        runExact("n=1000", 1000, 168);
        runExact("n=10000", 10000, 1229);
        runExact("n=100000", 100000, 9592);
        runExact("n=1000000", 1000000, 78498);
        runSmallStreamCheck("SmallStreamCrossCheck_n=20000", 20000);

        runCrossCheck("LargeCrossCheck_n=5000000", 5_000_000);
        runCrossCheck("LargeCrossCheck_n=10000000", 10_000_000);
    }
}
