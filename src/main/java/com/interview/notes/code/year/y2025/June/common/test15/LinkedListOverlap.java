package com.interview.notes.code.year.y2025.June.common.test15;

public class LinkedListOverlap {
    public static int findOverlappingNodeValue(Node list1, Node list2) {
        if (list1 == null || list2 == null) {
            return -1;
        }

        // Calculate lengths
        int length1 = getLength(list1);
        int length2 = getLength(list2);

        // Adjust starting points
        int diff = Math.abs(length1 - length2);
        if (length1 > length2) {
            while (diff-- > 0) list1 = list1.next;
        } else {
            while (diff-- > 0) list2 = list2.next;
        }

        // Find overlap
        while (list1 != null && list2 != null) {
            if (list1.value == list2.value) {
                return list1.value;
            }
            list1 = list1.next;
            list2 = list2.next;
        }

        return -1;
    }

    private static int getLength(Node head) {
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    public static void main(String[] args) {
        // Create first list: 1->2->3->4->5->9->10->11->12
        Node list1 = new Node(1);
        list1.next = new Node(2);
        list1.next.next = new Node(3);
        list1.next.next.next = new Node(4);
        list1.next.next.next.next = new Node(5);
        list1.next.next.next.next.next = new Node(9);
        list1.next.next.next.next.next.next = new Node(10);
        list1.next.next.next.next.next.next.next = new Node(11);
        list1.next.next.next.next.next.next.next.next = new Node(12);

        // Create second list: 6->7->8->9->10->11->12
        Node list2 = new Node(6);
        list2.next = new Node(7);
        list2.next.next = new Node(8);
        list2.next.next.next = new Node(9);
        list2.next.next.next.next = new Node(10);
        list2.next.next.next.next.next = new Node(11);
        list2.next.next.next.next.next.next = new Node(12);

        // Find overlapping value
        int result = findOverlappingNodeValue(list1, list2);

        // Print result
        System.out.println("List 1: 1->2->3->4->5->9->10->11->12");
        System.out.println("List 2: 6->7->8->9->10->11->12");
        System.out.println("Overlapping: " + (result != -1));
        System.out.println("Overlapping node value: " + result);
    }

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }
}
