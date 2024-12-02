package com.interview.notes.code.year.y2024.march24.test2;

import java.util.*;

public class KthOccurrence {
    public static int[] kthOccurrence(int X, int[] arr, int[] queryValues) {
        Map<Integer, List<Integer>> occurrenceMap = new HashMap<>();
        int n = arr.length;

        // Create a map to store the indices of each occurrence of X
        for (int i = 0; i < n; i++) {
            occurrenceMap.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i + 1);
        }

        int q = queryValues.length;
        int[] result = new int[q];

        // Process each query
        for (int i = 0; i < q; i++) {
            int k = queryValues[i];
            List<Integer> occurrences = occurrenceMap.getOrDefault(X, Collections.emptyList());

            if (k <= occurrences.size()) {
                // kth occurrence exists
                result[i] = occurrences.get(k - 1);
            } else {
                // kth occurrence does not exist
                result[i] = -1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example usage
        int X = 8;
        int[] arr = {1, 2, 20, 8, 8, 1, 2, 5, 8, 0};
        int[] queryValues = {100, 2, 1, 3, 4};

        int[] output = kthOccurrence(X, arr, queryValues);
        System.out.println(Arrays.toString(output)); // Output: [-1, -1, 5]
    }
}
