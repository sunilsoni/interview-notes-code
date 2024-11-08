package com.interview.notes.code.months.nov24.amazon.test6;

public class LinkedListDeepCopy {
    // Method to create deep copy of the linked list
    public static Node copyRandomList(Node head) {
        if (head == null) return null;

        // First pass: Create copy nodes and insert them after original nodes
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Second pass: Set random pointers for copied nodes
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Third pass: Separate original and copied lists
        Node dummy = new Node(0);
        Node copyTail = dummy;
        curr = head;

        while (curr != null) {
            Node next = curr.next.next;
            Node copy = curr.next;
            copyTail.next = copy;
            copyTail = copy;
            curr.next = next;
            curr = next;
        }

        return dummy.next;
    }

    // Helper method to create test linked list
    private static Node createTestList(int[] values, int[][] randomConnections) {
        if (values == null || values.length == 0) return null;

        Node[] nodes = new Node[values.length];

        // Create nodes
        for (int i = 0; i < values.length; i++) {
            nodes[i] = new Node(values[i]);
        }

        // Connect next pointers
        for (int i = 0; i < values.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        // Set random pointers
        for (int[] connection : randomConnections) {
            nodes[connection[0]].random = nodes[connection[1]];
        }

        return nodes[0];
    }

    // Helper method to verify if two lists are identical
    private static boolean verifyLists(Node original, Node copy) {
        if (original == copy) return false; // Ensuring deep copy

        Node curr1 = original;
        Node curr2 = copy;

        while (curr1 != null && curr2 != null) {
            if (curr1.val != curr2.val) return false;

            // Verify random pointers maintain same relationships
            if ((curr1.random == null && curr2.random != null) ||
                    (curr1.random != null && curr2.random == null)) {
                return false;
            }

            if (curr1.random != null) {
                Node origRandom = curr1.random;
                Node copyRandom = curr2.random;
                if (origRandom.val != copyRandom.val) return false;
            }

            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return curr1 == null && curr2 == null;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic test
        int[] values1 = {1, 2, 3};
        int[][] random1 = {{0, 2}, {1, 0}, {2, 1}};
        Node test1 = createTestList(values1, random1);
        Node copy1 = copyRandomList(test1);
        System.out.println("Test Case 1: " + (verifyLists(test1, copy1) ? "PASS" : "FAIL"));

        // Test Case 2: Single node
        int[] values2 = {1};
        int[][] random2 = {{0, 0}};
        Node test2 = createTestList(values2, random2);
        Node copy2 = copyRandomList(test2);
        System.out.println("Test Case 2: " + (verifyLists(test2, copy2) ? "PASS" : "FAIL"));

        // Test Case 3: Large list test (1000 nodes)
        int[] values3 = new int[1000];
        int[][] random3 = new int[1000][2];
        for (int i = 0; i < 1000; i++) {
            values3[i] = i;
            random3[i] = new int[]{i, (i + 1) % 1000};
        }
        Node test3 = createTestList(values3, random3);
        Node copy3 = copyRandomList(test3);
        System.out.println("Test Case 3 (Large List): " + (verifyLists(test3, copy3) ? "PASS" : "FAIL"));

        // Test Case 4: Null input
        Node test4 = null;
        Node copy4 = copyRandomList(test4);
        System.out.println("Test Case 4 (Null Input): " + (copy4 == null ? "PASS" : "FAIL"));
    }

    // Node class representing each element in the linked list
    static class Node {
        int val;
        Node next;
        Node random;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}