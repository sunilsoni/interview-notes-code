package com.interview.notes.code.months.april24.test8;

public class LinkedListUtils {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false; // If the list is empty or has only one node, it can't be circular
        }

        ListNode slow = head; // Tortoise pointer
        ListNode fast = head.next; // Hare pointer

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false; // If fast reaches the end of the list, it's not circular
            }
            slow = slow.next; // Move slow one step
            fast = fast.next.next; // Move fast two steps
        }

        return true; // If slow and fast pointers meet, there is a cycle
    }
}