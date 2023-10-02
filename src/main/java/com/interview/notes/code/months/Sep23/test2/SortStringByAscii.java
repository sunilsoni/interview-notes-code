package com.interview.notes.code.months.Sep23.test2;

import java.util.Arrays;


/**
 *
 Given a string s, sort the string to the following rules:

 a. All the characters in string should be sorted based on their asci value.

 b. Print the all character in sorted order along with before sorting index position.


 Input: s = "Test String!"

 Output:  Char>>index>>  >> 4

 Char>>index>>! >> 11

 Char>>index>>S >> 5

 Char>>index>>T >> 0

 Char>>index>>e >> 1

 Char>>index>>g >> 10

 Char>>index>>i >> 8

 Char>>index>>n >> 9

 Char>>index>>r >> 7

 Char>>index>>s >> 2

 Char>>index>>t >> 3

 Char>>index>>t >> 6
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
