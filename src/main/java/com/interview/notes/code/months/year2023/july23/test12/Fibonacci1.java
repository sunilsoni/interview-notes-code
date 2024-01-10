package com.interview.notes.code.months.year2023.july23.test12;

public class Fibonacci1 {
    public static void main(String[] args) {
        int n1 = 0, n2 = 1, n3; // Initialize the first two numbers in the series
        int count = 10; // Number of elements in the series

        // Print the first two numbers
        System.out.println(n1);
        System.out.println(n2);

        // Iterate to print the next numbers in the series
        for (int i = 2; i < count; ++i) {
            n3 = n1 + n2; // Compute the next number by adding the previous two
            System.out.println(n3);
            n1 = n2; // Update n1 with the value of n2
            n2 = n3; // Update n2 with the value of n3 (the new number in the series)
        }
    }
}
