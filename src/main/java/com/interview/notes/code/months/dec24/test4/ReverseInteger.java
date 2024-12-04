package com.interview.notes.code.months.dec24.test4;

/*
WORKING:

7. Reverse Integer

https://leetcode.com/problems/reverse-integer/description/

Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-2 power 31, 2 power 31 - 1], then return 0.
Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21

Constraints:
-2 power 31 <= x <= 2 power 31 - 1

 */

public class ReverseInteger {

    public static void main(String[] args) {
        com.interview.notes.code.misc.test4.ReverseInteger ri = new com.interview.notes.code.misc.test4.ReverseInteger();

        // Test cases
        int[] testInputs = {123, -123, 120, 0, 1534236469, -2147483648};
        int[] expectedOutputs = {321, -321, 21, 0, 0, 0};

        boolean allTestsPassed = true;

        for (int i = 0; i < testInputs.length; i++) {
            int input = testInputs[i];
            int expected = expectedOutputs[i];
            int result = ri.reverse(input);

            if (result == expected) {
                System.out.println("Test case " + (i + 1) + " passed.");
            } else {
                System.out.println("Test case " + (i + 1) + " failed: Input: " + input +
                        ", Expected: " + expected + ", Got: " + result);
                allTestsPassed = false;
            }
        }

        // Additional large data input test
        int largeInput = 1463847412; // Reversal within 32-bit integer range
        int largeExpected = 2147483641;
        int largeResult = ri.reverse(largeInput);

        if (largeResult == largeExpected) {
            System.out.println("Large input test passed.");
        } else {
            System.out.println("Large input test failed: Input: " + largeInput +
                    ", Expected: " + largeExpected + ", Got: " + largeResult);
            allTestsPassed = false;
        }

        if (allTestsPassed) {
            System.out.println("All test cases passed successfully.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }

    /**
     * Reverses the digits of a 32-bit signed integer.
     * If the reversed integer overflows, returns 0.
     *
     * @param x the integer to reverse
     * @return the reversed integer or 0 if it overflows
     */
    public int reverse(int x) {
        int reversed = 0;
        while (x != 0) {
            // Extract the last digit
            int digit = x % 10;
            x /= 10;

            // Check for overflow before it happens
            if (reversed > Integer.MAX_VALUE / 10 ||
                    (reversed == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0; // Overflow would occur
            }
            if (reversed < Integer.MIN_VALUE / 10 ||
                    (reversed == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0; // Underflow would occur
            }

            // Append the digit
            reversed = reversed * 10 + digit;
        }
        return reversed;
    }
}
