package com.interview.notes.code.year.y2024.nov24.amazon.test26;

public class CountLargerElements {
    private static int[] result;

    public static int[] countLarger(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        result = new int[nums.length];
        int[] indices = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indices[i] = i;
        }

        msort(nums, indices, 0, nums.length - 1);
        return result;
    }

    public static void msort(int[] nums, int[] indices, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            msort(nums, indices, start, mid);
            msort(nums, indices, mid + 1, end);
            merge(nums, indices, start, mid, end);
        }
    }

    public static void merge(int[] nums, int[] indices, int start, int mid, int end) {
        int[] leftIndices = new int[mid - start + 1];
        int[] rightIndices = new int[end - mid];

        // Copy indices to temp arrays
        System.arraycopy(indices, start + 0, leftIndices, 0, leftIndices.length);
        for (int i = 0; i < rightIndices.length; i++) {
            rightIndices[i] = indices[mid + 1 + i];
        }

        int i = 0, j = 0, k = start;
        int rightCount = 0;

        while (i < leftIndices.length && j < rightIndices.length) {
            if (nums[leftIndices[i]] <= nums[rightIndices[j]]) {
                result[leftIndices[i]] += rightCount;
                indices[k++] = leftIndices[i++];
            } else {
                rightCount++;
                indices[k++] = rightIndices[j++];
            }
        }

        while (i < leftIndices.length) {
            result[leftIndices[i]] += rightCount;
            indices[k++] = leftIndices[i++];
        }

        while (j < rightIndices.length) {
            indices[k++] = rightIndices[j++];
        }
    }

    public static void main(String[] args) {
        // Test cases
        test(new int[]{9, 2, 3, 1}, new int[]{0, 1, 0, 0});
        test(new int[]{5, 2, 6, 1}, new int[]{1, 1, 0, 0});
        test(new int[]{1, 2, 3, 4}, new int[]{3, 2, 1, 0});
        test(new int[]{4, 3, 2, 1}, new int[]{0, 0, 0, 0});

        // Performance test
        int[] largeArray = new int[100000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i;
        }
        long start = System.currentTimeMillis();
        countLarger(largeArray);
        long end = System.currentTimeMillis();
        System.out.println("Time for 100,000 elements: " + (end - start) + "ms");
    }

    private static void test(int[] input, int[] expected) {
        int[] result = countLarger(input);
        System.out.println("Input: " + arrayToString(input));
        System.out.println("Expected: " + arrayToString(expected));
        System.out.println("Got: " + arrayToString(result));
        System.out.println("Test: " + (arrayEquals(result, expected) ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(",");
        }
        return sb.append("]").toString();
    }

    private static boolean arrayEquals(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }
}
