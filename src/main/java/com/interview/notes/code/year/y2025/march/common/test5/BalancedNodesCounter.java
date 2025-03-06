package com.interview.notes.code.year.y2025.march.common.test5;

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
        Node test1 = createExampleTree1();
        int result1 = solution.solution(test1);
        System.out.println("Test 1: " + (result1 == 5 ? "PASS" : "FAIL") +
                " (Expected: 5, Got: " + result1 + ")");

        // Test case 2: Tree with 6 balanced nodes
        Node test2 = createExampleTree2();
        int result2 = solution.solution(test2);
        System.out.println("Test 2: " + (result2 == 6 ? "PASS" : "FAIL") +
                " (Expected: 6, Got: " + result2 + ")");

        // Test case 3: Tree with 7 balanced nodes
        Node test3 = createExampleTree3();
        int result3 = solution.solution(test3);
        System.out.println("Test 3: " + (result3 == 7 ? "PASS" : "FAIL") +
                " (Expected: 7, Got: " + result3 + ")");

        // Edge cases
        testEdgeCases(solution);

        // Performance test
        testPerformance(solution);
    }

    // Example 1: Tree with 5 balanced nodes
    private static Node createExampleTree1() {
        /*
            This structure represents:
                  A
                 / \
                B   C
               / \   \
              D   E   F
              
            Where A is the root, B and C are its children, and D, E, F are the leaf nodes.
            The balanced nodes should be D, E, F, B, and C.
        */
        Node root = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        Node nodeD = new Node();
        Node nodeE = new Node();
        Node nodeF = new Node();

        // Set up connections
        root.subtrees = new Node[]{nodeB, nodeC};
        nodeB.subtrees = new Node[]{nodeD, nodeE};
        nodeC.subtrees = new Node[]{nodeF};
        nodeD.subtrees = new Node[0];
        nodeE.subtrees = new Node[0];
        nodeF.subtrees = new Node[0];

        return root;
    }

    // Example 2: Tree with 6 balanced nodes
    private static Node createExampleTree2() {
        /*
            This structure represents a tree with 6 balanced nodes.
            The exact structure is not clear from the problem description,
            but we'll create a tree that has exactly 6 balanced nodes.
        */
        Node root = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        Node nodeD = new Node();
        Node nodeE = new Node();
        Node nodeF = new Node();
        Node nodeG = new Node();
        Node nodeH = new Node();
        Node nodeI = new Node();

        // Set up connections to get 6 balanced nodes
        root.subtrees = new Node[]{nodeB, nodeC};
        nodeB.subtrees = new Node[]{nodeD, nodeE};
        nodeC.subtrees = new Node[]{nodeF, nodeG};
        nodeD.subtrees = new Node[]{nodeH};
        nodeE.subtrees = new Node[0];
        nodeF.subtrees = new Node[]{nodeI};
        nodeG.subtrees = new Node[0];
        nodeH.subtrees = new Node[0];
        nodeI.subtrees = new Node[0];

        return root;
    }

    // Example 3: Tree with 7 balanced nodes
    private static Node createExampleTree3() {
        /*
            This structure represents a tree with 7 balanced nodes.
            We'll create a symmetric tree where most nodes are balanced.
        */
        Node root = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        Node nodeD = new Node();
        Node nodeE = new Node();
        Node nodeF = new Node();
        Node nodeG = new Node();
        Node nodeH = new Node();
        Node nodeI = new Node();
        Node nodeJ = new Node();

        // Set up connections to get 7 balanced nodes
        root.subtrees = new Node[]{nodeB, nodeC, nodeD, nodeE, nodeF};
        nodeB.subtrees = new Node[]{nodeG};
        nodeC.subtrees = new Node[]{nodeH};
        nodeD.subtrees = new Node[]{nodeI};
        nodeE.subtrees = new Node[]{nodeJ};
        nodeF.subtrees = new Node[0];
        nodeG.subtrees = new Node[0];
        nodeH.subtrees = new Node[0];
        nodeI.subtrees = new Node[0];
        nodeJ.subtrees = new Node[0];

        return root;
    }

    private static void testEdgeCases(Solution solution) {
        // Single node tree
        Node singleNode = new Node();
        singleNode.subtrees = new Node[0];
        int resultSingle = solution.solution(singleNode);
        System.out.println("Single Node Test: " + (resultSingle == 1 ? "PASS" : "FAIL") +
                " (Expected: 1, Got: " + resultSingle + ")");

        // Tree with all nodes having the same number of children
        Node perfectTree = createPerfectTree(3, 3); // 3 levels, 3 children per node
        int resultPerfect = solution.solution(perfectTree);
        System.out.println("Perfect Tree Test: " + resultPerfect + " balanced nodes");
    }

    private static void testPerformance(Solution solution) {
        // Large tree test
        Node largeTree = createLargeTree(100);
        long startTime = System.currentTimeMillis();
        int resultLarge = solution.solution(largeTree);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Tree Test: Processed 100 nodes in " + (endTime - startTime) + "ms");
        System.out.println("Result: " + resultLarge + " balanced nodes");
    }

    // Creates a perfect tree where each non-leaf node has exactly 'children' children
    // and the tree has 'levels' levels
    private static Node createPerfectTree(int levels, int children) {
        if (levels <= 0) {
            return null;
        }

        Node root = new Node();
        if (levels == 1) {
            root.subtrees = new Node[0];
            return root;
        }

        root.subtrees = new Node[children];
        for (int i = 0; i < children; i++) {
            root.subtrees[i] = createPerfectTree(levels - 1, children);
        }

        return root;
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