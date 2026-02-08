package com.interview.notes.code.year.y2026.feb.microsoft.test6;

import java.util.ArrayList;
import java.util.List;

// Node class as provided in the problem description
class Node {
    public int val;
    public List<Node> children;

    public Node(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }

    public void AddNode(Node child) {
        this.children.add(child);
    }
}

public class TreeDiameter {
    private int maxDistance = 0;

    public static void main(String[] args) {
        // Constructing the tree from the example provided in the image
        Node root = new Node(0); // Root distance to parent is always 0

        Node node5 = new Node(5);
        Node node1 = new Node(1);
        Node node3 = new Node(3);

        root.AddNode(node5);
        root.AddNode(node1);
        root.AddNode(node3);

        node5.AddNode(new Node(4));
        node5.AddNode(new Node(4));

        node1.AddNode(new Node(7));

        node3.AddNode(new Node(6));
        node3.AddNode(new Node(2));
        node3.AddNode(new Node(10));

        TreeDiameter solver = new TreeDiameter();
        System.out.println("Maximum Distance: " + solver.findMaxDistance(root));
        // Expected Output for the example: 22 (Path: 4 -> 5 -> 3 -> 10)
    }

    /**
     * Finds the greatest distance between any two nodes in the tree.
     * @param root The root node of the N-ary tree.
     * @return The maximum distance found.
     */
    public int findMaxDistance(Node root) {
        if (root == null) return 0;

        // Reset global max for multiple calls
        maxDistance = 0;

        // Start DFS from the root
        dfs(root);

        return maxDistance;
    }

    /**
     * Post-order DFS to calculate the longest downward path from each node.
     * Simultaneously updates the global maximum distance.
     */
    private int dfs(Node node) {
        // If it's a leaf node, it has no downward children paths
        if (node.children == null || node.children.isEmpty()) {
            // Update maxDistance in case the longest path is just a single leaf-to-root path
            maxDistance = Math.max(maxDistance, node.val);
            return node.val;
        }

        int firstMax = 0;
        int secondMax = 0;

        for (Node child : node.children) {
            int childPath = dfs(child);

            // Track the top two longest paths from children
            if (childPath > firstMax) {
                secondMax = firstMax;
                firstMax = childPath;
            } else if (childPath > secondMax) {
                secondMax = childPath;
            }
        }

        // The maximum distance through this node is the sum of its two longest child branches.
        // We do NOT add node.val here because node.val is the distance to its PARENT,
        // and a path through this node to two children doesn't use the edge to the parent.
        maxDistance = Math.max(maxDistance, firstMax + secondMax);

        // Return the longest downward branch + the current node's distance to its parent
        return firstMax + node.val;
    }
}