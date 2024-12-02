package com.interview.notes.code.year.y2024.aug24.test15;

import java.util.ArrayList;
import java.util.List;

public class MazeWordFinder {
    private final char[][] maze;
    private final int rows;
    private final int cols;

    public MazeWordFinder(char[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
    }

    // Example usage
    public static void main(String[] args) {
        char[][] maze = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        MazeWordFinder finder = new MazeWordFinder(maze);
        List<List<Cell>> paths = finder.findInMaze("ABCCED");

        System.out.println("Paths found: " + paths.size());
        for (List<Cell> path : paths) {
            System.out.println(path);
        }
    }

    public List<List<Cell>> findInMaze(String word) {
        List<List<Cell>> paths = new ArrayList<>();
        char firstChar = word.charAt(0);
        char lastChar = word.charAt(word.length() - 1);
        int wordLength = word.length();

        // Single loop for both horizontal and vertical searches
        for (int i = 0; i < rows * cols; i++) {
            int row = i / cols;
            int col = i % cols;

            // Horizontal search
            if (col <= cols - wordLength) {
                checkDirection(paths, word, row, col, 0, 1, firstChar, lastChar);
            }

            // Vertical search
            if (row <= rows - wordLength) {
                checkDirection(paths, word, row, col, 1, 0, firstChar, lastChar);
            }
        }

        return paths;
    }

    private void checkDirection(List<List<Cell>> paths, String word, int startRow, int startCol,
                                int rowIncrement, int colIncrement, char firstChar, char lastChar) {
        if (maze[startRow][startCol] != firstChar) return;

        int endRow = startRow + (word.length() - 1) * rowIncrement;
        int endCol = startCol + (word.length() - 1) * colIncrement;

        if (endRow >= rows || endCol >= cols || maze[endRow][endCol] != lastChar) return;

        List<Cell> path = new ArrayList<>();
        for (int k = 0; k < word.length(); k++) {
            int currentRow = startRow + k * rowIncrement;
            int currentCol = startCol + k * colIncrement;
            char currentChar = maze[currentRow][currentCol];

            if (currentChar != word.charAt(k)) return;

            path.add(new Cell(currentRow, currentCol, currentChar));
        }

        paths.add(path);
    }

    public static class Cell {
        public final int row;
        public final int col;
        public final char value;

        public Cell(int row, int col, char value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + row + "," + col + ")=" + value;
        }
    }
}
