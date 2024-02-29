package com.interview.notes.code.months.feb24.test5;

import java.util.*;

class MazeSolver2 {
    private static final int[] directions = {-1, 0, 1, 0, -1}; // Used for exploring in 4 directions

    public static List<int[]> findShortestPath(int[][] maze) {
        int rows = maze.length, cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];

        Queue<int[]> queue = new LinkedList<>();
        List<int[]> path = new ArrayList<>();

        // Start BFS from the entrance at (0, 0)
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        distance[0][0] = 1; // Starting cell is counted as a step

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            if (r == rows - 1 && c == cols - 1) { // Exit is reached
                return reconstructPath(distance, rows - 1, cols - 1);
            }

            // Explore all possible directions
            for (int i = 0; i < 4; i++) {
                int nr = r + directions[i], nc = c + directions[i + 1];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && maze[nr][nc] == 0 && !visited[nr][nc]) {
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                    distance[nr][nc] = distance[r][c] + 1; // Update distance
                }
            }
        }

        return path; // If the exit is not reachable
    }

    private static List<int[]> reconstructPath(int[][] distance, int row, int col) {
        LinkedList<int[]> path = new LinkedList<>();
        int r = row, c = col;
        while (distance[r][c] != 1) { // Trace back the path
            path.addFirst(new int[]{r, c});
            for (int i = 0; i < 4; i++) { // Look for the previous cell
                int nr = r + directions[i], nc = c + directions[i + 1];
                if (nr >= 0 && nr < distance.length && nc >= 0 && nc < distance[0].length &&
                        distance[nr][nc] == distance[r][c] - 1) {
                    r = nr;
                    c = nc;
                    break;
                }
            }
        }
        path.addFirst(new int[]{0, 0}); // Add the entrance at the beginning
        return path;
    }

    public static void main(String[] args) {
        int[][] maze = {
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0}
        };

        List<int[]> shortestPath = findShortestPath(maze);
        for (int[] step : shortestPath) {
            System.out.println(Arrays.toString(step));
        }
    }
}
