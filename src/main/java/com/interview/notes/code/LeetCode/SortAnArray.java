package com.interview.notes.code.LeetCode;

//Given an array of integers nums, sort the array in ascending order.
//https://leetcode.com/problems/sort-an-array/discuss/?currentPage=1&orderBy=hot&query=&tag=java
public class SortAnArray {


    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length -1);
        return nums;
    }

    public static void quickSort(int[] arr, int startIdx, int endIdx){

        if(startIdx<endIdx){
            int pivot = endIdx;
            int lessIndex = startIdx-1; // to keep track of the index i.e. one less than the correct index of pivot

            for(int i = startIdx; i<= endIdx; i++){
                if(arr[i] < arr[pivot]){
                    lessIndex++;
                    swap(lessIndex, i,arr); // swap is to make sure that when the pivot is at its correct position, the elements before it are smaller and elements after it are greater.
                }
            }

            lessIndex++; // before swapping the pivot with lessIndex we first increment the lessIndex value by one.
            swap(lessIndex, pivot, arr);
            quickSort(arr, startIdx, lessIndex-1);
            quickSort(arr, lessIndex+1,endIdx);
        }
    }

    public static void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
