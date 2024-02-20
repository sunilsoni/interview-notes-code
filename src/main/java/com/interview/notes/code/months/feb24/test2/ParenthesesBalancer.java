package com.interview.notes.code.months.feb24.test2;

import java.util.Stack;

public class ParenthesesBalancer {

    public static String balance(String s) {
        StringBuilder balanced = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    // Mark this closing parenthesis for removal
                    balanced.append('#');
                }
            }
            balanced.append(c);
        }

        // Mark any remaining unmatched opening parentheses for removal
        while (!stack.isEmpty()) {
            balanced.setCharAt(stack.pop(), '#');
        }

        // Remove marked parentheses and return balanced string
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < balanced.length(); i++) {
            char c = balanced.charAt(i);
            if (c != '#') {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(balance("()"));      // Output: "()"
        System.out.println(balance("a(b)c)"));  // Output: "a(b)c"
        System.out.println(balance(")("));      // Output: ""
        System.out.println(balance("(((((("));  // Output: ""
        System.out.println(balance("((())("));  // Output: "(())"
        System.out.println(balance(")()())()")); // Output: "()()"
        System.out.println(balance(")())((())(")); // Output: "(())(())"
    }
}
