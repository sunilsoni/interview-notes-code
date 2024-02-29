package com.interview.notes.code.months.feb24.test8;

public class Fibonacci {
    public static void main(String[] args) {
        int n = 10; // Change this value to print Fibonacci sequence up to a different number

        int prev = 0;
        int curr = 1;

        System.out.print("Fibonacci sequence up to " + n + ": ");
        System.out.print(prev + ", " + curr);

        while (curr + prev <= n) {
            int next = prev + curr;
            System.out.print(", " + next);
            prev = curr;
            curr = next;
        }
    }
}
