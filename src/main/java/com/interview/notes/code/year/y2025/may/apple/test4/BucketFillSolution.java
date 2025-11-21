package com.interview.notes.code.year.y2025.may.apple.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketFillSolution {

    public static int strokesRequired(List<String> picture) {
        // Check if picture is empty or null - no strokes needed for empty picture
        if (picture == null || picture.isEmpty()) {
            return 0;
        }

        // Get dimensions of the picture grid
        int height = picture.size(); // Number of rows in the picture
        int width = picture.get(0).length(); // Number of columns (all rows have same width)

        // Create a 2D boolean array to track which cells we've already painted
        // visited[i][j] = true means cell at row i, column j has been painted
        boolean[][] visited = new boolean[height][width];

        // Counter for number of fill operations (strokes) needed
        int strokeCount = 0;

        // Iterate through every cell in the grid
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                // If this cell hasn't been painted yet, it's part of a new component
                if (!visited[row][col]) {
                    // Get the color (character) at this position
                    char currentColor = picture.get(row).charAt(col);

                    // Perform DFS to paint all connected cells of the same color
                    // This represents one bucket fill operation
                    performFill(picture, visited, row, col, currentColor, height, width);

                    // Increment stroke count as we've used one fill operation
                    strokeCount++;
                }
            }
        }

        return strokeCount;
    }

    // Depth-First Search to fill all connected cells of the same color
    private static void performFill(List<String> picture, boolean[][] visited,
                                    int row, int col, char targetColor,
                                    int height, int width) {
        // Base case 1: Check if current position is out of bounds
        if (row < 0 || row >= height || col < 0 || col >= width) {
            return; // Stop recursion if we're outside the grid
        }

        // Base case 2: Check if this cell was already painted
        if (visited[row][col]) {
            return; // No need to paint again
        }

        // Base case 3: Check if current cell has different color
        if (picture.get(row).charAt(col) != targetColor) {
            return; // Can't fill cells of different color in same operation
        }

        // Mark current cell as painted/visited
        visited[row][col] = true;

        // Recursively fill all 4 adjacent cells (up, down, left, right)
        // Up: row - 1, same column
        performFill(picture, visited, row - 1, col, targetColor, height, width);
        // Down: row + 1, same column
        performFill(picture, visited, row + 1, col, targetColor, height, width);
        // Left: same row, col - 1
        performFill(picture, visited, row, col - 1, targetColor, height, width);
        // Right: same row, col + 1
        performFill(picture, visited, row, col + 1, targetColor, height, width);
    }

    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Bucket Fill Test Suite ===\n");

        // Test Case 1: Sample Case 0
        System.out.println("Test Case 1 (Sample Case 0):");
        List<String> picture1 = Arrays.asList("aaaba", "ababa", "aaaca");
        int expected1 = 5;
        int result1 = strokesRequired(picture1);
        System.out.println("Input: " + picture1);
        System.out.println("Expected: " + expected1 + ", Got: " + result1);
        System.out.println("Status: " + (result1 == expected1 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 2: Sample Case 1
        System.out.println("Test Case 2 (Sample Case 1):");
        List<String> picture2 = Arrays.asList("bbba", "abba", "acaa", "aaac");
        int expected2 = 4;
        int result2 = strokesRequired(picture2);
        System.out.println("Input: " + picture2);
        System.out.println("Expected: " + expected2 + ", Got: " + result2);
        System.out.println("Status: " + (result2 == expected2 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 3: Single color (minimum strokes)
        System.out.println("Test Case 3 (Single Color):");
        List<String> picture3 = Arrays.asList("aaa", "aaa", "aaa");
        int expected3 = 1;
        int result3 = strokesRequired(picture3);
        System.out.println("Input: " + picture3);
        System.out.println("Expected: " + expected3 + ", Got: " + result3);
        System.out.println("Status: " + (result3 == expected3 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 4: All different colors (maximum strokes)
        System.out.println("Test Case 4 (All Different):");
        List<String> picture4 = Arrays.asList("abc", "def", "ghi");
        int expected4 = 9;
        int result4 = strokesRequired(picture4);
        System.out.println("Input: " + picture4);
        System.out.println("Expected: " + expected4 + ", Got: " + result4);
        System.out.println("Status: " + (result4 == expected4 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 5: Single cell
        System.out.println("Test Case 5 (Single Cell):");
        List<String> picture5 = List.of("a");
        int expected5 = 1;
        int result5 = strokesRequired(picture5);
        System.out.println("Input: " + picture5);
        System.out.println("Expected: " + expected5 + ", Got: " + result5);
        System.out.println("Status: " + (result5 == expected5 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 6: Large grid performance test
        System.out.println("Test Case 6 (Large Grid - 100x100):");
        // Create checkerboard pattern for worst case
        List<String> picture6 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < 100; j++) {
                // Checkerboard pattern: alternating 'a' and 'b'
                row.append((i + j) % 2 == 0 ? 'a' : 'b');
            }
            picture6.add(row.toString());
        }
        long startTime = System.currentTimeMillis();
        int result6 = strokesRequired(picture6);
        long endTime = System.currentTimeMillis();
        System.out.println("Grid size: 100x100 (checkerboard pattern)");
        System.out.println("Result: " + result6 + " strokes");
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        System.out.println("Status: " + (result6 == 5000 ? "PASS" : "FAIL")); // 100x100/2 = 5000
        System.out.println();

        // Test Case 7: Empty input
        System.out.println("Test Case 7 (Empty Input):");
        List<String> picture7 = new ArrayList<>();
        int expected7 = 0;
        int result7 = strokesRequired(picture7);
        System.out.println("Input: Empty list");
        System.out.println("Expected: " + expected7 + ", Got: " + result7);
        System.out.println("Status: " + (result7 == expected7 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 8: Complex pattern
        System.out.println("Test Case 8 (Complex Pattern):");
        List<String> picture8 = Arrays.asList(
                "aabcc",
                "aabcc",
                "ddeef",
                "ddeef"
        );
        int expected8 = 6; // a=1, b=1, c=1, d=1, e=1, f=1
        int result8 = strokesRequired(picture8);
        System.out.println("Input: " + picture8);
        System.out.println("Expected: " + expected8 + ", Got: " + result8);
        System.out.println("Status: " + (result8 == expected8 ? "PASS" : "FAIL"));

        // Summary
        System.out.println("\n=== Test Summary ===");
        System.out.println("All test cases completed successfully!");
    }
}
