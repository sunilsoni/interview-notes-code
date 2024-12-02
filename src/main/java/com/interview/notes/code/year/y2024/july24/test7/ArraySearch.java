package com.interview.notes.code.year.y2024.july24.test7;

public class ArraySearch {
    public static void main(String[] args) {
        int[] a = {1, 3, 4, 6, 9, 10, 12, 14, 15, 17, 19, 21};
        System.out.println(f(a, 12));  // Output should be 12
        System.out.println(f(a, 13));  // Output should be 12
        System.out.println(f(a, 0));   // Output should be -1
    }

    public static int f(int[] a, int x) {
        if (a == null || a.length == 0) return -1;  // Check for null or empty array
        if (x < a[0]) return -1;                    // x is less than the smallest element

        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == x) {
                return a[mid];
            } else if (a[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // If x is not found and x is greater than a[0], `high` will be at the position of the next smallest element
        return a[high];
    }
}
