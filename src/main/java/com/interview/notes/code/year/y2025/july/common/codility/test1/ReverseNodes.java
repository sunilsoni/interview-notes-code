// File: src/main/java/com/interview/notes/code/year/y2025/july/common/codility/test1/ReverseNodes.java
package com.interview.notes.code.year.y2025.july.common.codility.test1;


public class ReverseNodes {

    /**
     * Build a linked list from an int[]
     */
    public static ListNode createLinkedList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : arr) {
            cur.next = new ListNode(v);
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * Serialize to “1->2->3” for easy comparison
     */
    public static String linkedListToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode cur = head;
        while (cur != null) {
            sb.append(cur.val);
            if (cur.next != null) sb.append("->");
            cur = cur.next;
        }
        return sb.toString();
    }

    /**
     * Task 3: Reverse nodes in k-group; leftover <k stay in place.
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            // 1) find k-th node
            ListNode kth = groupPrev;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) break;

            // 2) reverse [groupPrev.next ... kth]
            ListNode groupStart = groupPrev.next;
            ListNode nextGroup = kth.next;
            ListNode prev = nextGroup;
            ListNode curr = groupStart;
            while (curr != nextGroup) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // 3) re-attach
            groupPrev.next = kth;
            groupPrev = groupStart;
        }

        return dummy.next;
    }
}