package com.interview.notes.code.months.Oct23.test10;

/**
 * Time Complexity Analysis:
 *
 * The nested loops iterate through all rows and columns of the N by N multiplication table, which results in a time complexity of O(N^2).
 * Space Complexity Analysis:
 *
 * The algorithm uses a constant amount of extra space, so the space complexity is O(1).
 */
public class MultiplicationTableCount1 {
    public static int countXInMultiplicationTable(int N, int X) {
        int count = 0;
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                int cellValue = row * col;
                if (cellValue == X) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int N = 6;
        int X = 12;
        int result = countXInMultiplicationTable(N, X);
        System.out.println("Output: " + result);
    }
}
