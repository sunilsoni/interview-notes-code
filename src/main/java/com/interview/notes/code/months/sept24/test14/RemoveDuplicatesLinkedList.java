package com.interview.notes.code.months.sept24.test14;

import java.util.HashSet;

public class RemoveDuplicatesLinkedList {

    // Method to remove duplicates from the linked list
    public static ListNode removeDuplicates(ListNode head) {
        if (head == null) return null;

        HashSet<Integer> seen = new HashSet<>();
        ListNode current = head;
        ListNode prev = null;

        while (current != null) {
            if (seen.contains(current.val)) {
                // Duplicate found, remove the node
                prev.next = current.next;
            } else {
                // Add to seen set
                seen.add(current.val);
                prev = current;
            }
            current = current.next;
        }

        return head;
    }

    // Method to print the linked list
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Main method to process test cases
    public static void main(String[] args) {
        // Test case 1: List with duplicates
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(3);
        head1.next.next.next.next = new ListNode(3);

        System.out.println("Original List 1:");
        printList(head1);
        head1 = removeDuplicates(head1);
        System.out.println("After removing duplicates:");
        printList(head1);

        // Test case 2: List with no duplicates
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);

        System.out.println("Original List 2:");
        printList(head2);
        head2 = removeDuplicates(head2);
        System.out.println("After removing duplicates:");
        printList(head2);

        // Test case 3: List with all elements the same
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(1);
        head3.next.next = new ListNode(1);

        System.out.println("Original List 3:");
        printList(head3);
        head3 = removeDuplicates(head3);
        System.out.println("After removing duplicates:");
        printList(head3);

        // Test case 4: Empty list
        ListNode head4 = null;
        System.out.println("Original List 4 (empty):");
        printList(head4);
        head4 = removeDuplicates(head4);
        System.out.println("After removing duplicates:");
        printList(head4);

        // Test case 5: Single element list
        ListNode head5 = new ListNode(5);
        System.out.println("Original List 5:");
        printList(head5);
        head5 = removeDuplicates(head5);
        System.out.println("After removing duplicates:");
        printList(head5);
    }

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}
