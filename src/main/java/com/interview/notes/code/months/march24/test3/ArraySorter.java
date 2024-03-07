package com.interview.notes.code.months.march24.test3;

import java.util.Arrays;

public class ArraySorter {
    public static void main(String[] args) {
        String[] words = {"a", "b", "chase", "c", "chase", "d"};
        String targetWord = "chase"; // The word to move to the end and sort the rest

        sortArrayExcludingTarget(words, targetWord);
        System.out.println(Arrays.toString(words));
    }

    private static void sortArrayExcludingTarget(String[] array, String targetWord) {
        int count = 0; // Count of elements that are not the targetWord
        // Move targetWord to the end
        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals(targetWord)) {
                String temp = array[count];
                array[count] = array[i];
                array[i] = temp;
                count++;
            }
        }

        // Sort the array excluding the targetWord elements
        Arrays.sort(array, 0, count);
    }
}
