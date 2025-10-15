package com.interview.notes.code.year.y2025.october.microsoft.test2;

public class MatrixWordSearch {
    // Main method to test our implementation
    public static void main(String[] args) {
        // Test matrix from the problem
        char[][] matrix = {
            {'b', 'a', 'b'},
            {'c', 'l', 'x'},
            {'d', 'l', 'b'}
        };

        // Test cases
        runTest(matrix, "ball", "Test 1: Should find 'ball'");
        runTest(matrix, "papa", "Test 2: Should not find 'papa'");
        runTest(matrix, "bab", "Test 3: Should find 'bab'");
        runTest(matrix, "xyz", "Test 4: Should not find 'xyz'");
    }

    // Method to run each test case
    private static void runTest(char[][] matrix, String word, String testName) {
        boolean result = findWord(matrix, word);
        System.out.println(testName + " - Word: '" + word + "' - " + 
                         (result ? "FOUND" : "NOT FOUND"));
    }

    // Main method to find word in matrix
    public static boolean findWord(char[][] matrix, String word) {
        // Check for invalid inputs
        if (matrix == null || matrix.length == 0 || word == null || word.isEmpty()) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Try starting from each cell in the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If we find the word starting from current position, return true
                if (searchFromPosition(matrix, i, j, word, 0, new boolean[rows][cols])) {
                    return true;
                }
            }
        }
        return false;
    }

    // Recursive method to search word from a given position
    private static boolean searchFromPosition(char[][] matrix, int row, int col, 
                                           String word, int currentChar, 
                                           boolean[][] visited) {
        // If we've matched all characters, word is found
        if (currentChar == word.length()) {
            return true;
        }

        // Check if position is valid and character matches
        if (!isValidPosition(matrix, row, col) || 
            visited[row][col] || 
            matrix[row][col] != word.charAt(currentChar)) {
            return false;
        }

        // Mark current position as visited
        visited[row][col] = true;

        // Check all four directions
        // Up
        if (searchFromPosition(matrix, row-1, col, word, currentChar+1, visited)) {
            return true;
        }
        // Right
        if (searchFromPosition(matrix, row, col+1, word, currentChar+1, visited)) {
            return true;
        }
        // Down
        if (searchFromPosition(matrix, row+1, col, word, currentChar+1, visited)) {
            return true;
        }
        // Left
        if (searchFromPosition(matrix, row, col-1, word, currentChar+1, visited)) {
            return true;
        }

        // Unmark position (backtrack)
        visited[row][col] = false;
        return false;
    }

    // Helper method to check if position is valid
    private static boolean isValidPosition(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && 
               col >= 0 && col < matrix[0].length;
    }
}
