package com.interview.notes.code.year.y2025.march.common.test10;

import java.util.*;

public class MultiLevelListFlattener {
    public static List<Integer> flattenBFS(Node head) {
        List<Integer> result = new ArrayList<>();
        if (head == null) {
            return result;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            result.add(curr.val);

            // “next” goes to the node on the same level
            if (curr.next != null) {
                queue.offer(curr.next);
            }
            // “child” goes down one level
            if (curr.child != null) {
                queue.offer(curr.child);
            }
        }

        return result;
    }

    // Just a quick demo of how you might use it.
    public static void main(String[] args) {
        // Build an example multi-level structure manually:
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n9 = new Node(9);
        Node n10 = new Node(10);
        Node n8 = new Node(8);

        // Link “same level” pointers
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n10;  // e.g. suppose 10 is also on that top level

        // Link “child” pointers
        n2.child = n5;
        n5.next = n9;
        n4.child = n8;
        
        // Flatten via BFS
        List<Integer> bfsResult = flattenBFS(n1);
        System.out.println(bfsResult);
        
        // If you want to print with Java 8 streams:
        bfsResult.stream().forEach(x -> System.out.print(x + " "));
    }
}
