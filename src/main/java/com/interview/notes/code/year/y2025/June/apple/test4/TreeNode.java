package com.interview.notes.code.year.y2025.June.apple.test4;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    List<TreeNode> pathToP = new ArrayList<>();
    List<TreeNode> pathToQ = new ArrayList<>();
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Find paths to both nodes
        findPath(root, p, new ArrayList<>(), pathToP);
        findPath(root, q, new ArrayList<>(), pathToQ);
        
        // Compare paths to find LCA
        TreeNode lca = null;
        int i = 0;
        while (i < pathToP.size() && i < pathToQ.size()) {
            if (pathToP.get(i) == pathToQ.get(i)) {
                lca = pathToP.get(i);
            } else {
                break;
            }
            i++;
        }
        return lca;
    }
    
    private boolean findPath(TreeNode root, TreeNode target, 
                           List<TreeNode> currentPath, 
                           List<TreeNode> resultPath) {
        if (root == null) return false;
        
        currentPath.add(root);
        
        if (root == target) {
            resultPath.addAll(currentPath);
            return true;
        }
        
        if (findPath(root.left, target, currentPath, resultPath) || 
            findPath(root.right, target, currentPath, resultPath)) {
            return true;
        }
        
        currentPath.remove(currentPath.size() - 1);
        return false;
    }
}

// Test class
class Test {
    public static void main(String[] args) {
        // Test Case 1: Basic tree
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.right.left = new TreeNode(0);
        root1.right.right = new TreeNode(8);
        
        Solution solution = new Solution();
        
        // Test 1: LCA of 6 and 0 should be 3
        TreeNode result1 = solution.lowestCommonAncestor(root1, root1.left.left, root1.right.left);
        System.out.println("Test 1: " + (result1.val == 3 ? "PASS" : "FAIL"));
        
        // Test 2: LCA of 0 and 8 should be 1
        result1 = solution.lowestCommonAncestor(root1, root1.right.left, root1.right.right);
        System.out.println("Test 2: " + (result1.val == 1 ? "PASS" : "FAIL"));
        
        // Test Case 2: Single node tree
        TreeNode root2 = new TreeNode(1);
        TreeNode result2 = solution.lowestCommonAncestor(root2, root2, root2);
        System.out.println("Test 3: " + (result2.val == 1 ? "PASS" : "FAIL"));
        
        // Test Case 3: Linear tree
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(3);
        TreeNode result3 = solution.lowestCommonAncestor(root3, root3.left, root3.left.left);
        System.out.println("Test 4: " + (result3.val == 2 ? "PASS" : "FAIL"));
    }
}
