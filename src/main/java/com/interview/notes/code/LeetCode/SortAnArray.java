package com.interview.notes.code.LeetCode;

//Given an array of integers nums, sort the array in ascending order.
//https://leetcode.com/problems/sort-an-array/discuss/?currentPage=1&orderBy=hot&query=&tag=java
public class SortAnArray {

    public static void quickSort(int[] arr, int startIdx, int endIdx) {

        if (startIdx < endIdx) {
            int pivot = endIdx;
            int lessIndex = startIdx - 1; // to keep track of the index i.e. one less than the correct index of pivot

            for (int i = startIdx; i <= endIdx; i++) {
                if (arr[i] < arr[pivot]) {
                    lessIndex++;
                    swap(lessIndex, i, arr); // swap is to make sure that when the pivot is at its correct position, the elements before it are smaller and elements after it are greater.
                }
            }

            lessIndex++; // before swapping the pivot with lessIndex we first increment the lessIndex value by one.
            swap(lessIndex, pivot, arr);
            quickSort(arr, startIdx, lessIndex - 1);
            quickSort(arr, lessIndex + 1, endIdx);
        }
    }

    public static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /* https://leetcode.com/problems/sort-an-array/discuss/1648477/Java-or-QuickSort-or-faster-than-5.07-%3A(-or-brief-explanation
         - In this version of the quicksort algorithm we pick the last element as a pivot
         - We put the pivot at its correct position in a sorted array and put all smaller elements (smaller than pivot) before the pivot, and put all greater elements (greater than pivot) after the pivot.
         - Then we repeat the process for subarrays created by the pivot as the pivot is in its correct position.
         - Here the lessIndex is supposed to keep track of the index i.e. one less than the correct index of the pivot.
         - Therefore before swapping the pivot with lessIndex we first increment the lessIndex value by one.
         - While traversing, if we find an element smaller than the pivot, we increment lessIndex(to keep track of the index i.e. one less than the correct index of pivot) and swap the current element with arr[lessIndex]. Otherwise, we ignore the current element.
         - The reason why we do this swap is to make sure that when the pivot is at its correct position, the elements before it are smaller and elements after it are greater.
     */
    public int[] quickSortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    //https://leetcode.com/problems/sort-an-array/discuss/1631112/JAVA-or-MERGE-SORT
    public int[] mergeSortArray(int[] nums) {
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    public void merge(int[] arr, int start, int mid, int end) {
        int lSize = mid - start + 1;
        int rSize = end - mid;
        int[] left = new int[lSize];
        int[] right = new int[rSize];
        for (int i = 0; i < lSize; i++)
            left[i] = arr[start + i];
        for (int i = 0; i < rSize; i++)
            right[i] = arr[mid + 1 + i];
        int k = start;
        int i = 0;
        int j = 0;
        while (i < lSize && j < rSize) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < lSize) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < rSize) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }


    public void sort(int[] arr, int start, int end) {
        if (start == end)
            return;
        int mid = (start + end) / 2;
        sort(arr, start, mid);
        sort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

}
