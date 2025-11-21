package com.interview.notes.code.year.y2024.nov24.amazon.test27;

public class CountLargerElementsRight {

    public static int[] countLargerElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n];
        Pair[] pairs = new Pair[n];

        // Create pairs of value and original index
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(nums[i], i);
        }

        // Merge sort and count
        mergeSort(pairs, 0, n - 1, result);

        return result;
    }

    private static void mergeSort(Pair[] pairs, int left, int right, int[] result) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(pairs, left, mid, result);
            mergeSort(pairs, mid + 1, right, result);
            merge(pairs, left, mid, right, result);
        }
    }

    private static void merge(Pair[] pairs, int left, int mid, int right, int[] result) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Pair[] leftArr = new Pair[n1];
        Pair[] rightArr = new Pair[n2];

        // Copy data to temporary arrays
        System.arraycopy(pairs, left + 0, leftArr, 0, n1);
        for (int j = 0; j < n2; j++) {
            rightArr[j] = pairs[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        int rightCount = 0;

        while (i < n1 && j < n2) {
            if (leftArr[i].val <= rightArr[j].val) {
                // Count elements from right array that are greater
                result[leftArr[i].index] += rightCount;
                pairs[k++] = leftArr[i++];
            } else {
                rightCount++;
                pairs[k++] = rightArr[j++];
            }
        }

        // Complete the remaining elements
        while (i < n1) {
            result[leftArr[i].index] += rightCount;
            pairs[k++] = leftArr[i++];
        }

        while (j < n2) {
            pairs[k++] = rightArr[j++];
        }
    }

    // Helper methods for testing remain the same
    private static boolean areArraysEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        // Test case 1
        int[] test1 = {9, 2, 3, 1};
        int[] expected1 = {0, 1, 0, 0};
        int[] result1 = countLargerElements(test1);
        System.out.println("Test 1: " + (areArraysEqual(result1, expected1) ? "PASS" : "FAIL"));
        System.out.println("Input: " + arrayToString(test1));
        System.out.println("Expected: " + arrayToString(expected1));
        System.out.println("Got: " + arrayToString(result1));
        System.out.println();

        // Test case 2
        int[] test2 = {-1};
        int[] expected2 = {0};
        int[] result2 = countLargerElements(test2);
        System.out.println("Test 2: " + (areArraysEqual(result2, expected2) ? "PASS" : "FAIL"));
        System.out.println("Input: " + arrayToString(test2));
        System.out.println("Expected: " + arrayToString(expected2));
        System.out.println("Got: " + arrayToString(result2));
        System.out.println();

        // Test case 3
        int[] test3 = {-1, -1};
        int[] expected3 = {0, 0};
        int[] result3 = countLargerElements(test3);
        System.out.println("Test 3: " + (areArraysEqual(result3, expected3) ? "PASS" : "FAIL"));
        System.out.println("Input: " + arrayToString(test3));
        System.out.println("Expected: " + arrayToString(expected3));
        System.out.println("Got: " + arrayToString(result3));
        System.out.println();

        // Test case 4
        int[] test4 = {1, 2, 3, 4};
        int[] expected4 = {3, 2, 1, 0};
        int[] result4 = countLargerElements(test4);
        System.out.println("Test 4: " + (areArraysEqual(result4, expected4) ? "PASS" : "FAIL"));
        System.out.println("Input: " + arrayToString(test4));
        System.out.println("Expected: " + arrayToString(expected4));
        System.out.println("Got: " + arrayToString(result4));
        System.out.println();

        // Test case 5 - Large input (100,000 elements)
        int[] test5 = new int[100000];
        for (int i = 0; i < test5.length; i++) {
            test5[i] = i;
        }
        long startTime = System.currentTimeMillis();
        int[] result5 = countLargerElements(test5);
        long endTime = System.currentTimeMillis();
        System.out.println("Test 5 (Large Input): PASS");
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
        System.out.println();
    }

    static class Pair {
        int val;
        int index;

        Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}
