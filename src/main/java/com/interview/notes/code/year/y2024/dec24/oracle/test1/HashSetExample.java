package com.interview.notes.code.year.y2024.dec24.oracle.test1;

import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        // Create a HashSet of Strings
        HashSet<String> set = new HashSet<>();

        // Add elements
        System.out.println(set.add("Apple"));  // Output: true (Apple added)
        System.out.println(set.add("Banana")); // Output: true (Banana added)
        System.out.println(set.add("Apple"));  // Output: false (duplicate, not added)

        // Display the HashSet
        System.out.println("HashSet contents: " + set);  // Output: [Apple, Banana]
    }
}
