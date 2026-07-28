package com.interview.notes.code.year.y2026.july.common.test3;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] input = {5, 8, 10, 12, 15}; // Input array.

        int rotateBy = 2; // Number of positions to rotate left.

        int[] output = new int[input.length]; // Stores rotated values.

        for (int i = 0; i < input.length; i++) {

            int newIndex = (i + input.length - rotateBy) % input.length;

            output[newIndex] = input[i];
        }

        System.out.println(Arrays.toString(output));
    }
}