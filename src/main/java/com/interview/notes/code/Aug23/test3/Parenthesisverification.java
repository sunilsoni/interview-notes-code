package com.interview.notes.code.Aug23.test3;

import java.util.Stack;


public class Parenthesisverification {
    /**
     * For a given string of parenthesis, e.g. (()(((()())))((()()), return true if all the
     * parenthesis are correctly matched.
     *
     * @param input String of parenthesis
     * @return boolean indicating the string of parenthesis is valid
     */
    public static boolean verify(String input) {
        // Initialize an empty stack to keep track of opening parentheses.
        Stack<Character> stack = new Stack<>();

        // Loop through each character in the input string
        for (char ch : input.toCharArray()) {
            // If the character is an opening parenthesis, push it onto the stack
            if (ch == '(') {
                stack.push(ch);
            }
            // If the character is a closing parenthesis
            else if (ch == ')') {
                // Pop from stack if the stack is not empty, otherwise return false
                if (stack.empty()) {
                    return false;
                }
                stack.pop();
            }
        }

        // If the stack is empty, then the parentheses are balanced.
        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println(Parenthesisverification.verify("()"));
        Parenthesisverification.verify(")(");
    }

/*
    @Test
    public void verify_true_simpleValidInput() {
        assertTrue(Parenthesisverification.verify("()"));
    }

    @Test
    public void verify_false_simpleInvalidInput() {
        assertFalse(Parenthesisverification.verify(")("));
    }*/
}
