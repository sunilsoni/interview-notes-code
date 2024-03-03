package com.interview.notes.code.months.march24.test2;

import java.util.PriorityQueue;

public class KthSmallestInMatrix {
    
    // Method to find the kth smallest element in the matrix
    public static int findKthSmallest(int[][] matrix, int k) {
        // Priority queue to store the elements of the matrix
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // Add all elements of the matrix to the min heap
        for (int[] row : matrix) {
            for (int element : row) {
                minHeap.add(element);
            }
        }
        
        // Extract the minimum element k-1 times
        while (k-- > 1 && !minHeap.isEmpty()) {
            minHeap.poll();
        }
        
        // The top element of the min heap is the kth smallest element
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {10, 20, 30, 40},
            {15, 25, 35, 45},
            {24, 29, 37, 48},
            {32, 33, 39, 50},
        };
        int k = 7; // Example: Find the 7th smallest element
        
        System.out.println("The " + k + "th smallest element is " + findKthSmallest(matrix, k));
    }
}
