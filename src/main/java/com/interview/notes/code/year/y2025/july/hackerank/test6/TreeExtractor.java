package com.interview.notes.code.year.y2025.july.hackerank.test6;

import java.util.*;
import java.util.stream.Collectors;

class Node {
    int val;
    List<Node> children;
    
    public Node(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
    
    // Helper method to add child nodes
    public void addChild(Node child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }
}

public class TreeExtractor {
    
    // Main extraction method
    public static Node extractSubtree(Node root, int targetId) {
        if (root == null) return null;
        
        // If current node matches target, clone it and return
        if (root.val == targetId) {
            return deepCloneTree(root);
        }
        
        // Search in children using Java 8 Stream API
        return root.children.stream()
                .map(child -> extractSubtree(child, targetId))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
    
    // Helper method to create a deep clone of the tree
    private static Node deepCloneTree(Node node) {
        if (node == null) return null;
        
        Node newNode = new Node(node.val);
        newNode.children = node.children.stream()
                .map(TreeExtractor::deepCloneTree)
                .collect(Collectors.toList());
        return newNode;
    }
    
    // Helper method to print tree for testing
    private static void printTree(Node node, String prefix) {
        if (node == null) return;
        
        System.out.println(prefix + node.val);
        for (Node child : node.children) {
            printTree(child, prefix + "  ");
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Basic test
        Node root1 = createTestTree1();
        testExtraction(root1, 3, "Test 1: Basic extraction");
        
        // Test Case 2: Target not found
        testExtraction(root1, 99, "Test 2: Non-existent node");
        
        // Test Case 3: Empty tree
        testExtraction(null, 1, "Test 3: Null root");
        
        // Test Case 4: Large tree test
        Node largeRoot = createLargeTree(1000);
        testExtraction(largeRoot, 500, "Test 4: Large tree test");
    }
    
    private static void testExtraction(Node root, int targetId, String testName) {
        System.out.println("\n" + testName);
        System.out.println("Original tree:");
        printTree(root, "");
        
        Node result = extractSubtree(root, targetId);
        
        System.out.println("\nExtracted subtree (target=" + targetId + "):");
        printTree(result, "");
        
        boolean passed = validateExtraction(result, targetId);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
    }
    
    private static boolean validateExtraction(Node result, int targetId) {
        if (result == null) {
            return true; // Valid for non-existent targets
        }
        return result.val == targetId;
    }
    
    private static Node createTestTree1() {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        
        root.addChild(node2);
        root.addChild(node3);
        node2.addChild(node4);
        node2.addChild(node5);
        node3.addChild(node6);
        node3.addChild(node7);
        
        return root;
    }
    
    private static Node createLargeTree(int size) {
        Node root = new Node(1);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int value = 2;
        
        while (value <= size) {
            Node current = queue.poll();
            for (int i = 0; i < 3 && value <= size; i++) {
                Node child = new Node(value++);
                current.addChild(child);
                queue.offer(child);
            }
        }
        return root;
    }
}
