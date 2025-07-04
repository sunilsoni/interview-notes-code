package com.interview.notes.code.year.y2025.June.amazon.test18;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

### **Question: Connect nodes in Zig Zag order**

Given a binary tree, connect each node to its adjacent node on the same level in **zig-zag** order (alternating left-to-right and right-to-left for each level).

#### **Example Tree:**

```
        5
       / \
      4   6
     / \ / \
    3  2 7  8
```

#### **Zig Zag Connections:**

```
Level 0: 5 -> null
Level 1: null <- 4 <- 6
Level 2: 3 -> 2 -> 7 -> 8 -> null
```

---

### **Node Definition:**

```java
class Node {
    public int val;
    public Node left, right;
    public Node next;

    public Node(int val) {
        this.val = val;
    }
}
```

 */
class Node {
    int val;
    Node left, right, next;

    Node(int val) {
        this.val = val;
    }
}

class ZigZagConnector {
    // Main method to connect nodes in zigzag pattern
    public static void connectNodesZigZag(Node root) {
        if (root == null) return;

        // Using queue for level order traversal
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        // Track if current level should be processed left to right
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Node[] levelNodes = new Node[levelSize];

            // Store current level nodes in array
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                levelNodes[i] = current;

                // Add children for next level processing
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }

            // Connect nodes based on direction
            if (leftToRight) {
                // Connect left to right
                for (int i = 0; i < levelSize - 1; i++) {
                    levelNodes[i].next = levelNodes[i + 1];
                }
                levelNodes[levelSize - 1].next = null;
            } else {
                // Connect right to left
                for (int i = levelSize - 1; i > 0; i--) {
                    levelNodes[i].next = levelNodes[i - 1];
                }
                levelNodes[0].next = null;
            }

            // Toggle direction for next level
            leftToRight = !leftToRight;
        }
    }

    // Helper method to create test tree
    private static Node createTestTree() {
        Node root = new Node(5);
        root.left = new Node(4);
        root.right = new Node(6);
        root.left.left = new Node(3);
        root.left.right = new Node(2);
        root.right.left = new Node(7);
        root.right.right = new Node(8);
        return root;
    }

    // Helper method to verify connections
    private static boolean verifyConnections(Node root) {
        if (root == null) return true;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Node> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                level.add(current);
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }

            // Verify connections
            if (leftToRight) {
                for (int i = 0; i < level.size() - 1; i++) {
                    if (level.get(i).next != level.get(i + 1)) return false;
                }
                if (level.get(level.size() - 1).next != null) return false;
            } else {
                for (int i = level.size() - 1; i > 0; i--) {
                    if (level.get(i).next != level.get(i - 1)) return false;
                }
                if (level.get(0).next != null) return false;
            }

            leftToRight = !leftToRight;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test Case 1: Normal tree
        Node root = createTestTree();
        connectNodesZigZag(root);
        System.out.println("Test Case 1: " + (verifyConnections(root) ? "PASS" : "FAIL"));

        // Test Case 2: Single node tree
        Node singleNode = new Node(1);
        connectNodesZigZag(singleNode);
        System.out.println("Test Case 2: " + (verifyConnections(singleNode) ? "PASS" : "FAIL"));

        // Test Case 3: Null tree
        connectNodesZigZag(null);
        System.out.println("Test Case 3: PASS");

        // Test Case 4: Large tree test
        Node largeRoot = createLargeTree(1000);
        connectNodesZigZag(largeRoot);
        System.out.println("Test Case 4: " + (verifyConnections(largeRoot) ? "PASS" : "FAIL"));
    }

    // Helper method to create large tree for testing
    private static Node createLargeTree(int size) {
        Node root = new Node(1);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int count = 2;

        while (count <= size) {
            Node current = queue.poll();
            current.left = new Node(count++);
            if (count <= size) {
                current.right = new Node(count++);
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }
        return root;
    }
}
