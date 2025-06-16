package com.interview.notes.code.year.y2025.June.apple.test3;

class ListNode {
    ListNode next;
    int data;
    
    public ListNode(int d) {
        this.data = d;
    }
}

class LinkedListUtils {
    /**
     * Recursive approach with previous and current pointers
     * Usage: recursiveReverse(null, head)
     */
    public static ListNode recursiveReverse(ListNode previous, ListNode current) {
        // Base case: if current is null, return previous (new head)
        if (current == null) {
            return previous;
        }
        
        // Store next node before we change current.next
        ListNode nextTemp = current.next;
        
        // Reverse the link
        current.next = previous;
        
        // Recursive call moving both pointers forward
        return recursiveReverse(current, nextTemp);
    }
    
    /**
     * Wrapper method to make the recursive call easier
     */
    public static ListNode reverse(ListNode head) {
        return recursiveReverse(null, head);
    }

    // Helper methods for testing
    public static ListNode add(ListNode head, int data) {
        if (head == null) {
            return new ListNode(data);
        }
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new ListNode(data);
        return head;
    }
    
    public static void print(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.data + " → ");
            current = current.next;
        }
        System.out.println("null");
    }
}

public class LinkedListTest {
    public static void main(String[] args) {
        // Test cases
        testNormalCase();
        testSingleNode();
        testEmptyList();
        testTwoNodes();
    }
    
    private static void testNormalCase() {
        System.out.println("\nTest Case 1: Normal List");
        ListNode head = null;
        head = LinkedListUtils.add(head, 1);
        head = LinkedListUtils.add(head, 2);
        head = LinkedListUtils.add(head, 3);
        head = LinkedListUtils.add(head, 4);
        
        System.out.println("Original:");
        LinkedListUtils.print(head);
        
        head = LinkedListUtils.reverse(head);
        
        System.out.println("Reversed:");
        LinkedListUtils.print(head);
    }
    
    private static void testSingleNode() {
        System.out.println("\nTest Case 2: Single Node");
        ListNode head = new ListNode(1);
        
        System.out.println("Original:");
        LinkedListUtils.print(head);
        
        head = LinkedListUtils.reverse(head);
        
        System.out.println("Reversed:");
        LinkedListUtils.print(head);
    }
    
    private static void testEmptyList() {
        System.out.println("\nTest Case 3: Empty List");
        ListNode head = null;
        
        System.out.println("Original:");
        LinkedListUtils.print(head);
        
        head = LinkedListUtils.reverse(head);
        
        System.out.println("Reversed:");
        LinkedListUtils.print(head);
    }
    
    private static void testTwoNodes() {
        System.out.println("\nTest Case 4: Two Nodes");
        ListNode head = null;
        head = LinkedListUtils.add(head, 1);
        head = LinkedListUtils.add(head, 2);
        
        System.out.println("Original:");
        LinkedListUtils.print(head);
        
        head = LinkedListUtils.reverse(head);
        
        System.out.println("Reversed:");
        LinkedListUtils.print(head);
    }
}
