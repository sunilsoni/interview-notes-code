package com.interview.notes.code.year.y2024.feb24.test5;

import java.util.*;

public class MazeSolver {

    // Direction vectors for up, down, left, and right movements
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Method to find the shortest path in the maze
    public static List<List<Integer>> findShortestPath(int[][] maze) {
        int rows = maze.length, cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols]; // Track visited positions
        Queue<List<Integer>> queue = new LinkedList<>(); // Queue for BFS

        // Start from the entrance (0,0)
        queue.offer(Arrays.asList(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            List<Integer> position = queue.poll();
            int x = position.get(0), y = position.get(1);

            // Check if we have reached an exit
            if (isExit(x, y, rows, cols) && maze[x][y] == 0) {
                return tracePath(position);
            }

            // Explore neighbors
            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0], newY = y + direction[1];
                if (isValid(newX, newY, maze, visited)) {
                    visited[newX][newY] = true;
                    List<Integer> newPos = new ArrayList<>(position);
                    newPos.add(newX);
                    newPos.add(newY);
                    queue.offer(newPos);
                }
            }
        }
        // No path found
        return Collections.emptyList();
    }

    // Helper method to check if the current position is an exit
    private static boolean isExit(int x, int y, int rows, int cols) {
        return x == 0 || y == 0 || x == rows - 1 || y == cols - 1;
    }

    // Helper method to check if the new position is valid
    private static boolean isValid(int x, int y, int[][] maze, boolean[][] visited) {
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length &&
                !visited[x][y] && maze[x][y] == 0;
    }

    // Helper method to backtrack the path from the exit to the entrance
    private static List<List<Integer>> tracePath(List<Integer> position) {
        List<List<Integer>> path = new ArrayList<>();
        for (int i = 0; i < position.size(); i += 2) {
            path.add(Arrays.asList(position.get(i), position.get(i + 1)));
        }
        return path;
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 1, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0}
        };
        List<List<Integer>> shortestPath = findShortestPath(maze);
        // Output the path
        shortestPath.forEach(position -> System.out.println(position.get(0) + "," + position.get(1)));
    }
}
