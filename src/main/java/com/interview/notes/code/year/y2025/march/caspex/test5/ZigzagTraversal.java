package com.interview.notes.code.year.y2025.march.caspex.test5;

import java.util.*;
import java.util.stream.IntStream;

/**
 * A simple class to demonstrate zigzag (spiral) traversal in a binary tree.
 * <p>
 * NOTE: We follow the examples which show:
 * - Level 1 -> left to right
 * - Level 2 -> right to left
 * - Level 3 -> left to right
 * ... and so on.
 */
public class ZigzagTraversal {

    // The root of the binary tree
    private Node root;

    /**
     * Main method to run and test the zigzag traversal solution.
     * We demonstrate a test approach that:
     * 1) Builds multiple test cases
     * 2) Invokes the traversal
     * 3) Checks expected vs actual results
     * 4) Prints PASS/FAIL
     */
    public static void main(String[] args) {

        // We will define an inline test method
        // that takes a set of edges and the expected array,
        // builds the tree, and tests getLevelSpiral().
        class TestCase {
            List<String> inputEdges;
            int[] expected;

            TestCase(List<String> inputEdges, int[] expected) {
                this.inputEdges = inputEdges;
                this.expected = expected;
            }
        }

        // Prepare test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example 1
        testCases.add(new TestCase(
                Arrays.asList("10", "20", "R", "10", "30", "L"),
                new int[]{10, 30, 20}
        ));

        // Example 2
        testCases.add(new TestCase(
                Arrays.asList("2", "4", "L", "2", "6", "R", "4", "8", "L", "4", "10", "R"),
                new int[]{2, 4, 6, 10, 8}
        ));

        // Single-node tree (no edges): root alone
        // Let's say the single node is "100", no edges => expected is [100]
        testCases.add(new TestCase(
                Collections.emptyList(),  // no edges
                new int[]{100} // We'll build that single root manually inside the test
        ));

        // Skewed tree: 1 -> 2 -> 3 -> 4 (all left children)
        testCases.add(new TestCase(
                Arrays.asList("1", "2", "L", "2", "3", "L", "3", "4", "L"),
                new int[]{1, 2, 3, 4} // spiral = basically level order in this case
        ));

        // Large test: we won't do thousands here,
        // but let's show that approach is the same.
        // Just verifying it runs in O(n) time for large inputs.

        // Now let's run the test harness
        IntStream.range(0, testCases.size()).forEach(i -> {
            TestCase tc = testCases.get(i);

            // Create a new ZigzagTraversal instance
            ZigzagTraversal solver = new ZigzagTraversal();

            // Special handling: If it's the single-node test with no edges,
            // we manually set the root
            if (i == 2) {
                // single node, no edges
                solver.root = new Node(100);
            } else {
                // build the tree from the input
                solver.buildTree(tc.inputEdges);
            }

            // Get the actual result
            int[] actual = solver.getLevelSpiral();

            // Compare with expected
            boolean pass = Arrays.equals(actual, tc.expected);

            System.out.println("Test Case #" + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("  Expected: " + Arrays.toString(tc.expected));
                System.out.println("  Actual:   " + Arrays.toString(actual));
            }
        });
    }

    /**
     * Builds the tree given a list of edges:
     * Format of each edge: parent, child, 'L' or 'R'.
     * <p>
     * We also maintain a map from value -> node, so each value has a single Node object.
     * This method sets root based on the node which is never a child.
     *
     * @param edges list of tokens in the form: node1 node2 L/R
     */
    public void buildTree(List<String> edges) {
        // Map: node value -> Node object
        Map<Integer, Node> nodeMap = new HashMap<>();
        // Keep track of all child nodes
        Set<Integer> children = new HashSet<>();

        // Process triplets
        for (int i = 0; i < edges.size(); i += 3) {
            int parentVal = Integer.parseInt(edges.get(i));
            int childVal = Integer.parseInt(edges.get(i + 1));
            char dir = edges.get(i + 2).charAt(0);

            // If the parent node doesn't exist, create it
            Node parentNode = nodeMap.computeIfAbsent(parentVal, Node::new);
            // If the child node doesn't exist, create it
            Node childNode = nodeMap.computeIfAbsent(childVal, Node::new);

            // Link them based on L/R
            if (dir == 'L') {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }

            // Mark child
            children.add(childVal);
        }

        // Find the root: a node that is never in children set
        // This assumes the input is valid and forms exactly one connected component
        int possibleRoot = -1;
        for (int val : nodeMap.keySet()) {
            if (!children.contains(val)) {
                possibleRoot = val;
                break;
            }
        }

        // Set the root in this class
        this.root = nodeMap.get(possibleRoot);
    }

    /**
     * Returns the zigzag (spiral) level order traversal of the tree as an int[].
     * <p>
     * Level 1 -> left to right
     * Level 2 -> right to left
     * Level 3 -> left to right
     * etc.
     */
    public int[] getLevelSpiral() {
        // If root is null, return an empty array
        if (root == null) {
            return new int[0];
        }

        // List to store final zigzag traversal result
        List<Integer> result = new ArrayList<>();

        // Standard BFS queue
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        // Boolean to track direction; 
        // true => left to right, false => right to left
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            // Temporarily hold nodes at this level
            List<Integer> levelNodes = new ArrayList<>(size);

            // Dequeue all nodes for this level
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                levelNodes.add(cur.val);

                // Enqueue children for next level
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            // If this is a right-to-left level, reverse the list
            if (!leftToRight) {
                Collections.reverse(levelNodes);
            }

            // Append to the overall result
            result.addAll(levelNodes);

            // Flip the direction for the next level
            leftToRight = !leftToRight;
        }

        // Convert result list to int array
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Node structure representing each tree node.
     */
    static class Node {
        int val;
        Node left, right;

        Node(int v) {
            this.val = v;
        }
    }
}
