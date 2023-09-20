package com.interview.notes.code.months.june23.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ArrayListBackwardTraversalExample {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");

        // Obtaining a ListIterator for the ArrayList
        ListIterator<String> iterator = fruits.listIterator(fruits.size());

        // Traversing the ArrayList in backward direction using the ListIterator
        while (iterator.hasPrevious()) {
            String fruit = iterator.previous();
            System.out.println(fruit);
        }
    }
}
