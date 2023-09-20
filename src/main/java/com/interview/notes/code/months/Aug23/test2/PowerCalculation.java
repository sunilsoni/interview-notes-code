package com.interview.notes.code.months.Aug23.test2;

public class PowerCalculation {

    // Function to multiply two numbers using addition
    public static int multiply(int a, int b) {
        int result = 0;
        for (int i = 0; i < Math.abs(b); i++) {
            result += a;
        }
        return (b < 0) ? -result : result;  // Handle negative multiplier
    }

    // Function to calculate m^n using loop
    public static int power(int m, int n) {
        int result = 1;
        int absoluteN = Math.abs(n);  // Absolute value of n
        for (int i = 0; i < absoluteN; i++) {
            result = multiply(result, m);
        }
        // Handle negative exponent
        return (n < 0) ? 1 / result : result;
    }

    public static void main(String[] args) {
        int m = 2;
        int n = 3;
        int result = power(m, n);
        System.out.println(m + " raised to the power " + n + " is: " + result);
    }
}
