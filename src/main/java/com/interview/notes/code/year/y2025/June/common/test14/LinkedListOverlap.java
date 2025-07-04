package com.interview.notes.code.year.y2025.June.common.test14;

/*
**Problem Statement:**

Given two singly linked lists:

1. Determine if there is an overlapping node (i.e., the same node reference appears in both lists).
2. If overlapping exists, return the **value** of the overlapping node.

---

**Examples:**

**Example 1:**

```
List1: 1 → 2 → 3 → 4 → 5 → 9 → 10 → 11 → 12 → null
List2: 6 → 7 → 8 → 9 → 10 → 11 → 12 → null
```

**Overlapping:** `True`
**Overlapping node value:** `9`

---

**Example 2:**

```
List1: 1 → 2 → 3 → 4 → null
List2: 6 → 7 → 8 → 9 → null
```

**Overlapping:** `False`
**Overlapping node value:** `-1`

 */
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

    private static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.value);
            if (current.next != null) {
                System.out.print("->");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test Case 1: Original sorted lists
        Node list1 = new Node(1);
        list1.next = new Node(2);
        list1.next.next = new Node(3);
        list1.next.next.next = new Node(4);
        list1.next.next.next.next = new Node(5);
        list1.next.next.next.next.next = new Node(9);
        list1.next.next.next.next.next.next = new Node(10);
        list1.next.next.next.next.next.next.next = new Node(11);
        list1.next.next.next.next.next.next.next.next = new Node(12);

        Node list2 = new Node(6);
        list2.next = new Node(7);
        list2.next.next = new Node(8);
        list2.next.next.next = new Node(9);
        list2.next.next.next.next = new Node(10);
        list2.next.next.next.next.next = new Node(11);
        list2.next.next.next.next.next.next = new Node(12);

        System.out.println("Test Case 1 (Sorted Lists):");
        System.out.print("List 1: ");
        printList(list1);
        System.out.print("List 2: ");
        printList(list2);
        int result = findOverlappingNodeValue(list1, list2);
        System.out.println("Overlapping: " + (result != -1));
        System.out.println("Overlapping node value: " + result);
        System.out.println();

        // Test Case 2: Unsorted lists
        Node unsortedList1 = new Node(8);
        unsortedList1.next = new Node(3);
        unsortedList1.next.next = new Node(15);
        unsortedList1.next.next.next = new Node(2);
        unsortedList1.next.next.next.next = new Node(7);

        Node unsortedList2 = new Node(9);
        unsortedList2.next = new Node(4);
        unsortedList2.next.next = new Node(2);
        unsortedList2.next.next.next = new Node(7);

        System.out.println("Test Case 2 (Unsorted Lists):");
        System.out.print("List 1: ");
        printList(unsortedList1);
        System.out.print("List 2: ");
        printList(unsortedList2);
        result = findOverlappingNodeValue(unsortedList1, unsortedList2);
        System.out.println("Overlapping: " + (result != -1));
        System.out.println("Overlapping node value: " + result);
        System.out.println();

        // Test Case 3: No overlap
        Node noOverlapList1 = new Node(1);
        noOverlapList1.next = new Node(2);
        noOverlapList1.next.next = new Node(3);

        Node noOverlapList2 = new Node(4);
        noOverlapList2.next = new Node(5);
        noOverlapList2.next.next = new Node(6);

        System.out.println("Test Case 3 (No Overlap):");
        System.out.print("List 1: ");
        printList(noOverlapList1);
        System.out.print("List 2: ");
        printList(noOverlapList2);
        result = findOverlappingNodeValue(noOverlapList1, noOverlapList2);
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
