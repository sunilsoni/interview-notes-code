package com.interview.notes.code.year.y2026.august.common.test1;

public class Fibonacci {

    // Prints the first n Fibonacci numbers (0, 1, 1, 2, 3, 5, 8, ...)
    public static void printFibonacci(int n) {
        if (n <= 0) {
            System.out.println("Input must be a positive integer.");
            return;
        }

        long first = 0, second = 1;

        for (int i = 0; i < n; i++) {
            System.out.print(first + " ");
            long next = first + second;
            first = second;
            second = next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int count = 10;
        System.out.println("First " + count + " Fibonacci numbers:");
        printFibonacci(count);
    }
}