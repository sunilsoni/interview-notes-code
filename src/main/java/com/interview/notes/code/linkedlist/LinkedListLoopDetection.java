package com.interview.notes.code.linkedlist;

public class LinkedListLoopDetection {

    public static boolean hasLoop(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true; // loop found
            }
        }

        return false; // no loop found
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;

        // No loop
        System.out.println(hasLoop(head)); // Output: false

        node4.next = node2; // creating a loop

        // Loop exists
        System.out.println(hasLoop(head)); // Output: true
    }

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
