package com.interview.notes.code.year.y2025.august.common.test15;

import java.util.stream.IntStream;

public class PairSum {
    public static void main(String[] args) {
        // Input array
        int[] arr = {6, 5, 4, 2, 1, 3};
        int target = 7; // required sum

        System.out.println("Pairs with sum = " + target + " are:");

        // Using IntStream for clean iteration
        IntStream.range(0, arr.length) // loop over first index i
                .forEach(i ->
                        IntStream.range(i + 1, arr.length) // loop over second index j
                                .filter(j -> arr[i] + arr[j] == target) // check sum condition
                                .forEach(j ->
                                        System.out.println(arr[i] + " + " + arr[j] + " = " + target)
                                )
                );
    }
}