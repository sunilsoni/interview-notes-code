package com.interview.notes.code.months.year2023.dec23.test6;

import java.util.*;

/**
 * Find Kth smallest element in a two dimensional matrix.
 * <p>
 * Input: Two dimensional array of integers and integer k
 */
public class KthSmallestUniqueInMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {10, 20, 30},
                {5, 15, 25},
                {10, 5, 15}
        };
        int k = 4; // Example: Find the 4th smallest unique element
        int result = findKthSmallestUnique(matrix, k);
        System.out.println("The " + k + "th smallest unique element is: " + result);
    }

    public static int findKthSmallestUnique(int[][] matrix, int k) {
        Set<Integer> uniqueElements = new HashSet<>();

        // Flatten the matrix and remove duplicates
        for (int[] row : matrix) {
            for (int element : row) {
                uniqueElements.add(element);
            }
        }

        // Check if k is valid
        if (k > uniqueElements.size()) {
            throw new IllegalArgumentException("k is larger than the number of unique elements in the matrix");
        }

        // Convert Set to List and sort
        List<Integer> sortedList = new ArrayList<>(uniqueElements);
        Collections.sort(sortedList);

        // Return the kth smallest element
        return sortedList.get(k - 1);
    }
}
