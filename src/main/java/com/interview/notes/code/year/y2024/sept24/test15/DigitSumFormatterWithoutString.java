package com.interview.notes.code.year.y2024.sept24.test15;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DigitSumFormatterWithoutString {

    public static void main(String[] args) {
        // Create a Scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Read the input number
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        // Process the number and print the result
        String result = formatDigitSum(number);
        System.out.println(result);

        // Close the scanner
        scanner.close();
    }

    // Method to format the digit sum
    public static String formatDigitSum(int number) {
        // Handle negative numbers if needed (optional)
        if (number < 0) {
            throw new IllegalArgumentException("Input number must be non-negative.");
        }

        // List to store the individual digits (in reverse order)
        List<Integer> digits = new ArrayList<>();
        int sum = 0;

        // Edge case: if the number is 0
        if (number == 0) {
            return "0=0";
        }

        // Extract digits and calculate the sum
        while (number > 0) {
            int digit = number % 10;  // Get the last digit
            digits.add(digit);        // Store the digit
            sum += digit;             // Add the digit to the sum
            number /= 10;             // Remove the last digit
        }

        // Build the output string
        StringBuilder output = new StringBuilder();

        // Digits are stored in reverse order, so we need to reverse them for correct display
        for (int i = digits.size() - 1; i >= 0; i--) {
            output.append(digits.get(i));  // Append each digit
            if (i > 0) {
                output.append("+");        // Add '+' between digits
            }
        }

        // Append the sum at the end
        output.append("=").append(sum);

        // Return the formatted string
        return output.toString();
    }
}
