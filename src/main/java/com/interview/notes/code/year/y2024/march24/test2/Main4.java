package com.interview.notes.code.year.y2024.march24.test2;

import java.util.Collections;
import java.util.List;

public class Main4 {

    public static int getNumPairs(List<Integer> arr, int l, int r) {
        // Sort the array first to make the two-pointer technique viable.
        Collections.sort(arr);

        int count = 0;
        int left = 0;
        int right = 1;

        while (left < arr.size() - 1) {
            // Move the right pointer to find a valid pair
            while (right < arr.size() && arr.get(left) + arr.get(right) <= r) {
                if (arr.get(left) + arr.get(right) >= l) {
                    count++;
                }
                right++;
            }
            // Move the left pointer by one and reset the right pointer
            left++;
            right = left + 1;
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
