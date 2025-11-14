package com.interview.notes.code.year.y2025.november.oci.test7;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BalancedParenthesesChecker {

    // Function to check if parentheses are balanced
    public static boolean isBalanced(String input) {

        // Fail fast if input is null → communicates programming error
        Objects.requireNonNull(input, "Input string must not be null");

        // Stack to store opening brackets during traversal
        Deque<Character> stack = new ArrayDeque<>();

        // Use Java 8 IntStream to iterate through characters
        return IntStream.range(0, input.length()) // Loop through all indices
                .mapToObj(input::charAt)          // Convert each index to corresponding character
                .allMatch(ch -> {                 // Check each character in the stream
                    // If it's an opening bracket, push onto stack
                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                        return true;              // Continue processing next character
                    }
                    // If it's a closing bracket
                    if (ch == ')' || ch == '}' || ch == ']') {
                        // If stack empty -> unbalanced
                        if (stack.isEmpty()) return false;

                        // Pop the last opening bracket
                        char open = stack.pop();

                        // ✅ Strict matching rule (updated logic)
                        // Return false immediately if mismatched types
                        return (ch != ')' || open == '(') &&
                                (ch != '}' || open == '{') &&
                                (ch != ']' || open == '['); // mismatched pair or crossing pattern
                    }
                    return true; // For non-bracket chars or successful matches
                }) && stack.isEmpty();             // At end, ensure stack is empty (no unclosed brackets)
    }

    // ================== TEST METHOD ==================
    public static void main(String[] args) {

        // Define test cases with expected results
        Map<String, Boolean> testCases = new LinkedHashMap<>();
        testCases.put("()", true);
        testCases.put("({[]})", true);
        testCases.put("(]", false);
        testCases.put("((()))", true);
        testCases.put("(()", false);
        testCases.put(")(", false);
        testCases.put("{[()]}", true);
        testCases.put("{[(])}", false);
        testCases.put("", true); // empty string is balanced
        testCases.put("abc(def)[ghi]{jkl}", true);
        testCases.put("(a+b)*{c+[d/e]}", true);
        testCases.put("(a+b]*{c+[d/e]}", false);

        // Additional stricter crossing test
        testCases.put("{(})", false); // crossing → should be invalid now
        testCases.put("([)]", false); // crossing → should be invalid now
        testCases.put("({})", true);  // correct nested order

        // Large data test: repeat pattern to simulate big input
        String largeBalanced = IntStream.range(0, 100000).mapToObj(i -> "()").collect(Collectors.joining());
        testCases.put(largeBalanced, true);

        String largeUnbalanced = largeBalanced + "(";
        testCases.put(largeUnbalanced, false);

        // Run all test cases
        testCases.forEach((input, expected) -> {
            boolean actual = isBalanced(input); // Call the main function
            String result = actual == expected ? "PASS" : "FAIL";
            System.out.println("Input: " + summarize(input) +
                    " | Expected: " + expected +
                    " | Actual: " + actual +
                    " | Result: " + result);
        });
    }

    // Helper to shorten large input in print statements
    private static String summarize(String s) {
        return s.length() > 20 ? s.substring(0, 20) + "...(len=" + s.length() + ")" : s;
    }
}
