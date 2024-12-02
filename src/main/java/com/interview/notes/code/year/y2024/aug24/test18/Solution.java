package com.interview.notes.code.year.y2024.aug24.test18;

import java.util.Arrays;
import java.util.List;

public class Solution {

    public static List<Integer> performOperations(List<Integer> arr, List<List<Integer>> operations) {
        for (List<Integer> operation : operations) {
            int start = operation.get(0);
            int end = operation.get(1);
            reverseSubarray(arr, start, end);
        }
        return arr;
    }

    private static void reverseSubarray(List<Integer> arr, int start, int end) {
        while (start < end) {
            int temp = arr.get(start);
            arr.set(start, arr.get(end));
            arr.set(end, temp);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> arr1 = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        List<List<Integer>> operations1 = Arrays.asList(
                Arrays.asList(0, 9),
                Arrays.asList(4, 5),
                Arrays.asList(3, 6),
                Arrays.asList(2, 7),
                Arrays.asList(1, 8),
                Arrays.asList(0, 9)
        );
        System.out.println("Test Case 1 Result: " + performOperations(arr1, operations1));

        // Test Case 2
        List<Integer> arr2 = Arrays.asList(1, 2, 3);
        List<List<Integer>> operations2 = Arrays.asList(
                Arrays.asList(0, 2),
                Arrays.asList(1, 2),
                Arrays.asList(0, 2)
        );
        System.out.println("Test Case 2 Result: " + performOperations(arr2, operations2));

        // Test Case 3
        List<Integer> arr3 = Arrays.asList(5, 2, 5, 1);
        List<List<Integer>> operations3 = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(1, 1)
        );
        System.out.println("Test Case 3 Result: " + performOperations(arr3, operations3));
    }
}
