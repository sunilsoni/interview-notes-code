package com.interview.notes.code.year.y2025.october.microsoft.test2;

public class WordSearchMatrix {

    // Main method to test our word search implementation
    public static void main(String[] args) {
        // Test Case 1: Basic test with the given example
        char[][] matrix1 = {
                {'b', 'a', 'b'},
                {'c', 'l', 'x'},
                {'d', 'l', 'b'}
        };
        testCase(matrix1, "ball", "Test Case 1 - Should find 'ball'");

        // Test Case 2: Empty matrix
        char[][] matrix2 = {};
        testCase(matrix2, "ball", "Test Case 2 - Empty matrix");

        // Test Case 3: Single character matrix
        char[][] matrix3 = {{'b'}};
        testCase(matrix3, "ball", "Test Case 3 - Single character matrix");

        // Test Case 4: Large matrix test
        char[][] matrix4 = generateLargeMatrix(1000, 1000);
        testCase(matrix4, "ball", "Test Case 4 - Large matrix (1000x1000)");
    }

    // Method to search for word in the matrix
    public static boolean searchWord(char[][] matrix, String word) {
        // Check for null or empty inputs
        if (matrix == null || matrix.length == 0 || word == null || word.isEmpty()) {
            return false;
        }

        // Get matrix dimensions
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Try each cell as starting point
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If we find the word starting at current position, return true
                if (searchFromCell(matrix, i, j, word, 0, new boolean[rows][cols])) {
                    return true;
                }
            }
        }
        return false;
    }

    // Recursive method to search word from a specific cell
    private static boolean searchFromCell(char[][] matrix, int row, int col,
                                          String word, int index, boolean[][] visited) {
        // Base case: if we've matched all characters
        if (index == word.length()) {
            return true;
        }

        // Check if current position is valid
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length ||
                visited[row][col] || matrix[row][col] != word.charAt(index)) {
            return false;
        }

        // Mark current cell as visited
        visited[row][col] = true;

        // Define possible movements (up, right, down, left)
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        // Try all possible directions
        for (int dir = 0; dir < 4; dir++) {
            int newRow = row + dx[dir];
            int newCol = col + dy[dir];

            if (searchFromCell(matrix, newRow, newCol, word, index + 1, visited)) {
                return true;
            }
        }

        // Backtrack: mark cell as unvisited
        visited[row][col] = false;
        return false;
    }

    // Helper method to test cases
    private static void testCase(char[][] matrix, String word, String testName) {
        boolean result = searchWord(matrix, word);
        System.out.println(testName + ": " + (result ? "PASS" : "FAIL"));
    }

    // Helper method to generate large test matrix
    private static char[][] generateLargeMatrix(int rows, int cols) {
        char[][] matrix = new char[rows][cols];
        // Fill matrix with random characters
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = (char) ('a' + (Math.random() * 26));
            }
        }
        // Ensure the word "ball" exists in the matrix
        matrix[0][0] = 'b';
        matrix[0][1] = 'a';
        matrix[0][2] = 'l';
        matrix[0][3] = 'l';
        return matrix;
    }
}
