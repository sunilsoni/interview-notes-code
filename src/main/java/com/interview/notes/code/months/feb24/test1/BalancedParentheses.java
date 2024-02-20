package com.interview.notes.code.months.feb24.test1;

public class BalancedParentheses {

    public static String balance(String input) {
        if (input == null || input.isEmpty()) {
            return input; // Handle empty input gracefully
        }

        int openCount = 0;
        int closeCount = 0;
        StringBuilder balanced = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (ch == '(') {
                openCount++;
                balanced.append(ch);
            } else if (ch == ')') {
                if (openCount > 0) { // Ensure valid opening parenthesis before closing
                    closeCount++;
                    balanced.append(ch);
                }
            } else {
                balanced.append(ch); // Include other valid characters regardless of parentheses
            }
        }

        // Remove extra closing parentheses if they exceed openers
        if (closeCount > openCount) {
            balanced.delete(balanced.length() - (closeCount - openCount), balanced.length());
        }

        return balanced.toString();
    }

    public static void main(String[] args) {
        String[] tests = {
                "()()",
                "a(b)c)",
                ")(",
                "(((((",
                "((()))())",
                ")()(())",
                ")()()()()()"
        };

        for (String test : tests) {
            System.out.println(test + " -> " + balance(test));
        }
    }
}
