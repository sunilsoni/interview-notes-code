package com.interview.notes.code.months.year2023.Aug23.test1;

import java.util.PriorityQueue;

public class MatrixElement {
    int value;
    int row;
    int col;

    public MatrixElement(int value, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
    }
}

class KthSmallestElement {
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length; // Get the number of rows
        PriorityQueue<MatrixElement> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);

        // Step 1: Insert the first element of each row into the min-heap
        for (int i = 0; i < n; i++) {
            minHeap.offer(new MatrixElement(matrix[i][0], i, 0));
        }

        // Step 2: Remove the smallest element and insert the next element from the same row
        int count = 0, result = 0;
        while (!minHeap.isEmpty()) {
            MatrixElement element = minHeap.poll();
            result = element.value;
            count++;

            if (count == k) // Found the Kth smallest element
                return result;

            int nextCol = element.col + 1;
            if (nextCol < n) {
                minHeap.offer(new MatrixElement(matrix[element.row][nextCol], element.row, nextCol));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k = 8;
        System.out.println(kthSmallest(matrix, k)); // Output: 13
    }
}
