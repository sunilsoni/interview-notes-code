package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommonElementsFinder {

    public static void findCommonElements(int[] array1, int[] array2) {
        // Convert second array into List for easy lookup
        List<Integer> list2 = Arrays.stream(array2).boxed().collect(Collectors.toList());

        // Find common elements using Stream API
        List<Integer> commonElements = Arrays.stream(array1)   // stream of array1
                                             .boxed()          // convert int -> Integer
                                             .filter(list2::contains) // keep only elements also in list2
                                             .collect(Collectors.toList()); // collect into list

        // Print the result
        System.out.println("Common elements: " + commonElements);
    }

    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {3, 4, 5, 6, 7};

        findCommonElements(array1, array2);
    }
}