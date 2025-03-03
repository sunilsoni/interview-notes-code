package com.interview.notes.code.year.y2025.feb.common.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeLists {
    public static void main(String[] args) {
        // Creating two sample lists
        List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> list2 = Arrays.asList(2, 4, 6, 8, 10);

        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);

        // Method 1: Without Stream API
        List<Integer> mergedListTraditional = mergeListsTraditional(list1, list2);
        System.out.println("\nMerged List (Traditional approach): " + mergedListTraditional);

        // Method 2: Using Stream API
        List<Integer> mergedListStream = mergeListsStream(list1, list2);
        System.out.println("Merged List (Stream API): " + mergedListStream);
    }

    // Traditional approach without Stream API
    public static List<Integer> mergeListsTraditional(List<Integer> list1, List<Integer> list2) {
        List<Integer> mergedList = new ArrayList<>();

        // Add all elements from first list
        mergedList.addAll(list1);

        // Add all elements from second list
        mergedList.addAll(list2);

        // Optional: Sort the merged list
        Collections.sort(mergedList);

        return mergedList;
    }

    // Using Stream API
    public static List<Integer> mergeListsStream(List<Integer> list1, List<Integer> list2) {
        return Stream.concat(list1.stream(), list2.stream())
                .sorted()
                .collect(Collectors.toList());
    }
}
