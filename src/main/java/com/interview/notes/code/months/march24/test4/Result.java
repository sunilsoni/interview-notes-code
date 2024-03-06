package com.interview.notes.code.months.march24.test4;

import java.util.Arrays;

public class Result {

    public static String getLargestNumber(String num) {
        // Convert the string to a character array for manipulation
        char[] digits = num.toCharArray();

        // Sort even and odd digits separately
        Arrays.sort(digits); // Sorts in ascending order
        reverseSortByParity(digits); // Sorts in descending order by parity

        return new String(digits);
    }

    private static void reverseSortByParity(char[] digits) {
        int start = 0;
        while (start < digits.length) {
            // Find the next group of same parity digits
            int end = start;
            while (end < digits.length - 1 && digits[end] % 2 == digits[end + 1] % 2) {
                end++;
            }
            // Reverse the group to sort in descending order
            reverse(digits, start, end);
            start = end + 1; // Move to the next group
        }
    }

    private static void reverse(char[] digits, int start, int end) {
        while (start < end) {
            char temp = digits[start];
            digits[start] = digits[end];
            digits[end] = temp;
            start++;
            end--;
        }
    }


    public static void main(String[] args) {
        String num = "0082663";
        System.out.println(getLargestNumber(num)); // Output should be "9758601"
       // System.out.println(com.interview.notes.code.months.march24.test3.Result.getLargestNumber("0082663")); // Test Case 1
       // System.out.println(com.interview.notes.code.months.march24.test3.Result.getLargestNumber("1806579")); // Test Case 2
    }
}
