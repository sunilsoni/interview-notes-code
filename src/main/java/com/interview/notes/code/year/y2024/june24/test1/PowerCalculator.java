package com.interview.notes.code.year.y2024.june24.test1;

import java.util.Scanner;

public class PowerCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the base number: ");
        int base = scanner.nextInt();

        System.out.print("Enter the exponent (n) to calculate base^n: ");
        int n = scanner.nextInt();

        // Calculate base^n using a loop
        long result = 1;
        for (int i = 0; i < n; i++) {
            result *= base;
        }

        System.out.println(base + "^" + n + " = " + result);

        scanner.close();
    }
}
