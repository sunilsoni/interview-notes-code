package com.interview.notes.code.year.y2024.march24.test17;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses2 {

    // Main method to test the isValid function
    public static void main(String[] args) {
        ValidParentheses2 validator = new ValidParentheses2();

        // Test cases
        String input1 = "()";
        String input2 = "()[]{}";
        String input3 = "(]";

        // Output the results of the validation
        System.out.println("Input: " + input1 + " - Output: " + validator.isValid(input1));
        System.out.println("Input: " + input2 + " - Output: " + validator.isValid(input2));
        System.out.println("Input: " + input3 + " - Output: " + validator.isValid(input3));
    }

    // Method to validate the string of parentheses
    public boolean isValid(String s) {
        // Initialize a stack to keep track of opening parentheses
        Stack<Character> stack = new Stack<>();

        // Mapping of closing brackets to their corresponding opening brackets
        Map<Character, Character> mappings = new HashMap<>();
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');

        for (char c : s.toCharArray()) {
            // If the current char is a closing bracket
            if (mappings.containsKey(c)) {
                // Get the top element of the stack. If the stack is empty, use a dummy value '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false
                if (topElement != mappings.get(c)) {
                    return false;
                }
            } else {
                // It's an opening bracket, push to the stack
                stack.push(c);
            }
        }

        // The string is valid if the stack is empty at the end
        return stack.isEmpty();
    }
}
