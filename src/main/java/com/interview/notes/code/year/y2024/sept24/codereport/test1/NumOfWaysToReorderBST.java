package com.interview.notes.code.year.y2024.sept24.codereport.test1;

/*
Given an array nums that represents a permutation of integers from 1 to n. We are going to construct a binary search tree (BST) by inserting the elements of nums in order into an initially empty BST. Find the number of different ways to reorder nums so that the
constructed BST is identical to that formed from the original array nums. For example, given nums = [2,1,3], we will have 2 as the
root, 1 as a left child, and 3 as a right child. The array [2,3,1] also yields the same BST but [3,2,11 yields a different BST. Return the number of ways to reorder nums such that the BST formed is identical to the original BST formed from nums. Note: BST is, For each node, all values in its left subtree are smaller, and all values in its right subtree are larger. Since the answer may be very large,
return it modulo 109 + 7.
 */
public class NumOfWaysToReorderBST {
    private static final int MOD = 1000000007;
    private long[][] pascal;

    public static void main(String[] args) {
        NumOfWaysToReorderBST solution = new NumOfWaysToReorderBST();

        // Test case 1
        int[] nums1 = {2, 1, 3};
        int expected1 = 1;
        int result1 = solution.numOfWays(nums1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "Passed" : "Failed"));

        // Test case 2
        int[] nums2 = {3, 4, 5, 1, 2};
        int expected2 = 5;
        int result2 = solution.numOfWays(nums2);
        System.out.println("Test Case 2: " + (result2 == expected2 ? "Passed" : "Failed"));

        // Test case 3 (Edge case: single element)
        int[] nums3 = {1};
        int expected3 = 0;
        int result3 = solution.numOfWays(nums3);
        System.out.println("Test Case 3: " + (result3 == expected3 ? "Passed" : "Failed"));

        // Test case 4 (Large input)
        int[] nums4 = new int[1000];
        for (int i = 0; i < 1000; i++) {
            nums4[i] = i + 1;
        }
        long startTime = System.currentTimeMillis();
        int result4 = solution.numOfWays(nums4);
        long endTime = System.currentTimeMillis();
        System.out.println("Test Case 4 (Large input): " + (endTime - startTime < 3000 ? "Passed" : "Failed") +
                " (Execution time: " + (endTime - startTime) + "ms)");
    }

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Generate Pascal's triangle for combinations
        pascal = new long[n][n];
        for (int i = 0; i < n; i++) {
            pascal[i][0] = pascal[i][i] = 1;
            for (int j = 1; j < i; j++) {
                pascal[i][j] = (pascal[i - 1][j - 1] + pascal[i - 1][j]) % MOD;
            }
        }

        // Build the BST
        TreeNode root = buildBST(nums);

        // Calculate the number of ways
        return (int) ((dfs(root) - 1 + MOD) % MOD);
    }

    private long dfs(TreeNode node) {
        if (node == null) return 1;

        int leftCount = countNodes(node.left);
        int rightCount = countNodes(node.right);

        long leftWays = dfs(node.left);
        long rightWays = dfs(node.right);

        // Calculate combinations and multiply with subtree ways
        return (((pascal[leftCount + rightCount][leftCount] * leftWays) % MOD) * rightWays) % MOD;
    }

    private int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    private TreeNode buildBST(int[] nums) {
        TreeNode root = null;
        for (int num : nums) {
            root = insert(root, num);
        }
        return root;
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insert(node.left, val);
        else if (val > node.val) node.right = insert(node.right, val);
        return node;
    }

    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
