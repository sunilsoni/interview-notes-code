package com.interview.notes.code.year.y2025.June.amazon.test16;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;
    Node left;
    Node right;
    Node next;
    
    public Node(int data) {
        this.data = data;
        left = right = next = null;
    }
}

public class BinaryTreeZigZagConnect {
    
    public static void connectNodesZigZag(Node root) {
        if (root == null) return;
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Node prev = null;
            Node first = null;
            
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
                
                if (leftToRight) {
                    if (prev != null) {
                        prev.next = current;
                    }
                    prev = current;
                } else {
                    if (first == null) {
                        first = current;
                    }
                    current.next = prev;
                    prev = current;
                }
            }
            
            if (leftToRight) {
                prev.next = null;
            } else {
                first.next = null;
            }
            
            leftToRight = !leftToRight;
        }
    }
    
    // Helper method to print connections at each level
    public static void printConnections(Node root) {
        Node levelStart = root;
        
        while (levelStart != null) {
            Node current = levelStart;
            System.out.print("Level connections: ");
            
            while (current != null) {
                System.out.print(current.data);
                if (current.next != null) {
                    System.out.print(" -> ");
                }
                current = current.next;
            }
            System.out.println(" -> null");
            
            // Move to the first node of next level (assuming it's the leftmost node)
            levelStart = levelStart.left != null ? levelStart.left : 
                        (levelStart.right != null ? levelStart.right : null);
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Basic tree
        System.out.println("Test Case 1:");
        Node root1 = new Node(5);
        root1.left = new Node(4);
        root1.right = new Node(6);
        root1.left.left = new Node(3);
        root1.left.right = new Node(2);
        root1.right.left = new Node(7);
        root1.right.right = new Node(8);
        
        connectNodesZigZag(root1);
        printConnections(root1);
        
        // Test Case 2: Single node
        System.out.println("\nTest Case 2:");
        Node root2 = new Node(1);
        connectNodesZigZag(root2);
        printConnections(root2);
        
        // Test Case 3: Complete binary tree
        System.out.println("\nTest Case 3:");
        Node root3 = new Node(1);
        root3.left = new Node(2);
        root3.right = new Node(3);
        root3.left.left = new Node(4);
        root3.left.right = new Node(5);
        root3.right.left = new Node(6);
        root3.right.right = new Node(7);
        
        connectNodesZigZag(root3);
        printConnections(root3);
    }
}
