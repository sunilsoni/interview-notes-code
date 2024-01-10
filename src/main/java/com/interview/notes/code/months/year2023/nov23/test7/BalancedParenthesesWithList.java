package com.interview.notes.code.months.year2023.nov23.test7;

import java.util.ArrayList;
import java.util.List;

public class BalancedParenthesesWithList {

    public static boolean isBalanced(String str) {
        List<Character> list = new ArrayList<>();

        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                list.add(c); // push operation
            } else {
                if (list.isEmpty() || !isMatchingPair(list.remove(list.size() - 1), c)) {
                    return false; // pop operation with matching check
                }
            }
        }

        return list.isEmpty();
    }

    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '{' && closing == '}') ||
                (opening == '[' && closing == ']');
    }

    public static void main(String[] args) {
        String input = "{(()}]";
        boolean result = isBalanced(input);
        System.out.println("Is balanced: " + result); // Expected output: false
    }
}
