package com.interview.notes.code.year.y2026.august.assessments.test5;

import java.util.*; // Imports necessary utility classes for Maps, Lists, and Queues.

public class VerticalTraversal { // Defines the main class encapsulating our logic.

    public static List<List<Integer>> getVerticalOrder(TreeNode root) { // Core method to calculate the vertical order traversal.
        if (root == null) return List.of(); // Fast-fail check: returns an empty list immediately if the tree is empty.
        Map<Integer, List<Integer>> map = new TreeMap<>(); // TreeMap automatically sorts our columns from negative (left) to positive (right).
        Queue<Pair> q = new LinkedList<>(); // Queue facilitates Breadth-First Search to guarantee top-to-bottom processing.
        q.offer(new Pair(root, 0)); // Initializes the BFS by placing the root node at column 0 into the queue.

        while (!q.isEmpty()) { // Continues processing as long as there are undiscovered nodes in the queue.
            Pair p = q.poll(); // Extracts the first element from the queue to process it.
            map.computeIfAbsent(p.col(), k -> new ArrayList<>()).add(p.node().val); // Gets the list for this column (or makes a new one) and adds the node value.
            if (p.node().left != null) q.offer(new Pair(p.node().left, p.col() - 1)); // If a left child exists, queues it at the current column minus 1.
            if (p.node().right != null) q.offer(new Pair(p.node().right, p.col() + 1)); // If a right child exists, queues it at the current column plus 1.
        } // Closes the BFS while loop.

        return map.values().stream().toList(); // Uses Java Stream API to effortlessly convert map values to an unmodifiable List and return it.
    } // Closes the traversal method.

    static TreeNode build(Integer... vals) { // Helper method to build a binary tree from an array (varargs) for easy testing.
        if (vals.length == 0 || vals[0] == null) return null; // Returns null immediately if the input array is empty or starts with null.
        TreeNode root = new TreeNode(vals[0]); // Creates the root node using the first value in the array.
        Queue<TreeNode> q = new LinkedList<>(); // Uses a queue to keep track of nodes that need their children attached.
        q.offer(root); // Adds the root to the queue to start the building process.
        int i = 1; // Initializes an index counter to traverse the 'vals' array, starting from the second element.

        while (!q.isEmpty() && i < vals.length) { // Loops until all nodes are processed or the array values run out.
            TreeNode curr = q.poll(); // Pulls the next node that needs its left and right children assigned.
            if (vals[i] != null) { // Checks if the incoming array value for the left child is valid (not null).
                curr.left = new TreeNode(vals[i]); // Creates the left child node and attaches it to the current node.
                q.offer(curr.left); // Pushes the newly created left child to the queue for its future children.
            } // Closes left child check.
            i++; // Advances the array index to evaluate the value meant for the right child.
            if (i < vals.length && vals[i] != null) { // Safely checks if we are within bounds and the right child value is valid.
                curr.right = new TreeNode(vals[i]); // Creates the right child node and attaches it.
                q.offer(curr.right); // Pushes the right child to the queue.
            } // Closes right child check.
            i++; // Advances the array index to prepare for the next queue element's children.
        } // Closes the tree building while loop.
        return root; // Returns the fully connected root node of the constructed tree.
    } // Closes the build helper method.

    public static void main(String[] args) { // Standard main method used strictly to execute our test suite without JUnit.
        test(build(3, 9, 8, 4, 0, 1, 7), "[[4], [9], [3, 0, 1], [8], [7]]", "Test Case 1 (Standard)"); // Tests Example 1 exactly as requested.
        test(build(3, 9, 20, null, null, 15, 7), "[[9], [3, 15], [20], [7]]", "Test Case 2 (With Nulls)"); // Tests Example 2 exactly as requested.
        test(null, "[]", "Edge Case (Empty Tree)"); // Tests system behavior when handling an entirely empty tree.
        test(build(1), "[[1]]", "Edge Case (Single Node)"); // Tests system behavior when tree only has a root.

        Integer[] largeData = new Integer[10000]; // Allocates a large array to simulate high-volume/deep tree inputs.
        Arrays.fill(largeData, 5); // Populates the large array with a dummy integer value.
        TreeNode largeTree = build(largeData); // Builds a massive tree to test space/time complexity limits.
        List<List<Integer>> largeResult = getVerticalOrder(largeTree); // Executes the traversal on the large tree.
        System.out.println("Large Data Test : " + (!largeResult.isEmpty() ? "PASS" : "FAIL")); // Validates that the large test executed successfully without crashing.
    } // Closes main method.

    static void test(TreeNode root, String expected, String testName) { // Utility method to format and evaluate actual vs expected results.
        String resultStr = getVerticalOrder(root).toString(); // Fetches traversal result and immediately converts it to a String for direct comparison.
        if (resultStr.equals(expected)) { // Checks if the computed string perfectly matches the expected string output.
            System.out.println(testName + " : PASS"); // Outputs PASS if the strings are identical.
        } else { // Fallback block if the outputs do not match.
            System.out.println(testName + " : FAIL -> Expected " + expected + " got " + resultStr); // Outputs FAIL along with debugging details.
        } // Closes the conditional statement.
    } // Closes the test utility method.

    static class TreeNode { // Defines the blueprint for a standard binary tree node.
        int val; // Stores the integer data for the node.
        TreeNode left, right; // Pointers connecting to the left and right child nodes.
        TreeNode(int val) { this.val = val; } // Constructor to easily instantiate a node with a specific value.
    } // Closes the TreeNode class definition.

    record Pair(TreeNode node, int col) {} // Java 21 Record: A zero-boilerplate way to bind a TreeNode to its column index.
} // Closes the entire Java file structure.