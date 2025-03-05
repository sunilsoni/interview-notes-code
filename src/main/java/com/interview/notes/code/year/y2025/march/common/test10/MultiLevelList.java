package com.interview.notes.code.year.y2025.march.common.test10;

import java.util.*;

public class MultiLevelList {
    
    public static List<Integer> flattenByLevel(Node head) {
        List<Integer> result = new ArrayList<>();
        if (head == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()) {
            // Get the next head from the queue
            Node currentHead = queue.poll();

            // Walk through all 'next' siblings at this level
            Node temp = currentHead;
            while (temp != null) {
                // Add the current node's value
                result.add(temp.val);

                // If there is a child list, enqueue its head
                if (temp.child != null) {
                    queue.offer(temp.child);
                }

                // Move to the next sibling
                temp = temp.next;
            }
        }

        return result;
    }
    
    public static void main(String[] args) {
        // Example usage:
        // Construct a small multilevel list for demonstration:
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.next = n2; n2.next = n3; n3.next = n4;

        Node n5 = new Node(5);
        Node n9 = new Node(9);
        Node n10 = new Node(10);
        n5.next = n9; n9.next = n10;
        
        Node n8 = new Node(8);

        // Suppose n2 has a child list starting at n5
        n2.child = n5;
        // Suppose n3 has a child list starting at n8
        n3.child = n8;
        // Suppose n4 has a child list starting at new Node(??) if you like, etc.
        // Adjust as needed to match your diagram.

        // Flatten level by level
        List<Integer> flattened = flattenByLevel(n1);
        System.out.println(flattened);
    }
}