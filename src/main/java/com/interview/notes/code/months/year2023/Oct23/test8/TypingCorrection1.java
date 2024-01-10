package com.interview.notes.code.months.year2023.Oct23.test8;

import java.util.Stack;

public class TypingCorrection1 {

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
        String[] inputs = {"comn..pany", "mmm..aieee...n"};

        for (String input : inputs) {
            Thread thread = new Thread(new CorrectTypingTask(input));
            thread.start();
        }
    }

    public static class CorrectTypingTask implements Runnable {
        private final String input;

        public CorrectTypingTask(String input) {
            this.input = input;
        }

        @Override
        public void run() {
            String output = correctTyping(input);
            System.out.println(output);
        }
    }
}
