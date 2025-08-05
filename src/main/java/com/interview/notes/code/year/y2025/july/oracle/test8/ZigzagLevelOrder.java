package com.interview.notes.code.year.y2025.july.oracle.test8;

import com.interview.notes.code.year.y2023.june23.test8.Input;
import org.hibernate.result.Output;

import java.util.*;  // import utility classes for data structures and algorithms
/*
 **
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }


Input: root = [3,9,20,null,null,15,7] <TreeNode>
Output: [[3],[20,9],[15,7]]


        3
        / \
        9 20
        /  \
        15 7
 */
public class ZigzagLevelOrder {
    // Definition for a binary tree node.
    public static class TreeNode {
        int val;            // value of the node
        TreeNode left;      // reference to left child
        TreeNode right;     // reference to right child

        TreeNode(int val) { // constructor to create a node with given value
            this.val = val; // assign the value to the node
        }
    }

    // Function to perform zigzag (alternate direction) level order traversal of binary tree
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();             // list to store final zigzag order
        if (root == null) return result;                            // if tree is empty, return empty list immediately

        Queue<TreeNode> queue = new LinkedList<>();                 // queue to manage BFS traversal
        queue.offer(root);                                          // start BFS by adding root to the queue
        boolean leftToRight = true;                                 // flag to alternate traversal direction per level

        while (!queue.isEmpty()) {                                  // loop until there are no more nodes to process
            int size = queue.size();                                // number of nodes at the current level
            List<Integer> level = new ArrayList<>(size);           // list to collect values of nodes at this level

            for (int i = 0; i < size; i++) {                        // iterate through all nodes in current level
                TreeNode node = queue.poll();                       // dequeue the next node
                level.add(node.val);                                // record the node's value in level list
                if (node.left != null) queue.offer(node.left);     // enqueue left child if it exists
                if (node.right != null) queue.offer(node.right);   // enqueue right child if it exists
            }

            if (!leftToRight) {                                     // check if this level should be added right-to-left
                Collections.reverse(level);                         // reverse the level list to achieve zigzag order
            }

            result.add(level);                                      // add the ordered level list to result
            leftToRight = !leftToRight;                             // flip the direction flag for the next level
        }

        return result;                                              // return the zigzag level order traversal
    }

    // Main method to test zigzagLevelOrder with multiple cases, including large data handling
    public static void main(String[] args) {
        // Test 1: sample tree [3,9,20,null,null,15,7]
        TreeNode root1 = new TreeNode(3);                         // create root node with value 3
        root1.left = new TreeNode(9);                             // create left child with value 9
        root1.right = new TreeNode(20);                           // create right child with value 20
        root1.right.left = new TreeNode(15);                      // create left child of node 20 with value 15
        root1.right.right = new TreeNode(7);                      // create right child of node 20 with value 7
        List<List<Integer>> result1 = zigzagLevelOrder(root1);     // call zigzag function on sample tree
        List<List<Integer>> expected1 = Arrays.asList(             // define expected zigzag output
            Arrays.asList(3),                                     // level 1: [3]
            Arrays.asList(20, 9),                                 // level 2 zigzag: [20, 9]
            Arrays.asList(15, 7)                                  // level 3: [15, 7]
        );
        System.out.println(result1.equals(expected1)               // compare result with expected
            ? "Test 1 PASS"                                     // print PASS if they match
            : "Test 1 FAIL");                                   // otherwise print FAIL

        // Test 2: empty tree
        TreeNode root2 = null;                                    // represent an empty tree
        List<List<Integer>> result2 = zigzagLevelOrder(root2);     // call zigzag function on null
        List<List<Integer>> expected2 = new ArrayList<>();         // expected result is an empty list
        System.out.println(result2.equals(expected2)               // compare result with expected
            ? "Test 2 PASS"                                     // print PASS if match
            : "Test 2 FAIL");                                   // otherwise print FAIL

        // Test 3: single node tree
        TreeNode root3 = new TreeNode(1);                         // create a tree with only one node
        List<List<Integer>> result3 = zigzagLevelOrder(root3);     // call zigzag function on single-node tree
        List<List<Integer>> expected3 = Arrays.asList(             // expected result for single node
            Arrays.asList(1)                                      // level 1: [1]
        );
        System.out.println(result3.equals(expected3)               // compare result with expected
            ? "Test 3 PASS"                                     // print PASS if match
            : "Test 3 FAIL");                                   // otherwise print FAIL

        // Test 4: large skewed tree to test performance with 10000 nodes
        TreeNode root4 = new TreeNode(1);                         // create root of skewed tree
        TreeNode curr = root4;                                    // pointer to build the skewed structure
        for (int i = 2; i <= 10000; i++) {                        // loop to add 9999 more nodes
            curr.right = new TreeNode(i);                         // attach each new node as right child
            curr = curr.right;                                    // move pointer to the newly created node
        }
        List<List<Integer>> result4 = zigzagLevelOrder(root4);     // call zigzag function on large tree
        System.out.println(result4.size() == 10000                  // verify number of levels equals number of nodes
            ? "Test 4 PASS"                                     // print PASS if correct
            : "Test 4 FAIL");                                   // otherwise print FAIL
    }
}