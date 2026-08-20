package com.interview.notes.code.year.y2026.august.assessments.test4;

import java.util.*; // Imports List, Map, Queue and other utility classes.

public class VerticalTraversal { // Main class.

    public static List<List<Integer>> getVerticalOrder(TreeNode root) { // Finds vertical order traversal.

        if (root == null) return List.of(); // Returns empty list if tree is empty.

        Map<Integer, List<Integer>> map = new TreeMap<>(); // Stores nodes column-wise and keeps columns sorted.

        Queue<Pair> q = new ArrayDeque<>(); // Queue is used for BFS traversal.

        q.offer(new Pair(root, 0)); // Root starts at column 0.

        while (!q.isEmpty()) { // Runs until all nodes are processed.

            Pair p = q.poll(); // Gets the next node from the queue.

            map.computeIfAbsent(p.col(), k -> new ArrayList<>()) // Creates list if column is new.
                    .add(p.node().val); // Adds node value to its column.

            if (p.node().left != null) // Checks for left child.
                q.offer(new Pair(p.node().left, p.col() - 1)); // Left child goes one column left.

            if (p.node().right != null) // Checks for right child.
                q.offer(new Pair(p.node().right, p.col() + 1)); // Right child goes one column right.
        }

        return map.values().stream().toList(); // Returns columns from left to right.
    }

    public static void main(String[] args) { // Runs one example.

        TreeNode root = new TreeNode(3); // Creates root node.

        root.left = new TreeNode(9); // Adds 9 as left child of 3.

        root.right = new TreeNode(8); // Adds 8 as right child of 3.

        root.left.left = new TreeNode(4); // Adds 4 as left child of 9.

        root.left.right = new TreeNode(0); // Adds 0 as right child of 9.

        root.right.left = new TreeNode(1); // Adds 1 as left child of 8.

        root.right.right = new TreeNode(7); // Adds 7 as right child of 8.

        var actual = getVerticalOrder(root); // Runs vertical traversal.

        var expected = List.of( // Expected output.
                List.of(4),
                List.of(9),
                List.of(3, 0, 1),
                List.of(8),
                List.of(7)
        );

        System.out.println("Result   : " + actual); // Prints actual result.

        System.out.println("Expected : " + expected); // Prints expected result.

        System.out.println(actual.equals(expected) ? "PASS" : "FAIL"); // Checks result.
    }

    static class TreeNode { // Represents one binary tree node.

        int val; // Stores node value.

        TreeNode left, right; // Stores left and right child.

        TreeNode(int val) { // Constructor.

            this.val = val; // Sets node value.
        }
    }

    record Pair(TreeNode node, int col) {} // Stores node and its column number.
}