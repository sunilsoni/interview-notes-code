package com.interview.notes.code.year.y2024.aug24.test15;

import java.util.ArrayList;
import java.util.List;

/**
 * public class Maze 1
 * // given the following maze
 * // find all paths for the
 * // word "bird"
 * // a path could be:
 * // - left to right
 * // - top to bottom
 * // In the sample the answer is {row, col}
 * // path 1: {0,3}, {1,3}, (2,3}, {3, 3}
 * // path 2: (5,1}, {5,2}, 15,3}, 15,4}
 * private static final char [][]
 * maze = {
 * l'g'
 * , 'q' , 'b', "k', 'm'},
 * {'h'
 * 't'
 * 'r'
 * 'i'
 * 'x'
 * ，'n'｝，
 * {'x'
 * 'i'
 * 'd'
 * 'r'
 * ''、'b'，
 * C'b', 'i' , 'r', 'd',
 * 'i', 'y'},
 * l'a', 'b', 'i' , 'r', 'd', 'x'}
 * } ;
 * // implement find in Maze
 * static List‹List<Cell>> findInMaze(String word) {
 * return nulL；
 * public static void main (String[] args) {
 * print(findInMaze("bird")) ;
 * private static void print(List<List<Cell>> paths) {
 * System.out println (paths);
 * }
 * }
 * <p>
 * <p>
 * <p>
 * // implement find in Maze
 * static List‹List<Cell>> findInMaze(String word) {
 * return null;
 * }
 * }
 * }
 * public static void main (String[] args) {
 * print(findInMaze("bird")) ;
 * private static void print(List‹List<Cell>> paths) {
 * System. out.println (paths);
 * く
 * <p>
 * public static class Cell {
 * int row;| int col;
 * char value;
 * public Cell(int row,
 * int col, char value) {
 * this.row = row;
 * this.col = col;
 * this.value = value;
 * public String toString() {
 * return String-format ("[%d,%d, '%c']", row, col, value);
 * public int getRow() {
 * return row;
 * }
 * }
 * }
 * 1
 * public int getCol) {
 * return col;
 * public char getValue) {
 * return value;
 * }
 * <p>
 * }
 */
public class MazeWorking {
    private static final char[][] maze = {
            {'g', 'q', 'b', 'k', 'm'},
            {'h', 't', 'r', 'i', 'x'},
            {'x', 'i', 'd', 'r', 'b'},
            {'b', 'i', 'r', 'd', 'y'},
            {'a', 'b', 'i', 'r', 'd'}
    };

    static List<List<Cell>> findInMaze(String word) {
        List<List<Cell>> paths = new ArrayList<>();
        int rows = maze.length;
        int cols = maze[0].length;

        // Search horizontally
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols - word.length(); j++) {
                List<Cell> path = new ArrayList<>();
                boolean match = true;
                for (int k = 0; k < word.length(); k++) {
                    if (maze[i][j + k] != word.charAt(k)) {
                        match = false;
                        break;
                    }
                    path.add(new Cell(i, j + k, maze[i][j + k]));
                }
                if (match) {
                    paths.add(path);
                }
            }
        }

        // Search vertically
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i <= rows - word.length(); i++) {
                List<Cell> path = new ArrayList<>();
                boolean match = true;
                for (int k = 0; k < word.length(); k++) {
                    if (maze[i + k][j] != word.charAt(k)) {
                        match = false;
                        break;
                    }
                    path.add(new Cell(i + k, j, maze[i + k][j]));
                }
                if (match) {
                    paths.add(path);
                }
            }
        }

        return paths;
    }

    public static void main(String[] args) {
        print(findInMaze("bird"));
    }

    private static void print(List<List<Cell>> paths) {
        System.out.println(paths);
    }

    public static class Cell {
        int row;
        int col;
        char value;

        public Cell(int row, int col, char value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("[%d,%d,'%c']", row, col, value);
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public char getValue() {
            return value;
        }
    }
}
