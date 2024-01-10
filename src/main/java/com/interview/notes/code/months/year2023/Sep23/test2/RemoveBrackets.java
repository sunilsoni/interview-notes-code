package com.interview.notes.code.months.year2023.Sep23.test2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RemoveBrackets {

    private static final Map<Character, Character> BRACKET_PAIRS;

    static {
        BRACKET_PAIRS = new HashMap<>();
        BRACKET_PAIRS.put('(', ')');
        BRACKET_PAIRS.put('{', '}');
        BRACKET_PAIRS.put('[', ']');
    }

    public static String RemoveBrackets1(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        int unmatchedClosing = 0;

        for (char c : str.toCharArray()) {
            if (BRACKET_PAIRS.containsKey(c)) {
                stack.push(c);
            } else if (BRACKET_PAIRS.containsValue(c)) {
                if (stack.isEmpty() || BRACKET_PAIRS.get(stack.peek()) != c) {
                    unmatchedClosing++;
                } else {
                    stack.pop();
                }
            }
        }

        return Integer.toString(stack.size() + unmatchedClosing);
    }

    public static String RemoveBrackets(String str) {
        int open = 0, remove = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                open++;
            } else if (ch == ')' && open > 0) {
                open--;
            } else if (ch == ')' && open == 0) {
                remove++;
            }
        }
        remove += open;  // add the count of remaining unmatched open brackets
        return Integer.toString(remove);
    }


    public static void main(String[] args) {
        System.out.println("Test 1 passing: " + (RemoveBrackets("{[()]>[{{[(((").equals("6"))); // Example test, should print true
    }
}
