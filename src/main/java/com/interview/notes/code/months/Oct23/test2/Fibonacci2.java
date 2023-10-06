package com.interview.notes.code.months.Oct23.test2;

public class Fibonacci2 {

    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }

        int fib = 1;
        int prevFib = 1;

        for (int i = 2; i < n; i++) {
            int temp = fib;
            fib += prevFib;
            prevFib = temp;
        }

        return fib;
    }

    public static void main(String[] args) {
        int n = 100; // Change to get the nth Fibonacci number
        System.out.println(fibonacciIterative(n));
    }
}
