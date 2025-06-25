package com.interview.notes.code.year.y2025.June.amazon.test1;

import java.util.*;

// Node class representing each node in the n-ary tree
class Node {
    int val;  // Value stored in the node
    List<Node> children;  // List to store child nodes
    
    // Constructor to initialize node with a value
    public Node(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}

class TreeComparison {
    
    // Main method to compare two n-ary trees
    public static boolean areTreesIdentical(Node root1, Node root2) {
        // Base case: if both roots are null, trees are identical
        if (root1 == null && root2 == null) {
            return true;
        }
        
        // If one root is null and other isn't, trees are not identical
        if (root1 == null || root2 == null) {
            return false;
        }
        
        // Check if current nodes have same value
        if (root1.val != root2.val) {
            return false;
        }
        
        // Check if number of children are same
        if (root1.children.size() != root2.children.size()) {
            return false;
        }
        
        // Recursively compare all children
        for (int i = 0; i < root1.children.size(); i++) {
            if (!areTreesIdentical(root1.children.get(i), root2.children.get(i))) {
                return false;
            }
        }
        
        return true;
    }
    
    // Helper method to create a test tree
    private static Node createTestTree(int[] values, int[][] children) {
        if (values == null || values.length == 0) return null;
        
        Map<Integer, Node> nodeMap = new HashMap<>();
        
        // Create all nodes
        for (int val : values) {
            nodeMap.put(val, new Node(val));
        }
        
        // Connect nodes according to children array
        for (int i = 0; i < children.length; i++) {
            Node parent = nodeMap.get(children[i][0]);
            for (int j = 1; j < children[i].length; j++) {
                parent.children.add(nodeMap.get(children[i][j]));
            }
        }
        
        return nodeMap.get(values[0]);
    }
    
    public static void main(String[] args) {
        // Test Case 1: Identical trees
        int[] values1 = {1, 2, 3, 4};
        int[][] children1 = {{1, 2, 3}, {2, 4}};
        Node tree1 = createTestTree(values1, children1);
        
        int[] values2 = {1, 2, 3, 4};
        int[][] children2 = {{1, 2, 3}, {2, 4}};
        Node tree2 = createTestTree(values2, children2);
        
        System.out.println("Test Case 1 (Identical Trees): " + 
            (areTreesIdentical(tree1, tree2) ? "PASS" : "FAIL"));
        
        // Test Case 2: Different structure
        int[] values3 = {1, 2, 3, 4};
        int[][] children3 = {{1, 2}, {2, 3, 4}};
        Node tree3 = createTestTree(values3, children3);
        
        System.out.println("Test Case 2 (Different Structure): " + 
            (!areTreesIdentical(tree1, tree3) ? "PASS" : "FAIL"));
        
        // Test Case 3: Empty trees
        System.out.println("Test Case 3 (Empty Trees): " + 
            (areTreesIdentical(null, null) ? "PASS" : "FAIL"));
        
        // Test Case 4: Large tree test
        int[] largeValues = new int[1000];
        int[][] largeChildren = new int[999][];
        for (int i = 0; i < 1000; i++) {
            largeValues[i] = i;
            if (i < 999) {
                largeChildren[i] = new int[]{i, i + 1};
            }
        }
        Node largeTree1 = createTestTree(largeValues, largeChildren);
        Node largeTree2 = createTestTree(largeValues, largeChildren);
        
        System.out.println("Test Case 4 (Large Trees): " + 
            (areTreesIdentical(largeTree1, largeTree2) ? "PASS" : "FAIL"));
    }
}
