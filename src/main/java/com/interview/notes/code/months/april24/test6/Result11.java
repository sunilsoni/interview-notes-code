package com.interview.notes.code.months.april24.test6;

import java.util.*;

class Result11 {
    public static int findMedianOfSubarrayUniqueness(List<Integer> arr) {
        List<Integer> uniqueness = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            Set<Integer> uniqueElements = new HashSet<>();
            for (int j = i; j < arr.size(); j++) {
                uniqueElements.add(arr.get(j));
                uniqueness.add(uniqueElements.size());
            }
        }
        Collections.sort(uniqueness);
        int mid = uniqueness.size() / 2;
        return uniqueness.size() % 2 != 0 ? uniqueness.get(mid) : Math.min(uniqueness.get(mid - 1), uniqueness.get(mid));
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
    }
}
