package com.interview.notes.code.year.y2024.dec24.test12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinkedListIntersection {

    /**
     * Determines whether two singly linked lists share any common nodes.
     * The lists may or may not contain cycles.
     *
     * @param head1 The head of the first linked list.
     * @param head2 The head of the second linked list.
     * @return True if there is at least one node common to both lists; otherwise, false.
     */
    public static boolean haveCommonNode(ListNode head1, ListNode head2) {
        // Get the tail and meeting point (if any) for both lists
        Result result1 = getTailAndCycleNode(head1);
        Result result2 = getTailAndCycleNode(head2);

        // If one list is cyclic and the other is not, they cannot intersect in acyclic part
        // unless the acyclic list connects into the cycle of the cyclic list
        if (result1.hasCycle != result2.hasCycle) {
            // Check if the acyclic list enters into the cycle of the cyclic list
            ListNode acyclicHead = result1.hasCycle ? head2 : head1;
            ListNode cyclicHead = result1.hasCycle ? head1 : head2;
            ListNode cycleNode = result1.hasCycle ? result1.cycleNode : result2.cycleNode;
            return isNodeInList(acyclicHead, cycleNode);
        }

        // Both lists do not have cycles
        if (!result1.hasCycle && !result2.hasCycle) {
            // If tails are different, they do not intersect
            return result1.tail == result2.tail;
        }

        // Both lists have cycles
        if (result1.hasCycle && result2.hasCycle) {
            // Check if cycles are connected
            ListNode curr = result1.cycleNode;
            do {
                if (curr == result2.cycleNode) {
                    return true;
                }
                curr = curr.next;
            } while (curr != result1.cycleNode);

            return false;
        }

        return false;
    }

    /**
     * Helper method to check if a node is present in a linked list.
     *
     * @param head   The head of the linked list.
     * @param target The target node to find.
     * @return True if the target node is in the list; otherwise, false.
     */
    private static boolean isNodeInList(ListNode head, ListNode target) {
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (current == target) {
                return true;
            }
            if (visited.contains(current)) { // Cycle detected
                break;
            }
            visited.add(current);
            current = current.next;
        }
        return false;
    }

    /**
     * Detects if a linked list has a cycle and returns the tail and cycle node.
     *
     * @param head The head of the linked list.
     * @return A Result object containing information about the list.
     */
    private static Result getTailAndCycleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        // Detect cycle using Floyd's Tortoise and Hare algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        // No cycle detected
        if (!hasCycle) {
            // Find the tail
            ListNode tail = head;
            while (tail != null && tail.next != null) {
                tail = tail.next;
            }
            return new Result(false, tail, null);
        }

        // Cycle detected: find the start of the cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Now slow and fast both point to the start of the cycle
        return new Result(true, null, slow);
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // List of test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Two acyclic lists with no intersection
        ListNode a1 = new ListNode(1);
        a1.next = new ListNode(2);
        a1.next.next = new ListNode(3);

        ListNode b1 = new ListNode(4);
        b1.next = new ListNode(5);
        b1.next.next = new ListNode(6);

        testCases.add(new TestCase("Two acyclic lists with no intersection", a1, b1, false));

        // Test Case 2: Two acyclic lists with intersection
        ListNode a2 = new ListNode(1);
        a2.next = new ListNode(2);
        a2.next.next = new ListNode(3);

        ListNode b2 = new ListNode(4);
        b2.next = a2.next.next; // Intersection at node with value 3

        testCases.add(new TestCase("Two acyclic lists with intersection", a2, b2, true));

        // Test Case 3: One acyclic list and one cyclic list with no intersection
        ListNode a3 = new ListNode(1);
        a3.next = new ListNode(2);
        a3.next.next = new ListNode(3);

        ListNode b3 = new ListNode(4);
        b3.next = new ListNode(5);
        b3.next.next = new ListNode(6);
        b3.next.next.next = b3.next; // Cycle at node with value 5

        testCases.add(new TestCase("One acyclic list and one cyclic list with no intersection", a3, b3, false));

        // Test Case 4: One acyclic list and one cyclic list with intersection
        ListNode a4 = new ListNode(1);
        a4.next = new ListNode(2);
        a4.next.next = new ListNode(3);
        a4.next.next.next = new ListNode(4);
        a4.next.next.next.next = new ListNode(5);

        ListNode b4 = new ListNode(6);
        b4.next = a4.next.next.next; // b4.next points to node with value 4
        a4.next.next.next.next.next = a4.next.next; // Cycle starting at node with value 3

        testCases.add(new TestCase("One acyclic list and one cyclic list with intersection", a4, b4, true));

        // Test Case 5: Two cyclic lists with the same cycle
        ListNode a5 = new ListNode(1);
        a5.next = new ListNode(2);
        a5.next.next = new ListNode(3);
        a5.next.next.next = a5.next; // Cycle starting at node with value 2

        ListNode b5 = new ListNode(4);
        b5.next = a5.next; // b5.next points to node with value 2

        testCases.add(new TestCase("Two cyclic lists with the same cycle", a5, b5, true));

        // Test Case 6: Two cyclic lists with different cycles
        ListNode a6 = new ListNode(1);
        a6.next = new ListNode(2);
        a6.next.next = new ListNode(3);
        a6.next.next.next = a6.next; // Cycle starting at node with value 2

        ListNode b6 = new ListNode(4);
        b6.next = new ListNode(5);
        b6.next.next = new ListNode(6);
        b6.next.next.next = b6.next; // Cycle starting at node with value 5

        testCases.add(new TestCase("Two cyclic lists with different cycles", a6, b6, false));

        // Test Case 7: Large lists with intersection
        ListNode a7 = new ListNode(0);
        ListNode current = a7;
        for (int i = 1; i <= 100000; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }
        ListNode b7 = new ListNode(-1);
        b7.next = current; // Intersection at last node

        testCases.add(new TestCase("Large lists with intersection", a7, b7, true));

        // Run test cases
        int testNumber = 1;
        for (TestCase testCase : testCases) {
            boolean result = haveCommonNode(testCase.head1, testCase.head2);
            if (result == testCase.expected) {
                System.out.println("Test Case " + testNumber + ": PASS - " + testCase.description);
            } else {
                System.out.println("Test Case " + testNumber + ": FAIL - " + testCase.description);
                System.out.println("Expected: " + testCase.expected + ", Got: " + result);
            }
            testNumber++;
        }
    }

    /**
     * Class representing a node in a singly linked list.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /**
     * Helper class to store the result of cycle detection.
     */
    static class Result {
        boolean hasCycle;
        ListNode tail;
        ListNode cycleNode;

        Result(boolean hasCycle, ListNode tail, ListNode cycleNode) {
            this.hasCycle = hasCycle;
            this.tail = tail;
            this.cycleNode = cycleNode;
        }
    }

    /**
     * Helper class to represent a test case.
     */
    static class TestCase {
        String description;
        ListNode head1;
        ListNode head2;
        boolean expected;

        TestCase(String description, ListNode head1, ListNode head2, boolean expected) {
            this.description = description;
            this.head1 = head1;
            this.head2 = head2;
            this.expected = expected;
        }
    }
}