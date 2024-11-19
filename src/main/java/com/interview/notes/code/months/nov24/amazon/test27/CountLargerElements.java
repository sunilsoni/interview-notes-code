package com.interview.notes.code.months.nov24.amazon.test27;

import java.util.ArrayList;
import java.util.List;

public class CountLargerElements {

    public static List<Integer> countLarger(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        int[] counts = new int[n];
        int[] indices = new int[n];

        // Initialize the indices array
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Call merge sort
        msort(nums, indices, counts, 0, n - 1);

        // Convert counts array to a result list
        for (int count : counts) {
            result.add(count);
        }
        return result;
    }

    public static void msort(int[] nums, int[] indices, int[] counts, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;

        // Sort left half
        msort(nums, indices, counts, start, mid);

        // Sort right half
        msort(nums, indices, counts, mid + 1, end);

        // Merge and count
        merge(nums, indices, counts, start, mid, end);
    }

    public static void merge(int[] nums, int[] indices, int[] counts, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int[] tempIndices = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;

        while (i <= mid && j <= end) {
            if (nums[indices[j]] < nums[indices[i]]) {
                // Count elements from the right half that are larger
                counts[indices[i]] += (end - j + 1);
                tempIndices[k] = indices[i];
                i++;
            } else {
                tempIndices[k] = indices[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements from the left half
        while (i <= mid) {
            tempIndices[k] = indices[i];
            i++;
            k++;
        }

        // Copy remaining elements from the right half
        while (j <= end) {
            tempIndices[k] = indices[j];
            j++;
            k++;
        }

        // Update indices array
        System.arraycopy(tempIndices, 0, indices, start, tempIndices.length);
    }

    public static void main(String[] args) {
        int[] nums = {9, 2, 3, 1};
        System.out.println(countLarger(nums)); // Output: [0, 1, 0, 0]

        nums = new int[]{1, 2, 3, 4};
        System.out.println(countLarger(nums)); // Output: [3, 2, 1, 0]
    }
}
