package com.interview.notes.code.months.Aug23.test3;

public class PowerCalculation {

    // Function to multiply two numbers using addition
    public static int multiply(int a, int b) {
        int result = 0;
        for (int i = 0; i < Math.abs(b); i++) {
            result += a;
        }
        return (b < 0) ? -result : result;  // Handle negative multiplier
    }

    // Function to calculate m^n using recursion
    public static int power(int m, int n) {
        // Base case: Any number raised to 0 is 1
        if (n == 0) {
            return 1;
        }

        // Calculate m^(n-1) recursively
        int temp = power(m, Math.abs(n) - 1);

        // Multiply result by m using the multiply() function
        int result = multiply(m, temp);

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
