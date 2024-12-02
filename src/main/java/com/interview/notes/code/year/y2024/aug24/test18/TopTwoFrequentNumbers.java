package com.interview.notes.code.year.y2024.aug24.test18;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopTwoFrequentNumbers {
    public static int[] findTopTwoFrequent(int[] arr) {
        // Count the frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Create a priority queue to store numbers based on their frequency
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) ->
                frequencyMap.get(b).compareTo(frequencyMap.get(a)));

        pq.addAll(frequencyMap.keySet());

        // Get the top two frequent numbers
        int[] result = new int[2];
        for (int i = 0; i < 2 && !pq.isEmpty(); i++) {
            result[i] = pq.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        int[] arr1 = {1, 2, 5, 5, 5, 5, 2, 2, 3};
        int[] result1 = findTopTwoFrequent(arr1);
        System.out.println("Example 1: " + Arrays.toString(result1));

        // Example 2
        int[] arr2 = {3, 3, 3, 1, 1, 1, 2, 2, 4};
        int[] result2 = findTopTwoFrequent(arr2);
        System.out.println("Example 2: " + Arrays.toString(result2));

        // Example 3 (with ties)
        int[] arr3 = {1, 1, 2, 2, 3, 3, 4, 4};
        int[] result3 = findTopTwoFrequent(arr3);
        System.out.println("Example 3: " + Arrays.toString(result3));

        // Example 4 (with a single unique number)
        int[] arr4 = {5, 5, 5, 5, 5};
        int[] result4 = findTopTwoFrequent(arr4);
        System.out.println("Example 4: " + Arrays.toString(result4));

        // Example 5 (empty array)
        int[] arr5 = {};
        int[] result5 = findTopTwoFrequent(arr5);
        System.out.println("Example 5: " + Arrays.toString(result5));
    }
}
