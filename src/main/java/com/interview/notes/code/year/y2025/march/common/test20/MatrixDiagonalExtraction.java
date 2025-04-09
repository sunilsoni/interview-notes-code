package com.interview.notes.code.year.y2025.march.common.test20;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixDiagonalExtraction {

    public static List<List<Integer>> extractPattern(int[][] m) {
        List<List<Integer>> result = new ArrayList<>();
        int n = m.length;

        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;

        while (top <= bottom && left <= right) {
            // Top row left to right
            List<Integer> topRow = new ArrayList<>();
            for (int i = left; i <= right; i++) {
                topRow.add(m[top][i]);
            }
            result.add(topRow);
            top++;

            if (top > bottom) break;

            // Bottom row right to left
            List<Integer> bottomRow = new ArrayList<>();
            for (int i = right; i >= left; i--) {
                bottomRow.add(m[bottom][i]);
            }
            result.add(bottomRow);
            bottom--;

            if (left > right) break;

            // First column from bottom to top (excluding already printed elements)
            if (left <= right) {
                List<Integer> firstCol = new ArrayList<>();
                for (int i = bottom; i >= top; i--) {
                    firstCol.add(m[i][left]);
                }
                if (!firstCol.isEmpty()) result.add(firstCol);
                left++;
            }

            if (left > right) break;

            // Last column from top to bottom (excluding already printed elements)
            if (left <= right) {
                List<Integer> lastCol = new ArrayList<>();
                for (int i = top; i <= bottom; i++) {
                    lastCol.add(m[i][right]);
                }
                if (!lastCol.isEmpty()) result.add(lastCol);
                right--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 1},
                {2, 3, 4, 5, 6},
                {7, 8, 9, 1, 2},
                {3, 4, 5, 6, 7}
        };

        List<List<Integer>> extracted = extractPattern(matrix);

        String formatted = extracted.stream()
                .map(list -> list.stream().map(Object::toString).collect(Collectors.joining(" ")))
                .collect(Collectors.joining(" | "));

        System.out.println("Result: " + formatted);

        String expected = "1 2 3 4 5 | 7 6 5 4 3 | 7 3 6 | 1 6 2 | 7 8 9 | 1 9 8 | 4";

        if (formatted.equals(expected)) {
            System.out.println("Test PASSED");
        } else {
            System.out.println("Test FAILED");
        }
    }
}