package com.interview.notes.code.months.nov24.test10;

import java.util.Stack;

public class ParenthesesValidator {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || 
                    (c == '}' && top != '{') || 
                    (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
            "()",
            "()[]{}",
            "(]",
            "([)]",
            "{[]}",
            "",
            "((()))",
            "((())",
            "(((())))",
            "({[]})",
            "({[}])",
            "({}[]())",
            "(((){}[]()))",
            "(((){}[]())",
            "((((((((((((((((((((((((((((((())))))))))))))))))))))))))))))",
            "{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}",
            "[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]"
        };

        // Run tests
        for (int i = 0; i < testCases.length; i++) {
            String testCase = testCases[i];
            boolean result = isValid(testCase);
            System.out.println("Test Case " + (i + 1) + ": " + testCase);
            System.out.println("Result: " + (result ? "PASS" : "FAIL"));
            System.out.println();
        }

        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append("({[]})");
        }
        long startTime = System.currentTimeMillis();
        boolean largeResult = isValid(largeInput.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("Large Input Test (100,000 pairs):");
        System.out.println("Result: " + (largeResult ? "PASS" : "FAIL"));
        System.out.println("Execution Time: " + (endTime - startTime) + "ms");
    }
}
