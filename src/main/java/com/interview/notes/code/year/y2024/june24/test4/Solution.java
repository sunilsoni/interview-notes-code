package com.interview.notes.code.year.y2024.june24.test4;

import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {
        // Example for twoSum method
        System.out.println("Two Sum Problem:");
        int[] twoSumArray = {5, 2, -3, 3, 4};
        twoSum(twoSumArray, 5);

        System.out.println("");

        // Example for sortArr method
        System.out.println("Sorted 0s, 1s, and 2s Problem:");
        int[] arrayToSort = {0, 1, 2, 0, 1, 2};
        sortArr(arrayToSort);
    }

    public static void twoSum(int[] arr, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = sum - arr[i];
            if (map.containsKey(complement)) {
                System.out.println("Index1: " + map.get(complement) + ", Index2: " + i);
                return; // Assuming only one solution is needed
            }
            map.put(arr[i], i);
        }
        System.out.println("No two sum solution");
    }

    public static void sortArr(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1, temp;
        while (mid <= high) {
            switch (arr[mid]) {
                case 0:
                    temp = arr[low];
                    arr[low] = arr[mid];
                    arr[mid] = temp;
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    temp = arr[mid];
                    arr[mid] = arr[high];
                    arr[high] = temp;
                    high--;
                    break;
            }
        }
        System.out.print("Sorted Array: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(); // for a new line after printing the array
    }
}
