package com.interview.notes.code.year.y2024.march24.test13;

import java.util.Scanner;

class Queue {
    Node head;

    public void push(int new_data) {
        // new data contains the value to be pushed into the queue
        Node temp = new Node(new_data);
        temp.next = null;
        if (head == null) head = temp;
        else {
            Node temp1 = head;
            while (temp1.next != null) temp1 = temp1.next;
            temp1.next = temp;
        }
    }

    public int pop() {
        // returns the popped element
        // returns -1 if the queue is empty
        if (head == null) return -1;
        int value = head.data;
        head = head.next;
        return value;
    }

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }
}

class Stack {
    Queue q1, q2;

    Stack() {
        q1 = new Queue();
        q2 = new Queue();
    }

    public void push(int new_data) {
        // Push operation costs O(N)
        q2.push(new_data);
        while (q1.head != null) {
            q2.push(q1.pop());
        }
        Queue q = q1;
        q1 = q2;
        q2 = q;
    }

    public int pop() {
        // Pop operation costs O(1)
        return q1.pop();
    }
}

class Solution {
    public static void main(String[] args) throws java.lang.Exception {
        Stack S = new Stack();
        Scanner in = new Scanner(System.in);
        int q, type, value, popped, numPops = 0;
        q = in.nextInt();
        int[] output = new int[q];
        // Read input from stdin and apply query
        for (int i = 0; i < q; i++) {
            type = in.nextInt();
            if (type == 1) {
                value = in.nextInt();
                S.push(value);
            } else {
                popped = S.pop();
                output[numPops++] = popped;
            }
        }
        // Print output
        for (int i = 0; i < numPops; i++) {
            if (i != numPops - 1) {
                System.out.print(output[i] + " ");
            } else {
                System.out.print(output[i]);
            }
        }
    }
}
