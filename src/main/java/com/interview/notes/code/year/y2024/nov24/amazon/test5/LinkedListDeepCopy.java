package com.interview.notes.code.year.y2024.nov24.amazon.test5;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkedListDeepCopy {

    /**
     * Method to create a deep copy of a linked list with random pointers.
     *
     * @param head The head of the original linked list.
     * @return The head of the deep-copied linked list.
     */
    public static Node copyRandomList(Node head) {
        if (head == null) return null;

        // HashMap to keep track of original nodes and their clones
        Map<Node, Node> map = new HashMap<>();

        // First pass: Clone all the nodes and store in the map
        Node current = head;
        while (current != null) {
            map.put(current, new Node(current.value));
            current = current.next;
        }

        // Second pass: Assign next and random pointers
        current = head;
        while (current != null) {
            Node clonedNode = map.get(current);
            clonedNode.next = map.get(current.next); // Assign next
            clonedNode.random = map.get(current.random); // Assign random
            current = current.next;
        }

        return map.get(head);
    }

    /**
     * Helper method to compare two linked lists for deep equality.
     *
     * @param head1 The head of the first linked list.
     * @param head2 The head of the second linked list.
     * @return True if both lists are deeply equal, False otherwise.
     */
    public static boolean areListsEqual(Node head1, Node head2) {
        Map<Node, Node> map1 = new HashMap<>();
        Map<Node, Node> map2 = new HashMap<>();

        Node current1 = head1;
        Node current2 = head2;

        // Traverse both lists to map nodes to their positions
        int index = 0;
        while (current1 != null && current2 != null) {
            if (current1.value != current2.value) return false;
            map1.put(current1, current2);
            map2.put(current2, current1);
            current1 = current1.next;
            current2 = current2.next;
            index++;
        }

        // If one list has more nodes than the other
        if (current1 != null || current2 != null) return false;

        // Reset pointers
        current1 = head1;
        current2 = head2;

        // Verify random pointers
        while (current1 != null && current2 != null) {
            if (current1.random == null) {
                if (current2.random != null) return false;
            } else {
                if (map1.get(current1.random) != current2.random) return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }

        return true;
    }

    /**
     * Method to print the linked list for debugging purposes.
     *
     * @param head The head of the linked list.
     */
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            int randomVal = (current.random != null) ? current.random.value : -1;
            System.out.print("[" + current.value + ", Random: " + randomVal + "] -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    /**
     * Main method to execute test cases.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        int passCount = 0;
        int failCount = 0;

        // Test Case 1: Example Provided
        {
            // Creating original list A -> B -> C
            Node A = new Node(1); // A
            Node B = new Node(2); // B
            Node C = new Node(3); // C
            A.next = B;
            B.next = C;

            // Setting random pointers
            A.random = C; // A -> C
            B.random = A; // B -> A
            C.random = B; // C -> B

            // Cloning the list
            Node clonedHead = copyRandomList(A);

            // Verifying the cloned list
            boolean isEqual = areListsEqual(A, clonedHead);
            if (isEqual) {
                System.out.println("Test Case 1: PASS");
                passCount++;
            } else {
                System.out.println("Test Case 1: FAIL");
                failCount++;
            }
        }

        // Test Case 2: Empty List
        {
            Node head = null;
            Node clonedHead = copyRandomList(head);
            if (clonedHead == null) {
                System.out.println("Test Case 2: PASS");
                passCount++;
            } else {
                System.out.println("Test Case 2: FAIL");
                failCount++;
            }
        }

        // Test Case 3: Single Node with random pointer to itself
        {
            Node A = new Node(10);
            A.random = A;

            Node clonedHead = copyRandomList(A);

            boolean isEqual = areListsEqual(A, clonedHead);
            if (isEqual && clonedHead != A && clonedHead.random == clonedHead) {
                System.out.println("Test Case 3: PASS");
                passCount++;
            } else {
                System.out.println("Test Case 3: FAIL");
                failCount++;
            }
        }

        // Test Case 4: Multiple Nodes with random pointers as null
        {
            Node A = new Node(5);
            Node B = new Node(10);
            Node C = new Node(15);
            A.next = B;
            B.next = C;

            // All random pointers are null
            A.random = null;
            B.random = null;
            C.random = null;

            Node clonedHead = copyRandomList(A);

            boolean isEqual = areListsEqual(A, clonedHead);
            if (isEqual) {
                System.out.println("Test Case 4: PASS");
                passCount++;
            } else {
                System.out.println("Test Case 4: FAIL");
                failCount++;
            }
        }

        // Test Case 5: Large List
        {
            int size = 10000;
            Node head = new Node(0);
            Node current = head;
            for (int i = 1; i < size; i++) {
                current.next = new Node(i);
                current = current.next;
            }

            // Assign random pointers
            Random rand = new Random(42); // Seed for reproducibility
            current = head;
            Node[] nodes = new Node[size];
            current = head;
            int index = 0;
            while (current != null) {
                nodes[index++] = current;
                current = current.next;
            }
            current = head;
            index = 0;
            while (current != null) {
                int randIndex = rand.nextInt(size);
                current.random = nodes[randIndex];
                current = current.next;
                index++;
            }

            // Clone the list
            long startTime = System.currentTimeMillis();
            Node clonedHead = copyRandomList(head);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            // Verify the cloned list
            boolean isEqual = areListsEqual(head, clonedHead);
            if (isEqual) {
                System.out.println("Test Case 5: PASS (Time Taken: " + duration + " ms)");
                passCount++;
            } else {
                System.out.println("Test Case 5: FAIL");
                failCount++;
            }
        }

        // Test Case 6: Random Pointers Forming a Cycle
        {
            Node A = new Node(1);
            Node B = new Node(2);
            Node C = new Node(3);
            Node D = new Node(4);
            A.next = B;
            B.next = C;
            C.next = D;
            D.next = null;

            // Setting random pointers to form a cycle: A->C, B->D, C->A, D->B
            A.random = C;
            B.random = D;
            C.random = A;
            D.random = B;

            Node clonedHead = copyRandomList(A);

            boolean isEqual = areListsEqual(A, clonedHead);
            if (isEqual) {
                System.out.println("Test Case 6: PASS");
                passCount++;
            } else {
                System.out.println("Test Case 6: FAIL");
                failCount++;
            }
        }

        // Summary of Test Results
        System.out.println("\nTest Results:");
        System.out.println("Passed: " + passCount);
        System.out.println("Failed: " + failCount);
    }

    // Definition for a Node.
    static class Node {
        int value;
        Node next;
        Node random;

        Node(int val) {
            this.value = val;
            this.next = null;
            this.random = null;
        }
    }
}
