package com.interview.notes.code.misc.test3;


//https://mrleonhuang.gitbooks.io/lintcode/content/binary-search/closest-number-in-sorted-array.html
public class Solution {

    public static void main(String[] args) {
        int[] arr = {2, 5, 6, 7, 8, 8, 9};
        int target = 5;

        int res = closestNumber(arr, target);
        System.out.println(res);
    }

    /**
     * @param A      an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public static int closestNumber(int[] A, int target) {
        // Write your code here
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        int left = Math.abs(A[start] - target);
        int right = Math.abs(A[end] - target);
        if (left <= right) {
            return start;
        }
        return end;
    }
}