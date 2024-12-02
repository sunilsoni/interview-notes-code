package com.interview.notes.code.year.y2024.nov24.test10;

import java.util.*;

public class ArrayProcessingAndBracketValidator {

    // Process array and split into different types
    public static ProcessedArrays processArray(Object[] input) {
        List<Integer> integers = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        List<String> specialChars = new ArrayList<>();

        for (Object obj : input) {
            if (obj instanceof Integer) {
                integers.add((Integer) obj);
            } else if (obj instanceof String) {
                String str = (String) obj;
                if (str.matches("\\d+")) {
                    integers.add(Integer.parseInt(str));
                } else if (str.matches("[A-Za-z]")) {
                    strings.add(str);
                } else {
                    specialChars.add(str);
                }
            }
        }
        Collections.sort(integers);
        return new ProcessedArrays(integers, strings, specialChars);
    }

    // Validate brackets
    public static boolean isValidBrackets(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // Test method for array processing
    private static void testArrayProcessing() {
        System.out.println("Testing Array Processing:");

        // Test Case 1: Given example
        Object[] input1 = new Object[]{'A', 'B', 'C', 1, 2, 3, "4", "5", 6, '@', '~', 'D'};
        ProcessedArrays result1 = processArray(input1);

        boolean test1Pass = result1.integers.equals(Arrays.asList(1, 2, 3, 4, 5, 6)) &&
                result1.strings.equals(Arrays.asList("A", "B", "C", "D")) &&
                result1.specialChars.equals(Arrays.asList("@", "~"));

        System.out.println("Test Case 1: " + (test1Pass ? "PASS" : "FAIL"));

        // Test Case 2: Empty array
        Object[] input2 = new Object[]{};
        ProcessedArrays result2 = processArray(input2);
        boolean test2Pass = result2.integers.isEmpty() && result2.strings.isEmpty() &&
                result2.specialChars.isEmpty();

        System.out.println("Test Case 2: " + (test2Pass ? "PASS" : "FAIL"));
    }

    // Test method for bracket validation
    private static void testBracketValidation() {
        System.out.println("\nTesting Bracket Validation:");

        // Test cases
        String[] inputs = {
                "()",            // valid
                "()[]{}",       // valid
                "(]",           // invalid
                "([)]",         // invalid
                "{[]}",         // valid
                "((()()))",     // valid
                "",            // valid
                "(((",          // invalid
                "}{"           // invalid
        };

        boolean[] expected = {true, true, false, false, true, true, true, false, false};

        for (int i = 0; i < inputs.length; i++) {
            boolean result = isValidBrackets(inputs[i]);
            System.out.println("Test Case " + (i + 1) + ": " +
                    (result == expected[i] ? "PASS" : "FAIL") +
                    " (Input: " + inputs[i] + ")");
        }

        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeInput.append("()[]{}");
        }
        long start = System.currentTimeMillis();
        boolean largeResult = isValidBrackets(largeInput.toString());
        long end = System.currentTimeMillis();

        System.out.println("\nLarge Input Test: " + (largeResult ? "PASS" : "FAIL") +
                " (Time taken: " + (end - start) + "ms)");
    }

    public static void main(String[] args) {
        testArrayProcessing();
        testBracketValidation();
    }

    // Data structure to hold processed arrays
    static class ProcessedArrays {
        List<Integer> integers;
        List<String> strings;
        List<String> specialChars;

        ProcessedArrays(List<Integer> integers, List<String> strings, List<String> specialChars) {
            this.integers = integers;
            this.strings = strings;
            this.specialChars = specialChars;
        }
    }
}