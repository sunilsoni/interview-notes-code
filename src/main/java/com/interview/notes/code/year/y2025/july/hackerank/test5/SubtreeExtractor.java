package com.interview.notes.code.year.y2025.july.hackerank.test5;

import java.util.*;

/*
Here’s a properly constructed question based on the visual and the hint text you provided:

---

## 🧩 Problem: Subtree Extraction from N-ary Tree

### 🌳 Tree Structure (Visual Representation):

```
        1
      /   \
     2     3
    / \   / \
   4   5 6   7
```

Or programmatically represented as:

```java
1 -> {2 -> {4, 5}, 3 -> {6, 7}}
```

---

### 🧠 Objective:

Given the root of an N-ary tree (where each node may have multiple children), **implement a method that extracts and returns a subtree starting from a given node `targetId`**.

---

### ✍️ Function Signature:

```java
public Node extractSubtree(Node root, int targetId)
```

---

### 📥 Input Example:

```java
Input Tree:
Node root = 1
- Children: 2, 3
- 2's Children: 4, 5
- 3's Children: 6, 7

targetId = 3
```

---

### 📤 Output:

```java
Returned subtree:
3 -> {6, 7}
```

---

### 📝 Constraints:

* You may assume all node values are unique.
* Node is defined as:

```java
class Node {
    int val;
    List<Node> children;
}
```

---


 */
// Node class represents a single node in the N-ary tree
class Node {
    int val;                // Stores the value/ID of the node
    List<Node> children;    // List to store multiple children (N-ary tree)

    // Constructor for creating a leaf node (no children)
    public Node(int val) {
        this.val = val;
        this.children = new ArrayList<>();  // Initialize empty children list
    }

    // Constructor for creating a node with existing children
    // Useful for building test trees more concisely
    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}

public class SubtreeExtractor {

    // Main method to extract subtree given a target ID
    // Uses recursive DFS approach with Stream API for cleaner code
    public static Node extractSubtree(Node root, int targetId) {
        if (root == null) return null;                      // Base case: empty tree
        if (root.val == targetId) return root;              // Found target node
        return root.children.stream()                       // Search in children
            .map(child -> extractSubtree(child, targetId))  // Recursive call for each child
            .filter(Objects::nonNull)                       // Filter out null results
            .findFirst()                                    // Get first valid result
            .orElse(null);                                  // Return null if not found
    }

    // Utility method to convert tree to readable string format
    // Helps in visualizing the tree structure for debugging and testing
    public static String formatTree(Node node) {
        if (node == null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append(node.val);
        if (!node.children.isEmpty()) {
            sb.append(" { ");
            for (int i = 0; i < node.children.size(); i++) {
                if (i > 0) sb.append(", ");                 // Add comma between siblings
                sb.append(formatTree(node.children.get(i))); // Recursive call for children
            }
            sb.append(" }");
        }
        return sb.toString();
    }

    // Helper method to count nodes in a tree/subtree
    // Used for validation in testing
    private static int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + node.children.stream()                   // Count current node + all children
                    .mapToInt(SubtreeExtractor::countNodes) // Recursive count for each child
                    .sum();
    }

    // Test helper method to run and validate test cases
    // Compares actual vs expected node counts and prints results
    private static void runTest(Node root, int targetId, int expectedCount) {
        Node subtree = extractSubtree(root, targetId);
        System.out.println("Extracted subtree: " + formatTree(subtree));
        int actualCount = countNodes(subtree);
        if (actualCount == expectedCount) {
            System.out.println("→ PASS (size=" + actualCount + ")\n");
        } else {
            System.out.println("→ FAIL: expected size=" + expectedCount
                + " but got " + actualCount + "\n");
        }
    }

    // Main method to demonstrate and test the implementation
    public static void main(String[] args) {
        // Create test tree using the concise constructor
        Node root = new Node(1, Arrays.asList(
            new Node(2, Arrays.asList(new Node(4), new Node(5))),
            new Node(3, Arrays.asList(new Node(6), new Node(7)))
        ));

        System.out.println("Full tree: " + formatTree(root) + "\n");

        // Test various scenarios:
        runTest(root, 3, 3);  // Extract middle node with children
        runTest(root, 2, 3);  // Extract different middle node
        runTest(root, 4, 1);  // Extract leaf node
        runTest(root, 8, 0);  // Try to extract non-existent node
    }
}
