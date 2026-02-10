package com.interview.notes.code.year.y2026.feb.meta.test1;

import java.util.ArrayList;
import java.util.List;

public class RightSideView {

    // Global list to store our final visible nodes
    static List<Integer> result;

    public static void main(String[] args) {
        // --- Test Case 1: The First Example from your image ---
        // Constructing tree: 1 -> (2 -> (null, 5), 3)
        var root1 = new TreeNode(1,
            new TreeNode(2, null, new TreeNode(5, null, null)),
            new TreeNode(3, null, null));
        runTest("Test Case 1 (Standard)", root1, List.of(1, 3, 5));

        // --- Test Case 2: The Second Example from your image ---
        // Constructing tree where 4 blocks 5
        var root2 = new TreeNode(1,
            new TreeNode(2, null, new TreeNode(5, null, null)),
            new TreeNode(3, null, new TreeNode(4, null, null)));
        runTest("Test Case 2 (Overlapping)", root2, List.of(1, 3, 4));

        // --- Test Case 3: Left Skewed Tree (Right side missing) ---
        // Tree: 1 -> 2 -> 3. We should see [1, 2, 3] from the right.
        var root3 = new TreeNode(1, new TreeNode(2, new TreeNode(3, null, null), null), null);
        runTest("Test Case 3 (Left Heavy)", root3, List.of(1, 2, 3));

        // --- Test Case 4: Null/Empty Tree ---
        runTest("Test Case 4 (Empty)", null, List.of());

        // --- Test Case 5: Large Data Input (Skewed Tree) ---
        // Creating a deep tree of 10,000 nodes to test performance
        var largeRoot = generateLargeTree(10000);
        // We expect to see every node [0, 1, 2... 9999] because it's a straight line
        List<Integer> expectedLarge = new ArrayList<>();
        for(int i=0; i<10000; i++) expectedLarge.add(i);
        runTest("Test Case 5 (Large Data)", largeRoot, expectedLarge);
    }

    // --- Main Logic Method ---
    public static List<Integer> solve(TreeNode root) {
        result = new ArrayList<>(); // Initialize the list to hold the numbers we see
        dfs(root, 0); // Start searching from the root at level 0
        return result; // Return the filled list
    }

    // --- Helper Method (Recursive) ---
    private static void dfs(TreeNode node, int level) {
        if (node == null) return; // If the node is empty, stop. (Base case for recursion)

        // The Magic Logic: If the current level matches the list size, it means
        // this is the FIRST time we are visiting this specific depth level.
        // Since we go Right-first, this MUST be the right-most node.
        if (level == result.size()) {
            result.add(node.val()); // Add the value to our answer list
        }

        dfs(node.right(), level + 1); // IMPORTANT: Visit RIGHT child first to ensure we see right-side view
        dfs(node.left(), level + 1);  // Visit LEFT child second (only added if right child didn't exist for this level)
    }

    // --- Test Runner Method ---
    public static void runTest(String testName, TreeNode input, List<Integer> expected) {
        long start = System.nanoTime(); // Start timer for performance check
        List<Integer> actual = solve(input); // Run the solution
        long end = System.nanoTime(); // End timer

        // Check if the actual result matches expected result
        boolean pass = actual.equals(expected);

        // Print status using Java formatted strings
        System.out.printf("%-25s : %s [%.2f ms]%n",
            testName,
            (pass ? "PASS" : "FAIL Expected: " + expected + " Got: " + actual),
            (end - start) / 1_000_000.0);
    }

    // --- Helper to generate large data ---
    private static TreeNode generateLargeTree(int size) {
        TreeNode head = null; // Start with nothing
        // Build the tree backwards from bottom up to avoid deep recursion during construction
        for (int i = size - 1; i >= 0; i--) {
            head = new TreeNode(i, null, head); // Chain nodes to the right
        }
        return head; // Return top of the large tree
    }
    
    // A simple record class for the Tree Node (Java 14+ feature to reduce boilerplate code)
    // This replaces the lengthy "class TreeNode { int val; TreeNode left... }" definition
    record TreeNode(int val, TreeNode left, TreeNode right) {}
}