package com.interview.notes.code.year.y2025.october.common.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CountingPrimes {

    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static int countPrimes(int n, int m) {
        return (int) IntStream.rangeClosed(n, m)
                .filter(CountingPrimes::isPrime)
                .count();
    }

    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
                new TestCase(2, 10, 4),
                new TestCase(1, 5, 3),
                new TestCase(10, 20, 4),
                new TestCase(22, 29, 2),
                new TestCase(100, 120, 5),
                new TestCase(1, 1, 0),
                new TestCase(2, 2, 1)
        );

        tests.forEach(test -> {
            int result = countPrimes(test.n, test.m);
            System.out.println("Input: " + test.n + "," + test.m +
                    " | Expected: " + test.expected +
                    " | Got: " + result +
                    " | " + (result == test.expected ? "PASS" : "FAIL"));
        });

        int n = 1, m = 1000000;
        long start = System.currentTimeMillis();
        int result = countPrimes(n, m);
        long end = System.currentTimeMillis();
        System.out.println("Large Data Test [" + n + "," + m + "]: Completed in " + (end - start) + "ms, Result = " + result);
    }

    static class TestCase {
        int n, m, expected;

        TestCase(int n, int m, int expected) {
            this.n = n;
            this.m = m;
            this.expected = expected;
        }
    }
}