package com.interview.notes.code.year.y2025.april.common.test1;

class Node {
    char data;
    Node next;

    Node(char data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedListSolution {
    // Removes lowercase letters and breaks the cycle
    public static Node cleanupList(Node head) {
        if (head == null) return null;

        // Step 1: Remove lowercase letters
        Node current = head;
        Node prev = null;

        while (current != null) {
            // Skip lowercase nodes
            if (Character.isLowerCase(current.data)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                current = current.next;
            } else {
                prev = current;
                current = current.next;
            }
        }

        // Step 2: Detect and remove cycle using Floyd's algorithm
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                // Cycle detected, break it
                slow = head;
                while (slow.next != fast.next) {
                    slow = slow.next;
                    fast = fast.next;
                }
                fast.next = null;
                break;
            }
        }

        return head;
    }

    // Helper method to print the list
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case with cycle
        Node test1 = new Node('A');
        test1.next = new Node('B');
        test1.next.next = new Node('c');
        test1.next.next.next = new Node('D');
        test1.next.next.next.next = new Node('b');
        test1.next.next.next.next.next = new Node('F');
        test1.next.next.next.next.next.next = test1.next.next; // Creating cycle at 'c'

        System.out.println("Test Case 1:");
        Node result = cleanupList(test1);
        System.out.print("Result: ");
        printList(result);
        // Expected: A -> B -> D -> F

        // Test Case 2: No cycle
        Node test2 = new Node('A');
        test2.next = new Node('b');
        test2.next.next = new Node('C');

        System.out.println("\nTest Case 2:");
        result = cleanupList(test2);
        System.out.print("Result: ");
        printList(result);
        // Expected: A -> C

        // Test Case 3: All lowercase
        Node test3 = new Node('a');
        test3.next = new Node('b');
        test3.next.next = new Node('c');

        System.out.println("\nTest Case 3:");
        result = cleanupList(test3);
        System.out.print("Result: ");
        printList(result);
        // Expected: (empty list)
    }
}
