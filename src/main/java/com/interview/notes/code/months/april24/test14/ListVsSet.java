package com.interview.notes.code.months.april24.test14;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListVsSet {
    public static void main(String[] args) {
        List<String> namesList = new ArrayList<>();
        namesList.add("John");
        namesList.add("Alice");
        namesList.add("Alice");
        namesList.add("Bob");

        System.out.println("List: " + namesList); // Output: List: [John, Alice, Alice, Bob]

        Set<String> namesSet = new HashSet<>();
        namesSet.add("John");
        namesSet.add("Alice");
        namesSet.add("Alice");
        namesSet.add("Bob");

        System.out.println("Set: " + namesSet); // Output: Set: [Bob, John, Alice]
    }
}