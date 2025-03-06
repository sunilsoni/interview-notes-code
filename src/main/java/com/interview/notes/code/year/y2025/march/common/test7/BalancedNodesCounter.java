package com.interview.notes.code.year.y2025.march.common.test7;

/*
WORKING:

### Problem Statement:
You are given a **rooted tree**. A **node is balanced** if all of its subtrees are of the same size. The **size of a subtree** is the number of nodes it contains.

For example, in the given tree diagram:
- The **root is not balanced** because its left subtree has 3 nodes and the right subtree has 2 nodes.
- The **tree has five balanced nodes** (marked in gray).

### Function Definition:
Write a function:
```java
class Solution {
    public int solution(Node tree);
}
```
that, given a **tree**, returns the **number of balanced nodes** it contains.

### Technical Details:
Assume that the following declaration is given:
```java
class Node {
    public Node[] subtrees;
}
```
- A tree is specified using a **pointer-based data structure**.
- For each **Node**, the attribute **subtrees** holds pointers to its subtrees.

### Examples:

1. **Input:**
   - Given tree (shown in the diagram).
   - **Output:** `5`

2. **Input:**
   - Given tree with more nodes (shown in the diagram).
   - **Output:** `6`
   - (Balanced nodes are marked in **gray**).

3. **Input:**
   - Another given tree.
   - **Output:** `7`

 */
class Node {
    public Node[] subtrees;
}

class Solution {
    public int solution(Node tree) {
        if (tree == null) {
            return 0;
        }

        // Count balanced nodes using DFS
        int[] balancedCount = new int[1]; // Using array to pass by reference
        countSubtreeSize(tree, balancedCount);

        return balancedCount[0];
    }

    // Returns the size of the subtree rooted at node
    // Updates balancedCount as it traverses the tree
    private int countSubtreeSize(Node node, int[] balancedCount) {
        if (node == null) {
            return 0;
        }

        // If node has no subtrees, it's balanced by definition
        if (node.subtrees == null || node.subtrees.length == 0) {
            balancedCount[0]++;
            return 1;
        }

        // Calculate sizes of all subtrees
        int[] subtreeSizes = new int[node.subtrees.length];
        for (int i = 0; i < node.subtrees.length; i++) {
            subtreeSizes[i] = countSubtreeSize(node.subtrees[i], balancedCount);
        }

        // Check if all subtrees have the same size
        boolean isBalanced = true;
        for (int i = 1; i < subtreeSizes.length; i++) {
            if (subtreeSizes[i] != subtreeSizes[0]) {
                isBalanced = false;
                break;
            }
        }

        if (isBalanced) {
            balancedCount[0]++;
        }

        // Return the total size of this subtree (1 for this node + sum of all subtree sizes)
        int totalSize = 1; // Count the current node
        for (int size : subtreeSizes) {
            totalSize += size;
        }

        return totalSize;
    }
}

public class BalancedNodesCounter {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Tree with 5 balanced nodes
        // Format: ([([([],), ([],)],), ([([],)],)],)
        Node test1 = createTestCase1();
        int result1 = solution.solution(test1);
        System.out.println("Test 1: " + (result1 == 5 ? "PASS" : "FAIL") +
                " (Expected: 5, Got: " + result1 + ")");

        // Test case 2: Tree with 6 balanced nodes
        // Format: ([([([],), ([],)],), ([([],), ([([],)],)],)],)
        Node test2 = createTestCase2();
        int result2 = solution.solution(test2);
        System.out.println("Test 2: " + (result2 == 6 ? "PASS" : "FAIL") +
                " (Expected: 6, Got: " + result2 + ")");

        // Test case 3: Tree with 7 balanced nodes
        // Format: ([([([],), ([],), ([([],), ([],), ([],)],)],)],)
        Node test3 = createTestCase3();
        int result3 = solution.solution(test3);
        System.out.println("Test 3: " + (result3 == 7 ? "PASS" : "FAIL") +
                " (Expected: 7, Got: " + result3 + ")");

        // Edge cases
        testEdgeCases(solution);
    }

    // Test case 1: Tree with 5 balanced nodes
    // Format: ([([([],), ([],)],), ([([],)],)],)
    private static Node createTestCase1() {
        /*
        This represents:
                  A
                 / \
                B   C
               / \   \
              D   E   F
              
        Where:
        - A is the root
        - B has children D and E
        - C has child F
        - D, E, F are leaf nodes
        
        Balanced nodes: B, C, D, E, F (5 nodes)
        Unbalanced nodes: A (root)
        */
        Node root = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        Node nodeD = new Node();
        Node nodeE = new Node();
        Node nodeF = new Node();

        root.subtrees = new Node[]{nodeB, nodeC};
        nodeB.subtrees = new Node[]{nodeD, nodeE};
        nodeC.subtrees = new Node[]{nodeF};
        nodeD.subtrees = new Node[0];
        nodeE.subtrees = new Node[0];
        nodeF.subtrees = new Node[0];

        return root;
    }

    // Test case 2: Tree with 6 balanced nodes
    // Format: ([([([],), ([],)],), ([([],), ([([],)],)],)],)
    private static Node createTestCase2() {
        /*
        This represents:
                  A
                 / \
                B   C
               / \ / \
              D  E F  G
                     /
                    H
                    
        Where:
        - A is the root
        - B has children D and E
        - C has children F and G
        - G has child H
        - D, E, F, H are leaf nodes
        
        Balanced nodes: D, E, F, H, B, G (6 nodes)
        Unbalanced nodes: A (root), C
        */
        Node root = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        Node nodeD = new Node();
        Node nodeE = new Node();
        Node nodeF = new Node();
        Node nodeG = new Node();
        Node nodeH = new Node();

        root.subtrees = new Node[]{nodeB, nodeC};
        nodeB.subtrees = new Node[]{nodeD, nodeE};
        nodeC.subtrees = new Node[]{nodeF, nodeG};
        nodeD.subtrees = new Node[0];
        nodeE.subtrees = new Node[0];
        nodeF.subtrees = new Node[0];
        nodeG.subtrees = new Node[]{nodeH};
        nodeH.subtrees = new Node[0];

        return root;
    }

    // Test case 3: Tree with 7 balanced nodes
    // Format: ([([([],), ([],), ([([],), ([],), ([],)],)],)],)
    private static Node createTestCase3() {
        /*
        This represents:
                  A
                  |
                  B
                / | \
               C  D  E
                    /|\
                   F G H
                   
        Where:
        - A is the root with single child B
        - B has children C, D, and E
        - E has children F, G, and H
        - C, D, F, G, H are leaf nodes
        
        Balanced nodes: A, C, D, F, G, H, E (7 nodes)
        Unbalanced nodes: B
        */
        Node root = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        Node nodeD = new Node();
        Node nodeE = new Node();
        Node nodeF = new Node();
        Node nodeG = new Node();
        Node nodeH = new Node();

        root.subtrees = new Node[]{nodeB};
        nodeB.subtrees = new Node[]{nodeC, nodeD, nodeE};
        nodeC.subtrees = new Node[0];
        nodeD.subtrees = new Node[0];
        nodeE.subtrees = new Node[]{nodeF, nodeG, nodeH};
        nodeF.subtrees = new Node[0];
        nodeG.subtrees = new Node[0];
        nodeH.subtrees = new Node[0];

        return root;
    }

    private static void testEdgeCases(Solution solution) {
        // Single node tree
        Node singleNode = new Node();
        singleNode.subtrees = new Node[0];
        int resultSingle = solution.solution(singleNode);
        System.out.println("Single Node Test: " + (resultSingle == 1 ? "PASS" : "FAIL") +
                " (Expected: 1, Got: " + resultSingle + ")");

        // Large tree test
        Node largeTree = createLargeTree(100);
        long startTime = System.currentTimeMillis();
        int resultLarge = solution.solution(largeTree);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Tree Test: Processed 100 nodes in " + (endTime - startTime) + "ms");
        System.out.println("Result: " + resultLarge + " balanced nodes");
    }

    // Creates a large tree with specified number of nodes
    private static Node createLargeTree(int size) {
        if (size <= 0) {
            return null;
        }

        // Create a balanced binary tree
        Node root = new Node();
        if (size == 1) {
            root.subtrees = new Node[0];
            return root;
        }

        // Split remaining nodes between subtrees
        int numSubtrees = Math.min(5, size - 1); // At most 5 subtrees
        root.subtrees = new Node[numSubtrees];

        int nodesPerSubtree = (size - 1) / numSubtrees;
        int extraNodes = (size - 1) % numSubtrees;

        for (int i = 0; i < numSubtrees; i++) {
            int subtreeSize = nodesPerSubtree + (i < extraNodes ? 1 : 0);
            root.subtrees[i] = createLargeTree(subtreeSize);
        }

        return root;
    }
}