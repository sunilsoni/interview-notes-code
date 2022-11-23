package com.interview.notes.code.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class SinglyLinkedListHasCycleHashSet {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> mp = new HashSet<>();
        while (head != null) {
            if (mp.contains(head)) {
                return true;
            }
            mp.add(head);
            head = head.next;
        }
        return false;
    }

    //Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}