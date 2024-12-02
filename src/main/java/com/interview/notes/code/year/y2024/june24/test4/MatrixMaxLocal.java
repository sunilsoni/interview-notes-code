package com.interview.notes.code.year.y2024.june24.test4;

public class MatrixMaxLocal {
    public static int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] maxLocal = new int[n - 2][n - 2];

        for (int i = 0; i <= n - 3; i++) {
            // Initialize max for the first block in each row
            int max = Integer.MIN_VALUE;
            for (int ki = i; ki < i + 3; ki++) {
                for (int kj = 0; kj < 3; kj++) {
                    max = Math.max(max, grid[ki][kj]);
                }
            }
            maxLocal[i][0] = max;

            // Slide the window across the row
            for (int j = 1; j <= n - 3; j++) {
                max = Integer.MIN_VALUE;
                for (int ki = i; ki < i + 3; ki++) {
                    // Remove the leftmost column and add a new column on the right
                    // Note: This can be further optimized by not recalculating the entire 3x3 every time
                    for (int kj = j; kj < j + 3; kj++) {
                        max = Math.max(max, grid[ki][kj]);
                    }
                }
                maxLocal[i][j] = max;
            }
        }

        return maxLocal;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {9, 9, 8, 1},
                {5, 6, 2, 6},
                {8, 2, 6, 4},
                {6, 2, 2, 2}
        };
        int[][] result = largestLocal(grid);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
