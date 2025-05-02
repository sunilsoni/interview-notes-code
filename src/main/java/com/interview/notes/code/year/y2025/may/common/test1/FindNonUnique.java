package com.interview.notes.code.year.y2025.may.common.test1;

import java.util.*;

public class FindNonUnique {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 4, 5, 6, 7, 8, 8, 8};
        
        // Method 1: Using Set
        findNonUniqueUsingSet(arr);
        
        // Method 2: Using List
        findNonUniqueUsingList(arr);
    }

    // Method 1: Using Set
    public static void findNonUniqueUsingSet(int[] arr) {
        Set<Integer> uniqueSet = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (int num : arr) {
            // If number can't be added to uniqueSet, it's a duplicate
            if (!uniqueSet.add(num)) {
                duplicates.add(num);
            }
        }

        System.out.println("Non-unique elements (using Set): " + duplicates);
        System.out.println(" -unique elements (using Set): " + uniqueSet);

    }

    // Method 2: Using List
    public static void findNonUniqueUsingList(int[] arr) {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> duplicates = new ArrayList<>();

        for (int num : arr) {
            if (numbers.contains(num)) {
                if (!duplicates.contains(num)) {
                    duplicates.add(num);
                }
            } else {
                numbers.add(num);
            }
        }

        System.out.println("Non-unique elements (using List): " + duplicates);
    }
}
