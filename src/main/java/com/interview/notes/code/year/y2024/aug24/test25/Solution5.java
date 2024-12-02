package com.interview.notes.code.year.y2024.aug24.test25;

import java.util.HashSet;
import java.util.Set;

class Solution5 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Solution5 solution = new Solution5();

        // Test case 1
        char[][] board1 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word1 = "ABCCED";
        System.out.println("Test case 1: " + solution.exist(board1, word1)); // Expected: true

        // Test case 2
        char[][] board2 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word2 = "SEE";
        System.out.println("Test case 2: " + solution.exist(board2, word2)); // Expected: true

        // Test case 3
        char[][] board3 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word3 = "ABCB";
        System.out.println("Test case 3: " + solution.exist(board3, word3)); // Expected: false
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        // Create a set of characters in the word for quick lookup
        Set<Character> wordChars = new HashSet<>();
        for (char c : word.toCharArray()) {
            wordChars.add(c);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Only start DFS if the current cell contains a character from the word
                if (wordChars.contains(board[i][j])) {
                    if (dfs(board, word, 0, i, j, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int index, int i, int j, boolean[][] visited) {
        // Base case: all characters in the word have been matched
        if (index == word.length()) {
            return true;
        }

        // Check if current position is out of bounds or already visited or doesn't match current character
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
                visited[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }

        // Mark current cell as visited
        visited[i][j] = true;

        // Recursively search in all four directions
        for (int[] dir : DIRECTIONS) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (dfs(board, word, index + 1, newI, newJ, visited)) {
                return true;
            }
        }

        // Backtrack: mark current cell as unvisited
        visited[i][j] = false;

        return false;
    }
}
