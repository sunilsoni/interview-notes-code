package com.interview.notes.code.year.y2026.jan.common.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Node class for LinkedList
class Node {
    int val; Node next;
    Node(int v){ val=v; } // constructor
}

public class MergeLists {

    // Method to merge multiple sorted lists
    static Node merge(List<Node> lists){
        // Collect all values from all lists into one list
        List<Integer> all = lists.stream() // stream of Node heads
            .flatMap(n -> { // flatten each linked list
                List<Integer> temp=new ArrayList<>();
                while(n!=null){ temp.add(n.val); n=n.next; } // collect values
                return temp.stream(); // return as stream
            })
            .sorted() // sort all values
            .toList(); // collect into list

        // Build new sorted linked list
        Node dummy=new Node(0), cur=dummy;
        for(int v:all){ cur.next=new Node(v); cur=cur.next; }
        return dummy.next; // return head
    }

    // Helper to print list as string
    static String print(Node head){
        return Stream.iterate(head, Objects::nonNull, n->n.next)
                     .map(n->String.valueOf(n.val))
                     .collect(Collectors.joining(",")); 
    }

    // Main method for testing (no JUnit)
    public static void main(String[] args){
        // Test case 1: small lists
        Node a=new Node(1); a.next=new Node(3); a.next.next=new Node(4);
        Node b=new Node(1); b.next=new Node(2); b.next.next=new Node(3);
        Node c=new Node(2); c.next=new Node(6);

        Node result=merge(Arrays.asList(a,b,c));
        String out=print(result);
        System.out.println("Test1: "+out+" -> "+(out.equals("1,1,2,2,3,3,4,6")?"PASS":"FAIL"));

        // Test case 2: empty list
        Node result2=merge(List.of());
        System.out.println("Test2: "+(result2==null?"PASS":"FAIL"));

        // Test case 3: large data
        Node big=new Node(1); Node cur=big;
        for(int i=2;i<=10000;i++){ cur.next=new Node(i); cur=cur.next; }
        Node result3=merge(List.of(big));
        String out3=print(result3);
        System.out.println("Test3: "+(out3.startsWith("1,2,3")?"PASS":"FAIL"));
    }
}
