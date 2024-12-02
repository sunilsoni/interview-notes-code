package com.interview.notes.code.year.y2024.oct24.test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixWeightSorter {

    public static int[] solution(int[][] matrix) {
        int n = matrix.length;
        List<Element> elements = new ArrayList<>();

        // Calculate the bouncing diagonal sum (weight) for each element in the leftmost column
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int row = i, col = 0;
            int dir = -1; // Initial direction is upwards
            boolean bounced = false;

            while (col < n && row >= 0 && row < n) {
                sum += matrix[row][col];
                row += dir;
                col++;

                // Handle bouncing at the top of the matrix
                if (row < 0 && !bounced) {
                    bounced = true;
                    dir = 1;    // Change direction to downwards
                    row = 1;    // Adjust row to start moving downwards
                } else if (row < 0 && bounced) {
                    break;      // Can't continue after bouncing once
                } else if (row >= n) {
                    break;      // Can't continue if row exceeds matrix size
                }
            }

            // Store the element and its weight
            elements.add(new Element(matrix[i][0], sum));
        }

        // Sort by weight, and by value if weights are equal
        elements.sort((a, b) -> {
            if (a.weight == b.weight) {
                return Integer.compare(a.value, b.value);  // Sort by value if weights are the same
            }
            return Integer.compare(a.weight, b.weight);   // Sort by weight
        });

        // Extract sorted values from the elements
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = elements.get(i).value;
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] matrix1 = {
                {1, 3, 2, 5},
                {3, 2, 5, 0},
                {9, 0, 1, 3},
                {6, 1, 0, 8}
        };

        int[][] matrix2 = {
                {2, 3, 2},
                {0, 2, 5},
                {1, 0, 1}
        };

        System.out.println(Arrays.toString(solution(matrix1)));  // Output: [1, 9, 3, 6]
        System.out.println(Arrays.toString(solution(matrix2)));  // Output: [1, 2, 0]
    }

    // Helper class to store the value and its weight
    static class Element {
        int value;
        int weight;

        Element(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }
}
