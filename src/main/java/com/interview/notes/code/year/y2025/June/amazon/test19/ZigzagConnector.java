package com.interview.notes.code.year.y2025.June.amazon.test19;

import java.util.*;                                     // for List, ArrayList, Queue, LinkedList, Objects
import java.util.stream.*;                              // for Stream, Collectors

public class ZigzagConnector {

    /** Node definition with value, child pointers, and a 'next' pointer. */
    static class Node {
        public int val;            // node's integer value
        public Node left, right;   // left and right child references
        public Node next;          // pointer to the next node in zigzag order

        public Node(int val) {
            this.val = val;       // initialize node value
        }
    }

    /**
     * Connects each node's 'next' pointer in zigzag order across levels.
     */
    public static void connectZigzag(Node root) {
        if (root == null) return;                                 // nothing to connect if tree is empty
        List<Node> currentLevel = new ArrayList<>();               // will hold nodes of current level
        currentLevel.add(root);                                    // start with the root node
        boolean leftToRight = true;                                // first level goes left-to-right

        while (!currentLevel.isEmpty()) {                          // process until no more levels
            int size = currentLevel.size();                        // number of nodes at this level

            if (leftToRight) {
                for (int i = 0; i < size; i++) {                   // left-to-right linking
                    Node node = currentLevel.get(i);               // get the i-th node
                    node.next = (i < size - 1                        // if not last in this level
                                  ? currentLevel.get(i + 1)         //   link to the next node
                                  : null);                         // else end with null
                }
            } else {
                for (int i = size - 1; i >= 0; i--) {               // right-to-left linking
                    Node node = currentLevel.get(i);               // get the i-th node from end
                    node.next = (i > 0                             // if not the first in reverse
                                  ? currentLevel.get(i - 1)         //   link backwards
                                  : null);                         // else end with null
                }
            }

            // build list of children for the next level using streams
            List<Node> nextLevel = currentLevel.stream()
                .flatMap(n -> Stream.of(n.left, n.right))          // stream of all children
                .filter(Objects::nonNull)                          // drop nulls
                .collect(Collectors.toList());                     // collect into list

            currentLevel = nextLevel;                              // move down one level
            leftToRight = !leftToRight;                            // flip zigzag direction
        }
    }

    /** Helper: returns a list of levels (each level is a list of nodes). */
    private static List<List<Node>> getLevels(Node root) {
        List<List<Node>> levels = new ArrayList<>();              
        if (root == null) return levels;                          // empty list if tree is empty

        Queue<Node> queue = new LinkedList<>();                   
        queue.add(root);                                          // start BFS from root

        while (!queue.isEmpty()) {
            int sz = queue.size();                                
            List<Node> level = new ArrayList<>();                 
            for (int i = 0; i < sz; i++) {                        
                Node n = queue.poll();                            
                level.add(n);                                     
                if (n.left != null)  queue.add(n.left);           // enqueue left child
                if (n.right != null) queue.add(n.right);          // enqueue right child
            }
            levels.add(level);                                    
        }
        return levels;                                            // return all levels
    }

    /** Test: empty tree should not throw and do nothing. */
    private static boolean testEmpty() {
        Node root = null;                                        
        try {
            connectZigzag(root);                                 // should handle gracefully
            return true;                                         // PASS if no exception
        } catch (Exception e) {
            return false;                                        // FAIL on exception
        }
    }

    /** Test: single node's next should stay null. */
    private static boolean testSingleNode() {
        Node root = new Node(1);                                 // one-node tree
        connectZigzag(root);                                     // connect
        return root.next == null;                                // PASS if still null
    }

    /** Test: two levels: root->level1 reversed. */
    private static boolean testTwoLevels() {
        Node root = new Node(1);                                
        root.left = new Node(2);                                
        root.right = new Node(3);                               
        List<List<Node>> levels = getLevels(root);               // [[1], [2,3]]
        connectZigzag(root);                                     // link zigzag

        // level 0 left-to-right: [1]
        if (root.next != null) return false;                     // root.next must be null

        // level 1 right-to-left: start at 3
        Node start = levels.get(1).get(1);                       // node 3
        if (start.val != 3) return false;                       
        if (start.next != levels.get(1).get(0)) return false;    // 3.next must be 2
        if (start.next.next != null) return false;               // then null
        return true;                                            
    }

    /** Test: example from prompt. */
    private static boolean testExample() {
        // build the tree shown:
        Node root = new Node(5);
        root.left  = new Node(4);
        root.right = new Node(6);
        root.left.left  = new Node(3);
        root.left.right = new Node(2);
        root.right.left = new Node(7);
        root.right.right= new Node(8);

        List<List<Node>> levels = getLevels(root);               // capture original levels
        connectZigzag(root);                                     // do the linking

        boolean leftToRight = true;                              // direction flag
        for (int i = 0; i < levels.size(); i++) {
            List<Node> lvl = levels.get(i);
            List<Integer> expected = new ArrayList<>();
            if (leftToRight) {
                for (Node n : lvl) expected.add(n.val);          // in-order
            } else {
                for (int j = lvl.size() - 1; j >= 0; j--)         // reverse
                    expected.add(lvl.get(j).val);
            }
            // traverse .next pointers
            List<Integer> actual = new ArrayList<>();
            Node cur = leftToRight ? lvl.get(0)                  // start at front or back
                                    : lvl.get(lvl.size() - 1);
            while (cur != null) {
                actual.add(cur.val);
                cur = cur.next;                                   // follow next pointers
            }
            if (!actual.equals(expected)) return false;          // mismatch → FAIL
            leftToRight = !leftToRight;                          // flip for next level
        }
        return true;                                             // all levels matched
    }

    /** Test: large complete tree of size N to ensure performance and pointer counts. */
    private static boolean testLarge() {
        int N = 10000;                                           // number of nodes
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i);                              // create each node
        }
        for (int i = 0; i < N; i++) {
            int left = 2 * i + 1, right = 2 * i + 2;
            if (left < N)  nodes[i].left  = nodes[left];        // assign left child
            if (right < N) nodes[i].right = nodes[right];       // assign right child
        }
        Node root = nodes[0];                                    // root of large tree
        connectZigzag(root);                                     // should run quickly

        List<List<Node>> levels = getLevels(root);               
        boolean dir = true;
        for (List<Node> lvl : levels) {
            int count = 0;
            Node cur = dir ? lvl.get(0) : lvl.get(lvl.size() - 1);
            while (cur != null) {
                count++;                                         // count through .next chain
                cur = cur.next;
            }
            if (count != lvl.size()) return false;               // must match level width
            dir = !dir;
        }
        return true;                                             // performance & counts OK
    }

    /** Main method to run all tests and print PASS/FAIL. */
    public static void main(String[] args) {
        int passed = 0, total = 5;                              // we have 5 tests
        if (testEmpty())     { System.out.println("testEmpty: PASS");     passed++; }
        else                { System.out.println("testEmpty: FAIL"); }
        if (testSingleNode()){ System.out.println("testSingleNode: PASS"); passed++; }
        else                { System.out.println("testSingleNode: FAIL"); }
        if (testTwoLevels()) { System.out.println("testTwoLevels: PASS");  passed++; }
        else                { System.out.println("testTwoLevels: FAIL"); }
        if (testExample())   { System.out.println("testExample: PASS");    passed++; }
        else                { System.out.println("testExample: FAIL"); }
        if (testLarge())     { System.out.println("testLarge: PASS");      passed++; }
        else                { System.out.println("testLarge: FAIL"); }

        System.out.printf("Summary: %d/%d tests passed.%n", passed, total);
    }
}