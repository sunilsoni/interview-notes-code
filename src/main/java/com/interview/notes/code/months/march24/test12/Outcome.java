package com.interview.notes.code.months.march24.test12;

import java.util.Arrays;
import java.util.List;

/**
 * Tree Traversal
 * Determine the node which has the minimum absolute difference with respect to a target value.
 * Example:
 * 10
 * 6
 * /
 * /
 * \
 * 4
 * 8
 * \
 * 20
 * /
 * 15
 * \
 * 30
 * 9
 * 40
 * Here, you have a binary search tree and a target value of K.
 * <p>
 * No. Input
 * Output
 * 1
 * K = 7
 * 6
 * 2
 * K= 4
 * 4
 * 3
 * K = 12
 * 10
 * Note:
 * If multiple nodes have the same absolute difference with respect to K, select the smallest node value.
 * Input
 * In this problem, the method can take one of the following signatures, based on the programming language chosen for solving the problem:
 * 1. C/Python - findMAD(root node, k)
 * 2. C++/Java - findMAD(k), where the root node is present as a member of the method's class
 * <p>
 * <p>
 * <p>
 * A tree node has been defined and has the following:
 * 1. A "data" part
 * 2. A reference to the left child
 * 3. A reference to the right child
 * Output
 * The output will be the value of the node with the minimum absolute difference with regard to K.
 * Constraints
 * 1 <= N <= 200
 * Example #1
 * Input
 * 5
 * 43657
 * 2
 * Output
 * 3
 * Explanation: Node 3 has the minimum difference
 * with K = 2 (3-2 = 1)
 * <p>
 * <p>
 * Example #2
 * Input
 * 8
 * 10
 * 11 9 15 20 5 12 2 30
 * Output
 * 9
 * Explanation: Both node 11 and node 9 has minimum
 * absolute difference with 10(11-10 = 1 and 10-9 = 1).
 * Since 9 is smaller among the two, the output is 9.
 * The read-only code will take the inputs and create the BST for you. You only need to take this and complete the function to return the value of the node with the minimum absolute difference with regard to K.
 */
//WORKING
public class Outcome {
    static int minDifference;
    static int minNodeValue;

    public static int solve(List<Integer> ar, int K) {
        // Initialize the minimum difference and minimum node value
        minDifference = Integer.MAX_VALUE;
        minNodeValue = Integer.MAX_VALUE;

        // Construct the BST from the input list
        TreeNode root = constructBST(ar);

        // Find the node with the minimum absolute difference with respect to K
        findMinimumDifference(root, K);

        // Return the value of the node with the minimum absolute difference
        return minNodeValue;
    }

    // Helper method to construct the BST from the input list
    private static TreeNode constructBST(List<Integer> ar) {
        if (ar == null || ar.isEmpty())
            return null;

        TreeNode root = new TreeNode(ar.get(0));
        for (int i = 1; i < ar.size(); i++) {
            insertNode(root, ar.get(i));
        }
        return root;
    }

    // Helper method to insert a node into the BST
    private static void insertNode(TreeNode root, int value) {
        if (value < root.data) {
            if (root.left == null) {
                root.left = new TreeNode(value);
            } else {
                insertNode(root.left, value);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(value);
            } else {
                insertNode(root.right, value);
            }
        }
    }

    // Helper method to find the node with the minimum absolute difference
    private static void findMinimumDifference(TreeNode root, int K) {
        if (root == null)
            return;

        // Update the minimum difference and corresponding node value if needed
        int currentDifference = Math.abs(root.data - K);
        if (currentDifference < minDifference || (currentDifference == minDifference && root.data < minNodeValue)) {
            minDifference = currentDifference;
            minNodeValue = root.data;
        }

        // Recursively traverse left and right subtrees
        findMinimumDifference(root.left, K);
        findMinimumDifference(root.right, K);
    }

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        // Example #1
        List<Integer> list1 = Arrays.asList(4, 3, 6, 5, 7);

        System.out.println(solve(list1, 2)); // Output should be 3


        // Example usage
        List<Integer> list2 = Arrays.asList(10, 6, 4, 8, 20, 15, 30, 9, 40);
        System.out.println(solve(list2, 7)); // Output should be 6

        // Example usage
        List<Integer> ar = Arrays.asList(10, 6, 4, 8, 20, 15, 30, 9, 40);
        int K = 7;
        System.out.println(solve(ar, K)); // Output should be 6


        List<Integer> list3 = Arrays.asList(11, 9, 15, 20, 5, 12, 2, 30);
        System.out.println(solve(list3, 10)); // Output should be 9
    }
}