package com.interview.notes.code.year.y2025.april.common.test2;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    // Main method for testing
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Example tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);

        List<List<Integer>> result1 = solution.verticalOrder(root1);
        System.out.println("Test Case 1:");
        System.out.println("Expected: [[4], [2], [1,5,6], [3], [7]]");
        System.out.println("Actual: " + result1);
        System.out.println("PASS: " + result1.toString().equals("[[4], [2], [1, 5, 6], [3], [7]]"));

        // Test Case 2: Empty tree
        System.out.println("\nTest Case 2 (Empty Tree):");
        List<List<Integer>> result2 = solution.verticalOrder(null);
        System.out.println("Expected: []");
        System.out.println("Actual: " + result2);
        System.out.println("PASS: " + result2.isEmpty());

        // Test Case 3: Single node
        TreeNode root3 = new TreeNode(1);
        List<List<Integer>> result3 = solution.verticalOrder(root3);
        System.out.println("\nTest Case 3 (Single Node):");
        System.out.println("Expected: [[1]]");
        System.out.println("Actual: " + result3);
        System.out.println("PASS: " + result3.toString().equals("[[1]]"));
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // Map to store vertical order
        Map<Integer, List<Integer>> columnMap = new TreeMap<>();

        // Queue for level order traversal
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            TreeNode node = current.node;
            int hd = current.hd;

            // Add the current node to its vertical line
            columnMap.computeIfAbsent(hd, k -> new ArrayList<>()).add(node.val);

            if (node.left != null) {
                queue.offer(new Pair(node.left, hd - 1));
            }
            if (node.right != null) {
                queue.offer(new Pair(node.right, hd + 1));
            }
        }

        // Convert map to list
        result.addAll(columnMap.values());
        return result;
    }

    // Class to store node's value and its horizontal distance
    class Pair {
        TreeNode node;
        int hd; // horizontal distance

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
}
