package com.interview.notes.code.year.y2025.july.codility.test1;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    // Constructor to create a new node
    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    // Class to store node information during traversal
    class NodeInfo {
        TreeNode node;
        int row;
        int col;
        
        NodeInfo(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // TreeMap to store columns in sorted order
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        
        // Queue for BFS traversal
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.offer(new NodeInfo(root, 0, 0));
        
        // BFS traversal
        while (!queue.isEmpty()) {
            NodeInfo curr = queue.poll();
            
            // Get or create column map
            map.putIfAbsent(curr.col, new TreeMap<>());
            // Get or create row PriorityQueue
            map.get(curr.col).putIfAbsent(curr.row, new PriorityQueue<>());
            // Add current value to appropriate position
            map.get(curr.col).get(curr.row).offer(curr.node.val);
            
            // Process left child
            if (curr.node.left != null) {
                queue.offer(new NodeInfo(curr.node.left, curr.row + 1, curr.col - 1));
            }
            
            // Process right child
            if (curr.node.right != null) {
                queue.offer(new NodeInfo(curr.node.right, curr.row + 1, curr.col + 1));
            }
        }
        
        // Construct result list
        List<List<Integer>> result = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> colMap : map.values()) {
            List<Integer> colList = new ArrayList<>();
            for (PriorityQueue<Integer> nodes : colMap.values()) {
                while (!nodes.isEmpty()) {
                    colList.add(nodes.poll());
                }
            }
            result.add(colList);
        }
        
        return result;
    }
}

public class Main {
    // Helper method to create test trees
    private static TreeNode createTree(Integer[] values) {
        if (values == null || values.length == 0) return null;
        
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();
            
            if (i < values.length && values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++;
            
            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
            i++;
        }
        
        return root;
    }
    
    // Helper method to run test cases
    private static void runTest(Integer[] treeValues, List<List<Integer>> expected) {
        Solution solution = new Solution();
        TreeNode root = createTree(treeValues);
        List<List<Integer>> result = solution.verticalTraversal(root);
        
        System.out.println("Test Case: " + Arrays.toString(treeValues));
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (result.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    public static void main(String[] args) {
        // Test Case 1: Given example
        runTest(new Integer[]{3,9,20,null,null,15,7}, 
               Arrays.asList(
                   Arrays.asList(9),
                   Arrays.asList(3,15),
                   Arrays.asList(20),
                   Arrays.asList(7)
               ));
        
        // Test Case 2: Single node
        runTest(new Integer[]{1}, 
               Arrays.asList(Arrays.asList(1)));
        
        // Test Case 3: Large tree
        runTest(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}, 
               Arrays.asList(
                   Arrays.asList(8),
                   Arrays.asList(4),
                   Arrays.asList(2,9,10),
                   Arrays.asList(1,5,6),
                   Arrays.asList(3,11,12),
                   Arrays.asList(7),
                   Arrays.asList(13,14,15)
               ));
    }
}
