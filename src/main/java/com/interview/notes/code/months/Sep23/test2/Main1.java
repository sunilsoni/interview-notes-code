package com.interview.notes.code.months.Sep23.test2;

/**
 *
 In Java
 Have the function
 public static String RemoveBrackets(String str)


 parameter being passed, which
 will contain only the characters " (
 and ")", and determine the
 minimum number of brackets
 that need to be removed to
 create a string of correctly
 matched brackets. For example:
 if str Is "(()))" then your program
 should return the number 1. The
 answer could potentially be 0,
 and there will always be at least
 one set of matching brackets in
 the string.


 cLass Main <
 public static String RemoveBrackets(String str) {
 // code goes here
 return str;
 public static void main
 // keep this function
 er
 System.out.print("Test 1 passing: " + (RemoveBrackets("(()>()(((")
 S
 */
public class Main1 {

    public static int RemoveBrackets(String str) {
        int counter = 0; // For unmatched opening brackets
        int unmatchedClosing = 0; // For unmatched closing brackets
        
        for (char c : str.toCharArray()) {
            if (c == '(') {
                counter++;
            } else if (c == ')') {
                if (counter > 0) {
                    counter--;
                } else {
                    unmatchedClosing++;
                }
            }
        }

        return counter + unmatchedClosing; // sum of unmatched opening and closing brackets
    }
    
    public static void main(String[] args) {
        System.out.println("Test 1 passing: " + (RemoveBrackets("(()>()(((") == 4)); // Example test, should return 4
    }
}
