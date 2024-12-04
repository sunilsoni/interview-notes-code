package com.interview.notes.code.months.dec24.test3;

public class ReverseInteger1 {

    public static int reverse(int x) {
        int reversed = 0;
        int sign = (x < 0) ? -1 : 1;  // Preserve the sign of x
        x = Math.abs(x);  // Work with positive value of x

        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            // Check for overflow before multiplying and adding the digit
            if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return 0;
            }

            reversed = reversed * 10 + digit;
        }

        // Handle negative numbers by restoring the sign
        reversed *= sign;

        // Check if reversed number is within the 32-bit signed integer range
        if (reversed < Integer.MIN_VALUE || reversed > Integer.MAX_VALUE) {
            return 0;
        }

        return reversed;
    }

    public static void main(String[] args) {
        // Test cases
        testReverse(123);           // Expected: 321
        testReverse(-123);          // Expected: -321
        testReverse(120);           // Expected: 21
        testReverse(0);             // Expected: 0
        testReverse(1534236469);    // Expected: 0 (overflow case)
        testReverse(-2147483648);   // Expected: 0 (overflow case)
        testReverse(2147483647);    // Expected: 0 (overflow case)
    }

    private static void testReverse(int x) {
        int result = reverse(x);
        System.out.println("Input: " + x + " | Reversed: " + result);
    }
}
