package com.interview.notes.code.year.y2026.september.infosys.test3;

import java.util.Arrays;

public class Main {
    static void main(String[] args) {
        int[] a = {1, 2, 2, 3, 1, 4};

        int[] result = Arrays.stream(a)
                .distinct()
                .toArray();

        System.out.println(Arrays.toString(result));
    }
}