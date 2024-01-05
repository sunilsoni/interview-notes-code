package com.interview.notes.code.months.dec23.test5;

public class FibonacciSeries {
    public static void main(String[] args) {
        int n = 10; // Change this value to generate Fibonacci series up to a different number.

        System.out.println("Fibonacci Series up to " + n + " terms:");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
