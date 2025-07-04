package com.interview.notes.code.year.y2025.June.common.test17;

public class OverlapLinkedLists {
    // Returns the value of the overlapping node, or -1 if there is none
    public static int findOverlap(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) { // if either list is empty
            return -1;                        // no overlap possible
        }
        int lenA = getLength(headA); // compute length of list A
        int lenB = getLength(headB); // compute length of list B

        ListNode curA = headA;       // pointer for list A
        ListNode curB = headB;       // pointer for list B

        // Advance the longer list so both lists have equal remaining length
        if (lenA > lenB) {                           // if A is longer
            for (int i = 0; i < lenA - lenB; i++) {  // move A forward by the difference
                curA = curA.next;                    // step to next node
            }
        } else if (lenB > lenA) {                    // if B is longer
            for (int i = 0; i < lenB - lenA; i++) {  // move B forward by the difference
                curB = curB.next;                    // step to next node
            }
        }

        // Now traverse both in tandem looking for the same reference
        while (curA != null && curB != null) { // until one list ends
            if (curA == curB) {                // same object reference?
                return curA.val;              // return its value
            }
            curA = curA.next;                  // advance in A
            curB = curB.next;                  // advance in B
        }
        return -1; // reached end without finding overlap
    }

    // Helper to compute the length of a singly linked list
    private static int getLength(ListNode head) {
        int length = 0;           // initialize node counter
        ListNode cur = head;      // start from the head
        while (cur != null) {     // traverse until null
            length++;             // count this node
            cur = cur.next;       // move to next node
        }
        return length;            // return total count
    }

    // Simple main method to run PASS/FAIL tests (no JUnit)
    public static void main(String[] args) {
        // --- Example 1: Overlap at node with value 9 ---
        ListNode common = new ListNode(9);       // create the shared tail
        common.next = new ListNode(10);
        common.next.next = new ListNode(11);
        common.next.next.next = new ListNode(12);

        // Build List1: 1→2→3→4→5→common...
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        l1.next.next.next.next.next = common;    // attach shared sublist

        // Build List2: 6→7→8→common...
        ListNode l2 = new ListNode(6);
        l2.next = new ListNode(7);
        l2.next.next = new ListNode(8);
        l2.next.next.next = common;              // attach same tail

        int res1 = findOverlap(l1, l2);           // should be 9
        System.out.println("Example 1: " + (res1 == 9 ? "PASS" : "FAIL"));

        // --- Example 2: No overlap ---
        // List1: 1→2→3→4
        ListNode m1 = new ListNode(1);
        m1.next = new ListNode(2);
        m1.next.next = new ListNode(3);
        m1.next.next.next = new ListNode(4);

        // List2: 6→7→8→9
        ListNode m2 = new ListNode(6);
        m2.next = new ListNode(7);
        m2.next.next = new ListNode(8);
        m2.next.next.next = new ListNode(9);

        int res2 = findOverlap(m1, m2);           // should be -1
        System.out.println("Example 2: " + (res2 == -1 ? "PASS" : "FAIL"));

        // --- Large test: Two long lists without overlap ---
        int n = 100_000;                          // size parameter
        // Build first list of size n
        ListNode a = new ListNode(0);
        ListNode curr = a;
        for (int i = 1; i < n; i++) {
            curr.next = new ListNode(i);          // append new node
            curr = curr.next;                     // advance pointer
        }
        // Build second list of size n starting at different values
        ListNode b = new ListNode(n);
        curr = b;
        for (int i = n + 1; i < 2 * n; i++) {
            curr.next = new ListNode(i);          // no shared references
            curr = curr.next;                     // advance pointer
        }
        int res3 = findOverlap(a, b);             // should be -1
        System.out.println("Large test: " + (res3 == -1 ? "PASS" : "FAIL"));
    }

    // Node class for singly linked list
    static class ListNode {
        int val;                // the integer value stored in this node
        ListNode next;          // reference to the next node in the list

        ListNode(int v) {       // constructor to initialize node value
            this.val = v;       // set this node's value
            this.next = null;   // by default, next is null
        }
    }
}