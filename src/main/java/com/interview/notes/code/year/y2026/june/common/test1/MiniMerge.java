package com.interview.notes.code.year.y2026.june.common.test1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

class ListNode { int val; ListNode next; ListNode(int x) { val = x; } }

public class MiniMerge {
    public static ListNode merge(ListNode[] lists) {
        var pq = new PriorityQueue<ListNode>(Comparator.comparingInt(n -> n.val));
        Arrays.stream(lists).filter(Objects::nonNull).forEach(pq::add);
        
        var dummy = new ListNode(0);
        var tail = dummy;
        
        while (!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;
            if (tail.next != null) pq.add(tail.next);
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        var l1 = new ListNode(1); l1.next = new ListNode(5);
        var l2 = new ListNode(2); l2.next = new ListNode(4);
        
        var res = merge(new ListNode[]{l1, l2});
        
        while (res != null) { 
            System.out.print(res.val + (res.next != null ? "->" : "")); 
            res = res.next; 
        }
    }
}