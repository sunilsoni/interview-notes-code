package com.interview.notes.code.year.y2023.Oct23.test2;

import java.util.Stack;

/**
 * IN Java provide below solution
 * <p>
 * Postfix  notation
 * 1 2 + => 3
 * 1 2 3 + * =>  1 5 * => 5
 * 1 2 + 3 * => 3 3 * => 9
 * 2 3 4 * + => 6 2 + => 8
 * <p>
 * So format is like below
 * <p>
 * integer number, + or *
 * <p>
 * write function that will calculate
 */
public class PostfixCalculator {

    public static void main(String[] args) {
        String[] expression1 = {"1", "2", "+"};
        String[] expression2 = {"1", "2", "3", "+", "*"};
        String[] expression3 = {"1", "2", "+", "3", "*"};
        String[] expression4 = {"2", "3", "4", "*", "+"};

        System.out.println(calculate(expression1)); // 3
        System.out.println(calculate(expression2)); // 5
        System.out.println(calculate(expression3)); // 9
        System.out.println(calculate(expression4)); // 14 (Not 8, as your example suggested)
    }

    public static int calculate1(String[] postfix) {
        Stack<Integer> stack = new Stack<>();

        for (String s : postfix) {
            if (isOperator(s)) {
                int operand2 = stack.pop(); // Second operand
                int operand1 = stack.pop(); // First operand
                switch (s) {
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "*":
                        stack.push(operand1 * operand2);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported operator: " + s);
                }
            } else {
                // If not an operator, it's a number.
                stack.push(Integer.parseInt(s));
            }
        }
        // At the end, the result will be the only item left in the stack.
        return stack.pop();
    }

    public static int calculate(String[] postfix) throws IllegalArgumentException {
        Stack<Integer> stack = new Stack<>();

        for (String s : postfix) {
            if (isOperator(s)) {
                if (stack.size() < 2) { // Ensure there are at least two numbers to perform the operation.
                    throw new IllegalArgumentException("Invalid postfix expression: Insufficient operands for the operator " + s);
                }
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                switch (s) {
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "*":
                        stack.push(operand1 * operand2);
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported operator: " + s);
                }
            } else {
                try {
                    stack.push(Integer.parseInt(s));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid element in postfix expression: " + s);
                }
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression: There are either too many or too few operators.");
        }
        return stack.pop();
    }

    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("*");
    }
}
