package com.interview.notes.code.year.y2026.jan.microsoft.test7;

import java.util.stream.IntStream;

public class Solution {
    Node head;

    public static void main(String[] args) {
        // Test Cases
        test("Basic Test", new int[]{1, 1, 2, 3, 3}, "1,2,3");
        test("Single Item", new int[]{10}, "10");
        test("All Duplicates", new int[]{7, 7, 7, 7}, "7");

        // Large Data Test
        var bigList = new Solution();
        IntStream.range(0, 50000).forEach(i -> bigList.add(i % 5)); // Add many 0,1,2,3,4
        bigList.removeDuplicates(); // Should reduce to "0,1,2,3,4"
        System.out.println("Large Data -> " + (bigList.toStringList().equals("0,1,2,3,4") ? "PASS" : "FAIL"));
    }

    static void test(String name, int[] input, String expected) {
        var list = new Solution();
        for (int i : input) list.add(i);
        list.removeDuplicates();
        var res = list.toStringList();
        System.out.println(name + " [" + res + "] -> " + (res.equals(expected) ? "PASS" : "FAIL"));
    }

    // Standard ordered add logic
    void add(int val) {
        if (head == null || head.val >= val) {
            head = new Node(val, head);
            return;
        }
        var curr = head;
        while (curr.next != null && curr.next.val < val) curr = curr.next;
        curr.next = new Node(val, curr.next);
    }

    // Logic to remove adjacent duplicates
    void removeDuplicates() {
        var curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) curr.next = curr.next.next;
            else curr = curr.next;
        }
    }

    // Clean string output without trailing comma
    String toStringList() {
        if (head == null) return ""; // Return empty if list is empty
        var sb = new StringBuilder(); // Init builder
        for (var n = head; n != null; n = n.next) { // Loop nodes
            sb.append(n.val); // Add value
            if (n.next != null) sb.append(","); // Add comma ONLY if not last
        }
        return sb.toString(); // Return clean string
    }

    class Node {
        int val;
        Node next;

        Node(int v, Node n) {
            val = v;
            next = n;
        }
    }
}