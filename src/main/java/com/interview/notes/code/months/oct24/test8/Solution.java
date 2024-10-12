package com.interview.notes.code.months.oct24.test8;

import java.util.TreeSet;

/*
WORKING



Given an infinite integer number line, you would like to build some blocks and obstacles on it. Specifically, you have to implement code which supports two types of operations:
• [1, x] - builds an obstacle at coordinate x along the number line. It is guaranteed that coordinate × does not contain any obstacles when the operation is performed.
• [2, x, size] - checks whether it's possible to build a block centered on x and extending size - 1 in each direction. For example, for size = 3 and x = 0, it will check -2 through 2 on the number line for obstacles. Produces "1" if it is possible, i.e., if there are no obstacles at the specified coordinates, and produces "0" otherwise. Please note that this operation does not actually build the block, it only checks whether a block can be built.
Given an array of operations containing both types of operations above, your task is to return a binary string representing the outputs for all [2, x, sizel operations.
Example
For
operations = [[1, 2],
[1, 61,
[2, 4, 21,
[2, 5, 21,
[2, 1, 1],
[2, 1, 211
the output should be solution (operations) = "1010" .
Explanation:
Let's consider all operations:
• [1, 2] - builds an obstacle at coordinate 2 .
• [1, 61 - builds an obstacle at coordinate 6 .
• [2, 4, 2] - checks and produces "1" as it is possible to build a block occupying coordinates 3, 4 , and 5 .
• [2, 5, 21 - checks and produces "o" as it is not possible to build a block occupying coordinates 4, 5, and 6, because there is an obstacle at coordinate 6 .
• [2, 1, 11 - checks and produces "1" as it is possible to build a block occupying coordinate 1 (because size = 1, only one coordinate should be checked).
• [2, 1, 21 - checks and produces "o" as it is not possible to build a block occupying coordinates o, 1, and 2, because there is an obstacle at coordinate 2 .
So the output is "1010" .
• Expand to see the example video.
Input/Output
Input/Output
• [execution time limit] 3 seconds (java)
• [memory limit] 1 GB
• [input] array.array.integer operations
An array of integer arrays representing one of the two types of operations described above. All coordinates within the operations are within the interval 1-10%, 10º] . The size from the second type of operation are positive integers which do not exceed 10° .
Guaranteed constraints:
1 ≤ operations. length ≤ 10 power 5
• [output] string
A binary string representing the outputs for all [2, x, size] operations.


String solution (int[][] operations){

}
}

 */
public class Solution {

    /**
     * Main method to test the solution with provided test cases and additional edge cases.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Solution sol = new Solution();

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

        // Corrected Test Case 3: Obstacle blocks the block
        int[][] operations3 = {
                {1, 0},
                {2, 0, 1},
                {2, 0, 2}
        };
        String expectedOutput3 = "00";
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
        boolean largeTestPass = actualOutput4.length() == n / 2 && actualOutput4.chars().allMatch(c -> c == '1' || c == '0');
        System.out.println("Test Case 4: " + (largeTestPass ? "PASS" : "FAIL"));
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

                // Check if there is any obstacle within the range [left, right]
                if (obstacles.subSet(left, true, right, true).isEmpty()) {
                    output.append('1');
                } else {
                    output.append('0');
                }
            }
        }
        return output.toString();
    }
}
