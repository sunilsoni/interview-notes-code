package com.interview.notes.code.months.year2023.dec23.test5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UniqueElementsWithoutDistinct {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 2, 5, 6, 1};

        // Using a Set to collect unique elements
        Set<Integer> uniqueElementsSet = new HashSet<>();
        for (int num : arr) {
            uniqueElementsSet.add(num);
        }

        // Convert the Set to an int array
        int[] uniqueElements = uniqueElementsSet.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        System.out.println("Unique elements in the array: " + Arrays.toString(uniqueElements));
    }
}
