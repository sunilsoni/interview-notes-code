package com.interview.notes.code.year.y2026.june.common.test9;

import java.util.Arrays; // We use this to convert arrays to streams
import java.util.Comparator; // We need this to tell the PriorityQueue how to compare custom objects
import java.util.Objects; // We use this for null safety checks in our streams
import java.util.PriorityQueue; // We need this to automatically sort and fetch nodes

// Defining the standard Linked List Node structure
class ListNode { // Class representing a single block (node) in our linked list
    int val; // This variable stores the actual number (data) for this node
    ListNode next; // This acts as a pointer connecting to the next node in the chain
    ListNode(int val) { // Constructor used to quickly create a node with a specific number
        this.val = val; // Assigns the passed number to this node's internal value
    } // Ends the constructor block
} // Ends the ListNode class definition

public class MergeKListsReverseOrder { // Main class containing our logic and testing framework

    // Method taking an array of descending lists and returning one merged descending list
    public static ListNode mergeKListsReverse(ListNode[] lists) { // Accepts array of ListNodes
        if (lists == null || lists.length == 0) return null; // Immediate safety check: if there are no lists, return nothing
        
        // REVERSE ORDER (MAX-HEAP): We use .reversed() to prioritize pulling the LARGEST numbers first
        PriorityQueue<ListNode> queue = new PriorityQueue<>( // Create a new Priority Queue
            Comparator.comparingInt((ListNode node) -> node.val).reversed() // Tells the queue to sort by node value, but backwards
        ); // Ends Priority Queue initialization
        
        // Java 8 Stream API to cleanly filter out nulls and add the first node of each list to our queue
        Arrays.stream(lists) // Convert our standard array of lists into a Java Stream for processing
              .filter(Objects::nonNull) // Remove any empty (null) lists from the stream so they don't break our code
              .forEach(queue::add); // Add the head node of every valid list into our Max-Heap Priority Queue
        
        var dummy = new ListNode(0); // Java 10+ 'var': Create a fake starting node to easily anchor our new merged list
        var tail = dummy; // Create a 'tail' pointer that will move forward as we build the list, starting at the dummy
        
        while (!queue.isEmpty()) { // Keep looping as long as we have nodes waiting in our queue
            tail.next = queue.poll(); // Pluck the absolute LARGEST node out of the queue and attach it to our merged list
            tail = tail.next; // Move our tail pointer forward so it sits on the node we just added
            
            if (tail.next != null) { // Check if the node we just processed has another node connected behind it
                queue.add(tail.next); // If it does, throw that next node into the queue to be sorted and compared
            } // Ends the if condition block
        } // Ends the while loop block
        
        return dummy.next; // Return the completed merged list, skipping the fake 'dummy' node we used for anchoring
    } // Ends the mergeKListsReverse method

    // --- SIMPLE MAIN METHOD FOR TESTING (NO JUNIT) ---
    public static void main(String[] args) { // Standard entry point to run our tests
        System.out.println("Starting Reverse Order (Max-Heap) tests...\n"); // Print a starting message to the console
        
        // Notice the inputs are now sorted largest to smallest (Descending)
        runTest("Test 1: Standard Reverse Case", new int[][]{{5, 4, 1}, {4, 3, 1}, {6, 2}}, "6->5->4->4->3->2->1->1"); // Run basic test with descending inputs
        runTest("Test 2: Empty Lists", new int[][]{}, ""); // Run edge case: completely empty input
        runTest("Test 3: Lists with nulls", new int[][]{null, {4, 3}, null, {2, 1}}, "4->3->2->1"); // Run edge case: array contains null elements
        runLargeDataTest(); // Run a heavy performance test with large data constraints
    } // Ends main method

    // Helper method to build, execute, and verify a test case
    private static void runTest(String testName, int[][] inputArrays, String expectedOutput) { // Accepts test name, raw data, and expected string
        var lists = new ListNode[inputArrays.length]; // Create an array to hold our generated Linked Lists
        for (int i = 0; i < inputArrays.length; i++) { // Loop through each raw integer array provided
            lists[i] = buildList(inputArrays[i]); // Convert the integer array into a proper Linked List and store it
        } // Ends the list building loop
        
        var resultNode = mergeKListsReverse(lists); // Execute our core reverse-merging algorithm on the generated lists
        var actualOutput = listToString(resultNode); // Convert the resulting merged Linked List back into a readable string
        
        if (actualOutput.equals(expectedOutput)) { // Check if our algorithm's output matches what we expect
            System.out.println(testName + ": PASS"); // If they match, print PASS to the console
        } else { // If they don't match, we have a problem
            System.out.println(testName + ": FAIL"); // Print FAIL to the console
            System.out.println("Expected: " + expectedOutput); // Show what it should have been
            System.out.println("Actual:   " + actualOutput); // Show what it actually produced
        } // Ends the pass/fail check block
    } // Ends runTest method

    // Helper method to test huge volumes of data (Performance test for Reverse logic)
    private static void runLargeDataTest() { // Method specifically for testing high volume inputs
        int k = 1000; // Define K: We will merge 1,000 separate linked lists
        int nodesPerList = 50; // Each of those 1,000 lists will have 50 nodes
        var lists = new ListNode[k]; // Create an array to hold our 1,000 lists
        
        for (int i = 0; i < k; i++) { // Loop 1,000 times to create the lists
            var dummy = new ListNode(0); // Create a dummy node to help build the current list
            var current = dummy; // Set a pointer to build the list
            for (int j = nodesPerList; j > 0; j--) { // Loop backwards 50 times to create DESCENDING values
                current.next = new ListNode(i + j); // Create nodes with decreasing values so they remain reverse-sorted
                current = current.next; // Move pointer forward
            } // Ends node creation loop
            lists[i] = dummy.next; // Store the generated list into our array
        } // Ends list creation loop
        
        var startTime = System.currentTimeMillis(); // Record the exact time before we start merging
        var result = mergeKListsReverse(lists); // Execute the Max-Heap merge algorithm on all 50,000 nodes
        var endTime = System.currentTimeMillis(); // Record the exact time after the merge finishes
        
        int count = 0; // Create a counter to verify we haven't lost any data
        while (result != null) { // Loop through the massive merged list until we reach the end
            count++; // Increment the counter for every node we find
            result = result.next; // Move to the next node
        } // Ends the counting loop
        
        if (count == (k * nodesPerList)) { // Verify the total node count equals 50,000
            System.out.println("Test 4: Large Data Reverse (" + count + " nodes): PASS in " + (endTime - startTime) + "ms"); // Print PASS and show execution speed
        } else { // If the count is wrong, data was lost or duplicated
            System.out.println("Test 4: Large Data Reverse: FAIL (Count mismatch)"); // Print FAIL
        } // Ends verification block
    } // Ends runLargeDataTest method

    // Utility method to convert an array of ints into a Linked List
    private static ListNode buildList(int[] nums) { // Accepts an array of integers
        if (nums == null || nums.length == 0) return null; // If the array is empty, return null representing an empty list
        var dummy = new ListNode(0); // Dummy node to anchor the list
        var current = dummy; // Pointer to traverse and build
        for (int num : nums) { // Loop through every integer in the array
            current.next = new ListNode(num); // Create a new Linked List node for the integer
            current = current.next; // Move the pointer forward
        } // Ends the array iteration
        return dummy.next; // Return the built list, skipping the dummy anchor
    } // Ends buildList method

    // Utility method to convert a Linked List into a formatted string (e.g., "6->5->4")
    private static String listToString(ListNode head) { // Accepts the starting node of a list
        if (head == null) return ""; // If the list is empty, return a blank string
        var sb = new StringBuilder(); // Use StringBuilder for efficient string concatenation
        while (head != null) { // Loop until we reach the end of the list
            sb.append(head.val); // Add the current node's value to our string
            if (head.next != null) { // If there is another node after this one
                sb.append("->"); // Add the requested formatting arrow
            } // Ends the arrow append block
            head = head.next; // Move to the next node
        } // Ends the string building loop
        return sb.toString(); // Convert the StringBuilder object back into a standard String and return it
    } // Ends listToString method

} // Ends the MergeKListsReverseOrder class