package com.interview.notes.code.year.y2024.aug24.test20;

import java.util.*;

public class SortByOccurrence {
    public static int[] sortByOccurrence(int[] arr) {
        // Count occurrences of each number
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Create a list of unique numbers
        List<Integer> uniqueNumbers = new ArrayList<>(countMap.keySet());

        // Sort the unique numbers based on their occurrences and value
        Collections.sort(uniqueNumbers, (a, b) -> {
            int countCompare = countMap.get(a).compareTo(countMap.get(b));
            if (countCompare == 0) {
                return Integer.compare(a, b);
            }
            return countCompare;
        });

        // Create the result array
        int[] result = new int[arr.length];
        int index = 0;
        for (int num : uniqueNumbers) {
            int count = countMap.get(num);
            for (int i = 0; i < count; i++) {
                result[index++] = num;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        int[] arr1 = {4, 4, 2, 2, 2, 2, 3, 3, 1, 1, 6, 7, 5};
        System.out.println("Example 1 Input: " + Arrays.toString(arr1));
        System.out.println("Example 1 Output: " + Arrays.toString(sortByOccurrence(arr1)));

        // Example 2
        int[] arr2 = {3, 3, 3, 3, 2, 2, 1, 1, 1, 2, 2, 2};
        System.out.println("\nExample 2 Input: " + Arrays.toString(arr2));
        System.out.println("Example 2 Output: " + Arrays.toString(sortByOccurrence(arr2)));

        // Example 3 (new)
        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("\nExample 3 Input: " + Arrays.toString(arr3));
        System.out.println("Example 3 Output: " + Arrays.toString(sortByOccurrence(arr3)));

        // Example 4 (new)
        int[] arr4 = {5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 1};
        System.out.println("\nExample 4 Input: " + Arrays.toString(arr4));
        System.out.println("Example 4 Output: " + Arrays.toString(sortByOccurrence(arr4)));
    }
}
