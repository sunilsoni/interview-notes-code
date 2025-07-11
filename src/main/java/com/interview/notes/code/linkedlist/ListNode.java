package com.interview.notes.code.linkedlist;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListNode {

    public ListNode next;
    private int data;

    public ListNode(int data) {
        this.data = data;
        this.next = null;
    }
}