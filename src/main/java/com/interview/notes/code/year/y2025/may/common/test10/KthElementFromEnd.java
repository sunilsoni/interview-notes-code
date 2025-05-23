package com.interview.notes.code.year.y2025.may.common.test10;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class KthElementFromEnd {
    public static ListNode findKthFromEnd(ListNode head, int k) {
        ListNode first = head, second = head;

        // Move first K steps ahead
        for (int i = 0; i < k; i++) {
            if (first == null) return null; // K is larger than list size
            first = first.next;
        }

        // Move both pointers until first reaches the end
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        return second; // This is the Kth node from the end
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode result = findKthFromEnd(head, 2);
        System.out.println("Kth element from end: " + (result != null ? result.val : "Not found"));
    }
}
