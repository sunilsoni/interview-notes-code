package com.interview.notes.code.months.year2023.Aug23.test2;

import java.util.Arrays;
import java.util.Collections;

//Write a Java function that takes a string as input and returns the length of the longest substring without repeating characters. For example, if the input is "abcabcbb", the output should be 3 ("abc").
public class MaxNumberRearranger {
    public static int rearrangeToMaxNumber(int number) {
        // Convert the number to a string to work with its digits
        String numberStr = Integer.toString(number);

        // Convert the string to an array of characters
        Character[] digits = new Character[numberStr.length()];
        for (int i = 0; i < numberStr.length(); i++) {
            digits[i] = numberStr.charAt(i);
        }

        // Sort the array in descending order to get the maximum number
        Arrays.sort(digits, Collections.reverseOrder());

        // Build the result string and convert it back to an integer
        StringBuilder resultStr = new StringBuilder();
        for (Character digit : digits) {
            resultStr.append(digit);
        }
        int result = Integer.parseInt(resultStr.toString());

        return result;
    }

    public static void main(String[] args) {
        int input1 = 001;
        int input2 = 132;

        int output1 = rearrangeToMaxNumber(input1);
        int output2 = rearrangeToMaxNumber(input2);

        System.out.println("Input: " + input1 + " Output: " + output1);
        System.out.println("Input: " + input2 + " Output: " + output2);
    }
}
