package com.interview.notes.code.months.march24.test2;

import java.util.Arrays;

public class Main5 {

    public static int getNumPairs(int[] arr, int l, int r) {
        Arrays.sort(arr);
        int count = 0;
        
        for (int i = 0; i < arr.length; i++) {
            // Find the right boundary where sum is just greater than r
            int j = upperBound(arr, i, arr.length, r - arr[i]);
            
            // Find the left boundary where sum is just less than l
            int k = lowerBound(arr, i, arr.length, l - arr[i]);
            
            count += j - k;
        }
        
        return count;
    }
    
    // Find the index of the first element in the range [first, last) that is greater than value
    private static int upperBound(int[] arr, int first, int last, int value) {
        while (first < last) {
            int mid = first + (last - first) / 2;
            if (value >= arr[mid]) {
                first = mid + 1;
            } else {
                last = mid;
            }
        }
        return first;
    }
    
    // Find the index of the first element in the range [first, last) that is not less than value
    private static int lowerBound(int[] arr, int first, int last, int value) {
        while (first < last) {
            int mid = first + (last - first) / 2;
            if (value <= arr[mid]) {
                last = mid;
            } else {
                first = mid + 1;
            }
        }
        return first;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5};
        System.out.println(getNumPairs(arr, 5, 7)); // Expected output: 4
        
        arr = new int[]{6, 2, 3};
        System.out.println(getNumPairs(arr, 7, 10)); // Expected output: 2
        
        arr = new int[]{100, 100};
        System.out.println(getNumPairs(arr, 200, 200)); // Expected output: 1
    }
}
