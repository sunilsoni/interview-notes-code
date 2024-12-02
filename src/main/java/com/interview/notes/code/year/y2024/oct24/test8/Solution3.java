package com.interview.notes.code.year.y2024.oct24.test8;

import java.util.TreeSet;

public class Solution3 {

    /**
     * Main method to test the solution with provided test cases and additional edge cases.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Solution3 sol = new Solution3();

        // Test case from the problem statement
        int[][] operations1 = {
                {1, 2},
                {1, 6},
                {2, 4, 2},
                {2, 5, 2},
                {2, 1, 1},
                {2, 1, 2}
        };
        String expectedOutput1 = "1010";
        String actualOutput1 = sol.solution(operations1);
        System.out.println("Test Case 1: " + (expectedOutput1.equals(actualOutput1) ? "PASS" : "FAIL"));

        // Additional test case: No obstacles
        int[][] operations2 = {
                {2, 0, 1},
                {2, 10, 5},
                {2, -5, 3}
        };
        String expectedOutput2 = "111";
        String actualOutput2 = sol.solution(operations2);
        System.out.println("Test Case 2: " + (expectedOutput2.equals(actualOutput2) ? "PASS" : "FAIL"));

        // Additional test case: Obstacle blocks the block
        int[][] operations3 = {
                {1, 0},
                {2, 0, 1},
                {2, 0, 2}
        };
        String expectedOutput3 = "01";
        String actualOutput3 = sol.solution(operations3);
        System.out.println("Test Case 3: " + (expectedOutput3.equals(actualOutput3) ? "PASS" : "FAIL"));

        // Large data test case
        int n = 100000;
        int[][] operations4 = new int[n][];
        for (int i = 0; i < n / 2; i++) {
            operations4[i] = new int[]{1, i};
        }
        for (int i = n / 2; i < n; i++) {
            operations4[i] = new int[]{2, i, 1};
        }
        String actualOutput4 = sol.solution(operations4);
        System.out.println("Test Case 4: " + (actualOutput4.length() == n / 2 ? "PASS" : "FAIL"));
    }

    /**
     * Given an array of operations, processes the operations and returns a binary string representing
     * the outputs for all [2, x, size] operations.
     *
     * @param operations An array of integer arrays representing operations.
     * @return A binary string representing the outputs for all [2, x, size] operations.
     */
    public String solution(int[][] operations) {
        // TreeSet to store obstacles for efficient range queries
        TreeSet<Integer> obstacles = new TreeSet<>();
        StringBuilder output = new StringBuilder();

        for (int[] operation : operations) {
            if (operation[0] == 1) {
                // Build an obstacle at coordinate x
                int x = operation[1];
                obstacles.add(x);
            } else if (operation[0] == 2) {
                // Check if a block can be built centered at x with the given size
                int x = operation[1];
                int size = operation[2];
                int left = x - (size - 1);
                int right = x + (size - 1);

                // Find the smallest obstacle greater than or equal to left
                Integer obstacle = obstacles.ceiling(left);
                if (obstacle != null && obstacle <= right) {
                    output.append('0');
                } else {
                    output.append('1');
                }
            }
        }
        return output.toString();
    }
}
