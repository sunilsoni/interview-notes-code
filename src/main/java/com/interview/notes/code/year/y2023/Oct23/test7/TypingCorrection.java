package com.interview.notes.code.year.y2023.Oct23.test7;

import java.util.Stack;

public class TypingCorrection {

    public static String correctTyping(String input) {
        // Step 1: Initialize a Stack
        Stack<Character> charStack = new Stack<>();

        // Step 2: Iterate through the Input String
        for (char ch : input.toCharArray()) {
            if (ch != '.') {
                charStack.push(ch);  // Push the character onto the stack if it's not a dot
            } else if (!charStack.isEmpty()) {
                charStack.pop();  // Pop the top character off the stack if it's a dot and the stack is not empty
            }
        }

        // Step 3: Build the Final Word
        StringBuilder finalWord = new StringBuilder();
        while (!charStack.isEmpty()) {
            finalWord.insert(0, charStack.pop());  // Pop characters off the stack and prepend them to the final word
        }

        return finalWord.toString();
    }

    public static void main(String[] args) {
        String input = "mmm..aieee...n";
        String output = correctTyping(input);
        System.out.println(output);  // Output: company
    }
}
