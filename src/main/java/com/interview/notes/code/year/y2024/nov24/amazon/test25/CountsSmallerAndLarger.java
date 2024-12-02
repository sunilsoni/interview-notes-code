package com.interview.notes.code.year.y2024.nov24.amazon.test25;

import java.util.Arrays;

public class CountsSmallerAndLarger {
    public static void main(String[] args) {
        // Define test cases
        TestCase[] testCases = new TestCase[]{
                new TestCase(new int[]{9, 2, 3, 1}, new int[]{3, 1, 1, 0}, new int[]{0, 2, 1, 0}),
                new TestCase(new int[]{-1}, new int[]{0}, new int[]{0}),
                new TestCase(new int[]{-1, -1}, new int[]{0, 0}, new int[]{0, 0}),
                new TestCase(new int[]{1, 2, 3, 4}, new int[]{0, 0, 0, 0}, new int[]{3, 2, 1, 0}),
                new TestCase(new int[]{4, 3, 2, 1}, new int[]{3, 2, 1, 0}, new int[]{0, 0, 0, 0}),
                // Additional test cases
                new TestCase(new int[]{}, new int[]{}, new int[]{}),
                new TestCase(new int[]{5, 5, 5, 5}, new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0}),
                new TestCase(generateLargeArray(100000), generateCountsSmallerLargeArray(100000, true), generateCountsSmallerLargeArray(100000, false))
        };

        // Process each test case
        for (int i = 0; i < testCases.length; i++) {
            TestCase tc = testCases[i];
            int[] resultSmaller = countSmaller(tc.nums);
            int[] resultLarger = countLarger(tc.nums);
            boolean passSmaller = Arrays.equals(resultSmaller, tc.expectedSmaller);
            boolean passLarger = Arrays.equals(resultLarger, tc.expectedLarger);
            System.out.println("Test Case " + (i + 1) + ": " + (passSmaller && passLarger ? "PASS" : "FAIL"));
            if (!passSmaller || !passLarger) {
                System.out.println("  Expected countsSmaller: " + Arrays.toString(tc.expectedSmaller));
                System.out.println("  Actual countsSmaller:   " + Arrays.toString(resultSmaller));
                System.out.println("  Expected countsLarger:  " + Arrays.toString(tc.expectedLarger));
                System.out.println("  Actual countsLarger:    " + Arrays.toString(resultLarger));
            }
        }
    }

    // Function to count smaller elements to the right
    public static int[] countSmaller(int[] nums) {
        int n = nums.length;
        int[] countsSmaller = new int[n];
        if (n == 0) return countsSmaller;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(nums[i], i);
        }
        mergeSortSmaller(pairs, countsSmaller, 0, n - 1);
        return countsSmaller;
    }

    // Function to count larger elements to the right
    public static int[] countLarger(int[] nums) {
        int n = nums.length;
        int[] countsLarger = new int[n];
        if (n == 0) return countsLarger;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(nums[i], i);
        }
        mergeSortLarger(pairs, countsLarger, 0, n - 1);
        return countsLarger;
    }

    // Merge Sort for counting smaller elements
    private static void mergeSortSmaller(Pair[] pairs, int[] counts, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortSmaller(pairs, counts, left, mid);
        mergeSortSmaller(pairs, counts, mid + 1, right);
        mergeSmaller(pairs, counts, left, mid, right);
    }

    private static void mergeSmaller(Pair[] pairs, int[] counts, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Pair[] leftArr = new Pair[n1];
        Pair[] rightArr = new Pair[n2];
        System.arraycopy(pairs, left, leftArr, 0, n1);
        System.arraycopy(pairs, mid + 1, rightArr, 0, n2);
        int i = 0, j = 0, k = left;
        int rightCount = 0;
        while (i < n1 && j < n2) {
            if (leftArr[i].value > rightArr[j].value) {
                rightCount++;
                pairs[k++] = rightArr[j++];
            } else {
                counts[leftArr[i].index] += rightCount;
                pairs[k++] = leftArr[i++];
            }
        }
        while (i < n1) {
            counts[leftArr[i].index] += rightCount;
            pairs[k++] = leftArr[i++];
        }
        while (j < n2) {
            pairs[k++] = rightArr[j++];
        }
    }

    // Merge Sort for counting larger elements
    private static void mergeSortLarger(Pair[] pairs, int[] counts, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortLarger(pairs, counts, left, mid);
        mergeSortLarger(pairs, counts, mid + 1, right);
        mergeLarger(pairs, counts, left, mid, right);
    }

    private static void mergeLarger(Pair[] pairs, int[] counts, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Pair[] leftArr = new Pair[n1];
        Pair[] rightArr = new Pair[n2];
        System.arraycopy(pairs, left, leftArr, 0, n1);
        System.arraycopy(pairs, mid + 1, rightArr, 0, n2);
        int i = 0, j = 0, k = left;
        int rightCount = 0;
        while (i < n1 && j < n2) {
            if (leftArr[i].value < rightArr[j].value) {
                rightCount++;
                pairs[k++] = rightArr[j++];
            } else {
                counts[leftArr[i].index] += rightCount;
                pairs[k++] = leftArr[i++];
            }
        }
        while (i < n1) {
            counts[leftArr[i].index] += rightCount;
            pairs[k++] = leftArr[i++];
        }
        while (j < n2) {
            pairs[k++] = rightArr[j++];
        }
    }

    // Function to generate a large array for testing
    private static int[] generateLargeArray(int size) {
        int[] largeArray = new int[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = size - i; // Descending order
        }
        return largeArray;
    }

    // Function to generate expected counts for large array
    private static int[] generateCountsSmallerLargeArray(int size, boolean isSmaller) {
        int[] counts = new int[size];
        if (isSmaller) {
            // Since the array is in descending order, each element has (size - i -1) smaller elements to the right
            for (int i = 0; i < size; i++) {
                counts[i] = size - i - 1;
            }
        } else {
            // In descending order, no larger elements to the right
            Arrays.fill(counts, 0);
        }
        return counts;
    }

    // Helper class to hold value and original index
    static class Pair {
        int value;
        int index;

        Pair(int v, int i) {
            value = v;
            index = i;
        }
    }

    // Helper class for test cases
    static class TestCase {
        int[] nums;
        int[] expectedSmaller;
        int[] expectedLarger;

        TestCase(int[] n, int[] es, int[] el) {
            nums = n;
            expectedSmaller = es;
            expectedLarger = el;
        }
    }
}
