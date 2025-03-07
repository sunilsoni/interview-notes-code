package com.interview.notes.code.year.y2025.march.meta.test1;

import java.util.HashSet;
import java.util.Set;

public class OptimizedLowestCommonAncestor {
    
    // Node class as defined in the problem
    static class Node {
        char val;
        Node parent;
        
        Node(char val) {
            this.val = val;
            this.parent = null;
        }
        
        @Override
        public String toString() {
            return "Node(" + val + ")";
        }
    }
    
    /**
     * Find the lowest common ancestor using an optimized approach
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(1) as we use constant extra space
     */
    public static Node findLCA(Node node1, Node node2) {
        // Edge case: if either node is null, return null
        if (node1 == null || node2 == null) return null;
        
        // Quick check: if nodes are the same, that node is the LCA
        if (node1 == node2) return node1;
        
        // Calculate depths of both nodes
        int depth1 = getDepth(node1);
        int depth2 = getDepth(node2);
        
        // Equalize depths by moving the deeper node up
        while (depth1 > depth2) {
            node1 = node1.parent;
            depth1--;
        }
        
        while (depth2 > depth1) {
            node2 = node2.parent;
            depth2--;
        }
        
        // Move both pointers up until they meet
        while (node1 != node2) {
            node1 = node1.parent;
            node2 = node2.parent;
        }
        
        return node1; // This is the LCA
    }
    
    /**
     * Helper method to calculate the depth of a node from the root
     * A depth of 0 means the node is the root
     */
    private static int getDepth(Node node) {
        int depth = 0;
        while (node.parent != null) {
            depth++;
            node = node.parent;
        }
        return depth;
    }
    
    /**
     * Alternative implementation using a set for comparison
     * Time Complexity: O(h) where h is the height of the tree
     * Space Complexity: O(h) to store the path from node1 to root
     */
    public static Node findLCAUsingSet(Node node1, Node node2) {
        if (node1 == null || node2 == null) return null;
        
        // Use a set to track visited nodes
        Set<Node> ancestors = new HashSet<>();
        
        // Add all ancestors of node1 to the set
        while (node1 != null) {
            ancestors.add(node1);
            node1 = node1.parent;
        }
        
        // Find the first ancestor of node2 that's also an ancestor of node1
        while (node2 != null) {
            if (ancestors.contains(node2)) {
                return node2;
            }
            node2 = node2.parent;
        }
        
        return null; // Should never reach here if tree is properly formed
    }
    
    /**
     * Test method to verify our solution
     */
    public static void main(String[] args) {
        // Create the tree structure
        //      A
        //     / \
        //    B   C
        //   / \ / \
        //  D  E F  G
        //   \     /
        //    H   I
        
        Node A = new Node('A');
        Node B = new Node('B');
        Node C = new Node('C');
        Node D = new Node('D');
        Node E = new Node('E');
        Node F = new Node('F');
        Node G = new Node('G');
        Node H = new Node('H');
        Node I = new Node('I');
        
        // Set up parent pointers
        B.parent = A;
        C.parent = A;
        D.parent = B;
        E.parent = B;
        F.parent = C;
        G.parent = C;
        H.parent = D;
        I.parent = G;
        
        // Test cases from the problem statement
        testLCA(D, E, B); // Expected: B
        testLCA(H, D, D); // Expected: D
        testLCA(D, G, A); // Expected: A
        
        // Additional test cases
        testLCA(H, I, A); // Expected: A
        testLCA(F, G, C); // Expected: C
        testLCA(B, C, A); // Expected: A
        testLCA(A, I, A); // Expected: A (root is ancestor of everything)
        
        // Edge cases
        testLCA(null, E, null); // Expected: null
        testLCA(D, null, null); // Expected: null
        testLCA(D, D, D);       // Expected: D (same node)
        
        // Performance test with large tree
        testPerformance();
    }
    
    /**
     * Helper method to test LCA function
     */
    private static void testLCA(Node node1, Node node2, Node expected) {
        Node result = findLCA(node1, node2);
        boolean pass = (result == expected);
        
        System.out.println("LCA of " + 
                          (node1 != null ? node1 : "null") + " and " + 
                          (node2 != null ? node2 : "null") + " = " + 
                          (result != null ? result : "null") + 
                          " (Expected: " + (expected != null ? expected : "null") + ") - " + 
                          (pass ? "PASS" : "FAIL"));
    }
    
    /**
     * Test performance with a large tree
     */
    private static void testPerformance() {
        System.out.println("\nPerformance Test:");
        
        // Create a deep tree with 10,000 nodes in a line
        Node[] nodes = new Node[10000];
        nodes[0] = new Node('0');
        
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new Node((char)('0' + (i % 10))); // Just cycling through digits as values
            nodes[i].parent = nodes[i-1];
        }
        
        // Measure time to find LCA between leaf and a node near the root
        long startTime = System.nanoTime();
        Node lca = findLCA(nodes[9999], nodes[10]);
        long endTime = System.nanoTime();
        
        System.out.println("LCA found: " + lca);
        System.out.println("Time taken: " + ((endTime - startTime) / 1_000_000.0) + " ms");
        
        // Compare with set-based approach
        startTime = System.nanoTime();
        lca = findLCAUsingSet(nodes[9999], nodes[10]);
        endTime = System.nanoTime();
        
        System.out.println("LCA found (using Set): " + lca);
        System.out.println("Time taken (using Set): " + ((endTime - startTime) / 1_000_000.0) + " ms");
    }
}