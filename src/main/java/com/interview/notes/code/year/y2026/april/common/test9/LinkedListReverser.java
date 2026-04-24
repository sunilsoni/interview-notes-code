package com.interview.notes.code.year.y2026.april.common.test9;

import java.util.stream.IntStream; // Importing IntStream to easily generate large test data

public class LinkedListReverser { // Main class containing our logic and tests

    public static Node reverseList(Node head) { // Method to reverse the list, takes the current head
        var prev = (Node) null; // Initialize prev as null, as the new tail's next must point to null
        var curr = head; // Start iterating from the provided head node

        while (curr != null) { // Continue looping as long as we haven't reached the end of the list
            var nextTemp = curr.next; // Temporarily store the next node so we don't lose the chain
            curr.next = prev; // REVERSE: Point the current node backwards to the previous node
            prev = curr; // Move the 'prev' pointer forward to the current node
            curr = nextTemp; // Move the 'curr' pointer forward to the saved next node
        } // End of the while loop

        return prev; // Return prev, which now points to the new head of the reversed list
    } // End of reverseList method

    public static void main(String[] args) { // Simple main method for testing without JUnit
        System.out.println("Running Tests...\n"); // Print starting message

        // Test 1: Standard List (1 -> 2 -> 3)
        var head1 = new Node(1); // Create node 1
        head1.next = new Node(2); // Link node 1 to node 2
        head1.next.next = new Node(3); // Link node 2 to node 3
        var res1 = reverseList(head1); // Reverse the list
        var pass1 = res1.val == 3 && res1.next.val == 2 && res1.next.next.val == 1; // Check if order is 3, 2, 1
        System.out.println("Test 1 (Standard List): " + (pass1 ? "PASS" : "FAIL")); // Print PASS or FAIL

        // Test 2: Empty List (null)
        var res2 = reverseList(null); // Reverse a null list
        var pass2 = res2 == null; // Check if result is safely null
        System.out.println("Test 2 (Empty List): " + (pass2 ? "PASS" : "FAIL")); // Print PASS or FAIL

        // Test 3: Single Node
        var head3 = new Node(99); // Create a list with just one node
        var res3 = reverseList(head3); // Reverse it
        var pass3 = res3 != null && res3.val == 99 && res3.next == null; // Check if it remains unchanged
        System.out.println("Test 3 (Single Node): " + (pass3 ? "PASS" : "FAIL")); // Print PASS or FAIL

        // Test 4: Large Data Input (100,000 nodes using Stream API)
        var dummy = new Node(0); // Create a dummy node to help build the large list
        var current = dummy; // Setup a pointer to build the list
        var largeSize = 100000; // Define the large data size

        // Use Java 8 IntStream to generate elements, map to objects, and add to list
        IntStream.range(1, largeSize + 1).forEach(i -> { // Loop from 1 to 100,000
            current.next = new Node(i); // Create and attach new node
        }); // End of stream iteration

        // Note: dummy.next is the actual head (value 1)
        var res4 = reverseList(dummy.next); // Reverse the massive list
        var pass4 = res4 != null && res4.val == largeSize; // Check if the new head is the last element (100000)
        System.out.println("Test 4 (Large Data - 100k nodes): " + (pass4 ? "PASS" : "FAIL")); // Print PASS or FAIL
    } // End of main method

    static class Node { // Creating a simple static nested class to represent a list node
        int val; // Variable to store the integer value of the node
        Node next; // Pointer to the next node in the list
        Node(int val) { this.val = val; } // Constructor to easily create a node with a value
    } // End of Node class
} // End of main class