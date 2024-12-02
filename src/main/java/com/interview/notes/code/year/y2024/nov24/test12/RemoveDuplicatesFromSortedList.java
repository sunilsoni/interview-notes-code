package com.interview.notes.code.year.y2024.nov24.test12;

class RemoveDuplicatesFromSortedList {
    // Solution method
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    // Helper method to create a linked list from an array
    private static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper method to convert a linked list to a string
    private static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append("->");
            }
            head = head.next;
        }
        return sb.toString();
    }

    // Test method
    public static void testCase(int[] input, int[] expected) {
        ListNode inputList = createList(input);
        ListNode expectedList = createList(expected);
        ListNode result = deleteDuplicates(inputList);

        String resultStr = listToString(result);
        String expectedStr = listToString(expectedList);

        boolean passed = resultStr.equals(expectedStr);
        System.out.println("Input: " + listToString(inputList));
        System.out.println("Output: " + resultStr);
        System.out.println("Expected: " + expectedStr);
        System.out.println("Result: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }

    public static void main(String[] args) {
        // Test case 1
        testCase(new int[]{1, 1, 2}, new int[]{1, 2});

        // Test case 2
        testCase(new int[]{1, 1, 2, 3, 3}, new int[]{1, 2, 3});

        // Test case 3: Empty list
        testCase(new int[]{}, new int[]{});

        // Test case 4: Single element
        testCase(new int[]{1}, new int[]{1});

        // Test case 5: All duplicates
        testCase(new int[]{1, 1, 1, 1, 1}, new int[]{1});

        // Test case 6: No duplicates
        testCase(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5});

        // Test case 7: Large input
        int[] largeInput = new int[300];
        int[] largeExpected = new int[100];
        for (int i = 0; i < 300; i++) {
            largeInput[i] = i / 3;
        }
        for (int i = 0; i < 100; i++) {
            largeExpected[i] = i;
        }
        testCase(largeInput, largeExpected);
    }

    // Definition for singly-linked list
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
