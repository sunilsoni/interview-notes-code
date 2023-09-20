package com.interview.notes.code.months.july23.test5;

import java.util.Stack;

public class LexicographicallySmallestString {

    public static String findLexicographicallySmallest(String pattern) {
        int n = pattern.length();
        Stack<Integer> increasingStack = new Stack<>();
        Stack<Integer> decreasingStack = new Stack<>();

        // Initialize the stack with all digits from 1 to 9
        for (int i = 1; i <= 9; i++) {
            increasingStack.push(i);
            decreasingStack.push(i);
        }

        // Array to store the result digits
        int[] result = new int[n + 1];
        int resultIndex = 0;

        // Process the pattern string and build the result array
        for (int i = 0; i < n; i++) {
            if (pattern.charAt(i) == 'I') {
                // If pattern is 'I', pick the smallest digit from the increasing stack
                result[resultIndex++] = increasingStack.pop();
            } else if (pattern.charAt(i) == 'D') {
                // If pattern is 'D', pick the largest digit from the decreasing stack
                result[resultIndex++] = decreasingStack.pop();
            }
        }

        // Add the last remaining digit from the increasing stack
        result[resultIndex] = increasingStack.pop();

        // Build the final result string
        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            sb.append(digit);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String pattern1 = "IIIDIDDD";
        System.out.println("Input: " + pattern1);
        System.out.println("Output: " + findLexicographicallySmallest(pattern1));

    }
}
