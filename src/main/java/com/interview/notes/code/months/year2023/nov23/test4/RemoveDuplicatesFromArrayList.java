package com.interview.notes.code.months.year2023.nov23.test4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RemoveDuplicatesFromArrayList {
    public static void main(String[] args) {
        // Create an ArrayList with duplicate elements
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(2);
        numbers.add(4);
        numbers.add(3);

        // Remove duplicates
        List<Integer> uniqueNumbers = removeDuplicates(numbers);

        // Print the unique elements
        System.out.println("Unique numbers: " + uniqueNumbers);
    }

    public static <T> List<T> removeDuplicates(List<T> list) {
        // Create a HashSet to store unique elements
        HashSet<T> set = new HashSet<>();

        // Create a new ArrayList to store the unique elements in the original order
        List<T> uniqueList = new ArrayList<>();

        for (T item : list) {
            // If the element is not in the HashSet, add it to both the HashSet and the new ArrayList
            if (set.add(item)) {
                uniqueList.add(item);
            }
        }

        return uniqueList;
    }
}
