package com.interview.notes.code.year.y2025.april.goldman_sachs.test2;

import java.util.Arrays;

public class Solution {

    /**
     * Calculates final (x,y) position after following the path instructions.
     * Only 'U', 'D', 'L', 'R' are considered valid moves.
     * All other characters are ignored.
     *
     * @param path the string representing robot moves
     * @return Integer[] of size 2, containing final {x, y} coordinates.
     */
    public static Integer[] walk(String path) {
        int x = 0;
        int y = 0;

        if (path == null) {
            // Handle null input by returning the initial position
            return new Integer[]{x, y};
        }

        // Loop through each character in the path
        for (char c : path.toCharArray()) {
            switch (c) {
                case 'U': y++; break;
                case 'D': y--; break;
                case 'L': x--; break;
                case 'R': x++; break;
                default:  /* ignore any other character */ 
                    break;
            }
        }

        return new Integer[]{x, y};
    }

    /**
     * Utility method to check equality of two Integer arrays.
     */
    public static boolean checkEqual(Integer[] a, Integer[] b) {
        return Arrays.equals(a, b);
    }

    /**
     * Test harness to verify if all tests pass.
     * Returns true if all tests pass, false otherwise.
     */
    public static boolean doTestsPass() {
        boolean allPass = true;

        // Test 1
        {
            String test = "UUU";
            Integer[] result = walk(test);
            allPass &= checkEqual(result, new Integer[]{0, 3});
        }

        // Test 2
        {
            String test = "ULDR";
            Integer[] result = walk(test);
            allPass &= checkEqual(result, new Integer[]{0, 0});
        }

        // Test 3
        {
            String test = "ULLLDUDUURLLRLR";
            Integer[] result = walk(test);
            allPass &= checkEqual(result, new Integer[]{-2, 2});
        }

        // Test 4
        {
            // This test checks ignoring letters beyond U/D/L/R
            String test = "UP LEFT 2xDOWN DOWN RIGHT RIGHT UP UP";
            Integer[] result = walk(test);
            allPass &= checkEqual(result, new Integer[]{1, 1});
        }

        // Additional edge test: Empty string
        {
            String test = "";
            Integer[] result = walk(test);
            allPass &= checkEqual(result, new Integer[]{0, 0});
        }

        // Additional edge test: Large input (concept demonstration)
        {
            // We'll generate a long string of repeated moves
            // e.g., 100000 'U's and 100000 'D's (should net to 0 on y-axis)
            // This checks performance for large data
            StringBuilder largePath = new StringBuilder();
            for (int i = 0; i < 100000; i++) {
                largePath.append('U');
            }
            for (int i = 0; i < 100000; i++) {
                largePath.append('D');
            }
            // Expect final position: (0,0)
            Integer[] result = walk(largePath.toString());
            allPass &= checkEqual(result, new Integer[]{0, 0});
        }

        return allPass;
    }

    /**
     * Main method to run tests without JUnit.
     */
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }
    }
}
