package com.interview.notes.code.june23.test5;

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedListReversal {

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode current = head;
        ListNode next = null;
        ListNode prev = null;
        int count = 0;

        // Count the number of nodes in the current group
        while (current != null && count < k) {
            current = current.next;
            count++;
        }

        // If the current group has k nodes, reverse them
        if (count == k) {
            current = head;
            while (count > 0) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
                count--;
            }

            // Recursively reverse the remaining groups
            if (next != null) {
                head.next = reverseKGroup(next, k);
            }

            // 'prev' is the new head of the reversed group
            return prev;
        }

        // If the current group doesn't have k nodes, return the head as it is
        return head;
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
        // Create the linked list: 1->2->3->4->5->6->7->8->NULL
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        int k = 3;

        System.out.println("Original list: ");
        printList(head);

        ListNode reversedHead = reverseKGroup(head, k);

        System.out.println("Reversed list in groups of " + k + ":");
        printList(reversedHead);
    }
}
