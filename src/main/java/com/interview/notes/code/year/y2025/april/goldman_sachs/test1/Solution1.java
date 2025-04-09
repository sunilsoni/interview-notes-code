package com.interview.notes.code.year.y2025.april.goldman_sachs.test1;

import java.util.Arrays;

public class Solution1 {

    // Implement the walk method
    public static Integer[] walk(String path) {
        int x = 0, y = 0;
        // Process each character in the path string
        for (char c : path.toCharArray()) {
            switch (c) {
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
                case 'L':
                    x--;
                    break;
                case 'R':
                    x++;
                    break;
                // Ignore all other characters
                default:
                    break;
            }
        }
        return new Integer[]{x, y};
    }

    // Helper method to check equality of two coordinate arrays
    public static boolean checkEqual(Integer[] a, Integer[] b) {
        return Arrays.equals(a, b);
    }

    // Method to run multiple test cases
    public static boolean doTestsPass() {
        boolean res = true;

        // Test case 1: Simple upward moves
        {
            String test = "UUU";
            Integer[] result = walk(test);
            boolean testResult = checkEqual(result, new Integer[]{0, 3});
            System.out.println("Test 'UUU' result: " + Arrays.toString(result) + " Expected: " + Arrays.toString(new Integer[]{0, 3}));
            res &= testResult;
        }

        // Test case 2: Moves that cancel each other
        {
            String test = "ULDR";
            Integer[] result = walk(test);
            boolean testResult = checkEqual(result, new Integer[]{0, 0});
            System.out.println("Test 'ULDR' result: " + Arrays.toString(result) + " Expected: " + Arrays.toString(new Integer[]{0, 0}));
            res &= testResult;
        }

        // Test case 3: Complex sequence of moves
        {
            String test = "ULLLDUDUURLLRLR";
            Integer[] result = walk(test);
            boolean testResult = checkEqual(result, new Integer[]{-2, 2});
            System.out.println("Test 'ULLLDUDUURLLRLR' result: " + Arrays.toString(result) + " Expected: " + Arrays.toString(new Integer[]{-2, 2}));
            res &= testResult;
        }

        // Test case 4: Input with non-move characters (should ignore extra characters)
        {
            String test = "UP LEFT 2xDOWN DOWN RIGHT RIGHT UP UP";
            Integer[] result = walk(test);
            boolean testResult = checkEqual(result, new Integer[]{1, 1});
            System.out.println("Test 'UP LEFT 2xDOWN DOWN RIGHT RIGHT UP UP' result: " + Arrays.toString(result) + " Expected: " + Arrays.toString(new Integer[]{1, 1}));
            res &= testResult;
        }

        // Edge Case: Empty string
        {
            String test = "";
            Integer[] result = walk(test);
            boolean testResult = checkEqual(result, new Integer[]{0, 0});
            System.out.println("Test '' (empty string) result: " + Arrays.toString(result) + " Expected: " + Arrays.toString(new Integer[]{0, 0}));
            res &= testResult;
        }

        // Large Input Test: A large sequence of moves that cancel out
        {
            StringBuilder sb = new StringBuilder();
            int iterations = 1000000; // 1 million iterations
            for (int i = 0; i < iterations; i++) {
                sb.append("U");
                sb.append("R");
                sb.append("D");
                sb.append("L");
            }
            String test = sb.toString();
            Integer[] result = walk(test);
            boolean testResult = checkEqual(result, new Integer[]{0, 0});
            System.out.println("Test large input result: " + Arrays.toString(result) + " Expected: " + Arrays.toString(new Integer[]{0, 0}));
            res &= testResult;
        }

        return res;
    }

    // Execution entry point
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }
    }
}
