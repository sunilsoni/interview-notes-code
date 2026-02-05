package com.interview.notes.code.year.y2026.jan.common.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MergeKSorted_Linear {

    /**
     * O(N) Solution: Uses Frequency Array (Counting Sort).
     * Solves the "Lambda Variable" error by using standard loops for updates.
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        // Validation: If input is null or empty, return null immediately.
        if (lists == null || lists.length == 0) return null;

        // 1. FIND RANGE: We need Min and Max to define array size.
        int min = Integer.MAX_VALUE; // Start with max possible integer.
        int max = Integer.MIN_VALUE; // Start with min possible integer.

        // Use standard FOR loop to avoid "effectively final" lambda error.
        for (var node : lists) {
            var curr = node; // Temp pointer.
            while (curr != null) { // Traverse this list.
                if (curr.val < min) min = curr.val; // Update global min.
                if (curr.val > max) max = curr.val; // Update global max.
                curr = curr.next; // Move next.
            }
        }

        // Guard clause: If min is still MAX_VALUE, it means no nodes existed.
        if (min > max) return null;

        // 2. COUNT FREQUENCIES: Create array based on range.
        // Note: range must be reasonable (< 100M) for O(N) to work.
        int range = max - min + 1;
        int[] count = new int[range];

        for (var node : lists) { // Iterate all lists again.
            var curr = node;
            while (curr != null) {
                // 'val - min' shifts negative numbers to start at index 0.
                count[curr.val - min]++;
                curr = curr.next;
            }
        }

        // 3. REBUILD LIST: Create new sorted list from counts.
        var dummy = new ListNode(0); // Dummy head.
        var tail = dummy; // Pointer to build list.

        for (int i = 0; i < range; i++) { // Iterate through frequency array.
            while (count[i] > 0) { // While we have occurrences of this number...
                tail.next = new ListNode(i + min); // Restore actual value (index + min).
                tail = tail.next; // Move tail forward.
                count[i]--; // Decrement count.
            }
        }

        return dummy.next; // Return head (skip dummy).
    }

    public static void main(String[] args) {
        System.out.println("--- Running O(N) Merge Tests ---");

        // TC1: Basic Positive Numbers
        // Lists: [1->4->5], [1->3->4], [2->6]
        ListNode[] input1 = {
                createList(1, 4, 5),
                createList(1, 3, 4),
                createList(2, 6)
        };
        // Expected: 1->1->2->3->4->4->5->6
        runTest("TC1: Basic", input1, new int[]{1, 1, 2, 3, 4, 4, 5, 6});

        // TC2: Negative Numbers (Verifies 'val - min' logic)
        // Lists: [-10->-5], [-5->0->2]
        ListNode[] input2 = {
                createList(-10, -5),
                createList(-5, 0, 2)
        };
        // Expected: -10, -5, -5, 0, 2
        runTest("TC2: Negatives", input2, new int[]{-10, -5, -5, 0, 2});

        // TC3: Empty/Null Handling
        ListNode[] input3 = {null, createList()};
        runTest("TC3: Empty", input3, new int[]{});

        // TC4: Large Data (Stress Test)
        System.out.println("\nRunning Large Data Test...");
        int numLists = 1000;
        int nodesPerList = 500;
        ListNode[] largeInput = new ListNode[numLists];

        // Generate data ensuring values stay within safe array range (0 to 20,000)
        for (int i = 0; i < numLists; i++) {
            int start = i % 100; // Keep start values small
            // Create sorted list using streams (safe here as it's just data gen)
            int[] vals = IntStream.range(0, nodesPerList).map(n -> start + n).toArray();
            largeInput[i] = createList(vals);
        }

        long start = System.currentTimeMillis();
        ListNode res = mergeKLists(largeInput);
        long end = System.currentTimeMillis();

        // Verify results
        boolean sorted = isSorted(res);
        int totalNodes = countNodes(res);
        int expected = numLists * nodesPerList;

        if (sorted && totalNodes == expected) {
            System.out.println("TC4: Large Data -> PASS (" + (end - start) + "ms)");
        } else {
            System.out.println("TC4: Large Data -> FAIL (Count: " + totalNodes + ")");
        }
    }

    // --- TEST RUNNER (Simple Main Method) ---

    // Helper: Runs test and prints pass/fail.
    static void runTest(String name, ListNode[] input, int[] expected) {
        ListNode result = mergeKLists(input); // Execute Logic.
        List<Integer> actual = new ArrayList<>();
        while (result != null) {
            actual.add(result.val);
            result = result.next;
        }

        List<Integer> exp = Arrays.stream(expected).boxed().toList();
        if (actual.equals(exp)) System.out.println(name + " -> PASS");
        else System.out.println(name + " -> FAIL Expected " + exp + " but got " + actual);
    }

    // --- HELPER METHODS ---

    // Helper: Creates a list from numbers.
    static ListNode createList(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : values) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper: Validates sort order.
    static boolean isSorted(ListNode n) {
        while (n != null && n.next != null) {
            if (n.val > n.next.val) return false;
            n = n.next;
        }
        return true;
    }

    // Helper: Counts nodes.
    static int countNodes(ListNode n) {
        int c = 0;
        while (n != null) {
            c++;
            n = n.next;
        }
        return c;
    }

    // Simple Node class definition.
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}