package com.interview.notes.code.year.y2024.april24.test8;

public class LinkedList {
    Node head;

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);

        list.head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = second; // Create a cycle

        System.out.println("Does the list have a cycle? " + list.hasCycle());
    }

    // Method to detect a cycle in the linked list
    public boolean hasCycle() {
        if (head == null) return false;

        Node slow = head; // Slow pointer moves one step
        Node fast = head; // Fast pointer moves two steps

        while (fast != null && fast.next != null) {
            slow = slow.next;          // move slow by 1
            fast = fast.next.next;     // move fast by 2

            // Check if the pointers meet
            if (slow == fast) {
                return true;
            }
        }
        return false; // fast reached the end of the list, hence no cycle
    }

    // Method to remove the node at the third position
    public void removeThirdNode() {
        // Check if the list has at least three nodes
        if (head == null || head.next == null || head.next.next == null) {
            System.out.println("List does not have enough nodes.");
            return;
        }

        // Node to be removed is head.next.next (third node)
        // We need to change the next pointer of the second node
        Node second = head.next; // The second node
        Node fourth = second.next.next; // The fourth node

        // Bypass the third node
        second.next = fourth;

        System.out.println("Node removed.");
    }
}