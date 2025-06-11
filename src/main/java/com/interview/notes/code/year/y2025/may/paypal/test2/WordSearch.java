package com.interview.notes.code.year.y2025.may.paypal.test2;

import java.util.*;

public class WordSearch {
    // Store directions for moving right and down
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}};
    
    // Main class to test the solution
    public static void main(String[] args) {
        // Test case 1: Basic grid with multiple words
        char[][] grid1 = {
            {'b', 'b', 'b', 'a', 'l', 'l', 'o', 'o'},
            {'b', 'a', 'c', 'c', 'e', 's', 'c', 'n'},
            {'a', 'l', 't', 'e', 'w', 'c', 'e', 'w'},
            {'a', 'l', 'o', 's', 's', 'e', 'c', 'c'},
            {'w', 'o', 'o', 'w', 'a', 'c', 'a', 'w'},
            {'i', 'b', 'w', 'o', 'w', 'w', 'o', 'w'}
        };
        
        // Test cases with different words
        testSearch(grid1, "bball", "Test 1"); // Should find the word
        testSearch(grid1, "access", "Test 2"); // Should find the word
        testSearch(grid1, "wow", "Test 3"); // Should find multiple paths
        testSearch(grid1, "xyz", "Test 4"); // Should not find the word
        
        // Test case 2: Single character grid
        char[][] grid2 = {{'a'}};
        testSearch(grid2, "a", "Test 5"); // Should find the word
        testSearch(grid2, "b", "Test 6"); // Should not find the word
    }
    
    // Method to search for a word in the grid
    public static List<int[]> search(char[][] grid, String word) {
        // Validate input parameters
        if (grid == null || word == null || grid.length == 0) {
            return Collections.emptyList();
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Try starting from each cell in the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                List<int[]> path = new ArrayList<>();
                // If we find a path starting at this cell, return it
                if (dfs(grid, i, j, word, 0, path)) {
                    return path;
                }
            }
        }
        
        return Collections.emptyList();
    }
    
    // DFS helper method to find the word path
    private static boolean dfs(char[][] grid, int row, int col, String word, 
                             int index, List<int[]> path) {
        // Base case: if we've matched all characters
        if (index == word.length()) {
            return true;
        }
        
        // Check if current position is valid
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length 
            || grid[row][col] != word.charAt(index)) {
            return false;
        }
        
        // Add current position to path
        path.add(new int[]{row, col});
        
        // Try moving right or down
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (dfs(grid, newRow, newCol, word, index + 1, path)) {
                return true;
            }
        }
        
        // If no path found, backtrack by removing current position
        path.remove(path.size() - 1);
        return false;
    }
    
    // Helper method to test and print results
    private static void testSearch(char[][] grid, String word, String testName) {
        List<int[]> result = search(grid, word);
        System.out.println(testName + ": Searching for '" + word + "'");
        if (result.isEmpty()) {
            System.out.println("Word not found");
        } else {
            System.out.print("Found at coordinates: ");
            for (int[] coord : result) {
                System.out.print("(" + coord[0] + "," + coord[1] + ") ");
            }
            System.out.println();
        }
    }
}
