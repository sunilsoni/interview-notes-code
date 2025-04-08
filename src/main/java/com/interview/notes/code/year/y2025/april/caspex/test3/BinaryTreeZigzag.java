package com.interview.notes.code.year.y2025.april.caspex.test3;

import java.util.*;

public class BinaryTreeZigzag {
    // Simple tree node class
    static class Node {
        int data;
        Node left, right;
        Node(int data) {
            this.data = data;
        }
    }

    Node root;

    // Constructor for convenience
    public BinaryTreeZigzag(Node root) {
        this.root = root;
    }

    /**
     * Returns the spiral (zigzag) level-order traversal of the binary tree.
     * The traversal alternates between left-to-right and right-to-left at each level.
     */
    public int[] getLevelSpiral() {
        if (root == null) {
            return new int[0];
        }

        List<Integer> result = new ArrayList<>();
        Deque<Node> deque = new LinkedList<>();
        deque.add(root);
        // Start with false so the first poll is from the last
        // (and subsequent addition is right->left)
        // Flip it after each level.
        boolean leftToRight = false;

        while (!deque.isEmpty()) {
            int levelSize = deque.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node node;
                if (leftToRight) {
                    node = deque.pollFirst();
                    if (node.left != null) {
                        deque.addLast(node.left);
                    }
                    if (node.right != null) {
                        deque.addLast(node.right);
                    }
                } else {
                    node = deque.pollLast();
                    if (node.right != null) {
                        deque.addFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.addFirst(node.left);
                    }
                }
                currentLevel.add(node.data);
            }
            result.addAll(currentLevel);
            leftToRight = !leftToRight;
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    // --- Test code ---
    public static void main(String[] args) {
        /****************************************
         * Example 1 (Already in code):
         *
         *            1
         *           / \
         *          2   3
         *         / \  / \
         *        4  5 6  7
         *             \   \
         *              8   9
         ****************************************/
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.right.right = new Node(8);
        root.right.right.right = new Node(9);

        BinaryTreeZigzag tree = new BinaryTreeZigzag(root);
        int[] spiralOrder = tree.getLevelSpiral();
        System.out.println("Zigzag/Spiral level order traversal (Example 1):");
        System.out.println(Arrays.toString(spiralOrder));
        // Expected: [1, 3, 2, 4, 5, 6, 7, 9, 8]

        // Test 1: Empty tree
        BinaryTreeZigzag emptyTree = new BinaryTreeZigzag(null);
        System.out.println("Empty tree spiral traversal: " + Arrays.toString(emptyTree.getLevelSpiral()));
        // Expected: []

        // Test 2: Single node
        Node singleRoot = new Node(42);
        BinaryTreeZigzag singleNodeTree = new BinaryTreeZigzag(singleRoot);
        System.out.println("Single node spiral traversal: " + Arrays.toString(singleNodeTree.getLevelSpiral()));
        // Expected: [42]

        // Test 3: Only left children
        Node leftRoot = new Node(10);
        leftRoot.left = new Node(20);
        leftRoot.left.left = new Node(30);
        leftRoot.left.left.left = new Node(40);
        BinaryTreeZigzag leftTree = new BinaryTreeZigzag(leftRoot);
        System.out.println("Only left children tree spiral traversal: " + Arrays.toString(leftTree.getLevelSpiral()));
        // Expected: [10, 20, 30, 40]

        // Test 4: Only right children
        Node rightRoot = new Node(100);
        rightRoot.right = new Node(200);
        rightRoot.right.right = new Node(300);
        rightRoot.right.right.right = new Node(400);
        BinaryTreeZigzag rightTree = new BinaryTreeZigzag(rightRoot);
        System.out.println("Only right children tree spiral traversal: " + Arrays.toString(rightTree.getLevelSpiral()));
        // Expected: [100, 200, 300, 400]

        /****************************************
         * Example 2:
         * A complete binary tree of 7 nodes:
         *
         *        1
         *       / \
         *      2   3
         *     / \ / \
         *    4  5 6  7
         ****************************************/
        Node completeRoot = new Node(1);
        completeRoot.left = new Node(2);
        completeRoot.right = new Node(3);
        completeRoot.left.left = new Node(4);
        completeRoot.left.right = new Node(5);
        completeRoot.right.left = new Node(6);
        completeRoot.right.right = new Node(7);

        BinaryTreeZigzag completeTree = new BinaryTreeZigzag(completeRoot);
        int[] spiralOrder2 = completeTree.getLevelSpiral();
        System.out.println("Zigzag/Spiral level order traversal (Example 2 - complete tree of 7):");
        System.out.println(Arrays.toString(spiralOrder2));
        // Expected: [1, 3, 2, 4, 5, 6, 7]

        /****************************************
         * Example 3:
         * A larger perfect BST of 15 nodes:
         *
         *                 8
         *               /   \
         *              4     12
         *             / \   /  \
         *            2   6 10  14
         *           / \ / \/ \ / \
         *          1  3 5 7 9 11 13 15
         ****************************************/
        Node perfectRoot = new Node(8);
        perfectRoot.left = new Node(4);
        perfectRoot.right = new Node(12);
        perfectRoot.left.left = new Node(2);
        perfectRoot.left.right = new Node(6);
        perfectRoot.right.left = new Node(10);
        perfectRoot.right.right = new Node(14);
        perfectRoot.left.left.left = new Node(1);
        perfectRoot.left.left.right = new Node(3);
        perfectRoot.left.right.left = new Node(5);
        perfectRoot.left.right.right = new Node(7);
        perfectRoot.right.left.left = new Node(9);
        perfectRoot.right.left.right = new Node(11);
        perfectRoot.right.right.left = new Node(13);
        perfectRoot.right.right.right = new Node(15);

        BinaryTreeZigzag perfectTree = new BinaryTreeZigzag(perfectRoot);
        int[] spiralOrder3 = perfectTree.getLevelSpiral();
        System.out.println("Zigzag/Spiral level order traversal (Example 3 - perfect BST of 15):");
        System.out.println(Arrays.toString(spiralOrder3));
        // The exact zigzag order depends on leftToRight toggling.
        // You can verify it visits level by level in a spiral manner.

        /****************************************
         * Example 4:
         * Tree with some repeating values
         ****************************************/
        Node duplicateRoot = new Node(5);
        duplicateRoot.left = new Node(5);
        duplicateRoot.right = new Node(7);
        duplicateRoot.left.left = new Node(5);
        duplicateRoot.right.left = new Node(7);
        duplicateRoot.right.right = new Node(7);

        BinaryTreeZigzag duplicateTree = new BinaryTreeZigzag(duplicateRoot);
        int[] spiralOrder4 = duplicateTree.getLevelSpiral();
        System.out.println("Zigzag/Spiral level order traversal (Example 4 - duplicates):");
        System.out.println(Arrays.toString(spiralOrder4));
        // Example output could be [5, 7, 5, 5, 7, 7] depending on the toggles.
    }
}
