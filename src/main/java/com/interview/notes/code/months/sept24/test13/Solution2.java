package com.interview.notes.code.months.sept24.test13;

import java.util.TreeSet;

/*


Given an infinite integer number line, you would like to build some blocks and obstacles on it. Specifically, you have to implement code which supports two types of operations:
• [1, x] - builds an obstacle at coordinate x along the number line. It is guaranteed that coordinate x does not contain any obstacles when the operation is performed.
• [2, x, size] - checks whether it's possible to build a block of size size which ends immediately before x on the number line. For example, if x = 6 and size = 2, this operation checks coordinates 4 and 5. Produces
"I" if it is possible, i.e. if there are no obstacles at the specified coordinates, or produces "O" otherwise. Please note that this operation does not actually build the block, it only checks whether a block can be built.
Given an array of operations containing both types of operations described above, your task is to return a binary string representing the outputs for all [2, x, size] operations.
Example
For
operations = [[1, 2],
[1, 5],
[2, 5, 2],
[2, 6, 31,
[2, 2, 1],
[2, 3, 21]
the output should be solution(operations) = "1010" -
Explanation:
Let's consider all operations:
• [1, 2] - builds an obstacle at coordinate 2 •
• [1, 5] - builds an obstacle at coordinate 5.
• [2, 5, 2] - checks and produces "1" as it is possible to build a block occupying coordinates 3 and 4.
• [2, 6, 3] - checks and produces "O" as it is not possible to build a block occupying coordinates 3, 4, and 5, because there is an obstacle at coordinate 5
• [2, 2, 1] - checks and produces "1" as it is possible to build a block occupying coordinate 1.
• [2, 3, 2] - checks and produces "0" as it is not possible to build a block occupying coordinates 1 and 2 because there is an obstacle at coordinate 2
So the output is "1010"
Input/Output
• [execution time limit] 3 seconds (java)
• [memory limit] 1 GB-
• [input] array.array.integer operations
An array of integer arrays representing one of the two types of operations described above. All coordinates within operations are within the interval [-10 power 9, 10 power 9]. The size from the second type of operations are positive integers which do not exceed 10 power 9 .
Guaranteed constraints:
1 ≤ operations. length ≤ 10 power 5
• [output] string
A binary string representing the outputs for all [2, x, size] operations.

 */
public class Solution2 {
    private static final int BUILD = 1;
    private static final int CHECK = 2;

    public static String solution(int[][] operations) {
        TreeSet<Integer> obstacles = new TreeSet<>();
        StringBuilder result = new StringBuilder();

        for (int[] operation : operations) {
            if (operation[0] == BUILD) {
                obstacles.add(operation[1]);
            } else if (operation[0] == CHECK) {
                int x = operation[1];
                int size = operation[2];
                int start = x - size;

                Integer floor = obstacles.floor(x - 1);
                Integer ceiling = obstacles.ceiling(start);

                if ((floor == null || floor < start) && (ceiling == null || ceiling >= x)) {
                    result.append("1");
                } else {
                    result.append("0");
                }
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        int[][] operations1 = {{1, 2}, {1, 5}, {2, 5, 2}, {2, 6, 3}, {2, 2, 1}, {2, 3, 2}};
        System.out.println("Test Case 1: " + solution(operations1));

        int[][] operations2 = {{2, 5, 3}, {1, 3}, {2, 5, 3}, {1, 4}, {2, 5, 3}};
        System.out.println("Test Case 2: " + solution(operations2));

        int[][] operations3 = {{1, 0}, {1, 10}, {2, 5, 5}};
        System.out.println("Test Case 3: " + solution(operations3));
    }
}