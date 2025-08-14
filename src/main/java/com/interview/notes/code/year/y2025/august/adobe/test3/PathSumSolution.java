package com.interview.notes.code.year.y2025.august.adobe.test3;

import java.util.*;
import java.util.stream.Collectors;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    // Constructor to create a new node
    TreeNode(int val) {
        this.val = val;
    }
}

public class PathSumSolution {
    
    // Main method to test our solution with different test cases
    public static void main(String[] args) {
        // Test Case 1: Creating tree [5,4,8,11,null,13,4,7,2,null,null,5,1]
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.right.left = new TreeNode(5);
        root1.right.right.right = new TreeNode(1);
        
        // Test case 1 execution
        List<List<Integer>> result1 = findPaths(root1, 22);
        System.out.println("Test Case 1: " + (validateResult(result1, Arrays.asList(
            Arrays.asList(5,4,11,2), 
            Arrays.asList(5,8,4,5)
        )) ? "PASS" : "FAIL"));

        // Test Case 2: Simple tree [1,2,3]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        
        // Test case 2 execution
        List<List<Integer>> result2 = findPaths(root2, 5);
        System.out.println("Test Case 2: " + (result2.isEmpty() ? "PASS" : "FAIL"));

        // Test Case 3: Edge case with single node
        TreeNode root3 = new TreeNode(1);
        
        // Test case 3 execution
        List<List<Integer>> result3 = findPaths(root3, 0);
        System.out.println("Test Case 3: " + (result3.isEmpty() ? "PASS" : "FAIL"));
    }

    // Method to find all paths that sum to targetSum
    public static List<List<Integer>> findPaths(TreeNode root, int targetSum) {
        // List to store all valid paths
        List<List<Integer>> result = new ArrayList<>();
        // If tree is empty, return empty result
        if (root == null) return result;
        
        // Start DFS with initial path and sum
        dfs(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    // Helper method for DFS traversal
    private static void dfs(TreeNode node, int remainingSum, List<Integer> currentPath, 
                          List<List<Integer>> result) {
        // Base case: if node is null, return
        if (node == null) return;
        
        // Add current node to path
        currentPath.add(node.val);
        
        // If we're at a leaf node and remaining sum equals node value, we found a valid path
        if (node.left == null && node.right == null && remainingSum == node.val) {
            result.add(new ArrayList<>(currentPath));
        }
        
        // Recurse on left and right children with updated remaining sum
        dfs(node.left, remainingSum - node.val, currentPath, result);
        dfs(node.right, remainingSum - node.val, currentPath, result);
        
        // Backtrack: remove current node from path when going up
        currentPath.remove(currentPath.size() - 1);
    }

    // Helper method to validate test results
    private static boolean validateResult(List<List<Integer>> actual, List<List<Integer>> expected) {
        if (actual.size() != expected.size()) return false;
        
        // Convert lists to sets for comparison regardless of order
        Set<String> actualSet = actual.stream()
            .map(list -> list.toString())
            .collect(Collectors.toSet());
        
        Set<String> expectedSet = expected.stream()
            .map(list -> list.toString())
            .collect(Collectors.toSet());
        
        return actualSet.equals(expectedSet);
    }
}
