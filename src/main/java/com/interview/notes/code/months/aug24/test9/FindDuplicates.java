package com.interview.notes.code.months.aug24.test9;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class FindDuplicates {
    public static void main(String[] args) {
        int[] array1 = {9, 11, 2, 8, 21, 1};
        int[] array2 = {2, 8, 21, 1, 10, 12};

        // Convert array1 to a Set for efficient lookup
        Set<Integer> set1 = Arrays.stream(array1).boxed().collect(Collectors.toSet());

        // Find duplicates using streams
        int[] duplicates = Arrays.stream(array2)
                .filter(set1::contains)
                .toArray();

        // Print the result
        System.out.println("Duplicates: " + Arrays.toString(duplicates));
    }
}
