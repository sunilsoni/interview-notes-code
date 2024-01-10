package com.interview.notes.code.months.year2023.dec23.test3;

public class ReverseLinkedList {

    public static ListNode reverseListIterativeApproach(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next;

        while (current != null) {
            next = current.next; // Store the next node
            current.next = prev; // Reverse the current node's pointer
            prev = current;     // Move prev to the current node
            current = next;     // Move current to the next node
        }

        return prev; // The new head of the reversed list
    }

    public static ListNode reverseListRecursiveApproach(ListNode head) {
        if (head == null || head.next == null) {
            return head; // If the list is empty or has only one node, it's already reversed
        }

        // Reverse the rest of the list recursively
        ListNode reversedList = reverseListRecursiveApproach(head.next);

        // Update the pointers to reverse the current node
        head.next.next = head;
        head.next = null;

        return reversedList; // The new head of the reversed list
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original List:");
        printList(head);

        // Reverse the linked list
        // head = reverseListIterativeApproach(head);
        head = reverseListRecursiveApproach(head);

        System.out.println("Reversed List:");
        printList(head);

    }
}
