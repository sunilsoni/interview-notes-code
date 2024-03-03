package com.interview.notes.code.months.march24.test2;

import java.util.List;
import java.util.ArrayList;

class Result2 {

    public static List<Integer> kthOccurrence(int X, List<Integer> arr, List<Integer> query_values) {
        List<Integer> results = new ArrayList<>();

        for (int k : query_values) {
            int occurrence = 0;
            int index = -1;
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i) == X) {
                    occurrence++;
                    if (occurrence == k) {
                        index = i + 1; // 1-based index
                        break;
                    }
                }
            }
            results.add(index);
        }

        return results;
    }

    public static void main(String[] args) {
        List<Integer> arr = List.of(1, 2, 20, 8, 1, 2, 5, 8, 0);
        int X = 8;
        List<Integer> query_values = List.of(100, 2, 1);
        List<Integer> results = kthOccurrence(X, arr, query_values);
        for (int result : results) {
            System.out.println(result);
        }
        
        // Additional test cases based on the given examples
        arr = List.of(9, 8, 9, 9);
        X = 9;
        query_values = List.of(7, 3, 7, 6);
        results = kthOccurrence(X, arr, query_values);
        for (int result : results) {
            System.out.println(result);
        }
    }
}
