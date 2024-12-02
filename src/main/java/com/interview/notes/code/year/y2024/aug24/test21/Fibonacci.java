package com.interview.notes.code.year.y2024.aug24.test21;

public class Fibonacci {
    public static void main(String[] args) {
        int n = 10; // Number of Fibonacci numbers to generate
        int first = 0, second = 1;

        System.out.println("Fibonacci Series up to " + n + " terms:");

        for (int i = 1; i <= n; i++) {
            System.out.print(first + " ");

            // Compute the next number in the series
            int next = first + second;
            first = second;
            second = next;
        }
    }
}
