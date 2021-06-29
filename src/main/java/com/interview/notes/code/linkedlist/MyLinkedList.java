package com.interview.notes.code.linkedlist;

public class MyLinkedList {

    private Node head;

    public boolean isEmpty() {
        return (head==null);
    }
    public void insertFirst(int data) {

        Node newNode= new Node();
        newNode.data=data;
        newNode.next=head;
        head=newNode;
    }
    public void insertLast(int data) {
        Node current=head;
        while(current.next != null) {
            current=current.next;
        }
        Node newNode= new Node();
        newNode.data=data;
        current.next = newNode;
    }
    public Node deleteFirst() {
        Node temp=head;
        head=head.next;
        return temp;
    }
    public Node nthFromLastNode(Node head,int n) {
        Node firstPointer=head;
        Node secondPointer= head;
        for(int i=0; i<n; i++) {
            firstPointer=firstPointer.next;
        }
        while(firstPointer!=null) {
            firstPointer=firstPointer.next;
            secondPointer=secondPointer.next;
        }
        return secondPointer;

    }
    public int lengthOfLinkedList() {
        Node temp=head;
        int count=0;
        while(temp !=null) {
            temp=temp.next;
            count++;
        }

        return count;

    }
    public void printLinkedList() {
        Node current=head;
        Node node=new Node();
        while(current !=null) {
            current.displayNode();
            current=current.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        MyLinkedList linkedList= new MyLinkedList();
        linkedList.insertFirst(100);
        linkedList.insertFirst(500);
        linkedList.insertFirst(300);
        linkedList.insertFirst(200);
        linkedList.printLinkedList();
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
