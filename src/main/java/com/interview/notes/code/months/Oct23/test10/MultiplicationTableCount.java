package com.interview.notes.code.months.Oct23.test10;

/**

 * Suppose you have a multiplication table that is N by N. That is, a 2D array where the value at the i-th row and j-th column is (i + 1) * (j + 1) (if 0-indexed) or i * j (if 1-indexed).
 *
 * Given integers N and X, write a function that returns the number of times X appears as a value in an N by N multiplication table.
 *
 * Examples
 *
 * Testcase 1:
 *
 *                 Input: N = 6, X = 12
 *
 *                 Output: 4, since there are four 12’s in a 6x6 multiplication table
 *
 * | 1 | 2 | 3 | 4 | 5 | 6 |
 * | 2 | 4 | 6 | 8 | 10 | 12 |
 * | 3 | 6 | 9 | 12 | 15 | 18 |
 * | 4 | 8 | 12 | 16 | 20 | 24 |
 * | 5 | 10 | 15 | 20 | 25 | 30 |
 * | 6 | 12 | 18 | 24 | 30 | 36 |
 */
public class MultiplicationTableCount {
    public static int countXInMultiplicationTable(int N, int X) {
        int count = 0;
        int row = 1;
        int col = N;

        while (row <= N && col >= 1) {
            int cellValue = row * col;
            if (cellValue == X) {
                count++;
                col--; // Move left to find more occurrences
            } else if (cellValue < X) {
                row++; // Move down to potentially find a larger value
            } else {
                col--; // Move left within the same row
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int N = 6;
        int X = 12;
        int result = countXInMultiplicationTable(N, X);

        int N1 = 500;
        int X1 = 498;
        int result1 = countXInMultiplicationTable(N1, X1);
        System.out.println("Output1: " + result1);
        System.out.println("Output: " + result);
    }
}
