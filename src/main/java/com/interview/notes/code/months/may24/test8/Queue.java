package com.interview.notes.code.months.may24.test8;

class Queue {
    Stack s1, s2;

    Queue() {
        s1 = new Stack();
        s2 = new Stack();
    }

    public void push(int new_data) {
        while (s1.head != null) {
            s2.push(s1.pop());
        }
        s1.push(new_data);
        while (s2.head != null) {
            s1.push(s2.pop());
        }
    }

    public int pop() {
        if (s1.head == null) return -1;
        return s1.pop();
    }
}