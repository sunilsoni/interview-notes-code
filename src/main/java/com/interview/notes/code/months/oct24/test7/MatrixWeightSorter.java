package com.interview.notes.code.months.oct24.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrixWeightSorter {

    public static int[] solution(int[][] matrix) {
        int n = matrix.length;
        List<Element> elements = new ArrayList<>();

        // Calculate the diagonal sum (weight) for each element in the leftmost column
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int row = i, col = 0;

            // Move diagonally up-right
            while (row >= 0 && col < n) {
                sum += matrix[row][col];
                row--;
                col++;
            }

            // Store the element and its weight
            elements.add(new Element(matrix[i][0], sum));
        }

        // Sort by weight, and by value if weights are equal
        elements.sort((a, b) -> {
            if (a.weight == b.weight) {
                return Integer.compare(a.value, b.value);
            }
            return Integer.compare(a.weight, b.weight);
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
