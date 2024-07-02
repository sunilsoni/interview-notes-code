package com.interview.notes.code.months.june24.test10;

import java.io.*;

public class Solution {
    public static long getNumber(SinglyLinkedListNode binary) {
        long number = 0;  // Initialize the decimal number to 0
        while (binary != null) {
            number = (number << 1) | binary.data;  // Shift left by 1 bit and add the current node's data
            binary = binary.next;  // Move to the next node
        }
        return number;  // Return the computed decimal number
    }

    public static void main1(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int binaryCount = Integer.parseInt(bufferedReader.readLine().trim());

        SinglyLinkedList binary = new SinglyLinkedList();

        for (int i = 0; i < binaryCount; i++) {
            int binaryItem = Integer.parseInt(bufferedReader.readLine().trim());
            binary.insertNode(binaryItem);
        }

        long result = getNumber(binary.head);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
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