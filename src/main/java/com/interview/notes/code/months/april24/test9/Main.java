package com.interview.notes.code.months.april24.test9;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        
        // Adding a duplicate element
        boolean added = set.add(2); // Attempting to add 2 again
        System.out.println("Element added: " + added); // Output: Element added: false
        
        // Print the Set
        System.out.println("Set: " + set); // Output: Set: [1, 2, 3]
    }
}
