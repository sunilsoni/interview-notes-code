package com.interview.notes.code.year.y2024.feb24.test6;

public class FibonacciSequence {
    public static void printFibonacci(int n) {
        if (n <= 0) {
            System.out.println("Please enter a positive integer greater than 0.");
            return;
        }

        int first = 1, second = 1;

        // Handle edge cases
        if (n == 1) {
            System.out.println(first);
        } else if (n == 2) {
            System.out.println(first + ", " + second);
        } else {
            System.out.print(first + ", " + second); // Print the first two terms

            // Loop to calculate the rest of the sequence
            for (int i = 3; i <= n; i++) {
                int nextTerm = first + second;
                System.out.print(", " + nextTerm);
                first = second; // Update the value of the first term to the second's
                second = nextTerm; // Update the value of the second term to the newly calculated term
            }
        }
    }

    public static void main(String[] args) {
        int n = 10; // Example: Print first 10 terms of Fibonacci sequence
        printFibonacci(n);
    }
}
