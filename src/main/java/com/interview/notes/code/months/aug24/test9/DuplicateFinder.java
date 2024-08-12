package com.interview.notes.code.months.aug24.test9;

import java.util.Arrays;

public class DuplicateFinder {
    public static void main(String[] args) {
        int[] array1 = {9, 11, 2, 8, 21, 1};
        int[] array2 = {2, 8, 21, 1, 10, 12};

        int[] duplicates = Arrays.stream(array1)
                .filter(num -> Arrays.stream(array2).anyMatch(x -> x == num))
                .distinct()
                .toArray();

        System.out.println(Arrays.toString(duplicates)); // Output: [2, 8, 21, 1]
    }
}