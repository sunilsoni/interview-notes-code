package com.interview.notes.code.year.y2026.jan.microsoft.test7;

import java.util.stream.IntStream;

public class OrderedList {
    Node head; // The start of the list

    public static void main(String[] args) {
        // Test 1: Standard duplicates
        test("Standard", new int[]{1, 1, 2, 3, 3}, "1,2,3,");

        // Test 2: All same values
        test("All Same", new int[]{5, 5, 5, 5}, "5,");

        // Test 3: Large Data (100,000 items)
        var list = new OrderedList(); // Create list
        IntStream.range(0, 100000).forEach(i -> {
            list.add(1);
            list.add(2);
        }); // Add 200k nodes
        list.removeDuplicates(); // Should result in just "1, 2"
        System.out.println("Large Data Test: " + (list.toStringList().equals("1,2,") ? "PASS" : "FAIL"));
    }

    // Simple test runner to check expected vs actual results
    static void test(String name, int[] input, String expected) {
        var list = new OrderedList(); // Initialize
        for (int i : input) list.add(i); // Fill list
        list.removeDuplicates(); // Process
        var result = list.toStringList(); // Get result
        System.out.println(name + " -> " + (result.equals(expected) ? "PASS" : "FAIL")); // Print status
    }

    // Adds value in sorted order
    void add(int val) {
        if (head == null || head.val >= val) {
            head = new Node(val, head);
            return;
        } // Insert at start
        var curr = head; // Use 'var' (Java 21) to save code space
        while (curr.next != null && curr.next.val < val) curr = curr.next; // Find the right spot
        curr.next = new Node(val, curr.next); // Link the new node
    }

    // The core logic: removes duplicates from the sorted list
    void removeDuplicates() {
        var curr = head; // Start at the beginning
        while (curr != null && curr.next != null) { // Loop until the end
            if (curr.val == curr.next.val) curr.next = curr.next.next; // Skip the duplicate node
            else curr = curr.next; // Only move forward if no duplicate was found
        }
    }

    // Helper to turn list into a String for easy comparison
    String toStringList() {
        var sb = new StringBuilder(); // Efficient string building
        for (var n = head; n != null; n = n.next) sb.append(n.val).append(","); // Append each val
        return sb.toString(); // Return final string
    }

    class Node {
        int val;
        Node next;

        Node(int v, Node n) {
            val = v;
            next = n;
        } // Constructor to set values
    }
}