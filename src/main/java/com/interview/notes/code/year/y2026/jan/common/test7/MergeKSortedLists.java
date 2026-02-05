package com.interview.notes.code.year.y2026.jan.common.test7;

import java.util.*;
import java.util.stream.IntStream;

public class MergeKSortedLists {

    // Core Logic: Merges K sorted lists into one using a Min-Heap.
    public static ListNode mergeKLists(ListNode[] lists) {
        // Use Java 21 'var' for type inference; Create a Min-Heap based on node values.
        var queue = new PriorityQueue<ListNode>((a, b) -> Integer.compare(a.val, b.val));

        // Stream over the input array, filter nulls, and add valid heads to the heap.
        Arrays.stream(lists).filter(Objects::nonNull).forEach(queue::add);

        // Create a dummy head to simplify list construction (avoids null checks for head).
        var dummy = new ListNode(0);
        var tail = dummy; // 'tail' pointer tracks the end of our new sorted list.

        // Loop until the heap is empty (processing all nodes).
        while (!queue.isEmpty()) {
            var node = queue.poll(); // Extract the node with the smallest value from heap.
            tail.next = node; // Append this smallest node to our result list.
            tail = tail.next; // Move the tail pointer forward.

            // If the extracted node has a next node, add it to the heap to be considered.
            if (node.next != null) {
                queue.add(node.next); // Add the next node from the same list back to heap.
            }
        }
        return dummy.next; // Return the actual head (skipping the dummy node).
    }

    public static void main(String[] args) {
        System.out.println("Starting Tests...");

        // Test Case 1: Standard Example (1->4->5, 1->3->4, 2->6)
        // Creating lists using a helper method for conciseness.
        ListNode[] input1 = {
                createList(1, 4, 5),
                createList(1, 3, 4),
                createList(2, 6)
        };
        // Expected result: 1->1->2->3->4->4->5->6
        int[] expected1 = {1, 1, 2, 3, 4, 4, 5, 6};
        runTest("TC1: Basic Example", input1, expected1); // Run the verification.

        // Test Case 2: Empty Input
        ListNode[] input2 = {}; // Case with no lists.
        runTest("TC2: Empty Input", input2, new int[]{}); // Expect empty result.

        // Test Case 3: Lists with nulls/empty lists inside
        ListNode[] input3 = {null, createList(1)}; // Mixed valid and null lists.
        runTest("TC3: Mixed Nulls", input3, new int[]{1}); // Should handle null gracefully.

        // Test Case 4: Large Data Input (Stress Test)
        System.out.println("\nRunning Large Data Test...");
        int numLists = 1000; // Number of lists.
        int nodesPerList = 100; // Nodes per list.
        ListNode[] largeInput = new ListNode[numLists]; // Array to hold large input.

        // Generate sorted data: List i contains multiples of i (just to have sorted data).
        for (int i = 0; i < numLists; i++) {
            int start = i; // Start value.
            // Create a simple sorted list for this index.
            largeInput[i] = createList(IntStream.range(0, nodesPerList).map(n -> start + n * 10).toArray());
        }

        long startTime = System.currentTimeMillis(); // Start timer.
        ListNode largeResult = mergeKLists(largeInput); // Execute merge.
        long endTime = System.currentTimeMillis(); // End timer.

        // Verify Large Data: Check if result is sorted and count is correct.
        boolean isSorted = isSorted(largeResult); // Helper to check sort order.
        int count = countNodes(largeResult); // Helper to count nodes.
        int expectedCount = numLists * nodesPerList; // Calculate expected count.

        // Print PASS/FAIL for large data.
        if (isSorted && count == expectedCount) {
            System.out.println("TC4: Large Data (" + count + " nodes) -> PASS (" + (endTime - startTime) + "ms)");
        } else {
            System.out.println("TC4: Large Data -> FAIL (Count: " + count + ", Sorted: " + isSorted + ")");
        }
    }

    // --- Testing Infrastructure (No JUnit, simple Main method) ---

    // Helper: Run a test case and print PASS/FAIL.
    static void runTest(String name, ListNode[] input, int[] expected) {
        ListNode result = mergeKLists(input); // Run the algorithm.
        List<Integer> resultList = toList(result); // Convert result to Java List for easy comparison.
        List<Integer> expectedList = Arrays.stream(expected).boxed().toList(); // Convert expected array to List.

        // Check if actual result matches expected result.
        if (resultList.equals(expectedList)) {
            System.out.println(name + " -> PASS");
        } else {
            System.out.println(name + " -> FAIL");
            System.out.println("   Expected: " + expectedList);
            System.out.println("   Got:      " + resultList);
        }
    }

    // --- Helper Methods to keep code clean ---

    // Helper: Create a linked list from varargs (Java Stream used).
    static ListNode createList(int... values) {
        ListNode dummy = new ListNode(0); // Dummy head.
        ListNode curr = dummy; // Pointer.
        for (int v : values) { // Loop through values.
            curr.next = new ListNode(v); // Create node.
            curr = curr.next; // Move pointer.
        }
        return dummy.next; // Return head.
    }

    // Helper: Convert LinkedList to Java List for easy printing/comparison.
    static List<Integer> toList(ListNode node) {
        List<Integer> res = new ArrayList<>(); // Result list.
        while (node != null) { // Traverse list.
            res.add(node.val); // Add value.
            node = node.next; // Move next.
        }
        return res; // Return list.
    }

    // Helper: Check if a linked list is sorted strictly.
    static boolean isSorted(ListNode node) {
        while (node != null && node.next != null) { // Traverse.
            if (node.val > node.next.val) return false; // If current > next, it's not sorted.
            node = node.next; // Move next.
        }
        return true; // If we reach here, it is sorted.
    }

    // Helper: Count nodes in list.
    static int countNodes(ListNode node) {
        int count = 0; // Counter.
        while (node != null) { // Traverse.
            count++; // Increment.
            node = node.next; // Move next.
        }
        return count; // Return total.
    }

    // Simple Definition for singly-linked list using standard class structure.
    static class ListNode {
        int val; // Value of the node.
        ListNode next; // Reference to the next node.

        ListNode(int val) {
            this.val = val;
        } // Constructor to initialize value.
    }
}