package com.interview.notes.code.months.sept24.test6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays { // Main class

    public static void main(String[] args) { // Main method
        List<int[]> lists = Arrays.asList( // Initialize list of arrays
                new int[]{1, 4, 7},
                new int[]{2, 5, 8},
                new int[]{3, 6, 9}
        );

        int[] result = mergeKSortedArrays(lists); // Call merge function
        System.out.println(Arrays.toString(result)); // Print result
    }

    private static int[] mergeKSortedArrays(List<int[]> lists) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));
        int totalLength = 0;

        // Initialize the heap with the first element of each array
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).length > 0) {
                minHeap.offer(new Element(i, 0, lists.get(i)[0])); // Add first element of each array
                totalLength += lists.get(i).length; // Calculate total length
            }
        }

        int[] result = new int[totalLength]; // Result array
        int index = 0;

        // Extract the minimum element and add the next element from the same array
        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll(); // Get smallest element
            result[index++] = current.value; // Add to result

            if (current.index + 1 < lists.get(current.arrayIndex).length) {
                minHeap.offer(new Element(
                        current.arrayIndex,
                        current.index + 1,
                        lists.get(current.arrayIndex)[current.index + 1]
                )); // Add next element of the same array
            }
        }

        return result; // Return merged array
    }

    private static class Element { // Helper class to store array details
        int arrayIndex; // Index of the array
        int index; // Index within the array
        int value; // Value of the element

        Element(int arrayIndex, int index, int value) {
            this.arrayIndex = arrayIndex;
            this.index = index;
            this.value = value;
        }
    }
}
