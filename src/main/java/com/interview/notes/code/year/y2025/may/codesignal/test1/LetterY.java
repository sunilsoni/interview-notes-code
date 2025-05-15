package com.interview.notes.code.year.y2025.may.codesignal.test1;

public class LetterY {

    public static int solution(int[][] matrix) {
        int n = matrix.length;
        int mid = n / 2;

        // Count how many cells of each value lie on the "Y" and off the "Y"
        int[] shapeCount = new int[3];
        int[] bgCount    = new int[3];
        int shapeCells = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // on the two diagonals down to center, or vertical from center down
                boolean onDiagonalArm = (i <= mid && (i == j || j == n - 1 - i));
                boolean onStem        = (i >= mid && j == mid);
                if (onDiagonalArm || onStem) {
                    shapeCount[matrix[i][j]]++;
                    shapeCells++;
                } else {
                    bgCount[matrix[i][j]]++;
                }
            }
        }

        int totalCells = n * n;
        int bgCells    = totalCells - shapeCells;
        int best = Integer.MAX_VALUE;

        // Try every (A = shape-value, B = background-value), A≠B
        for (int A = 0; A < 3; A++) {
            for (int B = 0; B < 3; B++) {
                if (A == B) continue;
                // cost = change all shape-cells that ≠ A + change all bg-cells that ≠ B
                int cost = (shapeCells - shapeCount[A]) + (bgCells - bgCount[B]);
                best = Math.min(best, cost);
            }
        }

        return best;
    }

    // quick smoke tests
    public static void main(String[] args) {
        int[][] m1 = {
            {1, 0, 2},
            {1, 2, 0},
            {0, 2, 0}
        };
        // expected 2
        System.out.println(solution(m1));  

        int[][] m2 = {
            {2,0,0,0,2},
            {1,2,1,2,0},
            {0,1,2,1,0},
            {0,0,2,1,1},
            {1,1,2,1,1}
        };
        // expected 8
        System.out.println(solution(m2));  
    }
}