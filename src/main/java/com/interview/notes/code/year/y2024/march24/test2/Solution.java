package com.interview.notes.code.year.y2024.march24.test2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public static List<Integer> kthOccurrence(int X, int[] arr, int[] query_values) {
        Map<Integer, List<Integer>> indices = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!indices.containsKey(arr[i])) {
                indices.put(arr[i], new ArrayList<>());
            }
            indices.get(arr[i]).add(i + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < query_values.length; i++) {
            if (!indices.containsKey(X) || query_values[i] > indices.get(X).size()) {
                result.add(-1);
            } else {
                result.add(indices.get(X).get(query_values[i] - 1));
            }
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
