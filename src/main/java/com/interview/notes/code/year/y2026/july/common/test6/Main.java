package com.interview.notes.code.year.y2026.july.common.test6;

// Simple Node structure using Java 21 Record for brevity
record Node(int val, Node next) {}

public class Main {
    // Finds middle using two pointers: one moves 1x, one moves 2x speed
    public static Node findMiddle(Node head) {
        var slow = head; // Slow pointer starts at head
        var fast = head; // Fast pointer starts at head
        
        // Loop while fast can jump two steps
        while (fast != null && fast.next() != null) {
            slow = slow.next();       // Move slow 1 step
            fast = fast.next().next();// Move fast 2 steps
        }
        return slow; // When fast reaches end, slow is at middle
    }

    public static void main(String[] args) {
        // Test Case 1: Odd length (1 -> 2 -> 3 -> 4 -> 5)
        var list1 = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, null)))));
        check(list1, 3, "Test Odd Length");

        // Test Case 2: Even length (1 -> 2 -> 3 -> 4 -> 5 -> 6)
        var list2 = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, new Node(6, null))))));
        check(list2, 4, "Test Even Length");

        // Test Case 3: Large Data (100,000 nodes)
        var largeHead = buildLargeList(100000);
        check(largeHead, 50001, "Test Large Data (100k)");
    }

    private static void check(Node head, int expected, String testName) {
        var result = findMiddle(head); // Execute logic
        var passed = result.val() == expected; // Compare result
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL")); // Print status
    }

    private static Node buildLargeList(int n) {
        Node head = null; // Initialize empty
        for (int i = n; i > 0; i--) head = new Node(i, head); // Build backwards
        return head; // Return reference to first node
    }
}