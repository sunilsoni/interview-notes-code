package com.interview.notes.code.year.y2024.sept24.test1;

import java.util.Stack;

/*
* If you need more classes, simply define them inline.
balance("() ") → "()"
balance ("a (b)c)").→> "a(b)c"
balance(") (").->
balance (" (((((").→> "'
balance("(() ()(").→ "() ()"
balance (") ( ())("). →> "(()) "
balance (") ()) (()()(").->

 */
public class ParenthesesBalancer {
    public static void main(String[] args) {
        String[] testCases = {
                "() ", "a (b)c)", ") (", " (((((",
                "(() ()(", ") ( ())(", ") ()) (()()(", "(()())"
        };

        for (String testCase : testCases) {
            String result = balanceParentheses(testCase);
            System.out.println("Input: \"" + testCase + "\" -> Output: \"" + result + "\"");
        }
    }

    public static String balanceParentheses(String input) {
        StringBuilder result = new StringBuilder();
        Stack<Integer> openParenIndices = new Stack<>();
        int openCount = 0;

        for (char c : input.toCharArray()) {
            if (c == '(') {
                openParenIndices.push(result.length());
                result.append(c);
                openCount++;
            } else if (c == ')') {
                if (openCount > 0) {
                    result.append(c);
                    openCount--;
                    openParenIndices.pop();
                }
            } else {
                result.append(c);
            }
        }

        while (openCount > 0) {
            result.deleteCharAt(openParenIndices.pop());
            openCount--;
        }

        return result.toString().trim();
    }
}
