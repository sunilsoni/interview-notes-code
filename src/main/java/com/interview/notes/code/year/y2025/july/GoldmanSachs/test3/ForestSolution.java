package com.interview.notes.code.year.y2025.july.GoldmanSachs.test3;

import java.util.*;
import java.util.stream.*;

public class ForestSolution {
    
    public static int largestTree(Map<Integer, Integer> immediateParent) {
        // Store all nodes that appear in the relationships
        Set<Integer> allNodes = new HashSet<>();
        
        // Add both children and parents to allNodes set
        immediateParent.forEach((child, parent) -> {
            allNodes.add(child);
            allNodes.add(parent);
        });
        
        // Map to store root -> size of tree mapping
        Map<Integer, Integer> rootToSize = new HashMap<>();
        
        // Process each node to find its root and update tree sizes
        allNodes.forEach(node -> {
            // Find the root for current node
            int root = findRoot(node, immediateParent);
            // Update the size count for this root
            rootToSize.merge(root, 1, Integer::sum);
        });
        
        // Find maximum tree size
        int maxSize = rootToSize.values().stream()
                               .mapToInt(Integer::intValue)
                               .max()
                               .orElse(0);
        
        // Return smallest root ID among trees with maximum size
        return rootToSize.entrySet().stream()
                        .filter(e -> e.getValue() == maxSize)
                        .map(Map.Entry::getKey)
                        .min(Integer::compareTo)
                        .orElse(-1);
    }
    
    // Helper method to find root of a node by following parent chain
    private static int findRoot(int node, Map<Integer, Integer> immediateParent) {
        while (immediateParent.containsKey(node)) {
            node = immediateParent.get(node);
        }
        return node;
    }
    
    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Simple two trees of equal size
        Map<Integer, Integer> test1 = new HashMap<>();
        test1.put(1, 2);
        test1.put(3, 4);
        assertTest("Test 1", 2, largestTree(test1));
        
        // Test Case 2: One larger tree
        Map<Integer, Integer> test2 = new HashMap<>();
        test2.put(1, 2);
        test2.put(3, 2);
        test2.put(4, 5);
        assertTest("Test 2", 2, largestTree(test2));
        
        // Test Case 3: Large data test
        Map<Integer, Integer> test3 = new HashMap<>();
        for (int i = 1; i <= 10000; i++) {
            test3.put(i, 10001); // All nodes point to 10001
        }
        assertTest("Test 3", 10001, largestTree(test3));
        
        // Test Case 4: Empty forest
        Map<Integer, Integer> test4 = new HashMap<>();
        assertTest("Test 4", -1, largestTree(test4));
    }
    
    // Helper method to verify test results
    private static void assertTest(String testName, int expected, int actual) {
        System.out.println(testName + ": " + 
            (expected == actual ? "PASS" : "FAIL") +
            " (Expected: " + expected + ", Got: " + actual + ")");
    }
}
