package com.interview.notes.code.year.y2024.april24.test6;

import java.util.*;

class Result {
    public static int findMedianOfSubarrayUniqueness(List<Integer> arr) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        List<Integer> uniqueness = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            frequencyMap.clear();
            int uniqueCount = 0;
            for (int j = i; j < arr.size(); j++) {
                frequencyMap.put(arr.get(j), frequencyMap.getOrDefault(arr.get(j), 0) + 1);
                if (frequencyMap.get(arr.get(j)) == 1) {
                    uniqueCount++;
                } else if (frequencyMap.get(arr.get(j)) == 2) {
                    uniqueCount--;
                }
                uniqueness.add(uniqueCount);
            }
        }

        // Find median using Quickselect
        int n = uniqueness.size();
        int k = (n - 1) / 2; // index of the median element
        return quickselect(uniqueness, 0, n - 1, k);
    }

    private static int quickselect(List<Integer> list, int first, int last, int k) {
        if (first <= last) {
            int pivotIndex = partition(list, first, last);
            if (pivotIndex == k) {
                return list.get(pivotIndex);
            } else if (pivotIndex > k) {
                return quickselect(list, first, pivotIndex - 1, k);
            } else {
                return quickselect(list, pivotIndex + 1, last, k);
            }
        }
        return Integer.MAX_VALUE; // This will never happen if k is valid
    }

    private static int partition(List<Integer> list, int first, int last) {
        int pivot = list.get(last);
        int i = first - 1;
        for (int j = first; j < last; j++) {
            if (list.get(j) <= pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, last);
        return i + 1;
    }

    public static void main(String[] args) {
        // Example test case 1
        List<Integer> arr1 = Arrays.asList(1, 1);
        System.out.println(findMedianOfSubarrayUniqueness(arr1)); // Output should be 1

        // Example test case 2
        List<Integer> arr2 = Arrays.asList(1, 2, 3);
        System.out.println(findMedianOfSubarrayUniqueness(arr2)); // Output should be 1

        // Additional test cases
        // ... Add more test cases as needed

        // Large input test case
        List<Integer> largeInput = new ArrayList<>(Collections.nCopies(100000, 1));
        System.out.println(findMedianOfSubarrayUniqueness(largeInput)); // Should not cause OOM
    }
}
