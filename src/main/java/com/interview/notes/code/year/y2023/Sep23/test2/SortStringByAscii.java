package com.interview.notes.code.year.y2023.Sep23.test2;

import java.util.Arrays;


/**
 * Given a string s, sort the string to the following rules:
 * <p>
 * a. All the characters in string should be sorted based on their asci value.
 * <p>
 * b. Print the all character in sorted order along with before sorting index position.
 * <p>
 * <p>
 * Input: s = "Test String!"
 * <p>
 * Output:  Char>>index>>  >> 4
 * <p>
 * Char>>index>>! >> 11
 * <p>
 * Char>>index>>S >> 5
 * <p>
 * Char>>index>>T >> 0
 * <p>
 * Char>>index>>e >> 1
 * <p>
 * Char>>index>>g >> 10
 * <p>
 * Char>>index>>i >> 8
 * <p>
 * Char>>index>>n >> 9
 * <p>
 * Char>>index>>r >> 7
 * <p>
 * Char>>index>>s >> 2
 * <p>
 * Char>>index>>t >> 3
 * <p>
 * Char>>index>>t >> 6
 */
public class SortStringByAscii {

    public static void main(String[] args) {
        String s = "Test String!";
        sortAndPrintString(s);
    }

    private static void sortAndPrintString(String s) {
        // Convert the string to a character array.
        char[] charArray = s.toCharArray();

        // Sort the character array based on ASCII value.
        Arrays.sort(charArray);

        // Print the sorted characters along with their before sorting index position.
        for (int i = 0; i < charArray.length; i++) {
            System.out.println("Char>>index>>" + charArray[i] + ">>" + i);
        }
    }
}
