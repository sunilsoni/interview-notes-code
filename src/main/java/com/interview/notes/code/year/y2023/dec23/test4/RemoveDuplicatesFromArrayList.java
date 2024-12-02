package com.interview.notes.code.year.y2023.dec23.test4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicatesFromArrayList {
    public static void main(String[] args) {
        List<Integer> originalList = new ArrayList<>();
        originalList.add(1);
        originalList.add(2);
        originalList.add(3);
        originalList.add(3); // Duplicate
        originalList.add(5);

        // Remove duplicates using Java Streams
        List<Integer> resultList = originalList.stream()
                .distinct()
                .collect(Collectors.toList());

        // Print the result
        System.out.println("Original List: " + originalList);
        System.out.println("List with Duplicates Removed: " + resultList);
    }
}
