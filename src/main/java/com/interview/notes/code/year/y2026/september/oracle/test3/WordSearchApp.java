package com.interview.notes.code.year.y2026.september.oracle.test3;

public class WordSearchApp { // Defines the main class container to hold our executable program logic
    static void main(String[] args) { // Main method entry point used to execute and verify test cases directly
        char[][] board1 = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}}; // Initializes sample grid 1 for testing standard matching
        boolean r1 = wordExists(board1, "ABCCED"); // Executes search for word ABCCED to check correctness
        System.out.println("Test 1 (ABCCED): " + (r1 ? "PASS" : "FAIL")); // Prints test outcome status for verification
        
        char[][] board2 = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}}; // Initializes sample grid 2 for alternate word search
        boolean r2 = wordExists(board2, "SEE"); // Executes search for word SEE to check correctness
        System.out.println("Test 2 (SEE): " + (r2 ? "PASS" : "FAIL")); // Prints test outcome status for verification
        
        char[][] board3 = {{'A','B'}, {'C','D'}}; // Initializes small grid 3 for negative testing
        boolean r3 = wordExists(board3, "XYZ"); // Executes search for non-existent word XYZ
        System.out.println("Test 3 (XYZ): " + (!r3 ? "PASS" : "FAIL")); // Prints test outcome status for verification
        
        char[][] largeBoard = new char[50][50]; // Creates a large 50x50 grid instance to test performance under heavy data input
        java.util.Arrays.stream(largeBoard).forEach(row -> java.util.Arrays.fill(row, 'A')); // Fills large grid efficiently using Java streams with character 'A'
        boolean largeResult = wordExists(largeBoard, "AAAAA"); // Executes search for a valid repeating sequence on the large grid
        System.out.println("Test 4 (Large Data): " + (largeResult ? "PASS" : "FAIL")); // Prints performance test outcome status
    } // Closes the main method scope

    public static boolean wordExists(char[][] board, String word) { // Public static utility method to initiate grid-wide search
        int rows = board.length; // Stores total row count of the board to establish vertical boundaries
        int cols = board[0].length; // Stores total column count of the board to establish horizontal boundaries
        for (int r = 0; r < rows; r++) { // Outer loop iterating through each row index of the 2D grid
            for (int c = 0; c < cols; c++) { // Inner loop iterating through each column index of the 2D grid
                if (dfs(board, word, r, c, 0)) { // Triggers DFS from current cell and checks if the full word is matched
                    return true; // Immediately returns true if the complete word match is verified
                } // Closes the conditional statement block for successful match
            } // Closes the inner column iteration loop
        } // Closes the outer row iteration loop
        return false; // Returns false if all starting positions fail to produce a match
    } // Closes the wordExists method scope

    private static boolean dfs(char[][] board, String word, int r, int c, int index) { // Private recursive helper method implementing backtracking search
        if (index == word.length()) { // Base case checking if all characters of the target word have been successfully matched
            return true; // Returns true because the full word sequence has been validated
        } // Closes the base case condition block
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != word.charAt(index)) { // Checks out-of-bounds or character mismatch conditions
            return false; // Returns false to prune invalid paths immediately
        } // Closes the boundary and mismatch check block
        char temp = board[r][c]; // Temporarily stores the current cell character to enable state restoration later
        board[r][c] = '#'; // Marks current cell as visited using a special marker to prevent looping back onto itself
        boolean found = dfs(board, word, r + 1, c, index + 1) || // Recursively checks the downward adjacent cell
                        dfs(board, word, r - 1, c, index + 1) || // Recursively checks the upward adjacent cell
                        dfs(board, word, r, c + 1, index + 1) || // Recursively checks the rightward adjacent cell
                        dfs(board, word, r, c - 1, index + 1); // Recursively checks the leftward adjacent cell
        board[r][c] = temp; // Restores the original character value of the cell during backtracking phase
        return found; // Returns the aggregated boolean search result from the available directions
    } // Closes the dfs helper method scope
} // Closes the main application class scope