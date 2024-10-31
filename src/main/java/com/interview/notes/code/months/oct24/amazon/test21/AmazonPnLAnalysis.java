package com.interview.notes.code.months.oct24.amazon.test21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class AmazonPnLAnalysis {

    /**
     * Function to find the maximum number of PnL elements that can be made negative
     * while keeping cumulative sums strictly positive.
     *
     * @param PnL array of positive integers representing profit and loss
     * @return maximum number of PnL elements that can be negative
     */
    public static int getMaxNegativePnL(int[] PnL) {
        long cumulativeSum = PnL[0]; // Initialize cumulative sum with the first month's PnL
        int totalNegatives = 0; // Counter for negative PnL elements
        // Max-heap to store negative PnL elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 1; i < PnL.length; i++) {
            // Assume flipping PnL[i] to negative
            cumulativeSum -= PnL[i];
            maxHeap.add(PnL[i]);
            totalNegatives++;

            // If cumulative sum becomes non-positive, undo flips starting with the largest PnL
            while (cumulativeSum <= 0 && !maxHeap.isEmpty()) {
                int largestPnL = maxHeap.poll();
                cumulativeSum += 2L * largestPnL; // Undo the flip
                totalNegatives--;
            }
        }

        return totalNegatives;
    }

    /**
     * Main method for testing the getMaxNegativePnL function.
     */
    public static void main(String[] args) {
        try {
            // Reading input from STDIN
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(reader.readLine().trim()); // Number of elements in PnL
            int[] PnL = new int[n];

            for (int i = 0; i < n; i++) {
                PnL[i] = Integer.parseInt(reader.readLine().trim());
            }

            // Calling the function and printing the result
            int result = getMaxNegativePnL(PnL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
