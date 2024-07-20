package com.interview.notes.code.months.july24.test8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder inputData = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String thisLine;
        while ((thisLine = br.readLine()) != null && thisLine.length() != 0) {
            inputData.append(thisLine).append("\n");
        }
        System.out.print(codeHere(inputData));
    }

    public static String codeHere(StringBuilder inputData) {
        Scanner scanner = new Scanner(inputData.toString());
        StringBuilder output = new StringBuilder();
        int numberOfTestCases = scanner.nextInt();
        while (numberOfTestCases-- > 0) {
            int numberOfNodes = scanner.nextInt();
            int n = scanner.nextInt();
            Node head = null, current = null;

            if (numberOfNodes > 0) {
                head = new Node(scanner.nextInt());
                current = head;
                for (int i = 1; i < numberOfNodes; i++) {
                    current.next = new Node(scanner.nextInt());
                    current = current.next;
                }
            }

            Node result = findNthFromEnd(head, n);
            if (result != null) {
                output.append(result.data).append("\n");
            } else {
                output.append("none").append("\n");
            }
        }
        scanner.close();
        return output.toString();
    }

    // Method to find the nth node from the end of the linked list
    public static Node findNthFromTarget(Node head, int n) {
        Node first = head;
        Node second = head;

        for (int i = 0; i < n; i++) {
            if (first == null) return null; // n is larger than the number of nodes
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        return second;
    }

    public static Node findNthFromEnd(Node head, int n) {
        if (head == null) return null;

        Node first = head;
        Node second = head;
        for (int i = 0; i < n; i++) {
            if (first == null) return null; // n is larger than the number of nodes
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        return second;
    }
}
