package com.interview.notes.code.year.y2025.jan.M6012024.wallmart.test1;

import java.util.*;

public class SnakePassableLanes {

    /**
     * Finds rows and columns that are completely passable by snakes.
     *
     * @param board 2D array representing the board
     * @return A Map containing two entries:
     * "Rows" -> List of row numbers completely passable
     * "Columns" -> List of column numbers completely passable
     */
    public static Map<String, List<Integer>> findPassableLanes(char[][] board) {
        List<Integer> passableRows = new ArrayList<>();
        List<Integer> passableColumns = new ArrayList<>();

        int numRows = board.length;
        if (numRows == 0) {
            // Empty board
            return Collections.emptyMap();
        }
        int numCols = board[0].length;

        // Check passable rows
        for (int i = 0; i < numRows; i++) {
            boolean isPassable = true;
            for (int j = 0; j < numCols; j++) {
                if (board[i][j] == '+') {
                    isPassable = false;
                    break;
                }
            }
            if (isPassable) {
                passableRows.add(i + 1); // Adjusting row number (1-based indexing as per sample output)
            }
        }

        // Check passable columns
        for (int j = 0; j < numCols; j++) {
            boolean isPassable = true;
            for (int i = 0; i < numRows; i++) {
                if (board[i][j] == '+') {
                    isPassable = false;
                    break;
                }
            }
            if (isPassable) {
                passableColumns.add(j + 1); // Adjusting column number (1-based indexing as per sample output)
            }
        }

        Map<String, List<Integer>> result = new HashMap<>();
        result.put("Rows", passableRows);
        result.put("Columns", passableColumns);

        return result;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1
        char[][] board1 = {
                {'+', '+', '+', '+', '0', '0'},
                {'0', '0', '+', '0', '0', '0'},
                {'0', '0', '+', '0', '0', '0'},
                {'0', '0', '+', '0', '0', '0'}
        };
        Map<String, List<Integer>> expected1 = new HashMap<>();
        expected1.put("Rows", List.of(4));
        expected1.put("Columns", Arrays.asList(5, 6)); // Adjusted indices
        testCases.add(new TestCase("board1", board1, expected1));

        // Test Case 2
        char[][] board2 = {
                {'+', '+', '+', '+', '0', '0'},
                {'0', '0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '0'}
        };
        Map<String, List<Integer>> expected2 = new HashMap<>();
        expected2.put("Rows", List.of(1));
        expected2.put("Columns", List.of(4)); // Adjusted index
        testCases.add(new TestCase("board2", board2, expected2));

        // Test Case 3
        char[][] board3 = {
                {'+', '+', '+', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0', '0'}
        };
        Map<String, List<Integer>> expected3 = new HashMap<>();
        expected3.put("Rows", Collections.emptyList());
        expected3.put("Columns", Collections.emptyList());
        testCases.add(new TestCase("board3", board3, expected3));

        // Test Case 4
        char[][] board4 = {
                {'+'}
        };
        Map<String, List<Integer>> expected4 = new HashMap<>();
        expected4.put("Rows", List.of(0)); // Row index 0
        expected4.put("Columns", Collections.emptyList());
        testCases.add(new TestCase("board4", board4, expected4));

        // Test Case 5
        char[][] board5 = {
                {'0'}
        };
        Map<String, List<Integer>> expected5 = new HashMap<>();
        expected5.put("Rows", List.of(0));
        expected5.put("Columns", List.of(0));
        testCases.add(new TestCase("board5", board5, expected5));

        // Test Case 6
        char[][] board6 = {
                {'0', '0'},
                {'0', '0'},
                {'0', '0'}
        };
        Map<String, List<Integer>> expected6 = new HashMap<>();
        expected6.put("Rows", Arrays.asList(0, 1, 2));
        expected6.put("Columns", Arrays.asList(0, 1));
        testCases.add(new TestCase("board6", board6, expected6));

        // Run test cases
        int passedTests = 0;
        for (TestCase testCase : testCases) {
            Map<String, List<Integer>> actual = findPassableLanes(testCase.board);
            if (compareResults(testCase.expected, actual)) {
                System.out.println("Test Case " + testCase.name + ": PASS");
                passedTests++;
            } else {
                System.out.println("Test Case " + testCase.name + ": FAIL");
                System.out.println("Expected Rows: " + testCase.expected.get("Rows"));
                System.out.println("Actual Rows:   " + actual.get("Rows"));
                System.out.println("Expected Columns: " + testCase.expected.get("Columns"));
                System.out.println("Actual Columns:   " + actual.get("Columns"));
            }
        }
        System.out.println(passedTests + " out of " + testCases.size() + " tests passed.");
    }

    /**
     * Compares the expected and actual results.
     *
     * @param expected Expected result map
     * @param actual   Actual result map
     * @return true if both results match, false otherwise
     */
    private static boolean compareResults(Map<String, List<Integer>> expected, Map<String, List<Integer>> actual) {
        return expected.get("Rows").equals(actual.get("Rows")) &&
                expected.get("Columns").equals(actual.get("Columns"));
    }

    /**
     * Helper class to store test cases.
     */
    static class TestCase {
        String name;
        char[][] board;
        Map<String, List<Integer>> expected;

        TestCase(String name, char[][] board, Map<String, List<Integer>> expected) {
            this.name = name;
            this.board = board;
            this.expected = expected;
        }
    }
}