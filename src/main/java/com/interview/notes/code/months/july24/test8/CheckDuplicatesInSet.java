package com.interview.notes.code.months.july24.test8;

import java.util.HashSet;
import java.util.Set;

public class CheckDuplicatesInSet {
    public static void main(String[] args) {
        // Create a HashSet to store strings (HashSet does not allow duplicates)
        Set<String> set = new HashSet<>();

        // Add elements to the set
        set.add("Apple");
        set.add("Banana");
        set.add("Orange");

        // Element to check for duplication
        String elementToCheck = "Apple";

        // Check if the set already contains the element
        if (set.contains(elementToCheck)) {
            System.out.println(elementToCheck + " is a duplicate in the set.");
        } else {
            System.out.println(elementToCheck + " is not a duplicate in the set.");
        }
    }
}
