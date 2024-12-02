package com.interview.notes.code.year.y2024.oct24.test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Solution2 {
    public static int[] solution(int[][] matrix) {
        int n = matrix.length;
        int[] leftmostColumn = new int[n];
        int[] weights = new int[n];

        // Extract leftmost column and calculate weights
        for (int i = 0; i < n; i++) {
            leftmostColumn[i] = matrix[i][0];
            weights[i] = calculateWeight(matrix, i, 0);
        }

        // Create a list of indices and sort it based on weights and values
        List<Integer> indices = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            indices.add(i);
        }

        indices.sort((a, b) -> {
            if (weights[a] != weights[b]) {
                return Integer.compare(weights[a], weights[b]);
            }
            return Integer.compare(leftmostColumn[a], leftmostColumn[b]);
        });

        // Create the result array based on the sorted indices
        return indices.stream().mapToInt(i -> leftmostColumn[i]).toArray();
    }

    private static int calculateWeight(int[][] matrix, int row, int col) {
        int n = matrix.length;
        int sum = 0;
        while (col < n) {
            sum += matrix[row][col];
            row = (row - 1 + n) % n;  // Move up, wrapping around if necessary
            col++;
        }
        return sum;
    }

    public static void main(String[] args) {
        runTests();
    }

    public static void runTests() {
        List<TestCase> testCases = new ArrayList<>();

        // Provided test cases
        testCases.add(new TestCase(
                new int[][]{{2, 3, 2}, {0, 2, 5}, {1, 0, 1}},
                new int[]{1, 2, 0}
        ));
        testCases.add(new TestCase(
                new int[][]{{1, 3, 2, 5}, {3, 2, 5, 0}, {9, 0, 1, 3}, {6, 1, 0, 8}},
                new int[]{1, 9, 3, 6}
        ));

        // Edge cases
        testCases.add(new TestCase(
                new int[][]{{1}},
                new int[]{1}
        ));
        testCases.add(new TestCase(
                new int[][]{{1, 2}, {3, 4}},
                new int[]{1, 3}
        ));

        // Large data case
        int[][] largeMatrix = new int[500][500];
        Random rand = new Random(0);  // Use a seed for reproducibility
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                largeMatrix[i][j] = rand.nextInt(1001);  // 0 to 1000
            }
        }
        testCases.add(new TestCase(largeMatrix, null));  // We'll verify this separately

        // Run tests
        for (int i = 0; i < testCases.size(); i++) {
            TestCase testCase = testCases.get(i);
            long startTime = System.nanoTime();
            int[] result = solution(testCase.input);
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1e9;  // Convert to seconds

            if (i == testCases.size() - 1) {  // Large data case
                System.out.printf("Test case %d (Large data): %s (%.3f seconds)\n",
                        i + 1, duration <= 3 ? "PASS" : "FAIL", duration);
            } else if (Arrays.equals(result, testCase.expectedOutput)) {
                System.out.printf("Test case %d: PASS (%.3f seconds)\n", i + 1, duration);
            } else {
                System.out.printf("Test case %d: FAIL (%.3f seconds)\n", i + 1, duration);
                System.out.println("Expected: " + Arrays.toString(testCase.expectedOutput));
                System.out.println("Actual: " + Arrays.toString(result));
            }
        }
    }

    static class TestCase {
        int[][] input;
        int[] expectedOutput;

        TestCase(int[][] input, int[] expectedOutput) {
            this.input = input;
            this.expectedOutput = expectedOutput;
        }
    }
}
