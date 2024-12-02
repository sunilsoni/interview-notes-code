package com.interview.notes.code.year.y2024.feb24.test3;

//ServiceNow
public class BinarySearchMatrix {

    // Binary search on each row that returns the position of the target if found
    private static String binarySearchRow(int[][] matrix, int target, int row) {
        int left = 0, right = matrix[0].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[row][mid] == target) {
                return "(" + row + ", " + mid + ")"; // Target found, return position
            } else if (matrix[row][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ""; // Target not found in this row
    }

    // Method to search for a target value in the matrix and return its position
    public static String searchMatrix(int[][] matrix, int target) {
        for (int row = 0; row < matrix.length; row++) {
            String result = binarySearchRow(matrix, target, row);
            if (!result.isEmpty()) {
                return "Target " + target + " found: " + result;
            }
        }
        return "Target " + target + " not found";
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

        String found1 = searchMatrix(matrix, target1);
        String found2 = searchMatrix(matrix, target2);

        System.out.println(found1); // Should print the position of target1
        System.out.println(found2); // Should indicate target2 is not found
    }
}
