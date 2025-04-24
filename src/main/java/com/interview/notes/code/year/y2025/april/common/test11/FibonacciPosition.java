package com.interview.notes.code.year.y2025.april.common.test11;

public class FibonacciPosition {
    public static int getFibonacci(int position) {
        if (position < 0) {
            return -1;
        }
        
        // Initialize array to store Fibonacci numbers
        int[] fib = new int[position + 1];
        
        // Base cases
        if (position >= 0) fib[0] = 0;
        if (position >= 1) fib[1] = 1;
        
        // Calculate Fibonacci numbers up to position
        for (int i = 2; i <= position; i++) {
            if (i % 2 == 0) {
                fib[i] = fib[i-1];
            } else {
                fib[i] = fib[i-1] + fib[i-2];
            }
        }
        
        return fib[position];
    }

    public static void main(String[] args) {
        // Print sequence
        System.out.print("Fibonacci sequence: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(getFibonacci(i) + " ");
        }
        
        // Test specific position
        int position = 4;
        System.out.println("\nFibonacci number at position " + position + 
                         " is: " + getFibonacci(position));
    }
}
