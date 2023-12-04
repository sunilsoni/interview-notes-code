package com.interview.notes.code.months.dec23.test1;

import java.util.Stack;

public class BalancedParentheses {

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                } else if (!isMatchingPair(stack.pop(), c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char character1, char character2) {
        return (character1 == '(' && character2 == ')') || 
               (character1 == '{' && character2 == '}') || 
               (character1 == '[' && character2 == ']');
    }

    public static void main(String[] args) {
        String testString = "{[()]}"; // Example string
        System.out.println("Is balanced: " + isBalanced(testString));
    }
}
