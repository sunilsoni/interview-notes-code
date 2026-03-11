package com.interview.notes.code.year.y2026.march.microsoft.test7;

import java.util.ArrayDeque;

public class BracketMatcher {

    public static boolean isBalanced(String s) {
        var stack = new ArrayDeque<Character>();

        // CHANGE 1: Use a standard for-loop so we have access to the exact 'index' (i)
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); 
            
            switch (c) {
                case '(' -> stack.push(')');
                case '[' -> stack.push(']');
                case '{' -> stack.push('}');
                case ')', ']', '}' -> {
                    // CHANGE 2: Break out the failure conditions to log specific compiler-like errors
                    if (stack.isEmpty()) {
                        System.out.printf("Error at index %d: Unexpected closing bracket '%c'. No opening bracket found.%n", i, c);
                        return false;
                    }
                    char expected = stack.pop();
                    if (expected != c) {
                        System.out.printf("Error at index %d: Expected '%c' but found '%c'.%n", i, expected, c);
                        return false;
                    }
                }
                default -> {} 
            }
        }

        // CHANGE 3: Check if we reached the end but still have unclosed brackets
        if (!stack.isEmpty()) {
            System.out.printf("Error at end of string: Missing closing bracket(s). Expected '%c'.%n", stack.pop());
            return false;
        }

        return true; 
    }
}