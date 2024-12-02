package com.interview.notes.code.year.y2024.nov24.amazon.test27;

import java.util.Arrays;

public class CharacterPositionFinder {

    public static void main(String[] args) {
        // Test cases
        testCase("infosys", 'i', 0);
        testCase("hello", 'l', 3);
        testCase("aabbccddee", 'c', 5);
        testCase("zzzzaaaa", 'z', 7);
        testCase("", 'a', -1);  // Edge case: empty string
        testCase("abcdefghijklmnopqrstuvwxyz", 'z', 25);  // Large input
        testCase("aaaaaaaaaaaaaaaaaaaa", 'a', 19);  // Repeated characters
    }

    public static void testCase(String input, char target, int expected) {
        int result = findHighestPosition(input, target);
        boolean passed = result == expected;
        System.out.printf("Input: %s, Target: %c, Expected: %d, Result: %d, %s%n",
                input, target, expected, result, passed ? "PASS" : "FAIL");
    }

    public static int findHighestPosition(String input, char target) {
        if (input == null || input.isEmpty()) {
            return -1;
        }

        // Convert string to char array and sort
        char[] chars = input.toCharArray();
        Arrays.sort(chars);

        // Find the highest position of the target character
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == target) {
                return i;
            }
        }

        return -1;  // Character not found
    }
}
