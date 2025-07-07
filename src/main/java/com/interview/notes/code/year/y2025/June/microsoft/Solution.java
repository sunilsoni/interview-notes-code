package com.interview.notes.code.year.y2025.June.microsoft;

public class Solution {
    public static boolean level(int[] obstacles, String instructions) {
        int position = 0;
        char previousMove = 'R';
        final int MAX_POSITION = 10;

        for (char instruction : instructions.toCharArray()) {
            int prevPosition = position;
            
            switch (instruction) {
                case 'R':
                    position++;
                    previousMove = 'R';
                    break;
                case 'L':
                    if (position > 0) {
                        position--;
                        previousMove = 'L';
                    }
                    break;
                case 'J':
                    if (previousMove == 'R') {
                        position += 2;
                    } else {
                        position = Math.max(0, position - 2);
                    }
                    break;
            }

            position = Math.min(position, MAX_POSITION);

            for (int obstacle : obstacles) {
                if (position == obstacle) {
                    return false;
                }
            }
        }

        return position == MAX_POSITION;
    }

    public static void main(String[] args) {
        int[] obstacles_1 = {4, 6};
        int[] obstacles_2 = {9, 4, 2};
        int[] obstacles_3 = {};

        // Test cases with pass/fail validation
        testCase("Test 1", obstacles_1, "RRRJJRRR", true);
        testCase("Test 2", obstacles_1, "RRRLJ", false);
        testCase("Test 3", obstacles_1, "RRRJJRRRL", true);
        testCase("Test 4", obstacles_1, "RRRLRJJRRR", true);
        testCase("Test 5", obstacles_1, "RRRRRRRRRR", false);
        testCase("Test 6", obstacles_1, "RRJJJJ", false);
        testCase("Test 7", obstacles_1, "RLRRRJJRRLLJJJLRRRJJRRR", true);
        testCase("Test 8", obstacles_1, "RRRJJRLJJJRRR", false);
        testCase("Test 9", obstacles_1, "R", false);
        testCase("Test 10", obstacles_1, "RJJJJR", true);
        testCase("Test 11", obstacles_2, "RJJRRRRR", false);
        testCase("Test 12", obstacles_2, "RJJRRRJ", true);
        testCase("Test 13", obstacles_2, "RJJJLJRJRJ", false);
        testCase("Test 14", obstacles_3, "R", false);
    }

    private static void testCase(String testName, int[] obstacles, String instructions, boolean expected) {
        boolean result = level(obstacles, instructions);
        boolean passed = result == expected;
        System.out.printf("%-8s | Instructions: %-20s | Expected: %-5b | Got: %-5b | %s%n",
                testName,
                instructions,
                expected,
                result,
                passed ? "PASS ✅" : "FAIL ❌");
    }
}
