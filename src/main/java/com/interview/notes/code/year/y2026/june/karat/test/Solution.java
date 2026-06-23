package com.interview.notes.code.year.y2026.june.karat.test;

import java.util.ArrayList; // Required to maintain a dynamic list of our coordinate path
import java.util.List; // Required for the return type specification
import java.util.stream.Collectors; // Required to cleanly format the coordinate list into strings for test assertions

public class Solution { // Encapsulating class for the algorithm and test runner
    
    public static List<int[]> search(char[][] grid, String word) { // Main method accepting the 2D grid and target string
        for (var r = 0; r < grid.length; r++) { // Iterate vertically through every row in the grid
            for (var c = 0; c < grid[0].length; c++) { // Iterate horizontally through every column in the current row
                var path = new ArrayList<int[]>(); // Initialize a fresh, empty list to track the coordinate sequence for this attempt
                if (dfs(grid, word, r, c, 0, path)) { // Kick off the Depth-First Search; if it returns true, we successfully mapped the word
                    return path; // Immediately return the populated path list without checking further cells
                } // End the success check block
            } // End the column iteration
        } // End the row iteration
        return List.of(); // If all loops complete without returning, the word doesn't exist; return an immutable empty list
    } // End the main search method
    
    private static boolean dfs(char[][] grid, String word, int r, int c, int i, List<int[]> path) { // Recursive helper method to explore paths
        if (i == word.length()) return true; // Base case: If our index matches the word's length, we have matched all characters successfully
        
        // Bounds check & Letter match: Reject if row is out of bounds, column is out of bounds, or the grid letter doesn't match the word's current letter
        if (r >= grid.length || c >= grid[0].length || grid[r][c] != word.charAt(i)) return false; 
        
        path.add(new int[]{r, c}); // Since it matched, record the current valid [row, col] coordinates into our path history
        
        // Recursion step: Try exploring immediately DOWN (r + 1) OR immediately RIGHT (c + 1) looking for the next character (i + 1)
        if (dfs(grid, word, r + 1, c, i + 1, path) || dfs(grid, word, r, c + 1, i + 1, path)) { 
            return true; // If either direction recursively returns true, bubble that success all the way up the call stack
        } // End the recursion branch check
        
        path.removeLast(); // Java 21 Feature: The path was a dead end. Backtrack by cleanly popping the most recently added coordinate
        return false; // Return false to tell the previous caller that this route failed to yield the full word
    } // End the recursive DFS method

    public static void main(String[] args) { // Standalone main method acting as our zero-dependency test runner
        
        var grid1 = new char[][]{ // Defining the primary 6x8 test grid provided in the requirements
            {'b', 'b', 'b', 'a', 'l', 'l', 'o', 'o'}, // Row 0
            {'b', 'a', 'c', 'c', 'e', 's', 'c', 'n'}, // Row 1
            {'a', 'l', 't', 'e', 'w', 'c', 'e', 'w'}, // Row 2
            {'a', 'l', 'o', 's', 's', 'e', 'c', 'c'}, // Row 3
            {'w', 'o', 'o', 'w', 'a', 'c', 'a', 'w'}, // Row 4
            {'i', 'b', 'w', 'o', 'w', 'w', 'o', 'w'}  // Row 5
        }; // End grid1 initialization
        
        // Standard Tests using grid1 (Testing multiple allowed OR conditions by passing a list of valid expected strings)
        runTest(grid1, "access", List.of("[(1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4)]"), "Test 1_1"); 
        runTest(grid1, "balloon", List.of("[(0, 2), (0, 3), (0, 4), (0, 5), (0, 6), (0, 7), (1, 7)]"), "Test 1_2"); 
        runTest(grid1, "wow", List.of("[(4, 3), (5, 3), (5, 4)]", "[(5, 2), (5, 3), (5, 4)]", "[(5, 5), (5, 6), (5, 7)]"), "Test 1_3"); 
        runTest(grid1, "sec", List.of("[(3, 4), (3, 5), (3, 6)]", "[(3, 4), (3, 5), (4, 5)]"), "Test 1_4"); 
        runTest(grid1, "bbaal", List.of("[(0, 0), (1, 0), (2, 0), (3, 0), (3, 1)]"), "Test 1_5"); 

        var grid2 = new char[][]{{'a'}}; // Edge case: 1x1 grid
        runTest(grid2, "a", List.of("[(0, 0)]"), "Test 2_1"); 
        
        var grid3 = new char[][]{ // Testing early termination and specific branching
            {'c', 'a'}, 
            {'t', 't'}, 
            {'h', 'a'}, 
            {'a', 'c'}, 
            {'t', 'g'}
        }; // End grid3
        runTest(grid3, "cat", List.of("[(0, 0), (0, 1), (1, 1)]"), "Test 3_1"); 
        runTest(grid3, "hat", List.of("[(2, 0), (3, 0), (4, 0)]"), "Test 3_2"); 

        var grid4 = new char[][]{ // Testing complex zig-zag branching
            {'c', 'c', 'x', 't', 'i', 'b'}, 
            {'c', 'a', 't', 'n', 'i', 'i'}, 
            {'a', 'x', 'n', 'x', 'p', 't'}, 
            {'t', 'x', 'i', 'x', 't', 't'}
        }; // End grid4
        runTest(grid4, "catnip", List.of("[(1, 0), (1, 1), (1, 2), (1, 3), (1, 4), (2, 4)]", "[(0, 1), (1, 1), (1, 2), (1, 3), (1, 4), (2, 4)]"), "Test 4_1"); 

        // Large Data Test: Generating a 1000x1000 grid to ensure stack stability and speed
        var largeGrid = new char[1000][1000]; // Create a massive 1M cell grid
        for (int i = 0; i < 1000; i++) for (int j = 0; j < 1000; j++) largeGrid[i][j] = 'x'; // Fill entirely with filler characters
        largeGrid[998][998] = 'z'; largeGrid[998][999] = 'i'; largeGrid[999][999] = 'p'; // Plant the target word exactly at the bottom right boundary
        runTest(largeGrid, "zip", List.of("[(998, 998), (998, 999), (999, 999)]"), "Large Data Input Test"); // Execute to ensure no memory overflows or timeouts
    } // End main test runner

    private static void runTest(char[][] grid, String word, List<String> validOutputs, String testName) { // Helper to validate and print test results
        var result = search(grid, word); // Execute the algorithm and capture the List<int[]> result
        // Stream the coordinates, map them into string formats like "(r, c)", and join them cleanly inside brackets
        var resultStr = result.stream().map(p -> "(" + p[0] + ", " + p[1] + ")").collect(Collectors.joining(", ", "[", "]")); 
        var status = validOutputs.contains(resultStr) ? "PASS" : "FAIL"; // Evaluate if the generated string matches ANY of the acceptable answers
        System.out.println(testName + " : " + status + " -> " + resultStr); // Output the result to the console for verification
    } // End of test utility method
} // End of Solution class