// File: src/main/java/com/interview/notes/code/linkedlist/ListNode.java
package com.interview.notes.code.year.y2025.july.codility.test1;

/**
 * A simple singly-linked-list node.
 */
public class ListNode {
    public int val;
    public ListNode next;

    /**
     * Make this constructor public so other packages
     * (like your ReverseNodes) can call `new ListNode(...)`.
     */
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    /**
     * Optional convenience constructor. Also must be public.
     */
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}