package com.interview.notes.code.year.y2025.may.meta.test3;

import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class VerticalTraversal {
    // Main method to print vertical order
    public static List<Integer> verticalOrder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // TreeMap to store nodes at each horizontal distance
        TreeMap<Integer, List<Integer>> hdMap = new TreeMap<>();

        // Queue for level order traversal
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.offer(new NodeInfo(root, 0, 0));

        while (!queue.isEmpty()) {
            NodeInfo current = queue.poll();

            // Add current node to its HD list
            hdMap.computeIfAbsent(current.hd, k -> new ArrayList<>())
                    .add(current.node.data);

            // Process left child
            if (current.node.left != null) {
                queue.offer(new NodeInfo(
                        current.node.left,
                        current.hd - 1,
                        current.level + 1
                ));
            }

            // Process right child
            if (current.node.right != null) {
                queue.offer(new NodeInfo(
                        current.node.right,
                        current.hd + 1,
                        current.level + 1
                ));
            }
        }

        // Collect results from TreeMap
        hdMap.values().forEach(result::addAll);

        return result;
    }

    // Test method
    public static void main(String[] args) {
        // Test Case 1: Given example
        Node root1 = new Node(6);
        root1.left = new Node(3);
        root1.right = new Node(4);
        root1.left.left = new Node(5);
        root1.right.left = new Node(1);
        root1.right.right = new Node(0);
        root1.left.left.right = new Node(2);
        root1.right.right.left = new Node(8);
        root1.left.left.right.left = new Node(9);
        root1.right.right.left.right = new Node(7);

        List<Integer> result1 = verticalOrder(root1);
        System.out.println("Test Case 1:");
        System.out.println("Expected: 5 9 3 2 6 1 7 4 8 0");
        System.out.println("Actual  : " + result1.stream()
                .map(String::valueOf)
                .reduce((a, b) -> a + " " + b)
                .orElse(""));

        // Test Case 2: Empty tree
        System.out.println("\nTest Case 2 (Empty Tree):");
        System.out.println("Expected: []");
        System.out.println("Actual  : " + verticalOrder(null));

        // Test Case 3: Single node
        Node root3 = new Node(1);
        System.out.println("\nTest Case 3 (Single Node):");
        System.out.println("Expected: 1");
        System.out.println("Actual  : " + verticalOrder(root3));
    }

    // Class to store node's info with its horizontal distance and level
    static class NodeInfo {
        Node node;
        int hd;    // horizontal distance
        int level; // level in tree

        NodeInfo(Node node, int hd, int level) {
            this.node = node;
            this.hd = hd;
            this.level = level;
        }
    }
}
