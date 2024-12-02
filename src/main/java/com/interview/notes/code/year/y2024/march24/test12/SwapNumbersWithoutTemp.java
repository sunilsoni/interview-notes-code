package com.interview.notes.code.year.y2024.march24.test12;

import java.util.Scanner;

public class SwapNumbersWithoutTemp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the values of x and y
        System.out.println("Enter x and y:");
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        // Display the numbers before swapping
        System.out.println("Before Swapping: " + x + y);

        // Swap the numbers without using a temp variable
        x = x + y;
        y = x - y;
        x = x - y;

        // Display the numbers after swapping
        System.out.println("After Swapping: " + x + y);

        scanner.close();
    }
}
