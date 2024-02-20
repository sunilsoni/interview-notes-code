package com.interview.notes.code.months.feb24.test3;

public class RotatedArraySearch1 {

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int result = findElement(nums, target);
        
        if(result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found.");
        }
    }

    // A function to find an element in a rotated sorted array
    public static int findElement(int[] arr, int target) {
        // Find the pivot point using binary search
        int pivot = findPivot(arr, 0, arr.length - 1);
        // If the pivot is -1, then the array is not rotated
        if (pivot == -1) {
            // Perform binary search on the whole array
            return binarySearch(arr, 0, arr.length - 1, target);
        }
        // If the target element is equal to the pivot element, return the pivot index
        if (arr[pivot] == target) {
            return pivot;
        }
        // If the target element is greater than or equal to the first element, search in the left subarray of the pivot
        if (arr[0] <= target) {
            return binarySearch(arr, 0, pivot - 1, target);
        }
        // Otherwise, search in the right subarray of the pivot
        return binarySearch(arr, pivot + 1, arr.length - 1, target);
    }

    // A function to find the pivot point in a rotated sorted array
    public static int findPivot(int[] arr, int low, int high) {
        // Base case: if the low index is greater than the high index, the array is not rotated
        if (low > high) {
            return -1;
        }
        // Base case: if the low index is equal to the high index, the low index is the pivot
        if (low == high) {
            return low;
        }
        // Calculate the mid index
        int mid = (low + high) / 2;
        // If the mid index is smaller than the high index and the mid element is larger than the next element, the mid index is the pivot
        if (mid < high && arr[mid] > arr[mid + 1]) {
            return mid;
        }
        // If the mid index is larger than the low index and the mid element is smaller than the previous element, the previous index is the pivot
        if (mid > low && arr[mid] < arr[mid - 1]) {
            return mid - 1;
        }
        // If the first element is larger than the mid element, the pivot is in the left half of the array
        if (arr[low] > arr[mid]) {
            return findPivot(arr, low, mid - 1);
        }
        // Otherwise, the pivot is in the right half of the array
        return findPivot(arr, mid + 1, high);
    }

    // A function to perform binary search on a sorted array
    public static int binarySearch(int[] arr, int low, int high, int target) {
        // Base case: if the low index is greater than the high index, the target element is not found
        if (low > high) {
            return -1;
        }
        // Calculate the mid index
        int mid = (low + high) / 2;
        // If the target element is equal to the mid element, return the mid index
        if (target == arr[mid]) {
            return mid;
        }
        // If the target element is larger than the mid element, search in the right half of the array
        if (target > arr[mid]) {
            return binarySearch(arr, mid + 1, high, target);
        }
        // Otherwise, search in the left half of the array
        return binarySearch(arr, low, mid - 1, target);
    }

}
