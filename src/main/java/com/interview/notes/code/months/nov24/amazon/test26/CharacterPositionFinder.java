package com.interview.notes.code.months.nov24.amazon.test26;

import java.util.Comparator;

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

        return input.chars()
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(ch -> ch)
                .filter(ch -> ch == target)
                .findFirst()
                .stream()
                .mapToObj(Integer::valueOf)
                .map(ch -> (int) input.chars()
                        .sorted()
                        .filter(c -> c <= ch)
                        .count() - 1)
                .findFirst()
                .orElse(-1);
    }
}
