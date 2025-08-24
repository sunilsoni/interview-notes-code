package com.interview.notes.code.year.y2025.august.Uber.test;

import java.util.*;

public class Solution {
    static int[][] solution(int[][] bubbles) {
        int rows = bubbles.length;
        int cols = bubbles[0].length;
        boolean[][] visited = new boolean[rows][cols];
        boolean[][] mark = new boolean[rows][cols];

        // Step 1: BFS each component
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (bubbles[i][j] == 0 || visited[i][j]) continue;

                int color = bubbles[i][j];
                List<int[]> component = new ArrayList<>();
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{i, j});
                visited[i][j] = true;

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int x = cur[0], y = cur[1];
                    component.add(cur);

                    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                    for (int[] d : dirs) {
                        int nx = x + d[0], ny = y + d[1];
                        if (nx >= 0 && nx < rows && ny >= 0 && ny < cols
                                && !visited[nx][ny] && bubbles[nx][ny] == color) {
                            visited[nx][ny] = true;
                            q.add(new int[]{nx, ny});
                        }
                    }
                }

                // Step 2: Check if this component qualifies
                boolean eligible = false;
                for (int[] cell : component) {
                    int x = cell[0], y = cell[1], count = 0;
                    if (x > 0 && bubbles[x - 1][y] == color) count++;
                    if (x < rows - 1 && bubbles[x + 1][y] == color) count++;
                    if (y > 0 && bubbles[x][y - 1] == color) count++;
                    if (y < cols - 1 && bubbles[x][y + 1] == color) count++;
                    if (count >= 2) {
                        eligible = true;
                        break;
                    }
                }

                // Step 3: Mark the entire component if eligible
                if (eligible) {
                    for (int[] cell : component) {
                        mark[cell[0]][cell[1]] = true;
                    }
                }
            }
        }

        // Step 4: Explode marked cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mark[i][j]) bubbles[i][j] = 0;
            }
        }

        // Step 5: Gravity (only once)
        for (int j = 0; j < cols; j++) {
            int write = rows - 1;
            for (int i = rows - 1; i >= 0; i--) {
                if (bubbles[i][j] != 0) {
                    bubbles[write][j] = bubbles[i][j];
                    if (write != i) bubbles[i][j] = 0;
                    write--;
                }
            }
        }

        return bubbles;
    }

    public static void main(String[] args) {
        int[][] bubbles = {
                {3, 1, 2, 1},
                {1, 1, 1, 4},
                {3, 1, 2, 2},
                {3, 3, 3, 4}
        };

        int[][] expected = {
                {0, 0, 0, 1},
                {0, 0, 0, 4},
                {0, 0, 2, 2},
                {3, 0, 2, 4}
        };

        int[][] result = solution(bubbles);

        if (Arrays.deepEquals(result, expected)) {
            System.out.println("Test 1: PASS");
        } else {
            System.out.println("Test 1: FAIL");
            System.out.println("Expected: " + Arrays.deepToString(expected));
            System.out.println("Got: " + Arrays.deepToString(result));
        }
    }
}