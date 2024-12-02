package com.interview.notes.code.year.y2023.july23.test1;

import java.util.ArrayList;

public class NotifyingList<E> extends ArrayList<E> {

    public static void main(String[] args) {
        NotifyingList<String> myList = new NotifyingList<>();
        myList.add("Hello");
        myList.add("World");
    }

    // Additional constructors if needed

    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        if (added) {
            System.out.println("Element added: " + element.toString());
        }
        return added;
    }
}
