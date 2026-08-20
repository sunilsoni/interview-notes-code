package com.interview.notes.code.year.y2026.august.nvidia.test3;

import java.util.ArrayDeque; // Imports ArrayDeque so we can use it as an efficient BFS queue.
import java.util.Queue; // Imports the Queue interface used for breadth-first traversal.

public class NumberOfIslands { // Defines the class for the Number of Islands problem.

    public static int numIslands(char[][] grid) { // Returns the total number of connected islands.
        if (grid == null || grid.length == 0) return 0; // Handles a null or empty grid safely.

        int rows = grid.length; // Stores the total number of rows.
        int columns = grid[0].length; // Stores the total number of columns.
        int islandCount = 0; // Keeps track of how many separate islands we find.

        int[][] directions = { // Stores the four allowed movement directions.
                {1, 0}, // Moves one row down.
                {-1, 0}, // Moves one row up.
                {0, 1}, // Moves one column right.
                {0, -1} // Moves one column left.
        }; // Finishes the direction array.

        for (int row = 0; row < rows; row++) { // Scans every row in the grid.
            for (int column = 0; column < columns; column++) { // Scans every column in the current row.

                if (grid[row][column] != '1') continue; // Skips water or land that was already visited.

                islandCount++; // Finding unvisited land means we discovered a new island.

                Queue<int[]> queue = new ArrayDeque<>(); // Creates a queue for BFS traversal.
                queue.offer(new int[]{row, column}); // Adds the first land cell of this island to the queue.
                grid[row][column] = '0'; // Marks the starting land cell as visited immediately.

                while (!queue.isEmpty()) { // Continues until every connected land cell is processed.
                    int[] current = queue.poll(); // Removes the next cell that needs to be explored.

                    for (int[] direction : directions) { // Checks all four possible neighboring directions.
                        int nextRow = current[0] + direction[0]; // Calculates the neighboring row.
                        int nextColumn = current[1] + direction[1]; // Calculates the neighboring column.

                        if (nextRow < 0 || nextRow >= rows) continue; // Skips a neighbor outside the row boundaries.
                        if (nextColumn < 0 || nextColumn >= columns) continue; // Skips a neighbor outside the column boundaries.
                        if (grid[nextRow][nextColumn] != '1') continue; // Skips water or already visited land.

                        grid[nextRow][nextColumn] = '0'; // Marks the neighboring land as visited before adding it.
                        queue.offer(new int[]{nextRow, nextColumn}); // Adds the connected land cell for later exploration.
                    } // Finishes checking all four neighbors.
                } // Finishes BFS for the current island.
            } // Finishes scanning the current row.
        } // Finishes scanning the entire grid.

        return islandCount; // Returns the total number of separate islands.
    } // Finishes the numIslands method.
} // Finishes the class.