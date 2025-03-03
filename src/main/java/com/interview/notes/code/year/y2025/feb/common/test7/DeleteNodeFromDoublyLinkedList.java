package com.interview.notes.code.year.y2025.feb.common.test7;

public class DeleteNodeFromDoublyLinkedList {

    /**
     * Deletes the first node with the target value from the doubly linked list.
     *
     * @param head   The head of the linked list.
     * @param target The value to be deleted.
     * @return The head of the modified linked list.
     */
    public static Node deleteNode(Node head, int target) {
        if (head == null) return null;

        Node current = head;
        while (current != null) {
            if (current.val == target) {
                // Deleting the head node.
                if (current.prev == null) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    }
                } else {
                    // Update previous node's next pointer.
                    current.prev.next = current.next;
                    if (current.next != null) {
                        // Update next node's previous pointer.
                        current.next.prev = current.prev;
                    }
                }
                break; // Only delete the first occurrence.
            }
            current = current.next;
        }
        return head;
    }

    /**
     * Creates a doubly linked list from an array of integers.
     *
     * @param values Array of integers.
     * @return The head of the doubly linked list.
     */
    public static Node createList(int[] values) {
        if (values == null || values.length == 0) return null;

        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            Node newNode = new Node(values[i]);
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        return head;
    }

    /**
     * Converts the doubly linked list into a string using Java 8 Streams.
     * Each node's value is separated by " <-> ".
     *
     * @param head The head of the linked list.
     * @return A string representation of the list.
     */
    public static String listToString(Node head) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.val);
            current = current.next;
        }
        // Use Java 8 streams to join list values.
        return list.stream()
                .map(String::valueOf)
                .reduce((a, b) -> a + " <-> " + b)
                .orElse("");
    }

    /**
     * Runs several test cases to validate the deletion logic.
     */
    public static void runTests() {
        int testCount = 0;
        int passedCount = 0;

        // Test 1: Provided example.
        {
            testCount++;
            int[] values = {5, 6, 8, 0, 9, 11};
            Node head = createList(values);
            head = deleteNode(head, 8);
            String result = listToString(head);
            String expected = "5 <-> 6 <-> 0 <-> 9 <-> 11";
            if (result.equals(expected)) {
                System.out.println("Test 1 Passed");
                passedCount++;
            } else {
                System.out.println("Test 1 Failed: Expected " + expected + ", but got " + result);
            }
        }

        // Test 2: Delete head node.
        {
            testCount++;
            int[] values = {1, 2, 3};
            Node head = createList(values);
            head = deleteNode(head, 1);
            String result = listToString(head);
            String expected = "2 <-> 3";
            if (result.equals(expected)) {
                System.out.println("Test 2 Passed");
                passedCount++;
            } else {
                System.out.println("Test 2 Failed: Expected " + expected + ", but got " + result);
            }
        }

        // Test 3: Delete tail node.
        {
            testCount++;
            int[] values = {1, 2, 3};
            Node head = createList(values);
            head = deleteNode(head, 3);
            String result = listToString(head);
            String expected = "1 <-> 2";
            if (result.equals(expected)) {
                System.out.println("Test 3 Passed");
                passedCount++;
            } else {
                System.out.println("Test 3 Failed: Expected " + expected + ", but got " + result);
            }
        }

        // Test 4: Target node not found.
        {
            testCount++;
            int[] values = {1, 2, 3};
            Node head = createList(values);
            head = deleteNode(head, 4);
            String result = listToString(head);
            String expected = "1 <-> 2 <-> 3";
            if (result.equals(expected)) {
                System.out.println("Test 4 Passed");
                passedCount++;
            } else {
                System.out.println("Test 4 Failed: Expected " + expected + ", but got " + result);
            }
        }

        // Test 5: Large data input - Delete a node from a list of 10,000 nodes.
        {
            testCount++;
            int n = 10000;
            int[] values = new int[n];
            for (int i = 0; i < n; i++) {
                values[i] = i;
            }
            Node head = createList(values);
            int deleteVal = n / 2; // delete the middle node
            head = deleteNode(head, deleteVal);
            // Convert list to an ArrayList to verify if the target is removed.
            java.util.List<Integer> list = new java.util.ArrayList<>();
            Node current = head;
            while (current != null) {
                list.add(current.val);
                current = current.next;
            }
            boolean contains = list.contains(deleteVal);
            if (!contains && list.size() == n - 1) {
                System.out.println("Test 5 Passed");
                passedCount++;
            } else {
                System.out.println("Test 5 Failed: Deletion in large data did not work as expected");
            }
        }

        System.out.println("Passed " + passedCount + " out of " + testCount + " tests.");
    }

    /**
     * Main method to run the test cases.
     */
    public static void main(String[] args) {
        runTests();
    }

    // Doubly linked list node definition.
    static class Node {
        int val;
        Node prev, next;

        Node(int val) {
            this.val = val;
        }
    }
}