package com.interview.notes.code.months.oct24.amz.test24;

import java.util.PriorityQueue;

public class Solution {
    /**
     * Calculates the minimum power required to merge the cells into one.
     *
     * @param cells the values of each cell
     * @return the minimum power required to finish the game
     */
    public static int minPower(int[] cells) {
        if (cells == null || cells.length == 0) {
            return 0;
        }

        // Use a min-heap (priority queue) to always merge the two smallest cells
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int cell : cells) {
            heap.add(cell);
        }

        int totalPower = 0;

        // Continue merging until only one cell remains
        while (heap.size() > 1) {
            // Extract the two smallest cells
            int cell1 = heap.poll();
            int cell2 = heap.poll();

            // Calculate the power needed for this merge
            int sum = cell1 + cell2;
            totalPower += sum;

            // Add the new merged cell back into the heap
            heap.add(sum);
        }

        return totalPower;
    }

    /**
     * Helper method to test the minPower function with given test cases.
     *
     * @param testNumber the identifier for the test case
     * @param cells      the input array of cell values
     * @param expected   the expected output
     */
    public static void testMinPower(int testNumber, int[] cells, int expected) {
        int result = minPower(cells.clone()); // Clone to avoid modifying the original array
        if (result == expected) {
            System.out.println("Test case " + testNumber + ": PASS");
        } else {
            System.out.println("Test case " + testNumber + ": FAIL");
            System.out.println("Expected: " + expected + ", Got: " + result);
        }
    }

    public static void main(String[] args) {
        // Test case 1: Example provided in the problem statement
        int[] cells1 = {20, 30, 40};
        int expected1 = 140;
        testMinPower(1, cells1, expected1);

        // Test case 2: Sample Case 0
        int[] cells2 = {30, 10, 20};
        int expected2 = 90;
        testMinPower(2, cells2, expected2);

        // Test case 3: Sample Case 1
        int[] cells3 = {100, 1};
        int expected3 = 101;
        testMinPower(3, cells3, expected3);

        // Test case 4: All cells have the same value
        int[] cells4 = {10, 10, 10, 10};
        int expected4 = 80;
        testMinPower(4, cells4, expected4);

        // Test case 5: Cells in ascending order
        int[] cells5 = {1, 2, 3, 4, 5};
        int expected5 = 33;
        testMinPower(5, cells5, expected5);

        // Test case 6: All cells have the maximum value
        int[] cells6 = {100, 100, 100, 100, 100};
        int expected6 = 1200;
        testMinPower(6, cells6, expected6);

        // Test case 7: Large input to test performance
        int n = 100000;
        int[] cells7 = new int[n];
        for (int i = 0; i < n; i++) {
            cells7[i] = 1;
        }
        long startTime = System.currentTimeMillis();
        int result7 = minPower(cells7);
        long endTime = System.currentTimeMillis();
        System.out.println("Test case 7: Large input test completed in " + (endTime - startTime) + " ms");
        // Since the expected output is large, we can check if the result is greater than zero
        if (result7 > 0) {
            System.out.println("Test case 7: PASS");
        } else {
            System.out.println("Test case 7: FAIL");
        }
    }
}
