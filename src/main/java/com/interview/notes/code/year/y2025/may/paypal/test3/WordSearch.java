package com.interview.notes.code.year.y2025.may.paypal.test3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordSearch {
    // Direction arrays for 8 possible movements (right, down-right, down, down-left, left, up-left, up, up-right)
    private static final int[] ROW_DIR = {0, 1, 1, 1, 0, -1, -1, -1};
    private static final int[] COL_DIR = {1, 1, 0, -1, -1, -1, 0, 1};

    // Main search method that returns list of coordinates where word is found
    public static List<List<int[]>> findWord(char[][] grid, String word) {
        // Input validation to handle edge cases
        if (grid == null || grid.length == 0 || word == null || word.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<int[]>> results = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;

        // Search from each cell as potential starting point
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == word.charAt(0)) {
                    // Try all 8 directions from this starting point
                    for (int dir = 0; dir < 8; dir++) {
                        List<int[]> path = searchInDirection(grid, word, i, j, dir);
                        if (!path.isEmpty()) {
                            results.add(path);
                        }
                    }
                }
            }
        }
        return results;
    }

    // Helper method to search in a specific direction
    private static List<int[]> searchInDirection(char[][] grid, String word, int row, int col, int direction) {
        List<int[]> path = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        int len = word.length();

        // Check if word fits in this direction
        int endRow = row + ROW_DIR[direction] * (len - 1);
        int endCol = col + COL_DIR[direction] * (len - 1);
        if (endRow < 0 || endRow >= rows || endCol < 0 || endCol >= cols) {
            return path;
        }

        // Check each character along the path
        for (int k = 0; k < len; k++) {
            int newRow = row + ROW_DIR[direction] * k;
            int newCol = col + COL_DIR[direction] * k;
            if (grid[newRow][newCol] != word.charAt(k)) {
                return Collections.emptyList();
            }
            path.add(new int[]{newRow, newCol});
        }
        return path;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases from the problem
        char[][] grid1 = {
            {'b', 'b', 'b', 'a', 'l', 'l', 'o', 'o'},
            {'b', 'a', 'c', 'c', 'e', 's', 'c', 'n'},
            {'a', 'l', 't', 'e', 'w', 'c', 'e', 'w'},
            {'a', 'l', 'o', 's', 's', 'e', 'c', 'c'},
            {'w', 'o', 'o', 'w', 'a', 'c', 'a', 'w'},
            {'i', 'b', 'w', 'o', 'w', 'w', 'o', 'w'}
        };

        // Test words
        String[] testWords = {"access", "balloon", "wow", "sec", "bbaal"};
        
        // Run tests and print results
        for (String word : testWords) {
            List<List<int[]>> results = findWord(grid1, word);
            System.out.println("Word: " + word);
            System.out.println("Found at positions: ");
            for (List<int[]> path : results) {
                System.out.print("[");
                for (int[] pos : path) {
                    System.out.print("(" + pos[0] + "," + pos[1] + ") ");
                }
                System.out.println("]");
            }
            System.out.println();
        }
    }
}
