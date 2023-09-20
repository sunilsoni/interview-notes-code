package com.interview.notes.code.months.Aug23.test3;

import java.math.BigInteger;

public class FactorialCalculator {
    public static void main(String[] args) {
        try {
            System.out.println(getFactorial("5"));  // Output should be 120
            System.out.println(getFactorial("123"));  // Output will be a large number
            System.out.println(getFactorial("abc"));  // This should throw an exception
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public static BigInteger getFactorial(String numStr) throws NumberFormatException {
        // Step 1: Validate if the input is an integer
        int num;
        try {
            num = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid format. Please enter an integer.");
        }

        // Step 2: Calculate the factorial using BigInteger
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= num; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        return factorial;
    }
}
