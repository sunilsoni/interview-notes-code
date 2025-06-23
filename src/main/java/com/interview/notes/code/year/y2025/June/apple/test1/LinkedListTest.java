package com.interview.notes.code.year.y2025.June.apple.test1;

class ListNode {
    ListNode next;
    int data;

    public ListNode(int d) {
        this.data = d;
    }
}

class LinkedListUtils {
    // Helper method to add node at the end
    public static ListNode add(ListNode head, int data) {
        if (head == null) {
            return new ListNode(data);
        }
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new ListNode(data);
        return head;
    }

    // Helper method to print the list
    public static void print(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.data + " → ");
            current = current.next;
        }
        System.out.println();
    }

    // Main recursive reversal method
    public static ListNode recursiveReverse(ListNode head) {
        // Base case 1: Empty list
        if (head == null) {
            return null;
        }

        // Base case 2: Single node
        if (head.next == null) {
            return head;
        }
        
        /* Step 1: Recursively reverse the rest of the list
           This will return the new head of reversed sublist */
        ListNode reversedList = recursiveReverse(head.next);
        
        /* Step 2: After reversal, head.next is the last node of 
           reversed sublist. We make it point back to head */
        head.next.next = head;
        
        /* Step 3: Since head is now the last node, 
           its next should point to null */
        head.next = null;

        // Return the new head of the reversed list
        return reversedList;
    }
}

public class LinkedListTest {
    public static void main(String[] args) {
        // Test Case 1: Normal case
        System.out.println("Test Case 1: Normal list");
        ListNode head1 = null;
        head1 = LinkedListUtils.add(head1, 4);
        head1 = LinkedListUtils.add(head1, 1);
        head1 = LinkedListUtils.add(head1, 8);
        head1 = LinkedListUtils.add(head1, 2);
        System.out.println("Original list:");
        LinkedListUtils.print(head1);
        head1 = LinkedListUtils.recursiveReverse(head1);
        System.out.println("Reversed list:");
        LinkedListUtils.print(head1);

        // Test Case 2: Single node
        System.out.println("\nTest Case 2: Single node");
        ListNode head2 = new ListNode(5);
        System.out.println("Original list:");
        LinkedListUtils.print(head2);
        head2 = LinkedListUtils.recursiveReverse(head2);
        System.out.println("Reversed list:");
        LinkedListUtils.print(head2);

        // Test Case 3: Empty list
        System.out.println("\nTest Case 3: Empty list");
        ListNode head3 = null;
        System.out.println("Original list:");
        LinkedListUtils.print(head3);
        head3 = LinkedListUtils.recursiveReverse(head3);
        System.out.println("Reversed list:");
        LinkedListUtils.print(head3);

        // Test Case 4: Large list
        System.out.println("\nTest Case 4: Large list (100 nodes)");
        ListNode head4 = null;
        for (int i = 0; i < 100; i++) {
            head4 = LinkedListUtils.add(head4, i);
        }
        System.out.println("Original list (first few nodes):");
        LinkedListUtils.print(head4);
        head4 = LinkedListUtils.recursiveReverse(head4);
        System.out.println("Reversed list (first few nodes):");
        LinkedListUtils.print(head4);
    }
}
