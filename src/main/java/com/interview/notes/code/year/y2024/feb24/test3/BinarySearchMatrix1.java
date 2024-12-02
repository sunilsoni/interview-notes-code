package com.interview.notes.code.year.y2024.feb24.test3;

public class BinarySearchMatrix1 {

    // Binary search on each row
    private static boolean binarySearchRow(int[][] matrix, int target, int row) {
        int left = 0, right = matrix[0].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    // Method to search for a target value in the matrix
    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int row = 0; row < matrix.length; row++) {
            if (binarySearchRow(matrix, target, row)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {9, 19, 29, 39},
                {14, 24, 34, 44},
                {26, 28, 36, 47},
                {31, 32, 38, 491}
        };

        int target1 = 28;
        int target2 = 100;

        boolean found1 = searchMatrix(matrix, target1);
        boolean found2 = searchMatrix(matrix, target2);

        System.out.println("Target " + target1 + " found: " + found1);
        System.out.println("Target " + target2 + " not found: " + found2);
    }
}
