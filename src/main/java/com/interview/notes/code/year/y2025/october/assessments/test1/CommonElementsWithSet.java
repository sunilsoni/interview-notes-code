package com.interview.notes.code.year.y2025.october.assessments.test1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonElementsWithSet {

    // Alternative method using Set intersection
    public static List<Integer> findCommonElementsWithSet(Integer[] arr1, Integer[] arr2, Integer[] arr3) {
        // Convert first array to Set
        Set<Integer> set1 = new HashSet<>(Arrays.asList(arr1));

        // Retain only elements that exist in second array
        set1.retainAll(Arrays.asList(arr2));

        // Retain only elements that exist in third array
        set1.retainAll(Arrays.asList(arr3));

        // Convert Set to List
        return set1.stream().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Test cases remain the same as above
        Integer[] arr1 = {1, 2, 3, 4, 5};
        Integer[] arr2 = {2, 3, 4, 5, 6};
        Integer[] arr3 = {3, 4, 5, 6, 7};

        System.out.println("Using Set intersection method:");
        System.out.println("Common elements: " + findCommonElementsWithSet(arr1, arr2, arr3));

        // Performance comparison for large arrays
        Integer[] largeArr1 = new Integer[10000];
        Integer[] largeArr2 = new Integer[10000];
        Integer[] largeArr3 = new Integer[10000];

        for (int i = 0; i < 10000; i++) {
            largeArr1[i] = i;
            largeArr2[i] = i + 5000;
            largeArr3[i] = i + 7500;
        }

    }
}
