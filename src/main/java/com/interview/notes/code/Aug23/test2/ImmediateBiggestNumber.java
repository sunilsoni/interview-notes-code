package com.interview.notes.code.Aug23.test2;

import java.util.Arrays;


/**
 * In Java first no has 3 digits 123 so need to return next biggest int no which has all 3 no
 * <p>
 * 132  immediate biggest number
 */
class ImmediateBiggestNumber {
    public static int methodCall(int num) {
        char[] digits = Integer.toString(num).toCharArray(); // Convert the number to a char array for easier manipulation

        // Step 1: Start from the rightmost side
        int i;
        for (i = digits.length - 1; i > 0; i--) {
            if (digits[i] > digits[i - 1]) {
                break;
            }
        }

        // If no such digit is found, number is already the largest possible, so return the original number
        if (i == 0) {
            return num;
        }

        // Step 2: Find the left digit (digits[i-1])
        char leftDigit = digits[i - 1];
        int smallest = i;

        // Step 3: Find the smallest digit on right side of leftDigit and greater than leftDigit
        for (int j = i + 1; j < digits.length; j++) {
            if (digits[j] > leftDigit && digits[j] < digits[smallest]) {
                smallest = j;
            }
        }

        // Swap leftDigit with next smallest digit
        char temp = digits[i - 1];
        digits[i - 1] = digits[smallest];
        digits[smallest] = temp;

        // Step 4: Reverse the digits after the original index of leftDigit
        Arrays.sort(digits, i, digits.length);

        return Integer.parseInt(new String(digits)); // Convert the char array back to an integer
    }

    public static void main(String[] args) {
        // keep this function call here
        System.out.println(methodCall(123)); // Output should be: 132
        System.out.println(methodCall(12453)); // Output: 12534
        System.out.println(methodCall(11121)); // Output: 11211
        System.out.println(methodCall(41352)); // Output: 41523
    }
}
