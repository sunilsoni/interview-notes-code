package com.interview.notes.code.year.y2024.oct24.amazon.test5;

/*
You are given the heads of two sorted linked lists listl and list2. Merge the two lists into one sorted list in-place.
The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.
Example:
11 - [3,7,9,13]
L2 - [1,2,6]
Return pointer to 1 and the list should be [1,2,3,6,7,9,13]


 */
public class MergeSortedLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        if (l1 != null) {
            current.next = l1;
        }
        if (l2 != null) {
            current.next = l2;
        }

        return dummy.next;
    }

    public static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    public static boolean areListsEqual(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1 == null && l2 == null;
    }

    public static void testMergeTwoLists() {
        // Test case 1: Basic case
        ListNode l1 = createList(new int[]{3, 7, 9, 13});
        ListNode l2 = createList(new int[]{1, 2, 6});
        ListNode expected = createList(new int[]{1, 2, 3, 6, 7, 9, 13});
        ListNode result = mergeTwoLists(l1, l2);
        System.out.println("Test case 1: " + (areListsEqual(result, expected) ? "PASS" : "FAIL"));

        // Test case 2: One empty list
        l1 = createList(new int[]{1, 2, 3});
        l2 = null;
        expected = createList(new int[]{1, 2, 3});
        result = mergeTwoLists(l1, l2);
        System.out.println("Test case 2: " + (areListsEqual(result, expected) ? "PASS" : "FAIL"));

        // Test case 3: Both empty lists
        l1 = null;
        l2 = null;
        expected = null;
        result = mergeTwoLists(l1, l2);
        System.out.println("Test case 3: " + (areListsEqual(result, expected) ? "PASS" : "FAIL"));

        // Test case 4: Large data input
        int[] arr1 = new int[100000];
        int[] arr2 = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr1[i] = i * 2;
            arr2[i] = i * 2 + 1;
        }
        l1 = createList(arr1);
        l2 = createList(arr2);
        result = mergeTwoLists(l1, l2);
        boolean isCorrect = true;
        for (int i = 0; i < 200000; i++) {
            if (result.val != i) {
                isCorrect = false;
                break;
            }
            result = result.next;
        }
        System.out.println("Test case 4 (Large data): " + (isCorrect ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        testMergeTwoLists();
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
