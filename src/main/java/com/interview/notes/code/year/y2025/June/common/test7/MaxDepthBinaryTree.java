package com.interview.notes.code.year.y2025.June.common.test7;

import java.util.stream.Stream;

public class MaxDepthBinaryTree {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;              // value held by the node
        TreeNode left;        // reference to left child
        TreeNode right;       // reference to right child

        TreeNode(int val) {   // constructor to set node value
            this.val = val;    // assign passed-in value
        }
    }

    // Method to compute maximum depth of a binary tree
    public static int maxDepth(TreeNode root) {
        // if node is null, tree has no nodes ⇒ depth 0
        if (root == null) {
            return 0;                     // base case
        }
        // compute depth of left subtree
        int leftDepth = maxDepth(root.left);
        // compute depth of right subtree
        int rightDepth = maxDepth(root.right);
        // use Stream API to pick the larger depth
        int maxSubDepth = Stream.of(leftDepth, rightDepth)
                                .max(Integer::compare)  // find max of the two
                                .get();                // extract the integer
        // add 1 to include current node
        return maxSubDepth + 1;
    }

    // Simple main method to run test cases and print PASS/FAIL
    public static void main(String[] args) {
        // test 1: empty tree
        assertDepth(null, 0, "empty tree");

        // test 2: single node
        TreeNode single = new TreeNode(1);
        assertDepth(single, 1, "single node");

        // test 3: balanced tree of depth 3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        assertDepth(root, 3, "balanced depth 3");

        // test 4: right-skewed tree (depth 5)
        TreeNode skew = new TreeNode(0);
        TreeNode curr = skew;
        for (int i = 1; i < 5; i++) {
            curr.right = new TreeNode(i);
            curr = curr.right;
        }
        assertDepth(skew, 5, "right-skewed depth 5");

        // test 5: large skewed tree (depth 1000) to check performance/stack
        TreeNode large = new TreeNode(0);
        curr = large;
        for (int i = 1; i < 1000; i++) {
            curr.left = new TreeNode(i);
            curr = curr.left;
        }
        assertDepth(large, 1000, "large left-skewed depth 1000");
    }

    // helper to run a single test and print result
    private static void assertDepth(TreeNode root, int expected, String caseName) {
        int result = maxDepth(root);                    // compute depth
        if (result == expected) {
            System.out.println("PASS: " + caseName);   // correct
        } else {
            System.out.println("FAIL: " + caseName +
                               " expected=" + expected +
                               " got=" + result);       // incorrect
        }
    }
}