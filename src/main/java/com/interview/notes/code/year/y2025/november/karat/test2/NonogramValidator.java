package com.interview.notes.code.year.y2025.november.karat.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NonogramValidator {

    /**
     * Validates if a nonogram matrix matches the given row and column instructions
     *
     * @param matrix             2D array containing 'W' (white) and 'B' (black) cells
     * @param rowInstructions    Instructions for each row (runs of black cells)
     * @param columnInstructions Instructions for each column (runs of black cells)
     * @return true if matrix matches all instructions, false otherwise
     */
    public static boolean validateNonogram(String[][] matrix, int[][] rowInstructions, int[][] columnInstructions) {
        // Validate input parameters are not null
        if (matrix == null || rowInstructions == null || columnInstructions == null) {
            return false; // Null inputs are invalid
        }

        // Get matrix dimensions
        int numRows = matrix.length; // Number of rows in the matrix
        int numCols = matrix.length > 0 ? matrix[0].length : 0; // Number of columns (0 if empty matrix)

        // Check if instruction counts match matrix dimensions
        if (rowInstructions.length != numRows || columnInstructions.length != numCols) {
            return false; // Mismatched dimensions between matrix and instructions
        }

        // Validate all rows match their instructions
        boolean allRowsValid = IntStream.range(0, numRows) // Stream through each row index
                .allMatch(i -> validateRow(matrix[i], rowInstructions[i])); // Check if row i matches its instruction

        // If any row is invalid, return false immediately
        if (!allRowsValid) {
            return false; // Early exit if row validation fails
        }

        // Validate all columns match their instructions
        boolean allColumnsValid = IntStream.range(0, numCols) // Stream through each column index
                .allMatch(j -> {
                    // Extract column j as an array
                    String[] column = IntStream.range(0, numRows) // Stream through each row
                            .mapToObj(i -> matrix[i][j]) // Get element at row i, column j
                            .toArray(String[]::new); // Collect into String array
                    return validateColumn(column, columnInstructions[j]); // Validate column against instruction
                });

        return allColumnsValid; // Return final validation result
    }

    /**
     * Validates if a single row matches its instruction
     */
    private static boolean validateRow(String[] row, int[] instruction) {
        // Extract runs of consecutive black cells from the row
        List<Integer> runs = extractRuns(row); // Get actual runs in the row

        // Convert instruction array to list for comparison
        List<Integer> expectedRuns = Arrays.stream(instruction) // Stream the instruction array
                .boxed() // Convert int to Integer
                .collect(Collectors.toList()); // Collect into List

        // Compare actual runs with expected runs
        return runs.equals(expectedRuns); // Lists must be identical
    }

    /**
     * Validates if a single column matches its instruction
     */
    private static boolean validateColumn(String[] column, int[] instruction) {
        // Extract runs of consecutive black cells from the column
        List<Integer> runs = extractRuns(column); // Get actual runs in the column

        // Convert instruction array to list for comparison
        List<Integer> expectedRuns = Arrays.stream(instruction) // Stream the instruction array
                .boxed() // Convert int to Integer
                .collect(Collectors.toList()); // Collect into List

        // Compare actual runs with expected runs
        return runs.equals(expectedRuns); // Lists must be identical
    }

    /**
     * Extracts runs of consecutive black cells from an array
     *
     * @param cells Array of 'W' (white) and 'B' (black) cells
     * @return List of integers representing consecutive black cell counts
     */
    private static List<Integer> extractRuns(String[] cells) {
        List<Integer> runs = new ArrayList<>(); // List to store run lengths
        int currentRun = 0; // Counter for current run of black cells

        // Iterate through each cell in the array
        for (String cell : cells) {
            if ("B".equals(cell)) { // Check if current cell is black
                currentRun++; // Increment current run counter
            } else if (currentRun > 0) { // White cell encountered after black cells
                runs.add(currentRun); // Add completed run to list
                currentRun = 0; // Reset counter for next run
            }
        }

        // Add final run if array ends with black cells
        if (currentRun > 0) {
            runs.add(currentRun); // Don't forget the last run
        }

        return runs; // Return list of all runs
    }

    /**
     * Main method for comprehensive testing
     */
    public static void main(String[] args) {
        System.out.println("=== Nonogram Validator Test Suite ===\n");

        int totalTests = 0; // Counter for total test cases
        int passedTests = 0; // Counter for passed test cases

        // Test Matrix 1
        String[][] matrix1 = {
                {"W", "W", "W", "W"},
                {"B", "W", "W", "W"},
                {"B", "W", "B", "B"},
                {"W", "W", "B", "W"},
                {"B", "B", "W", "W"}
        };

        // Test Case 1: Valid configuration
        totalTests++; // Increment test counter
        int[][] rows1_1 = {{}, {1}, {1, 2}, {1}, {2}}; // Row instructions
        int[][] columns1_1 = {{2, 1}, {}, {2}, {1}}; // Column instructions
        boolean result1 = validateNonogram(matrix1, rows1_1, columns1_1); // Run validation
        if (result1) {
            System.out.println("✓ Test 1 PASS: Valid nonogram configuration");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 1 FAIL: Valid nonogram configuration");
        }

        // Test Case 2: Invalid configuration
        totalTests++; // Increment test counter
        int[][] rows1_2 = {{}, {}, {1}, {1}, {1, 1}}; // Different row instructions
        int[][] columns1_2 = {{2}, {1}, {2}, {1}}; // Different column instructions
        boolean result2 = validateNonogram(matrix1, rows1_2, columns1_2); // Run validation
        if (!result2) {
            System.out.println("✓ Test 2 PASS: Invalid nonogram configuration");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 2 FAIL: Invalid nonogram configuration");
        }

        // Test Case 3: Empty matrix
        totalTests++; // Increment test counter
        String[][] emptyMatrix = {}; // Empty matrix
        int[][] emptyRows = {}; // Empty row instructions
        int[][] emptyCols = {}; // Empty column instructions
        boolean emptyResult = validateNonogram(emptyMatrix, emptyRows, emptyCols); // Run validation
        if (emptyResult) {
            System.out.println("✓ Test 3 PASS: Empty matrix");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 3 FAIL: Empty matrix");
        }

        // Test Case 4: All white cells
        totalTests++; // Increment test counter
        String[][] whiteMatrix = {
                {"W", "W", "W"},
                {"W", "W", "W"},
                {"W", "W", "W"}
        };
        int[][] whiteRows = {{}, {}, {}}; // No black cells in any row
        int[][] whiteCols = {{}, {}, {}}; // No black cells in any column
        boolean whiteResult = validateNonogram(whiteMatrix, whiteRows, whiteCols); // Run validation
        if (whiteResult) {
            System.out.println("✓ Test 4 PASS: All white cells");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 4 FAIL: All white cells");
        }

        // Test Case 5: All black cells
        totalTests++; // Increment test counter
        String[][] blackMatrix = {
                {"B", "B", "B"},
                {"B", "B", "B"},
                {"B", "B", "B"}
        };
        int[][] blackRows = {{3}, {3}, {3}}; // All cells black in each row
        int[][] blackCols = {{3}, {3}, {3}}; // All cells black in each column
        boolean blackResult = validateNonogram(blackMatrix, blackRows, blackCols); // Run validation
        if (blackResult) {
            System.out.println("✓ Test 5 PASS: All black cells");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 5 FAIL: All black cells");
        }

        // Test Case 6: Single cell matrix
        totalTests++; // Increment test counter
        String[][] singleCell = {{"B"}}; // Single black cell
        int[][] singleRow = {{1}}; // One black cell in row
        int[][] singleCol = {{1}}; // One black cell in column
        boolean singleResult = validateNonogram(singleCell, singleRow, singleCol); // Run validation
        if (singleResult) {
            System.out.println("✓ Test 6 PASS: Single cell matrix");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 6 FAIL: Single cell matrix");
        }

        // Test Case 7: Complex pattern
        totalTests++; // Increment test counter
        String[][] complexMatrix = {
                {"B", "W", "B", "B", "W"},
                {"W", "B", "W", "B", "W"},
                {"B", "B", "B", "W", "W"},
                {"W", "W", "B", "B", "B"},
                {"B", "W", "W", "W", "B"}
        };
        int[][] complexRows = {{1, 2}, {1, 1}, {3}, {3}, {1, 1}}; // Complex row patterns
        int[][] complexCols = {{2}, {2}, {1, 1}, {3}, {1, 1}}; // Complex column patterns
        boolean complexResult = validateNonogram(complexMatrix, complexRows, complexCols); // Run validation
        if (complexResult) {
            System.out.println("✓ Test 7 PASS: Complex pattern");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 7 FAIL: Complex pattern");
        }

        // Test Case 8: Mismatched dimensions
        totalTests++; // Increment test counter
        String[][] mismatchMatrix = {
                {"B", "W"},
                {"W", "B"}
        };
        int[][] mismatchRows = {{1}}; // Only one row instruction (should be 2)
        int[][] mismatchCols = {{1}, {1}}; // Correct column count
        boolean mismatchResult = validateNonogram(mismatchMatrix, mismatchRows, mismatchCols); // Run validation
        if (!mismatchResult) {
            System.out.println("✓ Test 8 PASS: Mismatched dimensions");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 8 FAIL: Mismatched dimensions");
        }

        // Test Case 9: Large matrix (performance test)
        totalTests++; // Increment test counter
        int size = 100; // Large matrix size
        String[][] largeMatrix = new String[size][size]; // Create 100x100 matrix
        int[][] largeRows = new int[size][]; // Row instructions
        int[][] largeCols = new int[size][]; // Column instructions

        // Fill large matrix with alternating pattern
        for (int i = 0; i < size; i++) {
            List<Integer> rowRuns = new ArrayList<>(); // Track runs for this row
            int currentRun = 0; // Current run counter

            for (int j = 0; j < size; j++) {
                // Create checkerboard pattern
                if ((i + j) % 2 == 0) {
                    largeMatrix[i][j] = "B"; // Black cell
                    currentRun++; // Increment run
                } else {
                    largeMatrix[i][j] = "W"; // White cell
                    if (currentRun > 0) {
                        rowRuns.add(currentRun); // Add completed run
                        currentRun = 0; // Reset counter
                    }
                }
            }
            if (currentRun > 0) {
                rowRuns.add(currentRun); // Add final run if needed
            }
            largeRows[i] = rowRuns.stream().mapToInt(Integer::intValue).toArray(); // Convert to array
        }

        // Calculate column instructions
        for (int j = 0; j < size; j++) {
            List<Integer> colRuns = new ArrayList<>(); // Track runs for this column
            int currentRun = 0; // Current run counter

            for (int i = 0; i < size; i++) {
                if ("B".equals(largeMatrix[i][j])) {
                    currentRun++; // Count black cells
                } else if (currentRun > 0) {
                    colRuns.add(currentRun); // Add completed run
                    currentRun = 0; // Reset counter
                }
            }
            if (currentRun > 0) {
                colRuns.add(currentRun); // Add final run if needed
            }
            largeCols[j] = colRuns.stream().mapToInt(Integer::intValue).toArray(); // Convert to array
        }

        long startTime = System.currentTimeMillis(); // Record start time
        boolean largeResult = validateNonogram(largeMatrix, largeRows, largeCols); // Run validation
        long endTime = System.currentTimeMillis(); // Record end time

        if (largeResult) {
            System.out.println("✓ Test 9 PASS: Large matrix (100x100) - Time: " + (endTime - startTime) + "ms");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 9 FAIL: Large matrix");
        }

        // Test Case 10: Null inputs
        totalTests++; // Increment test counter
        boolean nullResult = validateNonogram(null, null, null); // Test with null inputs
        if (!nullResult) {
            System.out.println("✓ Test 10 PASS: Null input handling");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 10 FAIL: Null input handling");
        }

        // Test Case 11: Pattern with multiple runs
        totalTests++; // Increment test counter
        String[][] multiRunMatrix = {
                {"B", "B", "W", "B", "W", "B", "B", "B"},
                {"W", "B", "W", "B", "B", "W", "B", "W"},
                {"B", "W", "B", "W", "B", "W", "B", "W"}
        };
        int[][] multiRunRows = {{2, 1, 3}, {1, 2, 1}, {1, 1, 1, 1}}; // Multiple runs per row
        int[][] multiRunCols = {{1, 1}, {1, 1}, {1}, {2}, {1, 1}, {1}, {2}, {1}}; // Various column patterns
        boolean multiRunResult = validateNonogram(multiRunMatrix, multiRunRows, multiRunCols); // Run validation
        if (multiRunResult) {
            System.out.println("✓ Test 11 PASS: Multiple runs pattern");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 11 FAIL: Multiple runs pattern");
        }

        // Print test summary
        System.out.println("\n========================================");
        System.out.println("Test Summary: " + passedTests + "/" + totalTests + " tests passed");
        if (passedTests == totalTests) {
            System.out.println("✓✓✓ ALL TESTS PASSED! ✓✓✓");
        } else {
            System.out.println("Some tests failed. Please review.");
        }
    }
}