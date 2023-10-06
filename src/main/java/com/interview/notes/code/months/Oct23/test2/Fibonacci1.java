package com.interview.notes.code.months.Oct23.test2;

public class Fibonacci1 {

    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        int n = 10; // Change to get the nth Fibonacci number
        System.out.println(fibonacciRecursive(n));
    }
}
