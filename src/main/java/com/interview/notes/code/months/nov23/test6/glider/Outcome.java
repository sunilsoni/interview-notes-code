package com.interview.notes.code.months.nov23.test6.glider;

import java.util.*;

/**
 *
 Organized Shuffle

 You are given a sorted-then-rotated array (left/right
 shifted by some unknown value) ar[ ] and an
 element K.
 Find the index of the given element Kin the array ar[
 ] in O(log n) time. If the element does not exist in
 the array, print -1.
 Note:
 •  The array has no duplicate elements.
 •  Indexing starts from 0.
 Input
 The first line of input contains an integer N,
 representing the size of the array.
 The second line of input contains N space-separated
 integers, representing the array elements.
 The third line of input contains an integer K,
 representing the element to be searched in the
 array.
 Output
 Print the index of the element found in the array. If
 the element is not present, then print -1.


 constraints
 1 SNS 100005
 0<ar[i]s 10000005
 1 <KS 100005
 Example #1
 Input
 Output
 5
 4
 20 33 44 1
 11
 Explanation: 10 is present at index 5.
 Example #2
 Input
 Output
 -1
 Explanation: 11 does not exist in the given array.

 */
class Outcome {

    //Not working
    public static int solve(List<Integer> ar, int K) {
        int left = 0;
        int right = ar.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (ar.get(mid) == K) {
                return mid;
            }

            // Check if the left half is sorted
            if (ar.get(left) <= ar.get(mid)) {
                if (K >= ar.get(left) && K < ar.get(mid)) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // The right half must be sorted
                if (K > ar.get(mid) && K <= ar.get(right)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1; // Element not found
    }

    public static int solve2(List<Integer> ar, int K) {
        int left = 0;
        int right = ar.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (ar.get(mid) == K) {
                return mid;
            }

            // If left part is sorted
            if (ar.get(left) <= ar.get(mid)) {
                if (K >= ar.get(left) && K < ar.get(mid)) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // If right part is sorted
            else {
                if (K > ar.get(mid) && K <= ar.get(right)) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1; // Element not found
    }

    public static void main(String[] args) {
        List<Integer> ar = Arrays.asList(3, 4, 5, 6, 7, 10, 2);
        int K = 10;
        System.out.println(solve(ar, K)); // Should output 5

        ar = Arrays.asList(20, 33, 44, 1);
        K = 11;
        System.out.println(solve(ar, K)); // Should output -1

        ar = Arrays.asList(3, 5, 1, 2);
        K = 6;
        System.out.println(solve2(ar, K)); // Should output -1
    }
}
