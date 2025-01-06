package com.interview.notes.code.year.y2024.dec24.test3;

public class ReverseInteger {
    public static int reverse(int x) {
        int result = 0;

        while (x != 0) {
            int digit = x % 10;

            // Check for overflow before multiplying
            if (result > Integer.MAX_VALUE / 10 ||
                    (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            // Check for underflow before multiplying
            if (result < Integer.MIN_VALUE / 10 ||
                    (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            result = result * 10 + digit;
            x = x / 10;
        }

        return result;
    }

    public static void testCase(int input, int expected) {
        int result = reverse(input);
        boolean passed = result == expected;
        System.out.printf("Input: %d, Expected: %d, Got: %d -> %s%n",
                input, expected, result, passed ? "PASS" : "FAIL");
    }

    public static void main(String[] args) {
        // Basic test cases
        testCase(123, 321);
        testCase(-123, -321);
        testCase(120, 21);

        // Edge cases
        testCase(0, 0);
        testCase(1, 1);
        testCase(-1, -1);

        // Overflow cases
        testCase(1234567899, 0);  // Should return 0 due to overflow
        testCase(-2147483648, 0); // Should return 0 due to overflow
        testCase(2147483647, 0);  // Should return 0 due to overflow

        // Additional test cases
        testCase(1000000, 1);
        testCase(-1000000, -1);
        testCase(1000000003, 0);  // Should return 0 due to overflow
    }
}
