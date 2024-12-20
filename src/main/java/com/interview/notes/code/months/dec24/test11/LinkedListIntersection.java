package com.interview.notes.code.months.dec24.test11;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListIntersection {

    // Main solution method
    public static boolean hasCommonNode(Node list1, Node list2) {
        if (list1 == null || list2 == null) {
            return false;
        }

        // Using Floyd's cycle detection algorithm
        Node slow1 = list1;
        Node fast1 = list1;

        // Find cycle in first list
        while (fast1 != null && fast1.next != null) {
            slow1 = slow1.next;
            fast1 = fast1.next.next;
            if (slow1 == fast1) break;
        }

        // Check second list nodes against first list
        Node current2 = list2;
        while (current2 != null) {
            Node temp = list1;
            // Check each node in first list
            while (temp != null) {
                if (temp == current2) {
                    return true;
                }
                temp = temp.next;
                // Avoid infinite loop if first list has cycle
                if (temp == list1) break;
            }
            current2 = current2.next;
        }

        return false;
    }

    public static void main(String[] args) {
        // Test Case 1: No intersection
        Node list1 = new Node(1);
        list1.next = new Node(2);
        list1.next.next = new Node(3);

        Node list2 = new Node(4);
        list2.next = new Node(5);

        System.out.println("Test 1 (No intersection): " +
                (hasCommonNode(list1, list2) == false ? "PASS" : "FAIL"));

        // Test Case 2: With intersection
        Node common = new Node(6);
        list1.next.next.next = common;
        list2.next.next = common;

        System.out.println("Test 2 (With intersection): " +
                (hasCommonNode(list1, list2) == true ? "PASS" : "FAIL"));

        // Test Case 3: Cyclic list
        Node cyclic = new Node(7);
        cyclic.next = new Node(8);
        cyclic.next.next = cyclic; // Creates cycle

        Node list3 = new Node(9);
        list3.next = cyclic;

        System.out.println("Test 3 (Cyclic list): " +
                (hasCommonNode(cyclic, list3) == true ? "PASS" : "FAIL"));

        // Test Case 4: Null inputs
        System.out.println("Test 4 (Null inputs): " +
                (hasCommonNode(null, null) == false ? "PASS" : "FAIL"));

        // Test Case 5: Large lists
        Node largeList1 = createLargeList(1000);
        Node largeList2 = createLargeList(1000);
        largeList2.next.next = largeList1.next.next; // Create intersection

        System.out.println("Test 5 (Large lists): " +
                (hasCommonNode(largeList1, largeList2) == true ? "PASS" : "FAIL"));
    }

    private static Node createLargeList(int size) {
        Node head = new Node(0);
        Node current = head;
        for (int i = 1; i < size; i++) {
            current.next = new Node(i);
            current = current.next;
        }
        return head;
    }
}
