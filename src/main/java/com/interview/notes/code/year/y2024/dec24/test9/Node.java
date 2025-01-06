package com.interview.notes.code.year.y2024.dec24.test9;

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
        System.out.println("\nTest Case 1: Linear lists with no overlap");
        Node list1 = new Node(1, new Node(2, new Node(3, null)));
        Node list2 = new Node(4, new Node(5, null));
        System.out.println("Result: " + (overlap(list1, list2) == false ? "PASS" : "FAIL"));

        // Test Case 2: Lists with overlap
        System.out.println("\nTest Case 2: Lists with overlap");
        Node commonNode = new Node(6, null);
        Node head1 = new Node(1, new Node(2, new Node(3, commonNode)));
        Node head2 = new Node(4, new Node(5, commonNode));
        System.out.println("Result: " + (overlap(head1, head2) == true ? "PASS" : "FAIL"));

        // Test Case 3: Cyclic list
        System.out.println("\nTest Case 3: Cyclic list");
        Node cycleNode = new Node(7, null);
        cycleNode.setNext(new Node(8, new Node(9, cycleNode)));
        Node head3 = new Node(10, cycleNode);
        System.out.println("Result: " + (overlap(cycleNode, head3) == true ? "PASS" : "FAIL"));

        // Test Case 4: Null inputs
        System.out.println("\nTest Case 4: Null inputs");
        System.out.println("Result: " + (overlap(null, null) == false ? "PASS" : "FAIL"));

        // Test Case 5: Large lists with overlap
        System.out.println("\nTest Case 5: Large lists with overlap");
        Node largeList1 = createLargeList(1000);
        Node largeList2 = createLargeList(1000);
        Node commonLargeNode = largeList1.getNext().getNext();
        attachToEnd(largeList2, commonLargeNode);
        System.out.println("Result: " + (overlap(largeList1, largeList2) == true ? "PASS" : "FAIL"));

        // Test Case 6: List with cycle but no shared nodes
        System.out.println("\nTest Case 6: List with cycle but no shared nodes");
        // Create first list with cycle: 1 -> 2 -> 3 -> 4 -> 2 (cycles back)
        Node node2 = new Node(2, null);
        Node node3 = new Node(3, null);
        Node node4 = new Node(4, null);
        Node cycleList = new Node(1, node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node2);  // Creates cycle back to node2

        // Create second list: 5 -> 6 -> 7 -> 8
        Node separateList = new Node(5,
                new Node(6,
                        new Node(7,
                                new Node(8, null))));

        System.out.println("List 1: 1 -> 2 -> 3 -> 4 -┐");
        System.out.println("         ^-----------------|");
        System.out.println("List 2: 5 -> 6 -> 7 -> 8");
        System.out.println("Result: " + (overlap(cycleList, separateList) == false ? "PASS" : "FAIL"));

        // Verify list structures
        System.out.println("\nVerifying list structures:");
        printCyclicList(cycleList, 6);
        printLinearList(separateList);
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

    private static void printCyclicList(Node head, int limit) {
        System.out.print("Cyclic List (first " + limit + " elements): ");
        Node temp = head;
        int count = 0;
        while (temp != null && count < limit) {
            System.out.print(temp.getValue() + " -> ");
            temp = temp.getNext();
            count++;
        }
        System.out.println("...");
    }

    private static void printLinearList(Node head) {
        System.out.print("Linear List: ");
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.getValue() +
                    (temp.getNext() != null ? " -> " : ""));
            temp = temp.getNext();
        }
        System.out.println();
    }
}
