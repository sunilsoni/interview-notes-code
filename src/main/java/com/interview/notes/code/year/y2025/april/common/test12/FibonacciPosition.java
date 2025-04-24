package com.interview.notes.code.year.y2025.april.common.test12;

public class FibonacciPosition {
    // Method to get Fibonacci number at given position
    public static int getFibonacci(int position) {
        // Handle invalid position
        if (position < 0) {
            return -1;
        }
        
        // Base cases
        if (position == 0) return 0;
        if (position == 1) return 1;
        
        // Initialize first two numbers
        int a = 0, b = 1;
        int result = 0;
        
        // Calculate Fibonacci number at given position
        for (int i = 2; i <= position; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int[] positions = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        // Print Fibonacci sequence
        System.out.print("Fibonacci sequence: ");
        for (int pos : positions) {
            System.out.print(getFibonacci(pos) + " ");
        }
        System.out.println();
        
        // Test specific position
        int testPosition = 4;
        System.out.println("\nFibonacci number at position " + testPosition + 
                         " is: " + getFibonacci(testPosition));
    }
}
