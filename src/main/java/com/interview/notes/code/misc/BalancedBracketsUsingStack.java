package com.interview.notes.code.misc;

import java.util.Stack;

/**
 *
 * Problem Statement-
 * [Stacks: Balanced Brackets](https://www.hackerrank.com/challenges/ctci-balanced-brackets/problem)
 *
 */
public class BalancedBracketsUsingStack {

    private static String matchParenthisis(String str) {
        Stack<Character> st = new Stack<Character>();
        char[] ch = str.toCharArray();
        for (char c : ch) {

            if (c == '{' || c == '[' || c == '(') {
                st.push(c);
            } else {

                if (c == ']' && !st.isEmpty() && st.pop() == '[') {
                    continue;

                } else if (c == '}' && !st.isEmpty() && st.pop() == '{') {
                    continue;
                } else if (c == ')' && !st.isEmpty() && st.pop() == '(') {
                    continue;
                } else {
                    return "NO";
                }

            }
        }
        if (!st.isEmpty()) {
            return "NO";
        }

        return "YES";

    }

}