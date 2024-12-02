package com.interview.notes.code.year.y2024.sept24.test15;

import java.util.Scanner;

public class SumOfDigits {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the input number from the console
        System.out.println("Enter a number:");
        int input = scanner.nextInt();

        // Validate and test each case
        if (input >= 0) {
            int sum = calculateDigitSum(input);
            System.out.println("Sum of digits: " + sum);
        } else {
            System.out.println("Invalid input. Please enter a positive integer.");
        }

        scanner.close();
    }

    // Method to calculate the sum of digits without converting to a string
    public static int calculateDigitSum(int number) {
        int sum = 0;

        // Extract digits and sum them
        while (number > 0) {
            sum += number % 10;  // Get the last digit
            number /= 10;        // Remove the last digit
        }

        return sum;
    }
}
