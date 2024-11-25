package com.interview.notes.code.months.nov24.CodeSignal.test3;

public class MatrixYSolution {
    public static int solution(int[][] matrix) {
        int n = matrix.length;
        int minChanges = Integer.MAX_VALUE;
        int[][] combinations = {{0,1}, {0,2}, {1,0}, {1,2}, {2,0}, {2,1}};
        
        for (int[] combo : combinations) {
            int yValue = combo[0];
            int bgValue = combo[1];
            int changes = countChangesNeeded(matrix, yValue, bgValue);
            minChanges = Math.min(minChanges, changes);
        }
        
        return minChanges;
    }
    
    private static int countChangesNeeded(int[][] matrix, int yValue, int bgValue) {
        int n = matrix.length;
        int center = n / 2;
        int changes = 0;
        
        // Count changes needed for Y pattern
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int currentValue = matrix[i][j];
                if (isYPosition(i, j, n)) {
                    // Cell should be part of Y
                    if (currentValue != yValue) changes++;
                } else {
                    // Cell should be background
                    if (currentValue != bgValue) changes++;
                }
            }
        }
        return changes;
    }
    
    private static boolean isYPosition(int i, int j, int n) {
        int center = n / 2;
        
        // Stem of Y
        if (j == center && i >= center) {
            return true;
        }
        
        // Arms of Y
        if (i < center) {
            // Left diagonal
            if (i == j) return true;
            // Right diagonal
            if (i + j == n - 1) return true;
        }
        
        return false;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] test1 = {
            {1, 0, 2},
            {1, 2, 0},
            {0, 2, 0}
        };
        
        int[][] test2 = {
            {2, 0, 0, 0, 2},
            {0, 2, 1, 2, 0},
            {0, 1, 2, 1, 0},
            {0, 0, 2, 1, 1},
            {1, 1, 2, 1, 1}
        };
        
        // Additional test case (7x7)
        int[][] test3 = new int[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                test3[i][j] = (i + j) % 3;
            }
        }

        // Run all tests
        runTest("Test 1 (3x3)", test1, 2);
        runTest("Test 2 (5x5)", test2, 8);
        runTest("Test 3 (7x7)", test3, -1);
        
        // Additional verification for Test 2
        int result = solution(test2);
        if (result == 8) {
            System.out.println("\nDetailed verification for Test 2:");
            System.out.println("Original matrix:");
            printMatrix(test2);
            System.out.println("Required changes: " + result);
        }
    }
    
    private static void runTest(String testName, int[][] input, int expectedOutput) {
        long startTime = System.nanoTime();
        int result = solution(input);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0;
        
        boolean passed = expectedOutput == -1 || result == expectedOutput;
        System.out.printf("%s: %s (Result: %d, Expected: %d, Time: %.2f ms)%n",
                         testName,
                         passed ? "PASS" : "FAIL",
                         result,
                         expectedOutput,
                         duration);
    }
    
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
