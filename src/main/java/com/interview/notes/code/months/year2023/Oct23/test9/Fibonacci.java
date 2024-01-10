package com.interview.notes.code.months.year2023.Oct23.test9;

import java.util.Scanner;

public class Fibonacci {

    public static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int fibPrev = 0;
            int fibCurrent = 1;

            for (int i = 2; i <= n; i++) {
                int fibNext = fibPrev + fibCurrent;
                fibPrev = fibCurrent;
                fibCurrent = fibNext;
            }

            return fibPrev;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the position (n) to get the Fibonacci number: ");

        // Read the input integer
        int n = scanner.nextInt();

        // Calculate the Fibonacci number at position n
        int result = fibonacci(n);

        // Print the result
        System.out.println("The Fibonacci number at position " + n + " is: " + result);

        // Close the scanner
        scanner.close();
    }
}
