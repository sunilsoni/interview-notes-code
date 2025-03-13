package com.interview.notes.code.year.y2025.march.caspex.test4;

import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

public class ZigzagTraversal {

    // Main method to process input and test
    public static void main(String[] args) {
        // Test Case 1
        Node root1 = buildTree(new String[]{"10 20 R", "10 30 L"});
        testAndPrint(root1, new int[]{10, 30, 20}, "Test Case 1");

        // Test Case 2
        Node root2 = buildTree(new String[]{"2 4 L", "2 6 R", "4 8 L", "4 10 R"});
        testAndPrint(root2, new int[]{2, 6, 4, 8, 10}, "Test Case 2");

        // Edge case: Empty tree
        testAndPrint(null, new int[]{}, "Empty Tree");

        // Edge case: Single node
        Node root3 = new Node(5);
        testAndPrint(root3, new int[]{5}, "Single Node");

        // Large tree test
        Node largeRoot = buildLargeTree(5); // Create a tree with 31 nodes
        System.out.println("Large Tree Test: " + (getLevelSpiral(largeRoot).length == 31 ? "PASS" : "FAIL"));
    }

    // Function to perform zigzag traversal
    public static int[] getLevelSpiral(Node root) {
        if (root == null) return new int[0];

        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // According to the problem:
        // - Level 1 (root): left to right (normal)
        // - Level 2: right to left (reversed)
        // So we start with normal (leftToRight = true) and root is level 1
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);

            // Process nodes at current level
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();

                // Add to current level list
                currentLevel.add(current.data);

                // Add children for next level (always left then right)
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            // If we need to go right to left, reverse the current level
            if (!leftToRight) {
                Collections.reverse(currentLevel);
            }

            // Add current level nodes to result
            result.addAll(currentLevel);

            // Toggle direction for next level
            leftToRight = !leftToRight;
        }

        // Convert List<Integer> to int[]
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // Helper method to build tree from input format
    private static Node buildTree(String[] edges) {
        if (edges == null || edges.length == 0) return null;

        Map<Integer, Node> nodeMap = new HashMap<>();

        for (String edge : edges) {
            String[] parts = edge.split(" ");
            int parent = Integer.parseInt(parts[0]);
            int child = Integer.parseInt(parts[1]);
            String direction = parts[2];

            Node parentNode = nodeMap.getOrDefault(parent, new Node(parent));
            Node childNode = nodeMap.getOrDefault(child, new Node(child));

            nodeMap.putIfAbsent(parent, parentNode);
            nodeMap.putIfAbsent(child, childNode);

            if (direction.equals("L")) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
        }

        // Find the root (node that is not a child of any other node)
        Set<Integer> childValues = new HashSet<>();
        for (String edge : edges) {
            String[] parts = edge.split(" ");
            childValues.add(Integer.parseInt(parts[1]));
        }

        for (Map.Entry<Integer, Node> entry : nodeMap.entrySet()) {
            if (!childValues.contains(entry.getKey())) {
                return entry.getValue(); // This is the root
            }
        }

        return null;
    }

    // Helper method to build a large balanced tree for testing
    private static Node buildLargeTree(int height) {
        if (height <= 0) return null;

        Node root = new Node(1);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int count = 2;
        int level = 1;

        while (level < height) {
            int nodesAtLevel = (int) Math.pow(2, level);
            for (int i = 0; i < nodesAtLevel; i++) {
                Node current = queue.poll();
                current.left = new Node(count++);
                current.right = new Node(count++);
                queue.add(current.left);
                queue.add(current.right);
            }
            level++;
        }

        return root;
    }

    // Test helper method
    private static void testAndPrint(Node root, int[] expected, String testName) {
        int[] result = getLevelSpiral(root);
        boolean passed = Arrays.equals(result, expected);

        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("  Expected: " + Arrays.toString(expected));
            System.out.println("  Got: " + Arrays.toString(result));
        }
    }
}
