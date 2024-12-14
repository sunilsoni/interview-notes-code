package com.interview.notes.code.months.dec24.oracle.test1;

public class SumDigits {

    // Recursive method to sum the digits of a number
    public int sumDigits(int n) {
        // Base case: when n is 0, return 0 (no more digits to process)
        if (n == 0) {
            return 0;
        }
        // Recursive case: add the rightmost digit and recurse with n / 10
        return (n % 10) + sumDigits(n / 10);
    }

    // Main method to test the sumDigits function
    public static void main(String[] args) {
        SumDigits sum = new SumDigits();

        // Test cases
        System.out.println("Test Case 1: sumDigits(126) = " + sum.sumDigits(126)); // Expected output: 9
        System.out.println("Test Case 2: sumDigits(49) = " + sum.sumDigits(49));   // Expected output: 13
        System.out.println("Test Case 3: sumDigits(12) = " + sum.sumDigits(12));   // Expected output: 3
        System.out.println("Test Case 4: sumDigits(0) = " + sum.sumDigits(0));     // Expected output: 0
        System.out.println("Test Case 5: sumDigits(999) = " + sum.sumDigits(999)); // Expected output: 27
    }
}
