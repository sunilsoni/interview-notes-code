package com.interview.notes.code.months.sept24.meta.test2;

import java.util.PriorityQueue;

public class KthSmallestInSortedArrays {

    // Class to represent an element in the heap
    static class Element {
        int value;  // The value of the element
        int arrayIndex;  // The index of the array it belongs to
        int elementIndex;  // The index of the element in its array

        public Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static int findKthSmallest(int[][] arrays, int k) {
        // Min-heap to store elements from the arrays
        PriorityQueue<Element> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);

        // Step 1: Initialize the heap with the first element of each array
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                // Add the first element of each array to the heap
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }

        // Step 2: Extract the smallest element from the heap k times
        int count = 0;
        int result = -1;
        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            count++;

            // If we have extracted the k-th smallest element, return it
            if (count == k) {
                result = current.value;
                break;
            }

            // Step 3: If there are more elements in the current array, add the next element to the heap
            if (current.elementIndex + 1 < arrays[current.arrayIndex].length) {
                int nextValue = arrays[current.arrayIndex][current.elementIndex + 1];
                minHeap.offer(new Element(nextValue, current.arrayIndex, current.elementIndex + 1));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arrays = {
            {1, 3, 5, 7},
            {2, 4, 6},
            {8, 9}
        };
        int k = 5;
        int result = findKthSmallest(arrays, k);
        System.out.println("The " + k + "-th smallest element is: " + result);
    }
}
