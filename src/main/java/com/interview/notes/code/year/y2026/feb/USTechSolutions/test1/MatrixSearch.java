package com.interview.notes.code.year.y2026.feb.USTechSolutions.test1;

import java.util.stream.Stream;

public class MatrixSearch {

    // Main method to run our manual tests
    public static void main(String[] args) {
        // Define the matrix from the user's image
        int[][] matrix = {
                {1, 2, 4, 7, 9},
                {11, 13, 15, 18, 20},
                {23, 24, 28, 30, 31},
                {32, 35, 40, 42, 44},
                {70, 74, 80, 88, 90}
        };

        System.out.println("--- Starting Tests ---");

        // Define test cases using a simple custom record (Java 16+) or class
        // We will just iterate logic here using Stream for clean testing
        Stream.of(
                new TestCase("Find 42 (Present)", 42, "3, 3"),
                new TestCase("Find 7 (Present)", 7, "0, 3"),
                new TestCase("Find 90 (Bottom-Right)", 90, "4, 4"),
                new TestCase("Find 1 (Top-Left)", 1, "0, 0"),
                new TestCase("Find 100 (Too Large)", 100, "Not Found"),
                new TestCase("Find 0 (Too Small)", 0, "Not Found"),
                new TestCase("Find 25 (Missing Middle)", 25, "Not Found")
        ).forEach(test -> runTest(matrix, test));

        // Large Data Test Simulation
        testLargeData();
    }

    // The Logic: Search for the target coordinates
    public static String search(int[][] matrix, int target) {
        // If matrix is empty, we can't search
        if (matrix == null || matrix.length == 0) return "Not Found"; // Validation check

        // Start at the Top-Right corner
        var row = 0; // Start at first row
        var col = matrix[0].length - 1; // Start at last column

        // Loop as long as we are inside the matrix boundaries
        while (row < matrix.length && col >= 0) { // Keep going until we fall off grid
            var currentVal = matrix[row][col]; // Get value at current position

            if (currentVal == target) { // Case 1: We found the number!
                return row + ", " + col; // Return coordinates string immediately
            } else if (currentVal > target) { // Case 2: Current is too big
                col--; // Target must be to the left, so move left
            } else { // Case 3: Current is too small (currentVal < target)
                row++; // Target must be below, so move down
            }
        }

        // If loop finishes without returning, the number isn't there
        return "Not Found";
    }

    // Helper method to run a single test and print PASS/FAIL
    private static void runTest(int[][] matrix, TestCase test) {
        var result = search(matrix, test.target); // Run the algorithm
        var status = result.equals(test.expected) ? "PASS" : "FAIL"; // Check if matches

        // Print clean output
        System.out.printf("[%s] %s -> Expected: [%s], Got: [%s]%n",
                status, test.name, test.expected, result);
    }

    // Helper to simulate a large dataset check
    private static void testLargeData() {
        System.out.println("\n--- Testing Large Data Input ---");
        // Create a large 1000x1000 sorted matrix
        int[][] largeMatrix = new int[1000][1000];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                largeMatrix[i][j] = i + j; // Simple sorted logic
            }
        }

        long startTime = System.nanoTime(); // Start timer
        var result = search(largeMatrix, 1998); // Look for bottom right value (999+999)
        long endTime = System.nanoTime(); // End timer

        System.out.println("Large Matrix Search Time (ns): " + (endTime - startTime));
        if (result.equals("999, 999")) System.out.println("Large Data Test: PASS");
        else System.out.println("Large Data Test: FAIL");
    }

    // Simple container for test data
    record TestCase(String name, int target, String expected) {
    }
}