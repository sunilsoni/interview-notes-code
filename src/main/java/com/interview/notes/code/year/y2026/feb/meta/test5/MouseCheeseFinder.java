package com.interview.notes.code.year.y2026.feb.meta.test5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MouseCheeseFinder { // Main class to hold our application and logic
    
    public static int findCheese(char[][] grid) { // Main method that takes the 2D map and returns the shortest steps to the cheese
        int rows = grid.length; // Store the total number of rows so we don't go out of bounds
        int cols = grid[0].length; // Store the total number of columns so we don't go out of bounds
        boolean[][] visited = new boolean[rows][cols]; // 2D array to remember where we've walked so we don't get stuck in loops
        Queue<Node> queue = new LinkedList<>(); // Queue to line up the next squares the mouse needs to check

        for (int i = 0; i < rows; i++) { // Loop from top to bottom through every row in the grid
            for (int j = 0; j < cols; j++) { // Loop from left to right through every column in the current row
                if (grid[i][j] == 'M') { // Check if this exact square is where the Mouse ('M') starts
                    queue.add(new Node(i, j, 0)); // Put the starting spot in the queue with 0 steps taken
                    visited[i][j] = true; // Mark the start as visited so we never walk backwards onto it
                } // End of mouse-checking if statement
            } // End of the column loop
        } // End of the row loop

        int[] dRow = {-1, 1, 0, 0}; // Movement array: helps us calculate moving Up (-1) and Down (+1)
        int[] dCol = {0, 0, -1, 1}; // Movement array: helps us calculate moving Left (-1) and Right (+1)

        while (!queue.isEmpty()) { // Keep exploring as long as we have valid paths lined up in our queue
            var current = queue.poll(); // Java 10+ feature: 'var' infers the Type. Grabs and removes the next Node to process

            if (grid[current.r()][current.c()] == 'C') { // Check if the mouse's current standing spot has the Cheese ('C')
                return current.steps(); // If yes, immediately return the step count because BFS guarantees it's the shortest route
            } // End of the cheese-checking if statement

            for (int i = 0; i < 4; i++) { // Loop exactly 4 times to attempt moving in all 4 physical directions
                int newR = current.r() + dRow[i]; // Calculate what the row would be if we take this step
                int newC = current.c() + dCol[i]; // Calculate what the column would be if we take this step

                if (newR >= 0 && newR < rows && newC >= 0 && newC < cols) { // Verify the new step won't fall off the edges of the grid
                    if (!visited[newR][newC] && grid[newR][newC] != '#') { // Verify we haven't been here yet AND it is not a solid wall ('#')
                        visited[newR][newC] = true; // Drop a breadcrumb marking that we are visiting this new square
                        queue.add(new Node(newR, newC, current.steps() + 1)); // Add this valid new spot to the queue, adding 1 to our step count
                    } // End of wall and visited check
                } // End of boundary safety check
            } // End of 4-direction loop
        } // End of while loop

        return -1; // If the queue runs out and we never hit 'C', return -1 meaning the cheese is totally blocked off
    } // End of findCheese method
    
    public static void main(String[] args) { // Simple entry point to run all our custom test cases without JUnit
        System.out.println("Starting tests..."); // Print a starting banner to the console

        char[][] grid1 = { // Build test case 1: A small, normal maze
            {'#', '#', 'C', '#'}, // Row 0: walls and our target cheese
            {'#', ' ', ' ', ' '}, // Row 1: walls and open walking spaces
            {'M', ' ', '#', '#'}  // Row 2: starting mouse, open space, and dead ends
        }; // End of grid1 creation

        int res1 = findCheese(grid1); // Run the algorithm on our first test grid
        System.out.println("Test 1 (Normal Path): " + (res1 == 4 ? "PASS" : "FAIL") + " -> Steps: " + res1); // Verify it takes exactly 4 steps

        char[][] grid2 = { // Build test case 2: An impossible maze
            {'M', ' ', '#'}, // Row 0: mouse starts next to a wall
            {'#', '#', '#'}, // Row 1: an impenetrable line of walls
            {' ', ' ', 'C'}  // Row 2: the cheese is safe forever
        }; // End of grid2 creation

        int res2 = findCheese(grid2); // Run the algorithm on the impossible grid
        System.out.println("Test 2 (No Path): " + (res2 == -1 ? "PASS" : "FAIL") + " -> Steps: " + res2); // Verify it returns -1

        char[][] largeGrid = new char[1000][1000]; // Build test case 3: A massive grid to test large data handling and memory limits
        for (char[] row : largeGrid) { // Loop through every single row in the giant grid
            Arrays.fill(row, ' '); // Use Java standard library to rapidly fill the whole row with open space
        } // End of large grid filling loop
        largeGrid[0][0] = 'M'; // Drop the mouse at the absolute top-left corner
        largeGrid[999][999] = 'C'; // Drop the cheese at the absolute bottom-right corner

        int res3 = findCheese(largeGrid); // Run the algorithm on the massive 1,000,000 cell grid
        System.out.println("Test 3 (Large 1000x1000): " + (res3 == 1998 ? "PASS" : "FAIL") + " -> Steps: " + res3); // Verify the math checks out (999 down + 999 right)
    } // End of main method

    record Node(int r, int c, int steps) {} // Java 14+ feature: creates a tiny, immutable class to hold grid coordinates and distance
} // End of class