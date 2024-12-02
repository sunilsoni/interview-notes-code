package com.interview.notes.code.year.y2024.march24.test13;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Define two lists
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        List<String> list2 = new ArrayList<>();
        list2.add("b");
        list2.add("c");
        list2.add("d");

        // Merge lists and get unique values
        List<String> mergedList = Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .collect(Collectors.toList());

        // Print the result
        System.out.println("Merged list with unique values: " + mergedList);
    }
}

