package com.interview.notes.code.year.y2026.april.microsoft.test3;

import java.util.stream.Stream;

public class FactorialReciprocalDivisorsSolver {

    public static int ArithmeticEquation(int N) {
        long result = 1;
        var isPrime = new boolean[N + 1];
        
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }
        
        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                long count = 0;
                long temp = N;
                while (temp > 0) {
                    count += temp / i;
                    temp /= i;
                }
                result = (result * (2 * count + 1)) % 1000007;
            }
        }
        
        return (int) result;
    }

    public static void main(String[] args) {
        Stream.of(
            new TestCase(1, 1),
            new TestCase(2, 3),
            new TestCase(32327, 656502)
        ).forEach(tc -> {
            var actual = ArithmeticEquation(tc.input());
            var status = actual == tc.expected() ? "PASS" : "FAIL";
            System.out.printf("Input: %-7d | Status: %-4s | Expected: %-7d | Actual: %-7d%n",
                tc.input(), status, tc.expected(), actual);
        });

        var startTime = System.nanoTime();
        var largeResult = ArithmeticEquation(1000000);
        var durationMs = (System.nanoTime() - startTime) / 1_000_000.0;

        System.out.printf("Input: %-7d | Status: DONE | Actual: %-9d | Time: %.2f ms%n",
            1000000, largeResult, durationMs);
    }

    record TestCase(int input, int expected) {}
}