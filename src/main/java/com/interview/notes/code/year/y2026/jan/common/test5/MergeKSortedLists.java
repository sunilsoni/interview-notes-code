package com.interview.notes.code.year.y2026.jan.common.test5;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {

    static ListNode merge(ListNode[] lists) {
        PriorityQueue<ListNode> pq =
                new PriorityQueue<>(Comparator.comparingInt(n -> n.val));

        for (ListNode n : lists)
            if (n != null) pq.offer(n);

        ListNode dummy = new ListNode(0), cur = dummy;

        while (!pq.isEmpty()) {
            ListNode n = pq.poll();
            cur.next = n;
            cur = n;
            if (n.next != null) pq.offer(n.next);
        }
        return dummy.next;
    }

    static ListNode build(int... a) {
        ListNode d = new ListNode(0), c = d;
        for (int v : a) c = c.next = new ListNode(v);
        return d.next;
    }

    static void print(ListNode n) {
        while (n != null) {
            System.out.print(n.val + " ");
            n = n.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode l1 = build(1, 3, 4);
        ListNode l2 = build(1, 2, 3);
        ListNode l3 = build(2, 6);

        ListNode res = merge(new ListNode[]{l1, l2, l3});
        print(res); // 1 1 2 2 3 3 4 6
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }
    }
}
