package com.interview.notes.code.year.y2025.july.common.test1;

// Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    /**
     * Merge two sorted linked lists and return the head of the merged list.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Dummy node to simplify edge cases
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Traverse both lists and append the smaller node each time
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // One of l1 or l2 may have nodes left; append them
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        // The real head is dummy.next
        return dummy.next;
    }
}