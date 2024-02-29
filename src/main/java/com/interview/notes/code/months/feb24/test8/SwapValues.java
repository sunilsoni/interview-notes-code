package com.interview.notes.code.months.feb24.test8;

public class SwapValues {
    public static void main(String[] args) {
        int x = 34;
        int y = 67;

        // Print original values
        System.out.println("Original values:");
        System.out.println("x = " + x);
        System.out.println("y = " + y);

        // Swap values without using a temporary variable
        x = x + y;
        y = x - y;
        x = x - y;

        // Print swapped values
        System.out.println("\nSwapped values:");
        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }
}

