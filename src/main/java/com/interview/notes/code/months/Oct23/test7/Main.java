package com.interview.notes.code.months.Oct23.test7;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String input = "mmm..aieee...n";
        String result = processString(input);
        System.out.println("The final word is: " + result);
    }

    public static String processString(String input) {
        // Step 1: Initialize two stacks
        Stack<Character> finalStack = new Stack<>();
        Stack<Character> tempStack = new Stack<>();

        // Step 2: Iterate through the string
        for (char c : input.toCharArray()) {
            // Step 3: If it's not a dot
            if (c != '.') {
                // Remove as many characters as the number of dots
                while (!tempStack.isEmpty()) {
                    finalStack.pop();
                    tempStack.pop();
                }
                finalStack.push(c); // Add the current character
            }
            // Step 4: If it's a dot, add it to the temporary stack
            else {
                tempStack.push(c);
            }
        }

        // Step 5: Prepare the final word
        StringBuilder finalWord = new StringBuilder();
        for (char c : finalStack) {
            finalWord.append(c);
        }

        return finalWord.toString();
    }
}
