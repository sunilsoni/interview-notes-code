package com.interview.notes.code.months.aug24.test31;

import java.util.*;

public class Solution {


    public static String minRemoveToMakeValid(String s) {
        int leftCount = 0;
        int rightCount = 0;

        // First pass: Count parentheses
        for (char c : s.toCharArray()) {
            if (c == '(') leftCount++;
            if (c == ')') {
                if (leftCount == 0) rightCount++;
                else leftCount--;
            }
        }

        StringBuilder result = new StringBuilder();
        int remainingLeft = leftCount;

        // Second pass: Build result string
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (remainingLeft > 0) {
                    remainingLeft--;
                } else {
                    result.append(c);
                }
            } else if (c == ')') {
                if (rightCount > 0) {
                    rightCount--;
                } else if (leftCount > 0) {
                    leftCount--;
                    result.append(c);
                }
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static String minRemoveToMakeValid1(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        // First pass: Mark indices to remove
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }

        // Add any unmatched opening parentheses
        while (!stack.isEmpty()) {
            indexesToRemove.add(stack.pop());
        }

        // Second pass: Build the result string
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                result.append(s.charAt(i));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "lee(t(c)o)de)",
                "a)b(c)d",
                "))((",
                "((((("
        };

        String[] expectedOutputs = {
                "lee(t(c)o)de",
                "ab(c)d",
                "",
                ""
        };

        for (int i = 0; i < testCases.length; i++) {
            String result = minRemoveToMakeValid(testCases[i]);
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + testCases[i]);
            System.out.println("Output: " + result);
            System.out.println("Expected: " + expectedOutputs[i]);
            System.out.println("Result: " + (result.equals(expectedOutputs[i]) ? "PASS" : "FAIL"));
            System.out.println();
        }
    }
}
