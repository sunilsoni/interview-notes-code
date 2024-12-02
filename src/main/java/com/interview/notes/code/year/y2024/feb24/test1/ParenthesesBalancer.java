package com.interview.notes.code.year.y2024.feb24.test1;

public class ParenthesesBalancer {

    public static String balance(String input) {
        if (input == null || input.isEmpty()) {
            return ""; // Empty input or null string
        }

        StringBuilder result = new StringBuilder();
        int openCount = 0; // Count of unmatched opening parentheses

        for (char ch : input.toCharArray()) {
            if (ch == '(') {
                openCount++;
            } else if (ch == ')') {
                if (openCount > 0) {
                    // Matched closing parenthesis
                    result.append(ch);
                    openCount--;
                }
                // Ignore extra closing parentheses
            } else {
                // Other characters (alphanumeric, etc.)
                result.append(ch);
            }
        }

        // Remove any unmatched opening parentheses
        while (openCount > 0) {
            result.append(')');
            openCount--;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(balance("()")); // Output: "()"
        System.out.println(balance("a(b)c)")); // Output: "a(b)c"
        System.out.println(balance(")(")); // Output: ""
        System.out.println(balance("((((((")); // Output: ""
        System.out.println(balance("（（）（）")); // Output: "（）（）"
        System.out.println(balance("）（（））")); // Output: "（（））"
        System.out.println(balance("）（（））（）")); // Output: "（）（）（)"
    }
}
