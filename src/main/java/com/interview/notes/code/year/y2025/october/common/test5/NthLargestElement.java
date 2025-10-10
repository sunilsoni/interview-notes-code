package com.interview.notes.code.year.y2025.october.common.test5;

import java.util.Arrays;
import java.util.Random;

public class NthLargestElement {
    public static int findNthLargest(int[] arr, int n) {
        Arrays.sort(arr);
        return arr[arr.length - n];
    }

    public static void main(String[] args) {
        int[][] testArrays = {
            {3, 1, 5, 2, 4},              // Normal
            {10, 20, 20, 40, 30},         // Duplicates
            {5, 5, 5, 5, 5},              // All same
            {1, 2},                       // Small array
            new Random().ints(1000000, 1, 10000000).toArray() // Large data
        };
        int[] nValues = {2, 3, 1, 1, 5};
        boolean allPass = true;

        for (int i = 0; i < testArrays.length; i++) {
            int result = findNthLargest(testArrays[i], nValues[i]);
            System.out.printf("Test %d -> N=%d, Result=%d\n", i+1, nValues[i], result);
            if (testArrays[i].length < nValues[i]) {
                allPass = false;
                System.out.println("FAIL: N > array length");
            }
        }

        System.out.println(allPass ? "PASS" : "FAIL");
    }
}