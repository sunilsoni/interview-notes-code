package com.interview.notes.code.year.y2026.march.meta.test2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Using record for a clean, minimal TreeNode.

public class LevelAverage {

    public static List<Double> getLevelAverages(TreeNode root) {
        // Handle the edge case where the tree is empty.
        if (root == null) return List.of();

        var averages = new ArrayList<Double>(); // List to store our final results.
        var queue = new LinkedList<TreeNode>(); // Queue to manage BFS traversal.
        
        queue.add(root); // Start the process by adding the root node.

        // Continue as long as there are nodes to process in the queue.
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Number of nodes currently at this specific level.
            double levelSum = 0; // Running total of values at this level.

            // Process every node that belongs to the current level.
            for (int i = 0; i < levelSize; i++) {
                var currentNode = queue.poll(); // Remove the next node from the queue.
                levelSum += currentNode.val(); // Add its value to the level total.

                // If children exist, add them to the queue for the NEXT level.
                if (currentNode.left() != null) queue.add(currentNode.left());
                if (currentNode.right() != null) queue.add(currentNode.right());
            }

            // Calculate the average (Total / Count) and add to results.
            averages.add(levelSum / levelSize);
        }
        return averages; // Return the list of averages (e.g., [1.0, 2.5, 4.5]).
    }

    public static void main(String[] args) {
        // Building the tree from your image:
        // Level 0: 1
        // Level 1: 2, 3 -> Avg: (2+3)/2 = 2.5
        // Level 2: 5, 4 -> Avg: (5+4)/2 = 4.5
        TreeNode root = new TreeNode(1, 
            new TreeNode(2, null, new TreeNode(5, null, null)), 
            new TreeNode(3, new TreeNode(4, null, null), null)
        );

        var result = getLevelAverages(root);
        
        // Manual verification: Expected [1.0, 2.5, 4.5]
        System.out.println("Averages per level: " + result);
        
        // Simple PASS/FAIL check
        var expected = List.of(1.0, 2.5, 4.5);
        System.out.println(result.equals(expected) ? "TEST PASS" : "TEST FAIL");
    }
}