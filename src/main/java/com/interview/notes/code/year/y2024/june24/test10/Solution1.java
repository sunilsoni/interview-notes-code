package com.interview.notes.code.year.y2024.june24.test10;

class Solution1 {
    public static long getNumber(SinglyLinkedListNode binary) {
        long number = 0; // This will store the decimal value
        while (binary != null) {
            number = number * 2 + binary.data;
            binary = binary.next;
        }
        return number;
    }

    public static void main(String[] args) {
        // Example 1: binary number 0000011111 which should output 31
        SinglyLinkedListNode head1 = new SinglyLinkedListNode(0);
        SinglyLinkedListNode node1 = head1;
        for (int data : new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1}) {
            node1.next = new SinglyLinkedListNode(data);
            node1 = node1.next;
        }
        System.out.println("Decimal of 0000011111: " + getNumber(head1.next)); // Head.next to skip leading zero

        // Example 2: binary number 001010 which should output 26
        SinglyLinkedListNode head2 = new SinglyLinkedListNode(0);
        SinglyLinkedListNode node2 = head2;
        for (int data : new int[]{0, 1, 0, 1, 0}) {
            node2.next = new SinglyLinkedListNode(data);
            node2 = node2.next;
        }
        System.out.println("Decimal of 001010: " + getNumber(head2.next)); // Head.next to skip leading zero
    }
}