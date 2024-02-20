package com.interview.notes.code.months.jan24.test14;

public class ParenthesesValidator {
    public static boolean validateParentheses(String s) {
        int countOpening = 0;
        int countClosing = 0;

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                countOpening++;
            } else if (c == ')' || c == ']' || c == '}') {
                countClosing++;
            }
        }

        return countOpening == countClosing;
    }

    public static void main(String[] args) {
        String s1 = "{ [ ( ] ) }"; // true
        String s2 = "(()";          // false

        System.out.println(s1 + ": " + validateParentheses(s1)); // Output: true
        System.out.println(s2 + ": " + validateParentheses(s2)); // Output: false
    }
}
