package com.interview.notes.code.year.y2025.June.apple.test2;

class ListNode {
    ListNode next;
    int data;

    public ListNode(int d) {
        this.data = d;
    }
}

class LinkedListUtils {
    /**
     * Iterative approach to reverse a linked list
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static ListNode reverseList(ListNode head) {
        // Handle empty list and single node
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;    // Previous node pointer
        ListNode curr = head;    // Current node pointer
        ListNode next = null;    // Next node pointer

        while (curr != null) {
            // Store next node
            next = curr.next;

            // Reverse current node's pointer
            curr.next = prev;

            // Move pointers one position ahead
            prev = curr;
            curr = next;
        }

        // prev is the new head
        return prev;
    }

    /**
     * Recursive approach to reverse a linked list
     * Time Complexity: O(n)
     * Space Complexity: O(n) due to recursive stack
     */
    public static ListNode recursiveReverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode reversedList = recursiveReverse(head.next);
        head.next.next = head;
        head.next = null;
        return reversedList;
    }

    // Helper method to add node
    public static ListNode add(ListNode head, int data) {
        if (head == null) {
            return new ListNode(data);
        }
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new ListNode(data);
        return head;
    }

    // Helper method to print list
    public static void print(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.data + " → ");
            current = current.next;
        }
        System.out.println("null");
    }
}

public class LinkedListTest {
    public static void main(String[] args) {
        // Test both methods with various cases
        testCase("Test Case 1: Normal List - Iterative", true);
        testCase("Test Case 1: Normal List - Recursive", false);

        testSingleNode("Test Case 2: Single Node - Iterative", true);
        testSingleNode("Test Case 2: Single Node - Recursive", false);

        testEmptyList("Test Case 3: Empty List - Iterative", true);
        testEmptyList("Test Case 3: Empty List - Recursive", false);

        testLargeList("Test Case 4: Large List - Iterative", true);
        testLargeList("Test Case 4: Large List - Recursive", false);
    }

    private static void testCase(String testName, boolean useIterative) {
        System.out.println("\n" + testName);
        ListNode head = null;
        head = LinkedListUtils.add(head, 1);
        head = LinkedListUtils.add(head, 2);
        head = LinkedListUtils.add(head, 3);
        head = LinkedListUtils.add(head, 4);

        System.out.println("Original list:");
        LinkedListUtils.print(head);

        head = useIterative ?
                LinkedListUtils.reverseList(head) :
                LinkedListUtils.recursiveReverse(head);

        System.out.println("Reversed list:");
        LinkedListUtils.print(head);
    }

    private static void testSingleNode(String testName, boolean useIterative) {
        System.out.println("\n" + testName);
        ListNode head = new ListNode(1);

        System.out.println("Original list:");
        LinkedListUtils.print(head);

        head = useIterative ?
                LinkedListUtils.reverseList(head) :
                LinkedListUtils.recursiveReverse(head);

        System.out.println("Reversed list:");
        LinkedListUtils.print(head);
    }

    private static void testEmptyList(String testName, boolean useIterative) {
        System.out.println("\n" + testName);
        ListNode head = null;

        System.out.println("Original list:");
        LinkedListUtils.print(head);

        head = useIterative ?
                LinkedListUtils.reverseList(head) :
                LinkedListUtils.recursiveReverse(head);

        System.out.println("Reversed list:");
        LinkedListUtils.print(head);
    }

    private static void testLargeList(String testName, boolean useIterative) {
        System.out.println("\n" + testName);
        ListNode head = null;
        for (int i = 0; i < 1000; i++) {
            head = LinkedListUtils.add(head, i);
        }

        System.out.println("Original list (first few nodes):");
        LinkedListUtils.print(head);

        head = useIterative ?
                LinkedListUtils.reverseList(head) :
                LinkedListUtils.recursiveReverse(head);

        System.out.println("Reversed list (first few nodes):");
        LinkedListUtils.print(head);
    }
}
