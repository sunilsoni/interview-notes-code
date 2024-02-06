package com.interview.notes.code.months.jan24.test8;

import java.util.*;

class Solution {
    public static void main(final String[] args) {
        runTests();
    }

    public static void runTests() {
        int[] testCase1 = {2, 5, 2, 6, 8, 5, 8, 8};
        int[] expectedResponse = {8, 8, 8, 2, 2, 5, 5, 6};
        
        int[] actualResponse = new Solution().sortByOccurrence(testCase1);
        
        if (!Arrays.equals(expectedResponse, actualResponse)) {
            System.out.println("Test Case Failed.");
            return;
        }
        System.out.println("Test Case Passed.");
    }

    public int[] sortByOccurrence(final int[] reqArray) {
        // Count occurrences of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : reqArray) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Convert array to a list for sorting
        List<Integer> list = new ArrayList<>();
        for (int num : reqArray) {
            list.add(num);
        }

        // Custom comparator to sort by frequency, then by order
        Comparator<Integer> comp = (a, b) -> {
            int freqCompare = frequencyMap.get(b) - frequencyMap.get(a);
            if (freqCompare == 0) {
                return Integer.compare(Arrays.asList(reqArray).indexOf(a), Arrays.asList(reqArray).indexOf(b));
            }
            return freqCompare;
        };

        // Sort the list
        Collections.sort(list, comp);

        // Convert back to array
        for (int i = 0; i < reqArray.length; i++) {
            reqArray[i] = list.get(i);
        }

        return reqArray;
    }
}
