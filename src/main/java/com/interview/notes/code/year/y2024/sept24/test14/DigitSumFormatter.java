package com.interview.notes.code.year.y2024.sept24.test14;

import java.util.Scanner;

public class DigitSumFormatter {

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
        // Convert the number to a string
        String numberStr = String.valueOf(number);

        // Initialize variables to store the sum and formatted output
        int sum = 0;
        StringBuilder output = new StringBuilder();

        // Iterate over each character in the string
        for (int i = 0; i < numberStr.length(); i++) {
            // Get the digit from the character
            int digit = Character.getNumericValue(numberStr.charAt(i));

            // Add the digit to the sum
            sum += digit;

            // Append the digit to the output string
            output.append(digit);

            // Add '+' between digits (but not after the last digit)
            if (i < numberStr.length() - 1) {
                output.append("+");
            }
        }

        // Append the final sum to the output
        output.append("=").append(sum);

        // Return the formatted string
        return output.toString();
    }
}
