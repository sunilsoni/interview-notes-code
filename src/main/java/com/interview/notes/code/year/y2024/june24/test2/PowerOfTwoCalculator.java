package com.interview.notes.code.year.y2024.june24.test2;

import java.util.Scanner;

public class PowerOfTwoCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the exponent (n) to calculate 2^n: ");
        int n = scanner.nextInt();

        // Calculate 2^n using a loop
        long result = 1;
        for (int i = 0; i < n; i++) {
            result *= 2;
        }

        System.out.println("2^" + n + " = " + result);

        scanner.close();
    }
}
