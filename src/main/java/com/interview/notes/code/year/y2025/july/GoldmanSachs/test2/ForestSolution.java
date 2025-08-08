package com.interview.notes.code.year.y2025.july.GoldmanSachs.test2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ForestSolution {

    public static int findLargestTree(Map<Integer, Integer> childToParent) {
        // Handle empty input case
        if (childToParent == null || childToParent.isEmpty()) {
            return -1;
        }

        // Step 1: Collect all nodes (both children and parents)
        Set<Integer> allNodes = new HashSet<Integer>();
        // Add all children
        allNodes.addAll(childToParent.keySet());
        // Add all parents
        allNodes.addAll(childToParent.values());

        // Step 2: Create map to store root -> tree size
        Map<Integer, Integer> rootSizes = new HashMap<Integer, Integer>();

        // Step 3: Find root for each node and count tree sizes
        for (Integer node : allNodes) {
            int root = findRoot(node, childToParent);
            // Increment size count for this root
            if (rootSizes.containsKey(root)) {
                rootSizes.put(root, rootSizes.get(root) + 1);
            } else {
                rootSizes.put(root, 1);
            }
        }

        // Step 4: Find maximum tree size
        int maxSize = 0;
        for (Integer size : rootSizes.values()) {
            if (size > maxSize) {
                maxSize = size;
            }
        }

        // Step 5: Find smallest root ID among largest trees
        int smallestRoot = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : rootSizes.entrySet()) {
            if (entry.getValue() == maxSize && entry.getKey() < smallestRoot) {
                smallestRoot = entry.getKey();
            }
        }

        return smallestRoot;
    }

    // Helper method to find root of a node
    private static int findRoot(int node, Map<Integer, Integer> childToParent) {
        // Keep following parent pointers until we reach the root
        while (childToParent.containsKey(node)) {
            node = childToParent.get(node);
        }
        return node;
    }

    // Test method
    public static void main(String[] args) {
        // Test Case 1: Two equal-sized trees
        Map<Integer, Integer> test1 = new HashMap<Integer, Integer>();
        test1.put(1, 2);  // Tree 1: 1->2
        test1.put(3, 4);  // Tree 2: 3->4
        testAndPrint("Test 1 (Equal sized trees)", test1, 2);

        // Test Case 2: One larger tree
        Map<Integer, Integer> test2 = new HashMap<Integer, Integer>();
        test2.put(1, 2);  // Tree 1: 1->2<-3
        test2.put(3, 2);  //         
        test2.put(4, 5);  // Tree 2: 4->5
        testAndPrint("Test 2 (One larger tree)", test2, 2);

        // Test Case 3: Single node trees
        Map<Integer, Integer> test3 = new HashMap<Integer, Integer>();
        test3.put(1, 2);  // 1->2
        test3.put(3, 4);  // 3->4
        test3.put(5, 6);  // 5->6
        testAndPrint("Test 3 (Multiple small trees)", test3, 2);

        // Test Case 4: Empty input
        Map<Integer, Integer> test4 = new HashMap<Integer, Integer>();
        testAndPrint("Test 4 (Empty input)", test4, -1);

        // Test Case 5: Large tree
        Map<Integer, Integer> test5 = new HashMap<Integer, Integer>();
        // Create a tree with 1000 nodes all pointing to root 1001
        for (int i = 1; i <= 1000; i++) {
            test5.put(i, 1001);
        }
        testAndPrint("Test 5 (Large tree)", test5, 1001);
    }

    // Helper method to run and verify tests
    private static void testAndPrint(String testName, Map<Integer, Integer> input, int expected) {
        int result = findLargestTree(input);
        boolean passed = (result == expected);
        System.out.println(testName);
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }
}
