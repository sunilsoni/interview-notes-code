package com.interview.notes.code.year.y2025.september.Hackerank.test2;

class Node {
    int data;       // value of node
    Node next;      // pointer to next node

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class ReverseLinkedList {

    // Function to reverse linked list
    public static Node reverse(Node head) {
        Node prev = null;      // previous node
        Node curr = head;      // current node
        Node next = null;      // next node (for backup)

        while (curr != null) {
            next = curr.next;    // save next
            curr.next = prev;    // reverse link
            prev = curr;         // move prev
            curr = next;         // move curr
        }
        return prev; // new head
    }

    // Function to print linked list
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create linked list: 1 → 2 → 3 → 4 → 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.println("Original List:");
        printList(head);

        // Reverse
        head = reverse(head);

        System.out.println("Reversed List:");
        printList(head);
    }
}