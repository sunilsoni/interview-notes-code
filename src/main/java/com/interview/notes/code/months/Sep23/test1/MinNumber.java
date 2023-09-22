package com.interview.notes.code.months.Sep23.test1;

import java.util.Arrays;
import java.util.List;

public class MinNumber {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 5, 3, 7, 4, 2, 9, 6, 8);

        // Find the minimum number in the list
        Integer minNumber = numbers.stream()
                .min(Integer::compareTo)
                .orElse(null);

        // Print the minimum number
        System.out.println("The minimum number in the list is: " + minNumber);
    }
}
