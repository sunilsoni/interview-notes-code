package com.interview.notes.code.july23.test4;

import java.util.Arrays;

public class ArrayRemoveDuplicatesExample {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 2, 4, 1, 5, 4};

        // Remove duplicates from int array using Java 8 stream
        int[] distinctArray = Arrays.stream(array)
                .distinct()
                .toArray();

        // Print distinct array elements
        System.out.println(Arrays.toString(distinctArray));
    }
}
