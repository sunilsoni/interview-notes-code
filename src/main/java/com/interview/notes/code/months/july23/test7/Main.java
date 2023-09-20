package com.interview.notes.code.months.july23.test7;

import java.util.ArrayList;
import java.util.List;

/**
 * You are running a classroom and suspect that some of your students are passing around the
 * answer to a multiple-choice question in 2D grids of letters. The word may start anywhere in
 * the grid, and consecutive letters can be either immediately below or immediately to the right
 * of the previous letter.
 * Given a grid and a word, write a function that returns the location of the word in the grid as
 * a list of coordinates. If there are multiple matches, return any one.
 */
class Main {
    static int[][] dirs = {{0, 1}, {1, 0}};

    public static void main(String[] args) {
        char[][] grid = {
                {'b', 'b', 'a', 'l', 'o', 'o', 'n'},
                {'e', 'c', 's', 's', 'c', 's', 'n'},
                {'a', 'c', 'b', 'e', 'w', 'e', 'w'},
                {'s', 'c', 'a', 'e', 'w', 'c', 'w'},
                {'s', 'o', 'a', 'e', 's', 'o', 'o'},
                {'a', 'o', 'w', 'w', 'b', 'w', 'w'}
        };
        System.out.println(findWord(grid, "access")); // [(0, 2), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4)]
        System.out.println(findWord(grid, "balloon")); // [(0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (1, 6)]
        System.out.println(findWord(grid, "wow")); // [(5, 2), (5, 3), (5, 4)]
        System.out.println(findWord(grid, "sec")); // [(1, 1), (1, 2), (1, 3)]
        System.out.println(findWord(grid, "bbaal")); // [(0, 0), (1, 0), (2, 0), (2, 1), (2, 2)]
    }

    static List<Point> findWord(char[][] grid, String word) {
        int rows = grid.length, cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == word.charAt(0)) {
                    List<Point> path = new ArrayList<>();
                    if (dfs(grid, word, i, j, 0, path)) {
                        return path;
                    }
                }
            }
        }
        return null; // no such word found
    }

    static boolean dfs(char[][] grid, String word, int row, int col, int index, List<Point> path) {
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || row == grid.length || col < 0 || col == grid[0].length || grid[row][col] != word.charAt(index)) {
            return false;
        }

        path.add(new Point(row, col));
        char temp = grid[row][col];
        grid[row][col] = ' '; // mark as visited

        for (int[] dir : dirs) {
            if (dfs(grid, word, row + dir[0], col + dir[1], index + 1, path)) {
                return true;
            }
        }

        grid[row][col] = temp; // backtrack
        path.remove(path.size() - 1); // remove the point from the path

        return false;
    }

    static class Point {
        int row, col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }
}
