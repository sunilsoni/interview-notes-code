package com.interview.notes.code.year.y2025.december.microsoft.test5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

class Node {
    int val;
    Node left, right;
    Node(int v) { val = v; }
}

public class SecondLargestBST {
    
    // optimized: single unified traversal
    // tracks both largest and second largest in one pass
    static Integer findSecondLargest(Node root) {
        
        // need at least 2 nodes
        if (root == null || isLeaf(root)) return null;
        
        Node curr = root;  // traversal pointer
        
        // traverse until we find answer
        while (curr != null) {
            
            // case 1: curr is largest (no right child) but has left subtree
            // second largest = rightmost of left subtree
            if (curr.right == null && curr.left != null) {
                return getRightmost(curr.left);  // find rightmost in left
            }
            
            // case 2: curr's right child is largest (right has no children)
            // second largest = curr (parent of largest)
            if (isLeaf(curr.right)) {
                return curr.val;  // parent is answer
            }
            
            // keep moving right towards largest
            curr = curr.right;
        }
        
        return null;  // edge case
    }
    
    // helper: check if node is leaf (no children)
    static boolean isLeaf(Node n) {
        return n != null && n.left == null && n.right == null;
    }
    
    // helper: get rightmost node value
    static int getRightmost(Node n) {
        while (n.right != null) n = n.right;  // keep going right
        return n.val;
    }
    
    // insert into BST
    static Node insert(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }
    
    // build tree from array
    static Node build(int[] arr) {
        Node root = null;
        for (int v : arr) root = insert(root, v);
        return root;
    }
    
    // test helper
    static void test(String name, int[] arr, Integer expected) {
        var result = findSecondLargest(build(arr));
        var status = Objects.equals(result, expected) ? "PASS ✓" : "FAIL ✗";
        System.out.println(name + ": " + status + " | Expected: " + expected + " | Got: " + result);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Tests ===\n");
        
        test("Balanced tree", new int[]{50, 30, 70, 20, 40, 60, 80}, 70);
        test("Largest has left", new int[]{50, 30, 80, 60, 75}, 75);
        test("Right skewed", new int[]{1, 2, 3, 4, 5}, 4);
        test("Left skewed", new int[]{5, 4, 3, 2, 1}, 4);
        test("Two nodes", new int[]{5, 3}, 3);
        test("Single node", new int[]{5}, null);
        test("Empty", new int[]{}, null);
        
        // large data test
        System.out.println("\n=== Large Data ===");
        var large = new ArrayList<Integer>();
        for (int i = 1; i <= 100000; i++) large.add(i);
        Collections.shuffle(large);
        int[] arr = large.stream().mapToInt(i -> i).toArray();
        
        long start = System.currentTimeMillis();
        test("100k nodes", arr, 99999);
        System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
    }
}