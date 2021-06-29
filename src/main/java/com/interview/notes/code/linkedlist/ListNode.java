package com.interview.notes.code.linkedlist;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListNode {

    private int data;
    private ListNode next;

    ListNode(int data) {
        this.data = data;
        this.next = null;
    }
}