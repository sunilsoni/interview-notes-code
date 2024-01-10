package com.interview.notes.code.months.year2023.dec23.test4;

import java.util.ArrayList;
import java.util.List;

public class RemoveElementFromArrayList {
    public static void main(String[] args) {
        List<Integer> originalList = new ArrayList<>();
        originalList.add(1);
        originalList.add(2);
        originalList.add(3);
        originalList.add(3); // Duplicate
        originalList.add(5);


        int elementToRemove = 3;

        // Remove all occurrences of the specified element
        removeAllOccurrences(originalList, elementToRemove);

        // Print the result
        System.out.println("List with " + elementToRemove + " removed: " + originalList);
    }

    public static void removeAllOccurrences(List<Integer> list, int elementToRemove) {
        list.removeIf(element -> element == elementToRemove);
    }
}
