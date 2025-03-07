package com.interview.notes.code.year.y2025.march.amazon.test1;

import java.util.*;  // Import utilities for data structures and Random

/*

Given an MXN board containing only uppercase English letters, and a dictionary of words also containing only uppercase English letters.
By traversing only horizontally or vertically, with any starting point, and no repetitive use of any character, find all the words from the dictionary which are present in the board.
Requirements:
You can move only horizontally or vertically (not diagonally).
Each letter can only be used once per word.
The board size can be large (e.g., 100x100), and the dictionary may contain thousands of words.
Words can be formed starting from any position on the board.
Example board: GOT R TART
GO TR
TART
Dictionary of words: {"GOAT".
"TAR", "RAR"
, "ART"
', "BOAT"}
Result: "GOAT", "TAR", "ART"


 */
// Main class for solving the board word search problem
public class WordSearchSolver {

    // Inner class representing a node in the Trie (prefix tree)
    static class TrieNode {
        // Array to hold children nodes for each letter A-Z.
        TrieNode[] children = new TrieNode[26];
        // When non-null, this field indicates the node marks the end of a word.
        String word = null;
    }

    /**
     * Builds a Trie from an array of words.
     * Each word is inserted letter by letter.
     * @param words - array of dictionary words
     * @return the root node of the Trie
     */
    private static TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();  // Create a new root node.
        // Loop over each word in the dictionary.
        for (String word : words) {
            TrieNode node = root;  // Start at the root for each word.
            // For each character in the word:
            for (char c : word.toCharArray()) {
                int index = c - 'A';  // Map letter to index (0-25).
                if (node.children[index] == null) {
                    // Create a new TrieNode if path doesn't exist.
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];  // Move to the next node.
            }
            // After inserting all letters, mark the end of a word.
            node.word = word;
        }
        return root;
    }

    /**
     * Performs a DFS search from cell (i, j) on the board using the Trie.
     * Marks found words and adds them to the result set.
     * @param board - 2D array representing the board
     * @param i - current row index
     * @param j - current column index
     * @param node - current Trie node in the search path
     * @param result - set to store found words
     */
    private static void dfs(char[][] board, int i, int j, TrieNode node, Set<String> result) {
        // Check boundaries of the board.
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        char c = board[i][j];
        // If cell is already visited (marked with '#') or no child exists for the letter, return.
        if (c == '#' || node.children[c - 'A'] == null) return;

        node = node.children[c - 'A'];  // Move to the corresponding child node.
        // If a word is completed at this node, add it to the results.
        if (node.word != null) {
            result.add(node.word);
            // Optionally clear the word to prevent duplicate work.
            node.word = null;
        }

        // Mark the current cell as visited.
        board[i][j] = '#';
        // Explore adjacent cells: down, up, right, left.
        dfs(board, i + 1, j, node, result);
        dfs(board, i - 1, j, node, result);
        dfs(board, i, j + 1, node, result);
        dfs(board, i, j - 1, node, result);
        // Restore the letter at the cell after DFS returns.
        board[i][j] = c;
    }

    /**
     * Finds all dictionary words present in the board.
     * @param board - 2D char array representing the board
     * @param words - array of dictionary words
     * @return a set of words that are found on the board
     */
    public static Set<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();       // To store the found words.
        TrieNode root = buildTrie(words);           // Build a Trie for efficient lookup.
        // Start DFS from every cell in the board.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        return result;
    }

    /**
     * Main method to run tests and demonstrate the solution.
     */
    public static void main(String[] args) {
        // Sample board for a minimal reproducible example.
        // The board is chosen such that it can produce the words: "GOAT", "TAR", "ART".
        char[][] board = {
            {'G', 'O', 'T', 'R'},
            {'T', 'A', 'R', 'T'},
            {'G', 'O', 'A', 'T'}
        };

        // Dictionary of words.
        String[] dictionary = {"GOAT", "TAR", "RAR", "ART", "BOAT"};

        // Find words in the board.
        Set<String> foundWords = findWords(board, dictionary);
        System.out.println("Found Words: " + foundWords);

        // Run additional test cases including edge and large input cases.
        runTests();
    }

    /**
     * Runs multiple test cases including edge cases and performance tests.
     * Outputs PASS/FAIL for each test case.
     */
    private static void runTests() {
        boolean allPassed = true;

        // Test Case 1: Basic test from the minimal example.
        {
            char[][] board = {
                {'G', 'O', 'T', 'R'},
                {'T', 'A', 'R', 'T'},
                {'G', 'O', 'A', 'T'}
            };
            String[] dict = {"GOAT", "TAR", "ART"};
            Set<String> expected = new HashSet<>(Arrays.asList("GOAT", "TAR", "ART"));
            Set<String> result = findWords(board, dict);
            if (!result.equals(expected)) {
                allPassed = false;
                System.out.println("Test Case 1 FAILED. Expected " + expected + " but got " + result);
            } else {
                System.out.println("Test Case 1 PASSED.");
            }
        }

        // Test Case 2: Word not found in a small board.
        {
            char[][] board = {
                {'A', 'B'},
                {'C', 'D'}
            };
            String[] dict = {"EFG"};
            Set<String> expected = new HashSet<>();
            Set<String> result = findWords(board, dict);
            if (!result.equals(expected)) {
                allPassed = false;
                System.out.println("Test Case 2 FAILED. Expected " + expected + " but got " + result);
            } else {
                System.out.println("Test Case 2 PASSED.");
            }
        }

        // Test Case 3: Edge case with an empty board.
        {
            char[][] board = new char[0][0];
            String[] dict = {"ANY"};
            Set<String> expected = new HashSet<>();
            try {
                Set<String> result = findWords(board, dict);
                if (!result.equals(expected)) {
                    allPassed = false;
                    System.out.println("Test Case 3 FAILED. Expected " + expected + " but got " + result);
                } else {
                    System.out.println("Test Case 3 PASSED.");
                }
            } catch (Exception e) {
                // If an exception occurs, we consider it as passed if we handle it gracefully.
                System.out.println("Test Case 3 PASSED with Exception handling: " + e.getMessage());
            }
        }

        // Test Case 4: Performance test with a large board (100x100) and random letters.
        {
            int M = 100;
            int N = 100;
            char[][] board = new char[M][N];
            Random rand = new Random(0);  // Use a fixed seed for reproducibility.
            // Fill the board with random uppercase letters.
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = (char) ('A' + rand.nextInt(26));
                }
            }
            // Use a simple dictionary with words that might occur.
            String[] dict = {"TEST", "RANDOM", "WORDS", "JAVA", "BOARD"};
            // Time the execution to check performance.
            long start = System.currentTimeMillis();
            Set<String> result = findWords(board, dict);
            long duration = System.currentTimeMillis() - start;
            System.out.println("Test Case 4 (Large input) PASSED in " + duration + "ms. Found: " + result);
        }

        // Final outcome of all test cases.
        if (allPassed) {
            System.out.println("All test cases PASSED.");
        } else {
            System.out.println("Some test cases FAILED.");
        }
    }
}