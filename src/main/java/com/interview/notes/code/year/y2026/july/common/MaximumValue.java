package com.interview.notes.code.year.y2026.july.common;

import java.util.Arrays;

public class MaximumValue {
    public static void main(String[] args) {
        int[] numbers = {11, 32, 54, 65, 9, 16};

        int max = Arrays.stream(numbers)
                .max()
                .orElseThrow();

        System.out.println(max);
    }
}