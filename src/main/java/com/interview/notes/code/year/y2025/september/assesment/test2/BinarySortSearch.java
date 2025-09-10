package com.interview.notes.code.year.y2025.september.assesment.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinarySortSearch {

    // Binary Search method
    public static boolean binarySearch(List<Integer> sortedList, int key) {
        int left = 0;
        int right = sortedList.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (sortedList.get(mid) == key) {
                return true; // Key found
            } else if (sortedList.get(mid) < key) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        return false; // Not found
    }

    public static void main(String[] args) {
        // Input unsorted list
        List<Integer> numbers = Arrays.asList(45, 12, 78, 34, 23, 56, 89, 1, 67);

        // Sort using Java 8 Stream
        List<Integer> sortedList = numbers.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Sorted List: " + sortedList);

        // Example keys
        int key1 = 34;
        int key2 = 99;

        // Perform binary search
        System.out.println("Is " + key1 + " present? " + (binarySearch(sortedList, key1) ? "YES" : "NO"));
        System.out.println("Is " + key2 + " present? " + (binarySearch(sortedList, key2) ? "YES" : "NO"));
    }
}