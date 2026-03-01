package com.interview.notes.code.year.y2026.feb.meta.test6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 1. First, we define the API the interviewer described.
interface MouseAPI { // Represents the hidden system controlling the physical mouse
    boolean move(int direction); // 0=Up, 1=Right, 2=Down, 3=Left. Returns false if it hits a wall.
    boolean isCheese(); // Returns true if the mouse is currently standing on the cheese
} // End API definition

public class StatefulMouseFinder { // Main application class
    
    // Direction vectors matched to 0=Up, 1=Right, 2=Down, 3=Left for easy calculation
    static final int[] dRow = {-1, 0, 1, 0}; // Row changes for the 4 physical directions
    static final int[] dCol = {0, 1, 0, -1}; // Column changes for the 4 physical directions

    public static boolean findCheese(MouseAPI mouse) { // Public method to kick off the search
        Set<Pos> visited = new HashSet<>(); // Set to remember where we've been relative to start
        return dfs(0, 0, mouse, visited); // Start DFS assuming our initial spot is relative (0,0)
    } // End wrapper method

    private static boolean dfs(int r, int c, MouseAPI mouse, Set<Pos> visited) { // The core recursive search
        if (mouse.isCheese()) return true; // Base Case: If the mouse is on the cheese right now, stop and return success!

        visited.add(new Pos(r, c)); // Drop a breadcrumb at our current relative coordinate so we don't loop

        for (int i = 0; i < 4; i++) { // Loop exactly 4 times to try Up, Right, Down, Left
            int nextR = r + dRow[i]; // Calculate the relative row if we take this step
            int nextC = c + dCol[i]; // Calculate the relative column if we take this step

            if (!visited.contains(new Pos(nextR, nextC))) { // Check our memory: have we physically been to this relative spot yet?
                if (mouse.move(i)) { // Command the mouse to physically step. It returns true if it didn't hit a wall.

                    // The mouse is now physically standing at (nextR, nextC). Recursively explore from here.
                    if (dfs(nextR, nextC, mouse, visited)) { // If this path eventually finds the cheese...
                        return true; // ...cascade the success back up the chain immediately!
                    } // End success check

                    // BACKTRACKING: The path was a dead end. We must physically step back to our previous spot.
                    // If we moved Up (0), we must move Down (2). Math: (0 + 2) % 4 = 2.
                    int oppositeDirection = (i + 2) % 4; // Calculate the 180-degree opposite direction
                    mouse.move(oppositeDirection); // Command the mouse to physically step backwards
                } // End successful move block
            } // End visited check
        } // End 4-direction loop

        return false; // If all 4 directions are walls or dead ends, return false to trigger backtracking
    } // End DFS method

    // --- 5. TESTING ---
    public static void main(String[] args) { // Standard entry point to execute custom test cases

        char[][] grid1 = { // Build test case 1: A winding path
            {'#', '#', '#', '#'}, // Solid top wall
            {'#', 'C', ' ', '#'}, // Cheese hidden in a corner
            {'#', '#', ' ', '#'}, // A hallway
            {'#', 'M', ' ', '#'}  // Start point
        }; // End grid 1
        MockMaze maze1 = new MockMaze(grid1); // Initialize the stateful API with grid 1
        System.out.println("Test 1 (Winding Path): " + (findCheese(maze1) ? "PASS" : "FAIL")); // Verify the DFS finds it

        char[][] grid2 = { // Build test case 2: Impossible maze
            {'M', ' ', '#'}, // Start next to wall
            {'#', '#', '#'}, // Solid wall blocking everything
            {' ', ' ', 'C'}  // Unreachable cheese
        }; // End grid 2
        MockMaze maze2 = new MockMaze(grid2); // Initialize the stateful API with grid 2
        System.out.println("Test 2 (No Path): " + (!findCheese(maze2) ? "PASS" : "FAIL")); // Verify the DFS fails gracefully

        // Large data test: 1000x1000 open field
        char[][] largeGrid = new char[1000][1000]; // Allocate a massive grid
        for (char[] row : largeGrid) Arrays.fill(row, ' '); // Use Java Stream/Arrays API to instantly fill with empty space
        largeGrid[0][0] = 'M'; // Start top-left
        largeGrid[999][999] = 'C'; // Target bottom-right
        MockMaze maze3 = new MockMaze(largeGrid); // Initialize the stateful API with the huge grid
        System.out.println("Test 3 (Large 1000x1000): " + (findCheese(maze3) ? "PASS" : "FAIL")); // Verify no stack overflow!
    } // End main

    // Java 14+ feature: Tiny immutable class to track our relative (x,y) coordinates
    record Pos(int r, int c) {}

    // --- MOCK API IMPLEMENTATION FOR TESTING ---
    static class MockMaze implements MouseAPI { // A fake maze to test our DFS logic
        char[][] grid; // The 2D map holding the maze layout
        int mR, mC; // The mouse's current physical row and column

        MockMaze(char[][] grid) { // Constructor to initialize our test maze
            this.grid = grid; // Save the provided grid
            for (int i = 0; i < grid.length; i++) // Scan rows
                for (int j = 0; j < grid[0].length; j++) // Scan columns
                    if (grid[i][j] == 'M') { mR = i; mC = j; } // Locate starting 'M' and save its coordinates
        } // End constructor

        public boolean move(int dir) { // Simulates the physical movement
            int nr = mR + dRow[dir], nc = mC + dCol[dir]; // Calculate where the mouse is trying to go
            if (nr < 0 || nr >= grid.length || nc < 0 || nc >= grid[0].length || grid[nr][nc] == '#') return false; // Reject if off-grid or wall
            mR = nr; mC = nc; // Physically update the mouse's internal coordinates
            return true; // Confirm the step was successful
        } // End move simulation

        public boolean isCheese() { // Simulates the cheese sensor
            return grid[mR][mC] == 'C'; // Return true only if the current coordinate holds 'C'
        } // End sensor simulation
    } // End Mock class
} // End class