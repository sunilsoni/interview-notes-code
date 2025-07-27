package com.interview.notes.code.year.y2025.july.common.test9;

import java.io.BufferedReader;                 // for efficient reading from stdin
import java.io.InputStreamReader;              // to wrap System.in in a Reader
import java.util.Arrays;                       // for Arrays.stream(...)
import java.util.stream.IntStream;             // for generating a large test array

public class BinarySearchExample {

    // Performs binary search on a sorted array.
    public static int binarySearch(int[] arr, int target) {
        int left = 0;                           // start index of search range
        int right = arr.length - 1;             // end index of search range

        // continue while there is a valid range to search
        while (left <= right) {
            int mid = left + (right - left) / 2; // compute middle index safely

            if (arr[mid] == target) {           // if middle element equals target
                return mid;                     // return its index
            } else if (arr[mid] < target) {     // if middle is less than target
                left = mid + 1;                // search in the right half
            } else {                            // otherwise, middle is greater
                right = mid - 1;               // search in the left half
            }
        }

        return -1;                              // target not found
    }

    // Main method: runs tests, then processes actual input.
    public static void main(String[] args) throws Exception {
        // --- Test Harness ---
        int[][] testArrays = {
            {1, 2, 3, 4, 5},                   // simple sorted array
            {42},                              // single-element array
            {},                                // empty array
            IntStream.range(0, 1_000_000).toArray() // large array 0..999,999
        };

        int[] testTargets     = {3, 42, 10, 999_999};      // targets to find
        int[] expectedResults = {2, 0, -1, 999_999};       // expected indices

        // run each test and print PASS/FAIL
        for (int i = 0; i < testArrays.length; i++) {
            int result = binarySearch(testArrays[i], testTargets[i]); // execute search
            String status = (result == expectedResults[i]) ? "PASS" : "FAIL";
            System.out.println("Test " + (i + 1) + ": " + status);    // output result
        }

        // --- Actual Input Handling ---
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // reader setup

        int n = Integer.parseInt(reader.readLine().trim());       // read N (array size)

        // read next line, split by spaces, parse to ints, collect into array
        int[] arr = Arrays.stream(reader.readLine().trim().split("\\s+"))
                          .mapToInt(Integer::parseInt)
                          .toArray();

        int x = Integer.parseInt(reader.readLine().trim());       // read target value X

        int index = binarySearch(arr, x);                         // find target index
        System.out.println(index);                                // print index or -1
    }
}