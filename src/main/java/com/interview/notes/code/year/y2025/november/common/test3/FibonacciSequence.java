package com.interview.notes.code.year.y2025.november.common.test3;

import java.util.stream.Stream;

public class FibonacciSequence {

    // Generate n Fibonacci numbers
    public static void printFibonacci(int n) {
        if (n <= 0) {
            System.out.println("Invalid input. Enter a positive number.");
            return;
        }

        Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
                .limit(n)
                .map(f -> f[0])
                .forEach(System.out::println);
    }

    // Main method for testing
    public static void main(String[] args) {
        int n = 10; // number of terms
        System.out.println("Fibonacci sequence up to " + n + " terms:");
        printFibonacci(n);

        // Simple PASS/FAIL test for large data check
        long[] fib10 = Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
                             .limit(10)
                             .mapToLong(f -> f[0])
                             .toArray();

        long expectedLast = 34; // 10th term (0-indexed sequence)
        if (fib10[fib10.length - 1] == expectedLast) {
            System.out.println("PASS ✅ Last term = " + expectedLast);
        } else {
            System.out.println("FAIL ❌ Expected " + expectedLast + " but got " + fib10[fib10.length - 1]);
        }
    }
}
