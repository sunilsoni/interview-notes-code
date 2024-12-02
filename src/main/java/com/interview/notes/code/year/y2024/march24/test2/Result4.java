package com.interview.notes.code.year.y2024.march24.test2;

import java.util.ArrayList;
import java.util.List;

class Result4 {

    public static List<Integer> kthOccurrence(int X, List<Integer> arr, List<Integer> query_values) {
        List<Integer> result = new ArrayList<>();
        for (int targetOccurrence : query_values) {
            int occurrenceCount = 0;
            int position = -1; // Initialize with -1, which is the value we return if X is not found
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).equals(X)) {
                    occurrenceCount++;
                    if (occurrenceCount == targetOccurrence) {
                        position = i + 1; // Convert zero-based index to one-based index
                        break;
                    }
                }
            }
            result.add(position);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> arr = List.of(1, 2, 20, 8, 8, 1, 2, 5, 8, 0);
        int X = 8;
        List<Integer> query_values = List.of(100, 4, 2);
        System.out.println(kthOccurrence(X, arr, query_values)); // Expected: [-1, -1, 5]

        arr = List.of(9, 8, 9, 9);
        X = 9;
        query_values = List.of(7, 3, 7, 6);
        System.out.println(kthOccurrence(X, arr, query_values)); // Expected: [-1, 4, -1, -1]
    }
}
