package com.interview.notes.code.months.nov23.test6.glider;

import java.util.*;

class Outcome1 {
    // Helper function to find the pivot
    static   int findPivot (List < Integer > arr,int low, int high){
        if (high < low) return -1;
        if (high == low) return low;

        int mid = low + (high - low) / 2;
        if (mid < high && arr.get(mid) > arr.get(mid + 1)) return mid;
        if (mid > low && arr.get(mid) < arr.get(mid - 1)) return (mid - 1);
        if (arr.get(low) >= arr.get(mid)) return findPivot(arr, low, mid - 1);
        return findPivot(arr, mid + 1, high);
    }
    public static int solve(List<Integer> ar, int K) {
        // Main search function that uses the above two helper functions
        int n = ar.size();
        int pivot = findPivot(ar, 0, n - 1);

        if (pivot == -1) return binarySearch(ar, 0, n - 1, K);
        if (ar.get(pivot) == K) return pivot;
        if (ar.get(0) <= K) return binarySearch(ar, 0, pivot - 1, K);
        return binarySearch(ar, pivot + 1, n - 1, K);
    }
    // Helper function for standard binary search
    static int binarySearch (List < Integer > arr,int low, int high, int key){
        if (high < low) return -1;

        int mid = low + (high - low) / 2;
        if (key == arr.get(mid)) return mid;
        if (key > arr.get(mid)) return binarySearch(arr, (mid + 1), high, key);
        return binarySearch(arr, low, (mid - 1), key);
    }


    // Example use of the solve function
    public static void main(String[] args) {
        List<Integer> exampleList1 = Arrays.asList(3, 4, 5, 6, 7, 10, 2);
        int result1 = solve(exampleList1, 10);
        
        List<Integer> exampleList2 = Arrays.asList(20, 33, 44, 1);
        int result2 = solve(exampleList2, 11);
        
        System.out.println("Example 1 Result: " + result1);
        System.out.println("Example 2 Result: " + result2);
    }
}
