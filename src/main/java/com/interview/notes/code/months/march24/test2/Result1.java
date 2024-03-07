package com.interview.notes.code.months.march24.test2;

import java.util.ArrayList;
import java.util.List;

class Result1 {
    public static List<Integer> kthOccurrence(int X, List<Integer> arr, List<Integer> query_values) {
        List<Integer> results = new ArrayList<>();
        for (int query : query_values) {
            int count = 0;
            int index = -2; // start from -2 because we need 1-based index
            for (int num : arr) {
                if (num == X) {
                    count++;
                }
                if (count == query) {
                    index = arr.indexOf(num);
                    break;
                }
            }
            results.add(index + 1); // convert to 1-based index
        }
        return results;
    }

    public static void main(String[] args) {
        List<Integer> arr = List.of(1, 2, 20, 8, 8, 1, 2, 5, 8, 0);
        int X = 8;
        List<Integer> query_values = List.of(100, 4, 2);
        System.out.println(kthOccurrence(X, arr, query_values)); // Should output [-1, -1, 5]

        arr = List.of(9, 8, 9, 9);
        X = 9;
        query_values = List.of(7, 3, 7, 6);
        System.out.println(kthOccurrence(X, arr, query_values)); // Should output [-1, 4, -1, -1]
    }
}
