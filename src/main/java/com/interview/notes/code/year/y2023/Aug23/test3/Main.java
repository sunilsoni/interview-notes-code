package com.interview.notes.code.year.y2023.Aug23.test3;

import java.util.Stack;

public class Main {
    public static String removeStars(String s) {
        Stack<Character> stack = new Stack<>(); // Step 1: Initialize a stack

        // Step 2: Loop through each character in the string
        for (char c : s.toCharArray()) {
            if (c != '*') {
                stack.push(c); // Push the character onto the stack if it's not a star
            } else if (!stack.isEmpty()) {
                stack.pop(); // Pop the top element if the stack is not empty
            }
        }

        // Step 3: Reconstruct the string
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "leet**cod*e";
        String s2 = "erase*****";

        System.out.println(removeStars(s1));  // Output should be "lecoe"
        System.out.println(removeStars(s2));  // Output should be ""
    }
}
