package com.interview.notes.code.months.aug24.test29;

import java.util.Scanner;

public class FibonacciSeries {

    public static void main(String[] args) {
        // Reading input from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the length of Fibonacci series: ");
        int length = scanner.nextInt();
        scanner.close();

        // Validate input
        if (length <= 0) {
            System.out.println("Please enter a positive integer.");
            return;
        }

        // Generate Fibonacci series
        generateFibonacciSeries(length);
    }

    // Method to generate Fibonacci series
    private static void generateFibonacciSeries(int length) {
        int first = 0, second = 1;

        // Handle the first number separately if the length is 1
        if (length == 1) {
            System.out.println(first);
            return;
        }

        // Print the first two numbers
        System.out.print(first + ", " + second);

        // Loop to generate the rest of the series
        for (int i = 2; i < length; i++) {
            int next = first + second;
            System.out.print(", " + next);
            first = second;
            second = next;
        }
        System.out.println(); // To move to the next line after the series is printed
    }
}
