package com.interview.notes.code.months.may24.test5;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        Solution solution = new Solution();
        List<List<Integer>> result = solution.pathSum(root, 22);
        System.out.println(result);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();
        findPaths(root, targetSum, new ArrayList<>(), paths);
        return paths;
    }

    private void findPaths(TreeNode node, int targetSum, List<Integer> currentPath, List<List<Integer>> paths) {
        if (node == null) {
            return;
        }

        currentPath.add(node.val);
        // Check if it's a leaf node and the path sum equals the target sum
        if (node.left == null && node.right == null && targetSum == node.val) {
            paths.add(new ArrayList<>(currentPath));
        } else {
            // Continue searching down the paths
            findPaths(node.left, targetSum - node.val, currentPath, paths);
            findPaths(node.right, targetSum - node.val, currentPath, paths);
        }
        // Backtrack
        currentPath.remove(currentPath.size() - 1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
