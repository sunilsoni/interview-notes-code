package com.interview.notes.code.year.y2026.march.meta;

import java.util.ArrayList;
import java.util.List;

// Using record for a clean, minimal TreeNode.
record TreeNode(int val, TreeNode left, TreeNode right) {}

public class AverageOfLevelsDFS {

    // Main solver function that returns the list of average values.
    public static List<Double> getAverageOfLevels(TreeNode root) {
        // levelData will hold arrays of [sum, count] for each depth level.
        var levelData = new ArrayList<double[]>();
        
        // Start recursive depth-first search starting from root at depth 0.
        dfs(root, 0, levelData);
        
        // Create a final list to store the actual computed averages.
        var averages = new ArrayList<Double>();
        
        // Loop through our gathered data to calculate the final averages.
        for (double[] data : levelData) {
            // Average = Sum (data[0]) divided by Count (data[1]).
            averages.add(data[0] / data[1]);
        }
        
        // Return the final list of averages.
        return averages;
    }

    // Upgraded private helper method for recursive DFS traversal.
    private static void dfs(TreeNode node, int depth, List<double[]> levelData) {
        // Base case: if the branch ends (node is null), stop and return back up.
        if (node == null) return;
        
        // If current depth matches the list size, it's our first time at this level.
        if (depth == levelData.size()) {
            // Add a fresh array [0.0, 0.0] representing [initial sum, initial count].
            levelData.add(new double[]{0.0, 0.0});
        }
        
        // Add the current node's value to the sum tracker for this specific depth.
        levelData.get(depth)[0] += node.val();
        
        // Increment the node count tracker for this specific depth by 1.
        levelData.get(depth)[1] += 1;
        
        // Continue traversing down the left side, increasing depth by 1.
        dfs(node.left(), depth + 1, levelData);
        
        // Continue traversing down the right side, increasing depth by 1.
        dfs(node.right(), depth + 1, levelData);
    }

    // Standard main method used exclusively for running our test scenarios.
    public static void main(String[] args) {
        // Build the tree from your second screenshot: 
        // 1
        // / \
        // 2 3
        // \ /
        // 5 4
        TreeNode root = new TreeNode(1, 
            new TreeNode(2, null, new TreeNode(5, null, null)), 
            new TreeNode(3, new TreeNode(4, null, null), null)
        );

        // Call our DFS solver method.
        var actual = getAverageOfLevels(root);
        
        // Define the expected output based on the image.
        var expected = List.of(1.0, 2.5, 4.5);
        
        // Print the results.
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);
        System.out.println("Test:     " + (actual.equals(expected) ? "PASS" : "FAIL"));
    }
}