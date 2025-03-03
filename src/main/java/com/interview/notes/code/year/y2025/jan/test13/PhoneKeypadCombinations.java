package com.interview.notes.code.year.y2025.jan.test13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Phone Keypad Letter Combinations

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on telephone buttons) is given below:
2 -> ABC
3 -> DEF
4 -> GHI
5 -> JKL
6 -> MNO
7 -> PQRS
8 -> TUV
9 -> WXYZ

Example 1:
Input: digits = "23"
Output: ["AD","AE","AF","BD","BE","BF","CD","CE","CF"]

Example 2:
Input: digits = ""
Output: []

Example 3:
Input: digits = "2"
Output: ["A","B","C"]

Constraints:
• 0 <= digits.length <= 4
• digits[i] is a digit in the range ['2', '9']

 */
public class PhoneKeypadCombinations {
    // Map to store digit to letters mapping
    private static final Map<Character, String> KEYPAD = new HashMap<>() {{
        put('2', "ABC");
        put('3', "DEF");
        put('4', "GHI");
        put('5', "JKL");
        put('6', "MNO");
        put('7', "PQRS");
        put('8', "TUV");
        put('9', "WXYZ");
    }};

    // Main method to generate combinations
    public static List<String> generateCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        backtrack("", digits, 0, result);
        return result;
    }

    // Helper method using backtracking
    private static void backtrack(String current, String digits, int index, List<String> result) {
        if (index == digits.length()) {
            result.add(current);
            return;
        }

        String letters = KEYPAD.get(digits.charAt(index));
        for (char letter : letters.toCharArray()) {
            backtrack(current + letter, digits, index + 1, result);
        }
    }

    // Test runner method
    private static void runTest(String testName, String input, int expectedSize) {
        System.out.println("\nRunning " + testName);
        System.out.println("Input: " + input);

        long startTime = System.currentTimeMillis();
        List<String> result = generateCombinations(input);
        long endTime = System.currentTimeMillis();

        boolean passed = result.size() == expectedSize;

        System.out.println("Result size: " + result.size());
        System.out.println("Combinations: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
    }

    public static void main(String[] args) {
        // Test 1: Basic case "23"
        runTest("Test 1 - Basic case '23'", "23", 9);

        // Test 2: Empty input
        runTest("Test 2 - Empty input", "", 0);

        // Test 3: Single digit
        runTest("Test 3 - Single digit '2'", "2", 3);

        // Test 4: Three digits
        runTest("Test 4 - Three digits '234'", "234", 27);

        // Test 5: Four digits
        runTest("Test 5 - Four digits '2345'", "2345", 81);

        // Test 6: Invalid input
        runTest("Test 6 - Invalid input '1'", "1", 0);
    }
}
