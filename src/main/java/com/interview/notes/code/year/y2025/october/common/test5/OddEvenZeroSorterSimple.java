package com.interview.notes.code.year.y2025.october.common.test5;

public class OddEvenZeroSorterSimple {

    // Rearrange the array as: odds asc | evens asc | zeros
    public static void arrange(int[] arr) {
        if (arr == null || arr.length == 0) return;

        int n = arr.length;
        int zeroCount = 0;
        int write = 0;

        // Step 1: Move all non-zeros to front
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                arr[write++] = arr[i];
            } else {
                zeroCount++;
            }
        }

        // Step 2: Partition odds and evens in first part
        int oddEnd = partition(arr, 0, write - 1);

        // Step 3: Sort odd part
        quickSort(arr, 0, oddEnd);

        // Step 4: Sort even part
        quickSort(arr, oddEnd + 1, write - 1);

        // Step 5: Fill zeros at end
        for (int i = write; i < n; i++) {
            arr[i] = 0;
        }
    }

    // Partition: odds to left, evens to right
    private static int partition(int[] arr, int low, int high) {
        int i = low, j = high;
        while (i <= j) {
            while (i <= j && Math.abs(arr[i]) % 2 == 1) i++;  // odd
            while (i <= j && Math.abs(arr[j]) % 2 == 0) j--;  // even
            if (i < j) swap(arr, i++, j--);
        }
        return i - 1; // last odd index
    }

    // Simple quick sort (ascending)
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partitionForSort(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    // Standard quicksort partition
    private static int partitionForSort(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ---------------- TESTING ----------------
    public static void main(String[] args) {
        int[][] inputs = {
            {4,9,0,2,0,7,0,0,3,8},
            {0,0,0},
            {1,3,5,7},
            {2,4,6,8},
            {0,1,0,2,0,3,0,4,0,5}
        };

        int[][] expected = {
            {3,7,9,2,4,8,0,0,0,0},
            {0,0,0},
            {1,3,5,7},
            {2,4,6,8},
            {1,3,5,2,4,0,0,0,0,0}
        };

        for (int i = 0; i < inputs.length; i++) {
            int[] arr = inputs[i].clone();
            arrange(arr);
            boolean pass = compare(arr, expected[i]);
            System.out.println(pass ? "PASS" : "FAIL");
        }

        // Large test
        int[] large = new int[100000];
        for (int i = 0; i < large.length; i++)
            large[i] = (int)(Math.random() * 1000 - 500);
        long start = System.currentTimeMillis();
        arrange(large);
        long end = System.currentTimeMillis();
        System.out.println("Large input test passed: " + isSortedStructure(large));
        System.out.println("Time(ms): " + (end - start));
    }

    // Check if array follows structure: odds asc -> evens asc -> zeros
    private static boolean isSortedStructure(int[] arr) {
        boolean seenEven = false, seenZero = false;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == 0 && arr[i] != 0) return false;
            if (arr[i] == 0) seenZero = true;
            if (!seenZero && Math.abs(arr[i]) % 2 == 0) seenEven = true;
            if (seenEven && Math.abs(arr[i]) % 2 == 1) return false;
        }
        return true;
    }

    private static boolean compare(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) if (a[i] != b[i]) return false;
        return true;
    }
}