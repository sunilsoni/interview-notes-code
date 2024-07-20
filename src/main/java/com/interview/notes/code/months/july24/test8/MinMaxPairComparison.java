package com.interview.notes.code.months.july24.test8;

public class MinMaxPairComparison {
    public static void main(String[] args) {
        int[] arr = {3, 5, 4, 1, 9}; // Your array

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Process pairs
        for (int i = 0; i < arr.length - 1; i += 2) {
            int localMin, localMax;
            if (arr[i] < arr[i + 1]) {
                localMin = arr[i];
                localMax = arr[i + 1];
            } else {
                localMin = arr[i + 1];
                localMax = arr[i];
            }
            if (localMin < min) min = localMin;
            if (localMax > max) max = localMax;
        }

        // If the array has an odd number of elements, process the last one separately
        if (arr.length % 2 != 0) {
            min = Math.min(min, arr[arr.length - 1]);
            max = Math.max(max, arr[arr.length - 1]);
        }

        // Output the results
        System.out.println("Minimum number: " + min);
        System.out.println("Maximum number: " + max);
    }
}
