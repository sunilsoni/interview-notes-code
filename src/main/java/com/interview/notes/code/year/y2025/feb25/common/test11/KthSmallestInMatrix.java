package com.interview.notes.code.year.y2025.feb25.common.test11;

public class KthSmallestInMatrix {
    public static int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return -1;
        }

        int n = matrix.length;
        // Initialize the search range
        int low = matrix[0][0];          // Smallest element
        int high = matrix[n-1][n-1];     // Largest element

        // Binary search
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = countLessEqual(matrix, mid);

            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // Helper function to count elements less than or equal to target
    private static int countLessEqual(int[][] matrix, int target) {
        int n = matrix.length;
        int count = 0;
        int j = n - 1;  // Start from rightmost column

        // Count elements row by row
        for (int i = 0; i < n; i++) {
            // Move left while elements are greater than target
            while (j >= 0 && matrix[i][j] > target) {
                j--;
            }
            count += (j + 1);  // Add count of elements in current row
        }
        return count;
    }

    // Test method to print matrix
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.print("[");
            for (int i = 0; i < row.length; i++) {
                System.out.print(row[i]);
                if (i < row.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Standard 3x3 matrix
        int[][] matrix1 = {
            {1,  5,  9},
            {10, 11, 13},
            {12, 13, 15}
        };
        testMatrix(matrix1, "Test Case 1");

        // Test Case 2: 4x4 matrix
        int[][] matrix2 = {
            {1,  4,  7,  11},
            {2,  5,  8,  12},
            {3,  6,  9,  13},
            {10, 13, 14, 15}
        };
        testMatrix(matrix2, "Test Case 2");

        // Test Case 3: 2x2 matrix
        int[][] matrix3 = {
            {1, 2},
            {3, 4}
        };
        testMatrix(matrix3, "Test Case 3");

        // Test Case 4: 1x1 matrix
        int[][] matrix4 = {{1}};
        testMatrix(matrix4, "Test Case 4");
    }

    private static void testMatrix(int[][] matrix, String testName) {
        System.out.println("\n" + testName + ":");
        System.out.println("Matrix:");
        printMatrix(matrix);

        // Test different k values
        int n = matrix.length;
        int[] kValues = {1, n, n*n/2, n*n};
        
        for (int k : kValues) {
            if (k <= n*n) {
                int result = kthSmallest(matrix, k);
                System.out.println(k + "th smallest element: " + result);
            }
        }
    }
}
