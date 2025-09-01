package com.interview.notes.code.year.y2025.august.common.test17;

import java.util.Arrays;

public class SortArray {
    public static void main(String[] args) {
        int[] n = {33, 61, 59, 25, 16, 91};

        // Sorting using Streams
        int[] sorted = Arrays.stream(n).sorted().toArray();

        System.out.println("Original: " + Arrays.toString(n));
        System.out.println("Sorted:   " + Arrays.toString(sorted));
    }
}