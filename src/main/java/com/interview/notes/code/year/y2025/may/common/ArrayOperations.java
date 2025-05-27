package com.interview.notes.code.year.y2025.may.common;

import java.util.*;
import java.util.stream.*;

public class ArrayOperations {
    public static void main(String[] args) {
        // Test Case 1: Basic arrays
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {5, 6, 7, 8};
        int[] arr3 = {7, 8, 9, 10};

        // Print results for test case 1
        System.out.println("=== Test Case 1 ===");
        printArrayOperations(arr1, arr2, arr3);

        // Test Case 2: Arrays with duplicates
        int[] test1 = {1, 1, 2, 3};
        int[] test2 = {2, 3, 3, 4};
        int[] test3 = {3, 4, 4, 5};

        System.out.println("\n=== Test Case 2 ===");
        printArrayOperations(test1, test2, test3);
    }

    public static void printArrayOperations(int[] arr1, int[] arr2, int[] arr3) {
        // Convert arrays to List for easier operations
        List<Integer> list1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(arr2).boxed().collect(Collectors.toList());
        List<Integer> list3 = Arrays.stream(arr3).boxed().collect(Collectors.toList());

        // Combine all arrays into one list
        List<Integer> combined = new ArrayList<>();
        combined.addAll(list1);
        combined.addAll(list2);
        combined.addAll(list3);

        // Get unique elements using Set
        Set<Integer> uniqueElements = new HashSet<>(combined);

        // Find elements that appear only once (difference)
        Map<Integer, Long> frequency = combined.stream()
            .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        
        List<Integer> difference = frequency.entrySet().stream()
            .filter(e -> e.getValue() == 1)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        // Print results
        System.out.println("Unique elements: " + uniqueElements);
        System.out.println("Elements appearing once: " + difference);
        System.out.println("Combined array: " + combined);
    }
}
