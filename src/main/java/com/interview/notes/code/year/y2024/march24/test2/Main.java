package com.interview.notes.code.year.y2024.march24.test2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int getNumPairs(List<Integer> arr, int l, int r) {
        Collections.sort(arr);
        int count = 0;

        for (int i = 0; i < arr.size(); i++) {
            // Find the number of elements that, when added to arr.get(i), are less than or equal to r
            int high = upperBound(arr, i, r - arr.get(i));

            // Find the number of elements that, when added to arr.get(i), are less than l
            int low = lowerBound(arr, i, l - arr.get(i));

            count += high - low;
        }

        return count;
    }

    private static int upperBound(List<Integer> arr, int excludedIndex, int value) {
        int low = excludedIndex + 1;
        int high = arr.size();
        while (low < high) {
            int mid = (high + low) / 2;
            if (arr.get(mid) <= value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private static int lowerBound(List<Integer> arr, int excludedIndex, int value) {
        int low = excludedIndex + 1;
        int high = arr.size();
        while (low < high) {
            int mid = (high + low) / 2;
            if (arr.get(mid) < value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(2, 3, 4, 5);
        System.out.println(getNumPairs(arr, 5, 7)); // Expected output: 4

        arr = Arrays.asList(6, 2, 3);
        System.out.println(getNumPairs(arr, 7, 10)); // Expected output: 2

        arr = Arrays.asList(100, 100);
        System.out.println(getNumPairs(arr, 200, 200)); // Expected output: 1
    }
}
