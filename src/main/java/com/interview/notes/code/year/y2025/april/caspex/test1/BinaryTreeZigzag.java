package com.interview.notes.code.year.y2025.april.caspex.test1;

import java.util.*;

public class BinaryTreeZigzag {
    Node root;

    // Constructor for convenience
    public BinaryTreeZigzag(Node root) {
        this.root = root;
    }

    // --- Test code ---
    public static void main(String[] args) {
        /*
             Constructing a sample binary tree:

                     1
                    / \
                   2   3
                  / \  / \
                 4  5  6  7
                      \   \
                       8   9
        */
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
        System.out.println("Zigzag/Spiral level order traversal:");
        System.out.println(Arrays.toString(spiralOrder));
        // Expected output for this tree: [1, 3, 2, 4, 5, 6, 7, 9, 8]

        // Additional tests
        // Test 1: Empty tree
        BinaryTreeZigzag emptyTree = new BinaryTreeZigzag(null);
        System.out.println("Empty tree spiral traversal: " + Arrays.toString(emptyTree.getLevelSpiral()));
        // Expected []

        // Test 2: Single node
        Node singleRoot = new Node(42);
        BinaryTreeZigzag singleNodeTree = new BinaryTreeZigzag(singleRoot);
        System.out.println("Single node spiral traversal: " + Arrays.toString(singleNodeTree.getLevelSpiral()));
        // Expected [42]

        // Test 3: Tree with only left children
        Node leftRoot = new Node(10);
        leftRoot.left = new Node(20);
        leftRoot.left.left = new Node(30);
        leftRoot.left.left.left = new Node(40);
        BinaryTreeZigzag leftTree = new BinaryTreeZigzag(leftRoot);
        System.out.println("Only left children tree spiral traversal: " + Arrays.toString(leftTree.getLevelSpiral()));
        // Expected [10, 20, 30, 40] because each level has just one node.

        // Test 4: Tree with only right children
        Node rightRoot = new Node(100);
        rightRoot.right = new Node(200);
        rightRoot.right.right = new Node(300);
        rightRoot.right.right.right = new Node(400);
        BinaryTreeZigzag rightTree = new BinaryTreeZigzag(rightRoot);
        System.out.println("Only right children tree spiral traversal: " + Arrays.toString(rightTree.getLevelSpiral()));
        // Expected [100, 200, 300, 400].
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

    // Simple tree node class
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }
}
