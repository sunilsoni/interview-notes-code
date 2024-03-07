package com.interview.notes.code.months.march24.test2;

import java.util.Collections;
import java.util.List;

public class Main3 {

    public static int getNumPairs(List<Integer> arr, int l, int r) {
        int count = 0;
        // Sort the array to make use of binary search for pairs finding.
        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size() && arr.get(i) + arr.get(j) <= r; j++) {
                if (arr.get(i) + arr.get(j) >= l) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<Integer> arr = List.of(2, 3, 4, 5);
        System.out.println(getNumPairs(arr, 5, 7)); // Expected output: 4

        arr = List.of(6, 2, 3);
        System.out.println(getNumPairs(arr, 7, 10)); // Expected output: 2

        arr = List.of(100, 100);
        System.out.println(getNumPairs(arr, 200, 200)); // Expected output: 1
    }
}
