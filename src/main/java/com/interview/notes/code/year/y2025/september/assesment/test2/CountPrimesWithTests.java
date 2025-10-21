package com.interview.notes.code.year.y2025.september.assesment.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountPrimesWithTests {

    public static int countPrimes(int n) { // returns number of primes less than n
        if (n <= 2) return 0; // no primes below 2
        boolean[] isPrime = new boolean[n]; // boolean sieve array
        Arrays.fill(isPrime, true); // assume all numbers prime initially
        isPrime[0] = false; // 0 is not prime
        if (n > 1) isPrime[1] = false; // 1 is not prime
        int limit = (int) Math.sqrt(n - 1); // upper bound for marking composites
        for (int i = 2; i <= limit; i++) { // iterate potential prime factors
            if (!isPrime[i]) continue; // skip if already marked composite
            int step = i;
            int start = i * i; // start marking from i^2
            for (int j = start; j < n; j += step) { // mark multiples as composite
                isPrime[j] = false;
            }
        }
        int count = 0; // counter for primes
        for (int i = 2; i < n; i++) { // count primes < n
            if (isPrime[i]) count++;
        }
        return count; // return result
    }

    public static void main(String[] args) {
        List<TestCase> tests = new ArrayList<>(); // collect tests

        tests.add(new TestCase("example-n-10", 10, 4)); // primes <10 are 2,3,5,7 -> 4
        tests.add(new TestCase("example-n-0", 0, 0)); // none
        tests.add(new TestCase("example-n-1", 1, 0)); // none
        tests.add(new TestCase("n-2", 2, 0)); // none below 2
        tests.add(new TestCase("n-3", 3, 1)); // prime 2 only
        tests.add(new TestCase("n-100", 100, 25)); // known: 25 primes <100
        tests.add(new TestCase("large-5e6-performance", 5_000_000, null)); // performance/scale test (no fixed expected)
        tests.add(new TestCase("very-large-1e7-performance", 10_000_000, null)); // larger stress (if environment allows)

        List<String> results = tests.stream().map(tc -> {
            long start = System.currentTimeMillis();
            int count;
            try {
                count = countPrimes(tc.n);
            } catch (OutOfMemoryError e) {
                long duration = System.currentTimeMillis() - start;
                return String.format("%s: FAIL (n=%d, timeMs=%d) - OOM", tc.name, tc.n, duration);
            } catch (Throwable t) {
                long duration = System.currentTimeMillis() - start;
                return String.format("%s: FAIL (n=%d, timeMs=%d) - %s", tc.name, tc.n, duration, t.getMessage());
            }
            long duration = System.currentTimeMillis() - start;
            if (tc.expected != null) {
                boolean pass = (count == tc.expected);
                return String.format("%s: %s (n=%d, expected=%d, actual=%d, timeMs=%d)",
                        tc.name, pass ? "PASS" : "FAIL", tc.n, tc.expected, count, duration);
            } else {
                boolean pass = count > 0; // for performance tests just ensure result is reasonable (>0 for n>2)
                return String.format("%s: %s (n=%d, actual=%d, timeMs=%d)",
                        tc.name, pass ? "PASS" : "FAIL", tc.n, count, duration);
            }
        }).collect(Collectors.toList());

        results.forEach(System.out::println);
        long passCount = results.stream().filter(s -> s.contains("PASS")).count();
        System.out.printf("Summary: Passed %d out of %d tests%n", passCount, tests.size());
    }

    /**
     * @param expected nullable expected value; if null treat as performance check
     */
    record TestCase(String name, int n, Integer expected) { // simple test container
    }
}