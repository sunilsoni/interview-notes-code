package com.interview.notes.code.months.dec24.test10;

public class Node {
    private Integer value;
    private Node nextNode;

    public Node(Integer value, Node nextNode) {
        this.value = value;
        this.nextNode = nextNode;
    }

    public Integer getValue() {
        return value;
    }

    public Node getNext() {
        return nextNode;
    }

    public void setNext(Node nextNode) {
        this.nextNode = nextNode;
    }
}

class Solution {
    public static boolean overlap(Node head1, Node head2) {
        // Input validation
        if (head1 == null || head2 == null) {
            return false;
        }

        // Floyd's cycle detection for first list
        Node slow = head1;
        Node fast = head1;
        boolean hasCycle1 = false;

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                hasCycle1 = true;
                break;
            }
        }

        // Compare nodes
        Node current2 = head2;
        while (current2 != null) {
            Node temp = head1;
            boolean firstIteration = true;

            while (temp != null && (firstIteration || temp != head1)) {
                if (temp == current2) {
                    return true;
                }
                temp = temp.getNext();
                firstIteration = false;

                // Break if we've completed cycle traversal
                if (!firstIteration && hasCycle1 && temp == head1) {
                    break;
                }
            }
            current2 = current2.getNext();
        }

        return false;
    }

    public static void main(String[] args) {
        // Test Case 1: Linear lists with no overlap
        Node list1 = new Node(1, new Node(2, new Node(3, null)));
        Node list2 = new Node(4, new Node(5, null));
        System.out.println("Test 1 (No overlap): " +
                (overlap(list1, list2) == false ? "PASS" : "FAIL"));

        // Test Case 2: Lists with overlap
        Node commonNode = new Node(6, null);
        Node head1 = new Node(1, new Node(2, new Node(3, commonNode)));
        Node head2 = new Node(4, new Node(5, commonNode));
        System.out.println("Test 2 (With overlap): " +
                (overlap(head1, head2) == true ? "PASS" : "FAIL"));

        // Test Case 3: Cyclic list
        Node cycleNode = new Node(7, null);
        cycleNode.setNext(new Node(8, new Node(9, cycleNode)));
        Node head3 = new Node(10, cycleNode);
        System.out.println("Test 3 (Cyclic list): " +
                (overlap(cycleNode, head3) == true ? "PASS" : "FAIL"));

        // Test Case 4: Null inputs
        System.out.println("Test 4 (Null inputs): " +
                (overlap(null, null) == false ? "PASS" : "FAIL"));

        // Test Case 5: Large lists
        Node largeList1 = createLargeList(1000);
        Node largeList2 = createLargeList(1000);
        Node commonLargeNode = largeList1.getNext().getNext();
        attachToEnd(largeList2, commonLargeNode);
        System.out.println("Test 5 (Large lists): " +
                (overlap(largeList1, largeList2) == true ? "PASS" : "FAIL"));
    }

    private static Node createLargeList(int size) {
        Node dummy = new Node(0, null);
        Node current = dummy;
        for (int i = 1; i < size; i++) {
            current.setNext(new Node(i, null));
            current = current.getNext();
        }
        return dummy;
    }

    private static void attachToEnd(Node list, Node target) {
        Node current = list;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(target);
    }
}
