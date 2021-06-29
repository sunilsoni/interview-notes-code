package com.interview.notes.code.linkedlist;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class MyLinkedListTest {
    static MyLinkedList linkedList;
    @BeforeAll
    public static void setUp() {
        linkedList  = new MyLinkedList();
        linkedList.insertFirst(100);
        linkedList.insertFirst(500);
        linkedList.insertFirst(300);
        linkedList.insertFirst(200);
        linkedList.printLinkedList();
    }

    @Test
    void stackTest() {

        //linkedList.deleteFirst();
        //	linkedList.printLinkedList();
        //	linkedList.insertLast(7000);
        //	linkedList.printLinkedList();
        linkedList.lengthOfLinkedList();
        System.out.println(linkedList.lengthOfLinkedList());
        //linkedList.printLinkedList();
        //Node nthNodeFromLast= linkedList.nthFromLastNode(head,3);
        //System.out.println("3th node from end is : "+ nthNodeFromLast.value);


    }
}