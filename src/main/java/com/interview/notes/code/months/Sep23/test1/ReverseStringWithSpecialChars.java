package com.interview.notes.code.months.Sep23.test1;

import java.util.Stack;

public class ReverseStringWithSpecialChars {
    public static void main(String[] args) {
        System.out.println(reverseString("ab-cd"));       // "dc-ba"
        System.out.println(reverseString("@amit_dixit")); // "@tixi_dtima"
    }

    public static String reverseString(String s) {
        Stack<Character> stack = new Stack<>();
        
        // Push all the English letters onto the stack
        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                stack.push(ch);
            }
        }

        StringBuilder result = new StringBuilder();

        // Construct the output string
        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                result.append(stack.pop());
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
}
