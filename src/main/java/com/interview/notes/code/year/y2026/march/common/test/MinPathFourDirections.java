package com.interview.notes.code.year.y2026.march.common.test;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinPathFourDirections {

    public static int solve(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        var rows = grid.length;
        var cols = grid[0].length;
        var minCost = new int[rows][cols];
        
        for (var row : minCost) Arrays.fill(row, Integer.MAX_VALUE);
        minCost[0][0] = grid[0][0];

        // Priority queue to always process the cheapest path first
        var pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[2], b[2]));
        pq.offer(new int[]{0, 0, grid[0][0]});

        // 4 directions: Right, Down, Left, Up
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!pq.isEmpty()) {
            var current = pq.poll();
            var r = current[0]; 
            var c = current[1]; 
            var cost = current[2]; 

            // Destination reached
            if (r == rows - 1 && c == cols - 1) return cost;
            if (cost > minCost[r][c]) continue;

            for (var dir : directions) {
                var nextRow = r + dir[0];
                var nextCol = c + dir[1];

                // Check boundaries
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                    var newCost = cost + grid[nextRow][nextCol];
                    
                    if (newCost < minCost[nextRow][nextCol]) {
                        minCost[nextRow][nextCol] = newCost;
                        pq.offer(new int[]{nextRow, nextCol, newCost});
                    }
                }
            }
        }
        return minCost[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        // Here is the EXACT grid from your new screenshot
        int[][] screenshotGrid = {
            {1, 9, 1, 1, 1},
            {1, 9, 1, 1, 1},
            {1, 9, 1, 9, 1},
            {1, 1, 1, 9, 1}
        };

        var result = solve(screenshotGrid);
        
        // It outputs exactly 12, just like the image!
        System.out.println("Expected: 12 | Actual: " + result);
        System.out.println(result == 12 ? "PASS" : "FAIL");
    }
}