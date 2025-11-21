package com.interview.notes.code.year.y2025.april.Artech.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralMatrixTraversal {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse top row
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // Traverse right column
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // Traverse bottom row
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // Traverse left column
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        return result;
    }

    // MAIN METHOD for manual testing without JUnit
    public static void main(String[] args) {
        // Test cases
        int[][] matrix1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        int[][] matrix2 = {
                {1}
        };
        int[][] matrix3 = {
                {1, 2},
                {3, 4},
                {5, 6}
        };

        testSpiral(matrix1, Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7));
        testSpiral(matrix2, List.of(1));
        testSpiral(matrix3, Arrays.asList(1, 2, 4, 6, 5, 3));
    }

    private static void testSpiral(int[][] matrix, List<Integer> expected) {
        List<Integer> result = spiralOrder(matrix);
        System.out.println("Expected: " + expected);
        System.out.println("Actual  : " + result);
        System.out.println(result.equals(expected) ? "PASS" : "FAIL");
        System.out.println("----------------------");
    }
}
