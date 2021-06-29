package com.interview.notes.code.datastructure.Stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedListStackTest {
    LinkedListStack lls = null;

    @BeforeEach
    public void setUp() {
        lls = new LinkedListStack();
        lls.push(20);
        lls.push(50);
        lls.push(80);
        lls.push(40);
        lls.push(60);
        lls.push(75);
    }

    @AfterEach
    public void tearDown() {
        lls = null;
    }

    @Test
    void test() {
        System.out.println("Element removed from LinkedList: " + lls.pop());
        System.out.println("Element removed from LinkedList: " + lls.pop());
        lls.push(10);
        System.out.println("Element removed from LinkedList: " + lls.pop());
        //  lls.printList(lls.head);
    }
}