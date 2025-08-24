package com.interview.notes.code.year.y2025.august.adobe.test3;

import java.util.*;
import java.util.stream.Collectors;

// Define a public class to hold everything in a single file for easy copy-run.
public class PathSumII {

    // Core solution: returns all root-to-leaf paths where the sum equals targetSum.
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>(); // will store all valid paths
        List<Integer> current = new ArrayList<>();      // current path from root to the current node
        dfs(root, targetSum, current, result);          // start DFS traversal with backtracking
        return result;                                  // return all collected valid paths
    }

    // Depth-First Search helper with backtracking.
    private static void dfs(TreeNode node, int remaining, List<Integer> current, List<List<Integer>> result) {
        if (node == null) {                // base case: if node is null, nothing to do
            return;                        // simply return to caller
        }

        current.add(node.val);             // choose: include this node's value in the current path
        remaining -= node.val;             // update remaining sum after including current node

        boolean isLeaf = (node.left == null && node.right == null); // check if current node is a leaf
        if (isLeaf && remaining == 0) {    // if leaf AND remaining sum is 0, we found a valid path
            result.add(new ArrayList<>(current)); // add a copy of current path to result (defensive copy)
        }

        dfs(node.left, remaining, current, result);   // explore left subtree for more paths
        dfs(node.right, remaining, current, result);  // explore right subtree for more paths

        current.remove(current.size() - 1); // backtrack: remove the last added value before returning
    }

    // Build a binary tree from a level-order array of Integers, where null means no node.
    public static TreeNode buildTreeLevelOrder(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) { // handle empty or null-root
            return null;                                        // return null tree
        }
        TreeNode root = new TreeNode(arr[0]);                   // create the root node
        Queue<TreeNode> q = new LinkedList<>();                 // queue for BFS linking children
        q.offer(root);                                          // start with root in queue
        int i = 1;                                              // index pointing to next array element

        while (!q.isEmpty() && i < arr.length) {                // continue until all elements used
            TreeNode curr = q.poll();                           // take the next parent from queue
            if (i < arr.length) {                               // ensure bounds for left child
                Integer leftVal = arr[i++];                     // read potential left value
                if (leftVal != null) {                          // if not null, create left node
                    curr.left = new TreeNode(leftVal);          // link as left child
                    q.offer(curr.left);                         // enqueue left child for its children
                }
            }
            if (i < arr.length) {                               // ensure bounds for right child
                Integer rightVal = arr[i++];                    // read potential right value
                if (rightVal != null) {                         // if not null, create right node
                    curr.right = new TreeNode(rightVal);        // link as right child
                    q.offer(curr.right);                        // enqueue right child for its children
                }
            }
        }
        return root;                                            // return the constructed tree
    }

    // ------------------ Utilities for building trees and testing ------------------

    // Normalize list-of-list of integers into sorted list of strings for order-insensitive comparison.
    // Example: [[5,4,11,2], [5,8,4,5]] -> ["5,4,11,2", "5,8,4,5"] then sorted.
    private static List<String> normalizePaths(List<List<Integer>> paths) {
        return paths.stream()                                   // stream over paths
                .map(path -> path.stream()                      // for each path, stream ints
                        .map(String::valueOf)                   // map each int to string
                        .collect(Collectors.joining(",")))      // join by comma to a single string
                .sorted()                                       // sort to make order-insensitive
                .collect(Collectors.toList());                  // collect back to a list
    }

    // Pretty print helper to convert paths to "JSON-like" string for display.
    private static String displayPaths(List<List<Integer>> paths) {
        return paths.stream()                                   // stream paths
                .map(p -> p.toString())                         // use List.toString for each sub-list
                .collect(Collectors.joining(", ", "[", "]"));   // join with commas inside brackets
    }

    // Assert helper: compares expected and actual path sets without relying on order.
    private static boolean assertPathsEqual(List<List<Integer>> expected, List<List<Integer>> actual) {
        return normalizePaths(expected).equals(normalizePaths(actual)); // compare normalized
    }

    // Generate a balanced binary tree of given height with sequential values (for large test).
    // Height h => ~ (2^(h+1)-1) nodes. Values assigned level-order from 1.
    private static TreeNode generateBalancedTree(int height) {
        if (height < 0) return null;                            // negative height means empty tree
        int total = (1 << (height + 1)) - 1;                    // total nodes in a full tree of height h
        Integer[] arr = new Integer[total];                     // array to hold level-order values
        for (int i = 0; i < total; i++) {                       // fill every position
            arr[i] = i + 1;                                     // simple increasing sequence
        }
        return buildTreeLevelOrder(arr);                        // reuse builder
    }

    // Create a deep skewed tree (like a linked list) of length n with all values = 1.
    private static TreeNode generateSkewedOnes(int n) {
        if (n <= 0) return null;                                // empty when n <= 0
        TreeNode root = new TreeNode(1);                        // start with value 1
        TreeNode curr = root;                                   // pointer to build chain
        for (int i = 1; i < n; i++) {                           // for the remaining nodes
            curr.left = new TreeNode(1);                        // attach as left to create skew
            curr = curr.left;                                   // advance to new leaf
        }
        return root;                                            // return skewed tree
    }

    public static void main(String[] args) {
        // Test 1: Example 1
        Integer[] arr1 = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1}; // level-order with nulls
        TreeNode root1 = buildTreeLevelOrder(arr1);               // build the tree
        int target1 = 22;                                         // target sum as given
        List<List<Integer>> expected1 = Arrays.asList(            // expected paths (order may vary)
                Arrays.asList(5, 4, 11, 2),
                Arrays.asList(5, 8, 4, 5)
        );
        List<List<Integer>> actual1 = pathSum(root1, target1);    // compute result
        boolean pass1 = assertPathsEqual(expected1, actual1);     // verify ignoring order
        System.out.println("Test 1 -> " + (pass1 ? "PASS" : "FAIL")
                + " | Output=" + displayPaths(actual1));          // print verdict and output

        // Test 2: Example 2
        Integer[] arr2 = {1, 2, 3};                                 // simple small tree
        TreeNode root2 = buildTreeLevelOrder(arr2);               // build tree
        int target2 = 5;                                          // target 5
        List<List<Integer>> expected2 = Collections.emptyList();  // expect empty result
        List<List<Integer>> actual2 = pathSum(root2, target2);    // compute
        boolean pass2 = assertPathsEqual(expected2, actual2);     // verify
        System.out.println("Test 2 -> " + (pass2 ? "PASS" : "FAIL")
                + " | Output=" + displayPaths(actual2));          // print verdict

        // Test 3: Example 3
        Integer[] arr3 = {1, 2};                                   // two nodes
        TreeNode root3 = buildTreeLevelOrder(arr3);               // build tree
        int target3 = 0;                                          // target 0
        List<List<Integer>> expected3 = Collections.emptyList();  // expect empty
        List<List<Integer>> actual3 = pathSum(root3, target3);    // compute
        boolean pass3 = assertPathsEqual(expected3, actual3);     // verify
        System.out.println("Test 3 -> " + (pass3 ? "PASS" : "FAIL")
                + " | Output=" + displayPaths(actual3));          // print verdict

        // Edge Test 4: Empty tree
        Integer[] arr4 = {};                                      // no nodes
        TreeNode root4 = buildTreeLevelOrder(arr4);               // null
        int target4 = 10;                                         // any target
        List<List<Integer>> expected4 = Collections.emptyList();  // expect empty
        List<List<Integer>> actual4 = pathSum(root4, target4);    // compute
        boolean pass4 = assertPathsEqual(expected4, actual4);     // verify
        System.out.println("Test 4 (empty) -> " + (pass4 ? "PASS" : "FAIL")
                + " | Output=" + displayPaths(actual4));          // print verdict

        // Edge Test 5: Single node matches
        Integer[] arr5 = {7};                                     // single node 7
        TreeNode root5 = buildTreeLevelOrder(arr5);               // build
        int target5 = 7;                                          // equals node
        List<List<Integer>> expected5 = Arrays.asList(            // expect one path [7]
                Arrays.asList(7)
        );
        List<List<Integer>> actual5 = pathSum(root5, target5);    // compute
        boolean pass5 = assertPathsEqual(expected5, actual5);     // verify
        System.out.println("Test 5 (single-match) -> " + (pass5 ? "PASS" : "FAIL")
                + " | Output=" + displayPaths(actual5));          // print verdict

        // Edge Test 6: Negative numbers
        Integer[] arr6 = {1, -2, -3, 1, 3, -2, null, -1};               // includes negatives
        TreeNode root6 = buildTreeLevelOrder(arr6);               // build tree
        int target6 = -1;                                         // target includes negative
        // One valid path: 1 + (-2) + 1 + (-1) = -1
        List<List<Integer>> expected6 = Arrays.asList(
                Arrays.asList(1, -2, 1, -1)
        );
        List<List<Integer>> actual6 = pathSum(root6, target6);    // compute
        boolean pass6 = assertPathsEqual(expected6, actual6);     // verify
        System.out.println("Test 6 (negatives) -> " + (pass6 ? "PASS" : "FAIL")
                + " | Output=" + displayPaths(actual6));          // print verdict

        // Large Test 7: Balanced tree height 12 (~8191 nodes)
        TreeNode root7 = generateBalancedTree(12);                // full tree height 12
        // We craft a target likely to exist along a left-most path (sum of first 13 natural numbers)
        int target7 = 0;                                          // initialize
        TreeNode t = root7;                                       // pointer for computing left path sum
        List<Integer> leftPath = new ArrayList<>();               // record left path values
        while (t != null) {                                       // follow left edges
            target7 += t.val;                                     // accumulate sum
            leftPath.add(t.val);                                  // track values
            t = t.left;                                           // go left
        }
        List<List<Integer>> actual7 = pathSum(root7, target7);    // compute for large tree
        // We only check that there is at least one path and that one equals our constructed leftPath.
        boolean hasLeftPath = normalizePaths(actual7)
                .contains(leftPath.stream().map(String::valueOf).collect(Collectors.joining(",")));
        boolean pass7 = hasLeftPath && actual7.size() >= 1;       // basic sanity for large case
        System.out.println("Test 7 (large balanced) -> " + (pass7 ? "PASS" : "FAIL")
                + " | #PathsFound=" + actual7.size());            // print count for sanity

        // Large Test 8: Deep skewed tree length 5000, all ones; target = 5000 should match exactly one path (the only root->leaf path).
        TreeNode root8 = generateSkewedOnes(5000);                // very deep skewed
        int target8 = 5000;                                       // sum of 5000 ones
        List<List<Integer>> actual8 = pathSum(root8, target8);    // compute
        boolean pass8 = (actual8.size() == 1)                     // exactly one path expected
                && actual8.get(0).size() == 5000                  // path length equals depth
                && actual8.get(0).stream().allMatch(v -> v == 1); // all values are ones
        System.out.println("Test 8 (large skewed) -> " + (pass8 ? "PASS" : "FAIL")
                + " | Paths=" + actual8.size());                  // print verdict and path count
    }

    // ------------------------------- TESTS in main -------------------------------

    // Tree node definition used for building and traversing the binary tree.
    static class TreeNode {
        int val;             // holds the value of the current node
        TreeNode left;       // reference to left child
        TreeNode right;      // reference to right child

        TreeNode(int v) {    // constructor to initialize node with a value
            this.val = v;    // set node value
        }
    }
}