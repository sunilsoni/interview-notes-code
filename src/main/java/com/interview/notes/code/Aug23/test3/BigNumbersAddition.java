package com.interview.notes.code.Aug23.test3;

/**
 * write a program to add two numbers, given that nos can be of any digits for example:
 * num1 = 123456789123456789
 * num2 = 12345123456789123456789
 */
public class BigNumbersAddition {

    // Function to add two large numbers represented as strings
    public static String addBigNumbers(String num1, String num2) {
        StringBuilder result = new StringBuilder();  // To store the sum as a string

        int carry = 0;  // Initialize carry
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;

        // Loop from right to left, performing the addition digit by digit
        while (len1 >= 0 || len2 >= 0) {
            int sum = carry;

            if (len1 >= 0) {
                sum += num1.charAt(len1) - '0';  // Convert char to integer and add to sum
                len1--;
            }

            if (len2 >= 0) {
                sum += num2.charAt(len2) - '0';  // Convert char to integer and add to sum
                len2--;
            }

            carry = sum / 10;  // Calculate carry for next step
            sum = sum % 10;  // Get the last digit of sum

            // Append sum to the left of the result string
            result.insert(0, Integer.toString(sum));
        }

        // If there's any remaining carry, prepend it to the result
        if (carry > 0) {
            result.insert(0, Integer.toString(carry));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String num1 = "123456789123456789";
        String num2 = "12345123456789123456789";

        String sum = addBigNumbers(num1, num2);
        System.out.println("Sum of " + num1 + " and " + num2 + " is: " + sum);
    }
}
