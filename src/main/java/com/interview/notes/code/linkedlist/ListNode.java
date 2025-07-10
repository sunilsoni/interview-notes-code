package com.interview.notes.code.linkedlist;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListNode {

    private int data;
    public ListNode next;

    public ListNode(int data) {
        this.data = data;
        this.next = null;
    }
}