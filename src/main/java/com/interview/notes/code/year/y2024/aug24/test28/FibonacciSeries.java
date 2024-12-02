package com.interview.notes.code.year.y2024.aug24.test28;

public class FibonacciSeries {

    public static long fibonacci(int n) {
        if (n <= 1) return n;

        long prev = 0, curr = 1, next;
        for (int i = 2; i <= n; i++) {
            next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    public static void main(String[] args) {
        // Example test cases
        System.out.println("Fibonacci(0) = " + fibonacci(0));  // Expected: 0
        System.out.println("Fibonacci(1) = " + fibonacci(1));  // Expected: 1
        System.out.println("Fibonacci(2) = " + fibonacci(2));  // Expected: 1
        System.out.println("Fibonacci(5) = " + fibonacci(5));  // Expected: 5
        System.out.println("Fibonacci(10) = " + fibonacci(10));  // Expected: 55

        // Additional test cases
        System.out.println("Fibonacci(20) = " + fibonacci(20));  // Expected: 6765
        System.out.println("Fibonacci(30) = " + fibonacci(30));  // Expected: 832040
        System.out.println("Fibonacci(40) = " + fibonacci(40));  // Expected: 102334155

        // Edge case
        System.out.println("Fibonacci(50) = " + fibonacci(50));  // Expected: 12586269025
    }
}
