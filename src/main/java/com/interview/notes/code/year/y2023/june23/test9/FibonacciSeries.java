package com.interview.notes.code.year.y2023.june23.test9;

public class FibonacciSeries {
    public static void main(String[] args) {
        int n = 10; // The number of Fibonacci numbers to generate

        System.out.println("Fibonacci Series:");
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
