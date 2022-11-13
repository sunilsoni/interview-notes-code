package com.interview.notes.code.datastructure.list;

//https://www.educative.io/m/delete-node-with-given-key
public class DeleteNodeGivenKey {
    public static LinkedListNode deleteNode(LinkedListNode head, int key) {
        LinkedListNode prev = null;
        LinkedListNode current = head;

        while (current != null) {
            if (current.data == key) {
                if (current == head) {
                    head = head.next;
                    current = head;
                } else {
                    prev.next = current.next;
                    current = current.next;
                }
            } else {
                prev = current;
                current = current.next;
            }
        }

        // key not found in list
        if (current == null) {
            return head;
        }

        return head;
    }

    public static void main(String[] args) {
       /* LinkedListNode list_head = null;
        list_head = LinkedList.create_random_list(10);
        System.out.print("Original: ");
        LinkedList.display(list_head);

        ArrayList<Integer> lst = LinkedList.to_list(list_head);

        System.out.printf("\nDeleting %d", lst.get(5));
        list_head = deleteNode(list_head, lst.get(5));
        System.out.printf("\nAfter Delete Node:");
        LinkedList.display(list_head);

        System.out.printf("\nDeleting (Non-Existing) %d", 101);
        list_head = deleteNode(list_head, 101);
        System.out.print("\nAfter Delete Node:");
        LinkedList.display(list_head);

        System.out.printf("\nDeleting 1st node:%d", lst.get(0));
        list_head = deleteNode(list_head, lst.get(0));
        System.out.printf("\nAfter Delete 1st Node:");
        LinkedList.display(list_head);

        lst = LinkedList.to_list(list_head);
        System.out.printf("\nDeleting last node:", lst.get(lst.size() - 1));
        list_head = deleteNode(list_head, lst.get(lst.size() - 1));
        System.out.printf("\nAfter Delete last Node:");
        LinkedList.display(list_head);*/
    }
}

class LinkedListNode {
    public int data;
    public LinkedListNode next;

}





