package com.interview.notes.code.year.y2025.november.karat.test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubSudokuValidator {
    
    /**
     * Validates if a given matrix is a valid Sub-Sudoku grid
     * Each row and column must contain exactly numbers 1 to N
     */
    public static boolean validateSudoku(String[][] grid) {
        // Check if grid is null or empty - basic validation
        if (grid == null || grid.length == 0) {
            return false; // Empty or null grid is invalid
        }
        
        // Get the size of the grid (N for NxN matrix)
        int n = grid.length; // Number of rows determines the size
        
        // Check if it's a square matrix - all rows must have N columns
        boolean isSquare = Arrays.stream(grid) // Stream through all rows
            .allMatch(row -> row != null && row.length == n); // Each row must be non-null and have N columns
        
        if (!isSquare) {
            return false; // Not a square matrix, invalid sudoku
        }
        
        // Special case: 1x1 grid with value "1" is valid
        if (n == 1) {
            try {
                // Parse the single value and check if it equals 1
                return Integer.parseInt(grid[0][0]) == 1; // Single cell must contain 1
            } catch (NumberFormatException e) {
                return false; // Non-numeric value is invalid
            }
        }
        
        // Create the expected set of numbers (1 to N)
        // This single set is reused for every row/column comparison to avoid rebuilding it repeatedly.
        Set<Integer> expectedNumbers = IntStream.rangeClosed(1, n) // Generate numbers from 1 to N
            .boxed() // Convert int stream to Integer stream
            .collect(Collectors.toSet()); // Collect into a Set for comparison
        
        // Validate all rows
        // The IntStream drives the iteration and short-circuits on the first failure.
        boolean rowsValid = IntStream.range(0, n) // Iterate through row indices 0 to N-1
            .allMatch(i -> validateRowOrColumn(grid[i], expectedNumbers)); // Check each row
        
        if (!rowsValid) {
            return false; // If any row is invalid, grid is invalid
        }
        
        // Validate all columns
        // We rebuild each column as a temporary array so the same validation helper can be reused.
        boolean columnsValid = IntStream.range(0, n) // Iterate through column indices 0 to N-1
            .allMatch(j -> {
                // Extract column j as an array
                String[] column = IntStream.range(0, n) // For each row index
                    .mapToObj(i -> grid[i][j]) // Get element at row i, column j
                    .toArray(String[]::new); // Collect into String array
                return validateRowOrColumn(column, expectedNumbers); // Validate the column
            });
        
        return columnsValid; // Return true only if all columns are valid
    }
    
    /**
     * Helper method to validate a single row or column
     */
    private static boolean validateRowOrColumn(String[] array, Set<Integer> expectedNumbers) {
        try {
            // Convert String array to Set of Integers
            // Collectors.toSet removes duplicates automatically, so a mismatch indicates either duplicates or missing numbers.
            Set<Integer> actualNumbers = Arrays.stream(array) // Stream the array elements
                .map(Integer::parseInt) // Parse each string to integer
                .collect(Collectors.toSet()); // Collect unique values into a set
            
            // Check if the set matches expected numbers exactly
            return actualNumbers.equals(expectedNumbers); // Sets must be identical
        } catch (NumberFormatException e) {
            // If any element is not a valid integer, return false
            return false; // Non-numeric values make it invalid
        }
    }
    
    /**
     * Main method for testing all cases
     */
    public static void main(String[] args) {
        System.out.println("=== Sub-Sudoku Validator Test Suite ===\n");
        // The block below functions as a lightweight manual test runner so the validator can be
        // executed directly without relying on an external unit-testing framework.
        
        int totalTests = 0; // Counter for total test cases
        int passedTests = 0; // Counter for passed test cases
        
        // Test Case 1: Valid 3x3 grid
        totalTests++; // Increment test counter
        String[][] grid1 = {
            {"2", "3", "1"},
            {"1", "2", "3"},
            {"3", "1", "2"}
        };
        boolean result1 = validateSudoku(grid1); // Run validation
        boolean expected1 = true; // Expected result
        if (result1 == expected1) {
            System.out.println("✓ Test 1 PASS: Valid 3x3 grid");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 1 FAIL: Valid 3x3 grid");
        }
        
        // Test Case 2: Invalid - missing value 2 in first column
        totalTests++; // Increment test counter
        String[][] grid2 = {
            {"1", "2", "3"},
            {"3", "2", "1"},
            {"3", "1", "2"}
        };
        boolean result2 = validateSudoku(grid2); // Run validation
        boolean expected2 = false; // Expected result
        if (result2 == expected2) {
            System.out.println("✓ Test 2 PASS: Invalid grid (column issue)");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 2 FAIL: Invalid grid (column issue)");
        }
        
        // Test Case 3: Invalid - missing value 1 in first row
        totalTests++; // Increment test counter
        String[][] grid3 = {
            {"2", "2", "3"},
            {"3", "1", "2"},
            {"2", "3", "1"}
        };
        boolean result3 = validateSudoku(grid3); // Run validation
        boolean expected3 = false; // Expected result
        if (result3 == expected3) {
            System.out.println("✓ Test 3 PASS: Invalid grid (row issue)");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 3 FAIL: Invalid grid (row issue)");
        }
        
        // Test Case 4: Valid 1x1 grid
        totalTests++; // Increment test counter
        String[][] grid4 = {{"1"}}; // Single cell grid
        boolean result4 = validateSudoku(grid4); // Run validation
        boolean expected4 = true; // Expected result
        if (result4 == expected4) {
            System.out.println("✓ Test 4 PASS: Valid 1x1 grid");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 4 FAIL: Valid 1x1 grid");
        }
        
        // Test Case 5: Invalid - negative numbers
        totalTests++; // Increment test counter
        String[][] grid5 = {
            {"-1", "-2", "-3"},
            {"-2", "-3", "-1"},
            {"-3", "-1", "-2"}
        };
        boolean result5 = validateSudoku(grid5); // Run validation
        boolean expected5 = false; // Expected result
        if (result5 == expected5) {
            System.out.println("✓ Test 5 PASS: Invalid grid (negative numbers)");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 5 FAIL: Invalid grid (negative numbers)");
        }
        
        // Test Case 6: Invalid - duplicate in row
        totalTests++; // Increment test counter
        String[][] grid6 = {
            {"1", "3", "3"},
            {"3", "1", "2"},
            {"2", "3", "1"}
        };
        boolean result6 = validateSudoku(grid6); // Run validation
        boolean expected6 = false; // Expected result
        if (result6 == expected6) {
            System.out.println("✓ Test 6 PASS: Invalid grid (duplicate in row)");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 6 FAIL: Invalid grid (duplicate in row)");
        }
        
        // Test Case 7: Invalid 4x4 grid
        totalTests++; // Increment test counter
        String[][] grid7 = {
            {"1", "2", "3", "4"},
            {"4", "3", "2", "1"},
            {"1", "3", "2", "4"},
            {"4", "2", "3", "1"}
        };
        boolean result7 = validateSudoku(grid7); // Run validation
        boolean expected7 = false; // Expected result (column 1 has two 1s)
        if (result7 == expected7) {
            System.out.println("✓ Test 7 PASS: Invalid 4x4 grid");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 7 FAIL: Invalid 4x4 grid");
        }
        
        // Test Case 8: Invalid 2x2 - contains 0 and 3
        totalTests++; // Increment test counter
        String[][] grid8 = {
            {"0", "3"},
            {"3", "0"}
        };
        boolean result8 = validateSudoku(grid8); // Run validation
        boolean expected8 = false; // Expected result
        if (result8 == expected8) {
            System.out.println("✓ Test 8 PASS: Invalid 2x2 (wrong numbers)");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 8 FAIL: Invalid 2x2 (wrong numbers)");
        }
        
        // Test Case 9: Invalid - same as grid8, different values
        totalTests++; // Increment test counter
        String[][] grid9 = {
            {"0", "1"},
            {"1", "0"}
        };
        boolean result9 = validateSudoku(grid9); // Run validation
        boolean expected9 = false; // Expected result (contains 0, not 2)
        if (result9 == expected9) {
            System.out.println("✓ Test 9 PASS: Invalid 2x2 (contains 0)");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 9 FAIL: Invalid 2x2 (contains 0)");
        }
        
        // Test Case 10: Invalid - not square matrix
        totalTests++; // Increment test counter
        String[][] grid10 = {
            {"1", "1", "6"},
            {"1", "6", "1"},
            {"6", "1", "1"}
        };
        boolean result10 = validateSudoku(grid10); // Run validation
        boolean expected10 = false; // Expected result
        if (result10 == expected10) {
            System.out.println("✓ Test 10 PASS: Invalid 3x3 (wrong values)");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 10 FAIL: Invalid 3x3 (wrong values)");
        }
        
        // Test Case 11: Invalid 4x4 grid
        totalTests++; // Increment test counter
        String[][] grid11 = {
            {"1", "2", "3", "4"},
            {"2", "3", "1", "4"},
            {"3", "1", "2", "4"},
            {"4", "2", "3", "1"}
        };
        boolean result11 = validateSudoku(grid11); // Run validation
        boolean expected11 = false; // Expected result (column issues)
        if (result11 == expected11) {
            System.out.println("✓ Test 11 PASS: Invalid 4x4 grid");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 11 FAIL: Invalid 4x4 grid");
        }
        
        // Test Case 18: Valid 10x10 grid (large data test)
        totalTests++; // Increment test counter
        String[][] grid18 = {
            {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
            {"2", "3", "4", "5", "6", "7", "8", "9", "10", "1"},
            {"3", "4", "5", "6", "7", "8", "9", "10", "1", "2"},
            {"4", "5", "6", "7", "8", "9", "10", "1", "2", "3"},
            {"5", "6", "7", "8", "9", "10", "1", "2", "3", "4"},
            {"6", "7", "8", "9", "10", "1", "2", "3", "4", "5"},
            {"7", "8", "9", "10", "1", "2", "3", "4", "5", "6"},
            {"8", "9", "10", "1", "2", "3", "4", "5", "6", "7"},
            {"9", "10", "1", "2", "3", "4", "5", "6", "7", "8"},
            {"10", "1", "2", "3", "4", "5", "6", "7", "8", "9"}
        };
        boolean result18 = validateSudoku(grid18); // Run validation
        boolean expected18 = true; // Expected result
        if (result18 == expected18) {
            System.out.println("✓ Test 18 PASS: Valid 10x10 grid");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test 18 FAIL: Valid 10x10 grid");
        }
        
        // Large Data Test: 100x100 grid
        totalTests++; // Increment test counter
        System.out.print("Testing large 100x100 grid... ");
        String[][] largeGrid = new String[100][100]; // Create 100x100 array
        // Fill with valid pattern
        for (int i = 0; i < 100; i++) { // Iterate through rows
            for (int j = 0; j < 100; j++) { // Iterate through columns
                // Use modular arithmetic so each row is a rotated version of 1..100, guaranteeing valid rows/columns.
                largeGrid[i][j] = String.valueOf(((i + j) % 100) + 1); // Shift pattern
            }
        }
        long startTime = System.currentTimeMillis(); // Record start time
        boolean largeResult = validateSudoku(largeGrid); // Run validation
        long endTime = System.currentTimeMillis(); // Record end time
        if (largeResult) {
            System.out.println("✓ PASS (Time: " + (endTime - startTime) + "ms)");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ FAIL");
        }
        
        // Performance test with invalid large grid
        totalTests++; // Increment test counter
        System.out.print("Testing invalid large 50x50 grid... ");
        String[][] invalidLargeGrid = new String[50][50]; // Create 50x50 array
        // Fill with all 1s (invalid pattern)
        for (int i = 0; i < 50; i++) { // Iterate through rows
            for (int j = 0; j < 50; j++) { // Iterate through columns
                // Deliberately make every number identical to ensure the validator catches duplicates quickly.
                invalidLargeGrid[i][j] = "1"; // All cells contain 1 (invalid)
            }
        }
        startTime = System.currentTimeMillis(); // Record start time
        boolean invalidLargeResult = validateSudoku(invalidLargeGrid); // Run validation
        endTime = System.currentTimeMillis(); // Record end time
        if (!invalidLargeResult) {
            System.out.println("✓ PASS (Time: " + (endTime - startTime) + "ms)");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ FAIL");
        }
        
        // Edge case: Empty grid
        totalTests++; // Increment test counter
        String[][] emptyGrid = {}; // Empty array
        boolean emptyResult = validateSudoku(emptyGrid); // Run validation
        if (!emptyResult) {
            System.out.println("✓ Test Empty Grid PASS");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test Empty Grid FAIL");
        }
        
        // Edge case: Null grid
        totalTests++; // Increment test counter
        boolean nullResult = validateSudoku(null); // Run validation with null
        if (!nullResult) {
            System.out.println("✓ Test Null Grid PASS");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test Null Grid FAIL");
        }
        
        // Edge case: Non-numeric values
        totalTests++; // Increment test counter
        String[][] nonNumericGrid = {
            {"a", "b"},
            {"c", "d"}
        };
        boolean nonNumericResult = validateSudoku(nonNumericGrid); // Run validation
        if (!nonNumericResult) {
            System.out.println("✓ Test Non-Numeric Grid PASS");
            passedTests++; // Increment passed counter
        } else {
            System.out.println("✗ Test Non-Numeric Grid FAIL");
        }
        
        // Print summary
        System.out.println("\n========================================");
        System.out.println("Test Summary: " + passedTests + "/" + totalTests + " tests passed");
        if (passedTests == totalTests) {
            System.out.println("✓✓✓ ALL TESTS PASSED! ✓✓✓");
        } else {
            System.out.println("Some tests failed. Please review.");
        }
    }
}
