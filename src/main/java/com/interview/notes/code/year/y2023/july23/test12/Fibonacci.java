package com.interview.notes.code.year.y2023.july23.test12;

public class Fibonacci {
    public static void main(String[] args) {
        int count = 10; // Number of elements in the series
        for (int i = 0; i < count; i++) {
            System.out.println(fib(i)); // Call the recursive function for each number in the series
        }
    }

    // Recursive method to compute the nth number in the Fibonacci series
    static int fib(int n) {
        if (n <= 1) return n; // Base cases for the first two numbers
        return fib(n - 1) + fib(n - 2); // Recursive call for the sum of the previous two numbers
    }
}
