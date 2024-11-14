package com.interview.notes.code.months.nov24.test10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {

    // Method to separate elements into integer, string, and character lists
    public static void separateElements(List<Object> input) {
        List<Integer> integerList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        List<Character> charList = new ArrayList<>();

        for (Object element : input) {
            if (element instanceof Integer) {
                integerList.add((Integer) element);
            } else if (element instanceof String) {
                try {
                    // Try to convert string to integer if possible
                    integerList.add(Integer.parseInt((String) element));
                } catch (NumberFormatException e) {
                    // If it fails, it must be a string
                    stringList.add((String) element);
                }
            } else if (element instanceof Character) {
                charList.add((Character) element);
            }
        }

        // Print the results
        System.out.println("Integer List: " + integerList);
        System.out.println("String List: " + stringList);
        System.out.println("Char List: " + charList);
    }

    // Method to validate if the string of brackets is balanced
    public static boolean isValidBrackets(String s) {
        Stack<Character> stack = new Stack<>();

        // Traverse the string and handle each bracket
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (!isMatchingPair(top, c)) {
                    return false;
                }
            }
        }

        // If stack is empty, all brackets matched correctly
        return stack.isEmpty();
    }

    // Helper method to check matching pair of brackets
    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '{' && closing == '}') ||
                (opening == '[' && closing == ']');
    }

    // Test Method to validate all cases, including large data
    public static void testMethods() {
        // Test Case 1: Test for separating elements into lists
        List<Object> input = Arrays.asList('A', 'B', 'C', 1, 2, 3, '4', '5', 6, '@', '~', 'D');
        separateElements(input);

        // Test Case 2: Test for valid and invalid bracket strings
        String test1 = "({[]})"; // Valid
        String test2 = "([)]";   // Invalid
        String test3 = "{[}";    // Invalid
        String test4 = "((((((((()))))))))"; // Valid

        System.out.println("Test 1 - Expected: true, Result: " + isValidBrackets(test1));
        System.out.println("Test 2 - Expected: false, Result: " + isValidBrackets(test2));
        System.out.println("Test 3 - Expected: false, Result: " + isValidBrackets(test3));
        System.out.println("Test 4 - Expected: true, Result: " + isValidBrackets(test4));

        // Edge Case: Large Data Input (balanced)
        String largeTest1 = "(".repeat(10000) + ")".repeat(10000);
        System.out.println("Large Test 1 - Expected: true, Result: " + isValidBrackets(largeTest1));

        // Edge Case: Large Data Input (unbalanced)
        String largeTest2 = "(".repeat(10000) + "(".repeat(10000);
        System.out.println("Large Test 2 - Expected: false, Result: " + isValidBrackets(largeTest2));
    }

    public static void main(String[] args) {
        // Running test cases
        testMethods();
    }
}
