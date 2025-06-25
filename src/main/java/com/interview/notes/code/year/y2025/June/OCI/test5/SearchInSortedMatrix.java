package com.interview.notes.code.year.y2025.June.OCI.test5;

public class SearchInSortedMatrix {

    /**
     * Custom binary search on a sorted array.
     * @param arr     the sorted 1D array to search
     * @param target  the value we’re looking for
     * @return        true if found, false otherwise
     */
    private static boolean binarySearch(int[] arr, int target) {
        int left = 0;                   // start index of current search window
        int right = arr.length - 1;    // end index of current search window

        // keep going while we haven’t crossed pointers
        while (left <= right) {
            // pick the middle index (avoid overflow)
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return true;            // found the target exactly
            }
            if (arr[mid] < target) {
                // target is larger, so drop the left half
                left = mid + 1;
            } else {
                // target is smaller, so drop the right half
                right = mid - 1;
            }
        }

        return false;                   // window is empty → not found
    }

    /**
     * Searches each row with our custom binarySearch.
     * @param matrix  the 2D sorted matrix
     * @param target  the value to find
     * @return        true if target exists anywhere, false otherwise
     */
    public static boolean searchRowBinaryCustom(int[][] matrix, int target) {
        if (matrix == null) {
            return false;              // nothing to search
        }

        // try each row independently
        for (int[] row : matrix) {
            if (row == null || row.length == 0) {
                continue;              // skip empty rows
            }
            // if binarySearch says “found”, we’re done
            if (binarySearch(row, target)) {
                return true;
            }
        }

        return false;                  // checked all rows → not found
    }

    /** Simple main to demonstrate it runs. */
    public static void main(String[] args) {
        int[][] mat = {
            {1, 2, 3},
            {3, 4, 5},
            {7, 8, 9}
        };

        System.out.println(searchRowBinaryCustom(mat, 5));   // true
        System.out.println(searchRowBinaryCustom(mat, 10));  // false
    }
}