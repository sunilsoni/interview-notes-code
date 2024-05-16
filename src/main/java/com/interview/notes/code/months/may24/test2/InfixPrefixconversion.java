package com.interview.notes.code.months.may24.test2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Infix to Prefix conversion
 * This question accounts for 60% of the total test. Please do not forget to submit your answer, timed-out tests will result in 0 points awarded.
 * Infix Expressions:
 * In Infix expressions, operators are written in-between their operands.
 * An expression such as A * (B + C) / D means-
 * 1. First add B and C together
 * 2. Multiply the result by A
 * 3. Divide by D to get the final answer
 * The expression for adding the numbers 1 and 2 is "1 + 2".
 * Prefix Expressions:
 * In Prefix expressions, operators are written before their operands.
 * The expression for adding the numbers 1 and 2 is "+ 1 2".
 * In more complex expressions, the operators still precede their operands.
 * For instance, the expression that would be written in conventional infix notation as -
 * (5 - 6) * 7 can be written in prefix as *- 567.
 * Write a program that takes a string as input containing an infix expression and returns the corresponding prefix expression.
 * Note:
 * 1. The string contains operators (+,-./.*), parenthesis, and operands (digits).
 * 2. Each digit is a separate operand.
 */
class InfixPrefixconversion {

    public static String infixToPrefix(String infix) {
        // Operators precedence map
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);

        // Stack for operators
        Stack<Character> operators = new Stack<>();
        // Stack for operands
        Stack<String> operands = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            // If the character is a digit (operand), push it to the operands stack
            if (Character.isDigit(ch)) {
                operands.push(ch + "");
            }
            // If the character is '(', push it to the operators stack
            else if (ch == '(') {
                operators.push(ch);
            }
            // If the character is ')', pop from both stacks and push result to operands stack
            else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    String op2 = operands.pop();
                    String op1 = operands.pop();
                    char op = operators.pop();
                    String exp = op + op1 + op2;
                    operands.push(exp);
                }
                operators.pop(); // Remove '(' from stack
            }
            // If the character is an operator, process all operators with higher or same precedence
            else if (precedence.containsKey(ch)) {
                while (!operators.isEmpty() && precedence.containsKey(operators.peek()) &&
                        precedence.get(operators.peek()) >= precedence.get(ch)) {
                    String op2 = operands.pop();
                    String op1 = operands.pop();
                    char op = operators.pop();
                    String exp = op + op1 + op2;
                    operands.push(exp);
                }
                operators.push(ch); // Push current operator to stack
            }
        }

        // Process remaining operators in the stack
        while (!operators.isEmpty()) {
            String op2 = operands.pop();
            String op1 = operands.pop();
            char op = operators.pop();
            String exp = op + op1 + op2;
            operands.push(exp);
        }

        // The final prefix expression
        return operands.pop();
    }

    public static void main(String[] args) {
        // First example
        String input1 = "8+(7-9)*2";
        String output1 = infixToPrefix(input1);
        System.out.println("Prefix Expression for '" + input1 + "': " + output1);

        // Second example
        String input2 = "(1)+8";
        String output2 = infixToPrefix(input2);
        System.out.println("Prefix Expression for '" + input2 + "': " + output2);
    }
}
